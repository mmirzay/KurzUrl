package com.project.my.kurzurl.controller

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.out.ActionResult
import com.project.my.kurzurl.repository.UrlRepository
import com.project.my.kurzurl.service.interfaces.ShortUrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.Boolean
import javax.validation.Valid
import kotlin.String

@RestController
class KortUrlController(val urlService: ShortUrlService) {

    @PostMapping("/create")
    fun createShortUrl(@RequestBody @Valid dto: CreateShortUrlInDto): ActionResult<String> {
        return ActionResult.Builder<String>()
            .success(Boolean.TRUE)
            .data(urlService.createShortUrl(dto))
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