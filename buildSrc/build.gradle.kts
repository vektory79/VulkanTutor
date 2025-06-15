import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {
        register("vulkan-kotlin-wrapper-generator") {
            id = "vulkan-kotlin-wrapper-generator"
            implementationClass = "me.vektory79.vulkan.wrapper"
        }
    }
}

repositories {
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    languageVersion.set(KotlinVersion.KOTLIN_1_8)
}

dependencies {
    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.26.1")
    implementation("com.github.javaparser:javaparser-core:3.26.1")
}
