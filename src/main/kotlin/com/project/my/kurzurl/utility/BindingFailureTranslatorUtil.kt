package com.project.my.kurzurl.utility

import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import java.util.stream.Collectors
import org.slf4j.LoggerFactory
import java.util.*

object BindingFailureTranslatorUtil {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun buildErrorReasonInCaseOfBindingFailure(bindingResult: BindingResult): List<String> {
        if (logger.isDebugEnabled) logger.debug("binding failure with reason [{}]", bindingResult.fieldErrors.stream()
            .map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.joining(", ")))
        return bindingResult.fieldErrors.stream()
            .map { br: FieldError ->
                val args = Arrays.stream(Objects.requireNonNull(br.arguments)).collect(Collectors.toList())
                args.removeAt(0)
                MessageTranslatorUtil.getText(br.defaultMessage, *args.toTypedArray())
            }
            .collect(Collectors.toList())
    }
}