plugins {
    id("org.jetbrains.kotlin.jvm")
    application
}

val jdkVersion: String by project
val gdxVersion: String by project
val kotlinVersion: String by project

dependencies {
    implementation(project(":core"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk$jdkVersion")

    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
    implementation("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-desktop")

    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

val packageName: String by project
val versionName: String by project

application {
    mainClassName = "$packageName.DesktopLauncher"
}

tasks.register<Jar>("dist") {
    archiveClassifier.set("dist")

    manifest {
        attributes(
                "Main-Class" to application.mainClassName,
                "Implementation-Title" to packageName,
                "Implementation-Version" to versionName
        )
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}