package com.project.my.kurzurl.configuration

import com.project.my.kurzurl.utility.MessageTranslatorUtil
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import javax.annotation.PostConstruct

@Configuration
class ResourceBundleConfig {

    companion object {
        const val CLASSPATH_MESSAGES = "classpath:messages/core_messages"
    }

    fun messageSource():
            MessageSource {
        val rs = ReloadableResourceBundleMessageSource()
        rs.setBasenames(CLASSPATH_MESSAGES)
        rs.setDefaultEncoding("UTF-8")
        rs.setUseCodeAsDefaultMessage(true)
        return rs
    }

    @PostConstruct
    fun initial() {
        MessageTranslatorUtil.setMessageSource(messageSource())
    }
}