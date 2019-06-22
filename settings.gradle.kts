rootProject.name = "mytest"

include("core", "desktop", "android", "ios")

val kotlinVersion: String by settings
val androidGradleVersion: String by settings
val robovmVersion: String by settings

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "kotlin" -> useVersion(kotlinVersion)
                "kotlin-android" -> useVersion(kotlinVersion)
                "com.android.application" -> useVersion(androidGradleVersion)
                "robovm" -> useVersion(robovmVersion)
            }
        }
    }
}