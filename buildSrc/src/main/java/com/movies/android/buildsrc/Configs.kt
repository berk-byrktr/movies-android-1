object Configs {
    val applicationId = "com.movies.android"
    val compileSdkVersion = 29
    val minSdkVersion = 21
    val targetSdkVersion = 29
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val flavorDimension = "default"
    val versionCode = calculateVersionCode()
    val versionName = calculateVersionName()
    private val versionMajor = 1
    private val versionMinor = 0
    private val versionPatch = 0
    private val versionBuild = 0
    val vectorDrawables = true

    private fun calculateVersionCode(): Int =
        versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild

    private fun calculateVersionName(): String =
        "${versionMajor}.${versionMinor}.${versionPatch}.${versionBuild}"
}