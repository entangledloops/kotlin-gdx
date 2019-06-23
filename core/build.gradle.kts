plugins {
    id("org.jetbrains.kotlin.jvm")
}

val jdkVersion: String by project
val gdxVersion: String by project
val ktxVersion: String by project
val kotlinVersion: String by project

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk$jdkVersion")

    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-freetype:$gdxVersion")

    // use "api" so that other projects depending on :core can understand classes derived from ktx
    api("io.github.libktx:ktx-actors:$ktxVersion")
    api("io.github.libktx:ktx-app:$ktxVersion")
    api("io.github.libktx:ktx-assets:$ktxVersion")
    api("io.github.libktx:ktx-async:$ktxVersion")
    api("io.github.libktx:ktx-collections:$ktxVersion")
    api("io.github.libktx:ktx-freetype:$ktxVersion")
    //api("io.github.libktx:ktx-freetype-async:$ktxVersion") // enable once coroutines are finished
    api("io.github.libktx:ktx-graphics:$ktxVersion")
    api("io.github.libktx:ktx-i18n:$ktxVersion")
    api("io.github.libktx:ktx-inject:$ktxVersion")
    api("io.github.libktx:ktx-log:$ktxVersion")
    api("io.github.libktx:ktx-math:$ktxVersion")
    api("io.github.libktx:ktx-scene2d:$ktxVersion")
    api("io.github.libktx:ktx-style:$ktxVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
