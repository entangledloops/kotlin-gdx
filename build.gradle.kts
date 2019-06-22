buildscript {
    val kotlinVersion: String by project
    val androidGradleVersion: String by project
    val robovmVersion: String by project

    repositories {
        mavenLocal()
        google()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:$androidGradleVersion")
        classpath("com.mobidevelop.robovm:robovm-gradle-plugin:$robovmVersion")
    }
}

val packageName: String by project
val versionName: String by project

allprojects {
    group = packageName
    version = versionName
}

subprojects {
    repositories {
        mavenLocal()
        jcenter()
        google()
    }
}
