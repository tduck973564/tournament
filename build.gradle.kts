import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "me.thoma"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls", "javafx.graphics")
}

dependencies {
    implementation("org.apache.commons:commons-csv:1.9.0")
    implementation("io.insert-koin:koin-core:3.1.5")
    testImplementation("io.insert-koin:koin-test:3.1.5")
    implementation("junit:junit:4.13.1")
    implementation("no.tornado:tornadofx:1.7.20")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}