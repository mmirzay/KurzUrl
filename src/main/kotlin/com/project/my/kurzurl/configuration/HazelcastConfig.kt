package com.project.my.kurzurl.configuration

import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.cache.HazelcastCacheManager
import org.slf4j.LoggerFactory
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.annotation.PreDestroy

@Configuration
@EnableCaching
class HazelcastConfig {

    private val logger = LoggerFactory.getLogger(javaClass)
    @PreDestroy
    fun destroy() {
        logger.info("Closing Cache Manager")
        Hazelcast.shutdownAll()
    }

    @Bean
    @Primary
    fun cacheManager(hazelcastInstance: HazelcastInstance?): CacheManager {
        return HazelcastCacheManager(hazelcastInstance)
    }

    @Bean
    @Primary
    fun hazelcastInstance(): HazelcastInstance {
        return Hazelcast.newHazelcastInstance()
    }
}