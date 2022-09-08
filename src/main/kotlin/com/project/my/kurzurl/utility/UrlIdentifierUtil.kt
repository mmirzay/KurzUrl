package com.project.my.kurzurl.utility

import com.project.my.kurzurl.utility.MessageTranslatorUtil.getText
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import com.project.my.kurzurl.utility.UrlIdentifierUtil
import com.project.my.kurzurl.utility.MessageTranslatorUtil
import org.apache.commons.validator.routines.UrlValidator
import java.lang.Exception
import java.nio.charset.StandardCharsets

object UrlIdentifierUtil {
    private val VALIDATOR = UrlValidator()
    private val HASH_FUNCTION = Hashing.murmur3_32_fixed()
    fun identifierOf(url: String): String {
        validateUrl(url)
        return HASH_FUNCTION
            .hashString(url, StandardCharsets.UTF_8)
            .toString()
    }

    private fun validateUrl(url: String) {
        if (!VALIDATOR.isValid(url)) throw Exception(getText("utility.url.identifier.invalid.url"))
    }
}