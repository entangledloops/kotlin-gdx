plugins {
    id("kotlin")
    id("robovm")
}

val kotlinVersion: String by project
val robovmVersion: String by project
val gdxVersion: String by project
val packageName: String by project

dependencies {
    implementation(project(":core"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("com.mobidevelop.robovm:robovm-rt:$robovmVersion")
    implementation("com.mobidevelop.robovm:robovm-cocoatouch:$robovmVersion")
    implementation("com.badlogicgames.gdx:gdx-backend-robovm:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-ios")
    implementation("com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-ios")
    implementation("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-ios")
}

ext["mainClassName"] = "$packageName.IOSLauncher"

tasks["launchIPhoneSimulator"].dependsOn("build")
tasks["launchIPadSimulator"].dependsOn("build")
tasks["launchIOSDevice"].dependsOn("build")
tasks["createIPA"].dependsOn("build")

robovm {
    archs = "thumbv7:arm64"
}
