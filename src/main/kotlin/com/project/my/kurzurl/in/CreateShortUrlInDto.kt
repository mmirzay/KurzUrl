package com.project.my.kurzurl.`in`

import com.project.my.kurzurl.entity.Url
import java.io.Serializable
import javax.validation.constraints.NotEmpty

class CreateShortUrlInDto(@field:NotEmpty val url: String) : Serializable {

    fun toUrl(shortUrl: String): Url {
        return Url.Builder()
            .longUrl(url)
            .shortUrl(shortUrl)
            .build()
    }
}