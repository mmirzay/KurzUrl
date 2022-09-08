package com.project.my.kurzurl.utility

import com.google.common.hash.Hashing
import com.project.my.kurzurl.exception.InternalException
import org.apache.commons.validator.routines.UrlValidator
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
        if (!VALIDATOR.isValid(url)) throw InternalException(MessageTranslatorUtil.getText("utility.url.identifier.invalid.url"))
    }
}