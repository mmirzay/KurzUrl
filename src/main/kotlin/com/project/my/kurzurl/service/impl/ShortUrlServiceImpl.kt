package com.project.my.kurzurl.service.impl

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.service.interfaces.ShortUrlService
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class ShortUrlServiceImpl : ShortUrlService {
    override fun createShortUrl(dto: CreateShortUrlInDto?): String? {
        return "shortUrl"
    }

    override fun getLongUrl(shortUrl: String?): String? {
        return "LongURL"
    }
}