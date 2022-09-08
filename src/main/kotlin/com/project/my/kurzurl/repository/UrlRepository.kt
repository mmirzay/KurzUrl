package com.project.my.kurzurl.repository

import com.project.my.kurzurl.entity.Url
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UrlRepository : BaseJpaRepository<Url, String> {

    fun findByShortUrl(shortUrl: String): Optional<Url>
}