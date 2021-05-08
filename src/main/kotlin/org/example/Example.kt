package org.example

import com.github.konge.registerListener
import com.google.inject.Inject
import org.apache.logging.log4j.Logger
import org.spongepowered.api.Server
import org.spongepowered.api.event.lifecycle.StartingEngineEvent
import org.spongepowered.plugin.PluginContainer
import org.spongepowered.plugin.jvm.Plugin

@Plugin("example") // Plugin id here. Same with plugin meta in build script
class Example @Inject constructor(
    logger: Logger,
    pluginContainer: PluginContainer
) {
    init {
        pluginContainer.registerListener<StartingEngineEvent<Server>> {
            logger.info("Example is starting!")
        }
    }
}