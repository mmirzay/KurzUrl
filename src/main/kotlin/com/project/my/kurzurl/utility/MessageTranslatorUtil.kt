package com.project.my.kurzurl.utility

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import java.util.*

object MessageTranslatorUtil {
    private var messageSource: MessageSource? = null

    fun setMessageSource(messageSource: MessageSource?) {
        MessageTranslatorUtil.messageSource = messageSource
    }

    fun getText(msgCode: String?): String {
        val locale = LocaleContextHolder.getLocale()
        return getText(msgCode, locale)
    }

    fun getText(msgCode: String?, vararg params: Any?): String {
        val locale = LocaleContextHolder.getLocale()
        return getText(msgCode, locale, *params)
    }

    fun getText(msgCode: String?, locale: Locale?, vararg params: Any?): String {
        return messageSource!!.getMessage(msgCode!!, params, locale!!)
    }
}