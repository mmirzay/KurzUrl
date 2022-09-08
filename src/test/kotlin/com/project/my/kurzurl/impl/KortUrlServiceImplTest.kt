package com.project.my.kurzurl.impl

import com.project.my.kurzurl.`in`.CreateShortUrlInDto
import com.project.my.kurzurl.exception.InternalException
import com.project.my.kurzurl.exception.NotFoundException
import com.project.my.kurzurl.service.interfaces.ShortUrlService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class KortUrlServiceImplTest(@Autowired val urlService: ShortUrlService) {

    @Test
    fun givenAValidLongUrl_whenCreatingShortUrl_thenEverythingMustBeOk() {
        val sampleUrlInDto: CreateShortUrlInDto = createSampleUrlInDto()
        val shortUrl = urlService.createShortUrl(sampleUrlInDto)
        Assertions.assertNotNull(shortUrl)
    }

    @Test
    fun givenAnInvalidLongUrl_whenCreatingShortUrl_thenMustThrowException() {
        val invalidUrlInDto: CreateShortUrlInDto = createInvalidUrlInDto()
        Assertions.assertThrows(InternalException::class.java) { urlService.createShortUrl(invalidUrlInDto) }
    }

    @Test
    fun givenTwoSimilarLongUrls_whenCreatingShortUrl_thenResultMustBeSame() {
        val sampleUrlInDto1: CreateShortUrlInDto = createSampleUrlInDto()
        val sampleUrlInDto2: CreateShortUrlInDto = createSampleUrlInDto()
        val shortUrl1 = urlService.createShortUrl(sampleUrlInDto1)
        val shortUrl2 = urlService.createShortUrl(sampleUrlInDto2)
        Assertions.assertEquals(shortUrl1, shortUrl2)
    }

    @Test
    fun givenPartialDifferentLongUrls_whenCreatingShortUrl_thenResultMustBeDifferent() {
        val sampleUrlInDto: CreateShortUrlInDto = createSampleUrlInDto()
        val partialDifferent: CreateShortUrlInDto = createPartialDifferentLongUrl(sampleUrlInDto.url!!)
        val shortUrl = urlService.createShortUrl(sampleUrlInDto)
        val partialDifferentShortUrl = urlService.createShortUrl(partialDifferent)
        Assertions.assertNotEquals(shortUrl, partialDifferentShortUrl)
    }

    @Test
    fun givenALongUrlAndItsRelatedShortUrl_whenGetLongUrl_thenEverythingMMustBeOk() {
        val sampleUrlInDto: CreateShortUrlInDto = createSampleUrlInDto()
        val shortUrl = urlService.createShortUrl(sampleUrlInDto)
        val longUrl = urlService.getLongUrl(shortUrl)
        Assertions.assertEquals(sampleUrlInDto.url, longUrl)
    }

    @Test
    fun givenAShortUrlNotAddedBefore_whenGetLongUrl_thenMustThrowNotFoundException() {
        val shortUrl = "invalid_short_url"
        Assertions.assertThrows(NotFoundException::class.java) { urlService.getLongUrl(shortUrl) }
    }

    private fun createSampleUrlInDto(): CreateShortUrlInDto {
        val url =
            "https://www.google.com/search?q=long+url+adrress&oq=long+url&aqs=chrome.0.69i59j69i57j0i271l3j69i60l3.3596j0j7&sourceid=chrome&ie=UTF-8"
        return CreateShortUrlInDto(url)
    }

    private fun createPartialDifferentLongUrl(url: String): CreateShortUrlInDto {
        return CreateShortUrlInDto(url+"d")
    }

    private fun createInvalidUrlInDto(): CreateShortUrlInDto {
        val url = "http::something@invalid/"
        return CreateShortUrlInDto(url)
    }
}