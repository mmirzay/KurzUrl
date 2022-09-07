package com.project.my.kurzurl.controller

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class KortUrlController {
    @PostMapping("/create")
    fun createShortUrl(@RequestBody dto: CreateShortUrlInDto?): String {
        return "shortUrl"
    }

    @GetMapping("/{shortUrl}")
    fun createShortUrl(@PathVariable shortUrl: String?): String {
        return "LongURL"
    }
}