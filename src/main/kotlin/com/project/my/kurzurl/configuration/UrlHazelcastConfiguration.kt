package com.project.my.kurzurl.configuration

import com.hazelcast.config.*
import com.hazelcast.core.HazelcastInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class UrlHazelcastConfiguration @Autowired constructor(
    hazelcastInstance: HazelcastInstance,
    @param:Value(
        "\${cache.config.url.timeToLiveSeconds.seconds}"
    ) private val cacheTimeToLiveSeconds: Int
) {
    init {
        initConfig(hazelcastInstance.config)
    }

    private fun initConfig(config: Config) {
        addUrlMapConfig(config)
    }

    private fun addUrlMapConfig(config: Config) {
        val map = MapConfig(urlCacheName)
        map.isReadBackupData = true
        map.evictionConfig =  EvictionConfig()
            .setEvictionPolicy(EvictionPolicy.LRU).setMaxSizePolicy(MaxSizePolicy.USED_HEAP_SIZE)
        map.backupCount = 0
        map.timeToLiveSeconds = cacheTimeToLiveSeconds
        config.addMapConfig(map)
    }

    companion object {
        const val urlCacheName = "UrlMap"
    }
}