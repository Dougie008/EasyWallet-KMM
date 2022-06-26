object Versions {
    const val koin = "3.2.0"
    const val ktor = "2.0.2"
    const val sqldelight = "1.5.3"
}

object Deps {
    const val Settings = "com.russhwolf:multiplatform-settings:0.9"
    const val Serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }
    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

        const val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val darwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    }
    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqldelight}"
        const val android = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
        const val native = "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
        const val coroutines = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqldelight}"
    }
}