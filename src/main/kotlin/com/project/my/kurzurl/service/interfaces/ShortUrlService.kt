package com.project.my.kurzurl.service.interfaces

import com.project.my.kurzurl.`in`.CreateShortUrlInDto

interface ShortUrlService {
    fun createShortUrl(dto: CreateShortUrlInDto?): String?
    fun getLongUrl(shortUrl: String?): String?
}