import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.serialization") version "1.5.0"
    id("org.jetbrains.dokka") version "1.5.0"
}

group = "com.github.warriorzz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://schlaubi.jfrog.io/artifactory/envconf/")
}

dependencies {
    implementation(platform("io.ktor:ktor-bom:1.6.1"))
    implementation("io.ktor", "ktor-client-okhttp")
    implementation("io.ktor", "ktor-client-serialization")

    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.5.0")
    implementation("io.github.microutils", "kotlin-logging-jvm", "2.0.6")
    implementation("org.slf4j", "slf4j-simple", "1.7.31")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
    dokkaHtml {
        moduleName.set("ktify")
        dokkaSourceSets {
            configureEach {
                jdkVersion.set(11)
                platform.set(org.jetbrains.dokka.Platform.jvm)

                sourceLink {
                    localDirectory.set(file("src/main/kotlin"))
                    remoteUrl.set(uri("https://github.com/warriorzz/ktify/tree/main/src/main/kotlin").toURL())
                    remoteLineSuffix.set("#L")
                }
            }
        }
    }
}
