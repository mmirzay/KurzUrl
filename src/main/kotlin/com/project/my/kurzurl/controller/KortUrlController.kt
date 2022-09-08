package com.project.my.kurzurl.controller

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.out.ActionResult
import com.project.my.kurzurl.service.interfaces.ShortUrlService
import com.project.my.kurzurl.utility.UrlRedirectionUtil.redirectTo
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid
import kotlin.String

@RestController
class KortUrlController(val urlService: ShortUrlService) {

    @PostMapping("/create")
    fun createShortUrl(@RequestBody @Valid dto: CreateShortUrlInDto): ActionResult<String> {
        return ActionResult.Builder<String>()
            .success(true)
            .data(urlService.createShortUrl(dto))
            .message("ShortURL created Successfully.")
            .build()
    }

    @GetMapping("/{shortUrl}")
    fun createShortUrl(@PathVariable shortUrl: String): ModelAndView {
        return redirectTo(urlService.getLongUrl(shortUrl))
    }
}