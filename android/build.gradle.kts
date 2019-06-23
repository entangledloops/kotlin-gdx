import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
}

val natives: Configuration by configurations.creating
val jdkVersion: String by project
val gdxVersion: String by project
val ktxVersion: String by project
val kotlinVersion: String by project

dependencies {
    implementation(project(":core"))

    implementation("com.badlogicgames.gdx:gdx-backend-android:$gdxVersion")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-armeabi")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-armeabi-v7a")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-arm64-v8a")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-x86")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-x86_64")

    implementation("com.badlogicgames.gdx:gdx-freetype:$gdxVersion")
    natives("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-armeabi")
    natives("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-armeabi-v7a")
    natives("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-arm64-v8a")
    natives("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-x86")
    natives("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-x86_64")
}

val packageName: String by project
val versionName: String by project
val androidBuildToolsVersion: String by project
val androidTargetSdkVersion: String by project
val androidMinSdkVersion: String by project
val androidVersionName: String = versionName

android {
    buildToolsVersion(androidBuildToolsVersion)
    compileSdkVersion("android-$androidTargetSdkVersion")

    defaultConfig {
        applicationId = packageName
        minSdkVersion(androidMinSdkVersion)
        targetSdkVersion(androidTargetSdkVersion)
        versionCode = androidVersionName.toInt()
        versionName = androidVersionName
    }

    sourceSets {
        getByName("main") {
            aidl.srcDirs("src/main/kotlin")
            assets.srcDirs("../core/src/main/resources")
            java.srcDirs("src/main/kotlin")
            jniLibs.srcDirs("libs")
            manifest.srcFile("src/main/AndroidManifest.xml")
            renderscript.srcDirs("src/main/kotlin")
            res.srcDirs("src/main/res")
        }

        getByName("test") {
            java.srcDirs("src/test/kotlin")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "src/main/proguard-rules.pro")
        }
    }
}

// called every time gradle gets executed, takes the native dependencies of
// the natives configuration, and extracts them to the proper libs/ folders
// so they get packed with the APK.
tasks.register("copyAndroidNatives") {
    doFirst {
        file("libs/armeabi").mkdirs()
        file("libs/armeabi-v7a").mkdirs()
        file("libs/arm64-v8a").mkdirs()
        file("libs/x86_64").mkdirs()
        file("libs/x86").mkdirs()

        configurations["natives"].files.forEach { jar ->
            var outputDir: File? = null
            if (jar.name.endsWith("natives-arm64-v8a.jar")) outputDir = file("libs/arm64-v8a")
            if (jar.name.endsWith("natives-armeabi-v7a.jar")) outputDir = file("libs/armeabi-v7a")
            if (jar.name.endsWith("natives-armeabi.jar")) outputDir = file("libs/armeabi")
            if (jar.name.endsWith("natives-x86_64.jar")) outputDir = file("libs/x86_64")
            if (jar.name.endsWith("natives-x86.jar")) outputDir = file("libs/x86")
            if (outputDir != null) {
                copy {
                    from(zipTree(jar))
                    into(outputDir)
                    include("*.so")
                }
            }
        }
    }
}

tasks.whenTaskAdded {
    if (this.name.contains("package")) {
        this.dependsOn("copyAndroidNatives")
    }
}

tasks.register<Exec>("run") {
    val localProperties: File = project.file("../local.properties")
    val path: String = if (localProperties.exists()) {
        val properties = Properties().apply { load(localProperties.inputStream()) }
        val sdkDir: Any? = properties["sdk.dir"]
        if (sdkDir != null) {
            "$sdkDir"
        } else {
            System.getenv("ANDROID_HOME")
        }
    } else {
        System.getenv("ANDROID_HOME")
    }

    val adb = "$path/platform-tools/adb"
    commandLine(adb, "shell", "am", "start", "-n", "$packageName/$packageName.AndroidLauncher")
}
