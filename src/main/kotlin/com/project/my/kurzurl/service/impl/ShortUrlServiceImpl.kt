package com.project.my.kurzurl.service.impl

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.configuration.UrlHazelcastConfiguration
import com.project.my.kurzurl.entity.Url
import com.project.my.kurzurl.exception.NotFoundException
import com.project.my.kurzurl.repository.UrlRepository
import com.project.my.kurzurl.service.interfaces.ShortUrlService
import com.project.my.kurzurl.utility.MessageTranslatorUtil
import com.project.my.kurzurl.utility.UrlIdentifierUtil
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
@Cacheable(cacheNames = [UrlHazelcastConfiguration.urlCacheName])
class ShortUrlServiceImpl(val urlRepository: UrlRepository) : ShortUrlService {

    private val logger = LoggerFactory.getLogger(javaClass)


    override fun createShortUrl(dto: CreateShortUrlInDto): String {
        logger.info("creating new short url")
        val shortURL = UrlIdentifierUtil.identifierOf(dto.url)
        if (shortUrlExists(shortURL))
            return shortURL

        val url: Url = dto.toUrl(shortURL)
        val save: Url = urlRepository.save(url)
        logger.info("New short URL with id [{}] created successfully", save.id)
        return shortURL
    }

    private fun shortUrlExists(shortURL: String): Boolean {
        return findUrl(shortURL).isPresent
    }

    @Cacheable
    fun findUrl(shortUrl: String): Optional<Url> {
        return urlRepository.findByShortUrl(shortUrl)
    }

    override fun getLongUrl(shortUrl: String): String {
        logger.info("getting URL with short identifier [{}]", shortUrl)
        val url: Url = findUrl(shortUrl)
            .orElseThrow {
                NotFoundException(
                    MessageTranslatorUtil.getText(
                        "service.url.get.long.url.not.found"
                    )
                )
            }
        logger.info("URL with short identifier [{}] found successfully.", shortUrl)
        return url.longUrl!!
    }
}