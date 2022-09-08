package com.project.my.kurzurl.service.impl

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.entity.Url
import com.project.my.kurzurl.repository.UrlRepository
import com.project.my.kurzurl.service.interfaces.ShortUrlService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ShortUrlServiceImpl : ShortUrlService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val urlRepository: UrlRepository? = null


    override fun createShortUrl(dto: CreateShortUrlInDto?): String {
        logger.info("creating new short url")
        val url: Url = dto!!.toUrl("shortUrl")
        var save: Url = urlRepository!!.save(url)
        logger.info("New short URL with id [{}] created successfully", save.id)
        return "shortUrl"+ save.id.toString()
    }

    override fun getLongUrl(shortUrl: String?): String? {
        return "LongURL"
    }
}