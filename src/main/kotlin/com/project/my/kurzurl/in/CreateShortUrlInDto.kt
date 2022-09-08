package com.project.my.kurzurl.`in`

import com.project.my.kurzurl.configuration.Constants
import com.project.my.kurzurl.entity.Url
import com.project.my.kurzurl.exception.InternalException
import com.project.my.kurzurl.utility.MessageTranslatorUtil

class CreateShortUrlInDto(val url: String?) {
    init {
        validateUrl(url)
    }

    fun toUrl(shortUrl: String): Url {
        return Url.Builder()
            .longUrl(url!!)
            .shortUrl(shortUrl)
            .build()
    }

    private fun validateUrl(url: String?) {
        if (url == null)
            throw InternalException(MessageTranslatorUtil.getText("in.url.create.short.url.not.empty"))
        if (url.length > Constants.LONG_URL_MAX_LENGTH)
            throw InternalException(MessageTranslatorUtil.getText("in.url.create.short.url.size.limit"))
    }
}