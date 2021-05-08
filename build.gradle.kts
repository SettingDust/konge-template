import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.PluginDependency

plugins {
    kotlin("jvm") version "1.5.0"
    id("org.spongepowered.gradle.plugin") version "1.1.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

val spongeVersion: String by project
val pluginId: String by project
val pluginName: String by project
val pluginVersion: String by project

group = "org.example"
version = pluginVersion

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

sponge {
    apiVersion(spongeVersion)
    plugin(pluginId) {
        loader(PluginLoaders.JAVA_PLAIN)
        displayName(pluginName)
        mainClass("org.example.Example")
        description("Kotlin plugin for sponge")
        contributor("SettingDust") {
            description("Owner")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
        dependency("konge") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
            version("[1.5.0,)")
        }
    }
}

fun DependencyHandlerScope.plugin(dep: Any) {
    shadow(dep)
}

fun DependencyHandlerScope.plugin(
    dep: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
) {
    shadow(dep, dependencyConfiguration)
}

dependencies {
    api("com.github.SettingDust:konge:1.5.0")
    plugin("com.github.SettingDust:konge:1.5.0:all")
}

tasks {
    shadowJar {
        configurations = listOf(project.configurations.shadow.get())
    }
    runServer {
        classpath(shadowJar)
    }
}