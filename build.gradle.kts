plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
}

version = "0.1"
group = "hello.world"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("hello.world.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.graphql:micronaut-graphql")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    implementation("com.expediagroup:graphql-kotlin-schema-generator:4.1.1")
    implementation("io.github.classgraph:classgraph:4.8.108")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}

application {
    mainClass.set("hello.world.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("1.8")
}

tasks {

    nativeImage {
        args("--initialize-at-build-time=hello.world.NativeImageInit,io.github.classgraph.,nonapi.io.github.classgraph.")
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}
