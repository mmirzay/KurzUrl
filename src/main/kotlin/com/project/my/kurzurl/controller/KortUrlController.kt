package com.project.my.kurzurl.controller

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.out.ActionResult
import org.springframework.web.bind.annotation.*
import java.lang.Boolean
import kotlin.String

@RestController
class KortUrlController {


    @PostMapping("/create")
    fun createShortUrl(@RequestBody dto: CreateShortUrlInDto?): ActionResult<String> {
        return ActionResult.Builder<String>()
            .success(Boolean.TRUE)
            .data("shortURL")
            .message("ShortURL created Successfully.")
            .build()
    }

    @GetMapping("/{shortUrl}")
    fun createShortUrl(@PathVariable shortUrl: String?): ActionResult<String> {
        return ActionResult.Builder<String>()
            .success(Boolean.TRUE)
            .data("LongURL")
            .message("LongURL is retrieved Successfully.")
            .build()
    }
}