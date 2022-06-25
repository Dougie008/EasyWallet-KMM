object Versions {
    const val koin = "3.2.0"
    const val ktor = "2.0.2"
}

object Deps {
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }
    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

        const val darwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
    }
}