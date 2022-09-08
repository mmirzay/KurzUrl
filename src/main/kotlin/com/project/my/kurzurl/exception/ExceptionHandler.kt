package com.project.my.kurzurl.exception

import com.project.my.kurzurl.out.ActionResult
import com.project.my.kurzurl.out.ErrorModel
import com.project.my.kurzurl.utility.BindingFailureTranslatorUtil
import com.project.my.kurzurl.utility.DbExceptionTranslatorUtil
import com.project.my.kurzurl.utility.MessageTranslatorUtil
import org.postgresql.util.PSQLException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Boolean
import kotlin.String

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(InternalException::class)
    @ResponseStatus(HttpStatus.OK)
    fun handlingInternalException(ex: InternalException): ActionResult<String> {
        logger.error("Internal Error occurred : [{}]", ex.message)
        return ActionResult.Builder<String>()
            .message(ex.message!!)
            .success(Boolean.FALSE)
            .build()
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.OK)
    fun handlingNotFoundException(ex: NotFoundException): ActionResult<String> {
        logger.error("result not found : [{}]", ex.message)
        return ActionResult.Builder<String>()
            .message(ex.message!!)
            .success(Boolean.FALSE)
            .build()
    }

    @ExceptionHandler(PSQLException::class)
    @ResponseStatus(HttpStatus.OK)
    fun handlingDbException(ex: PSQLException): ActionResult<String> {
        logger.error("db exception thrown : [{}]", ex.message)
        return ActionResult.Builder<String>()
            .message(DbExceptionTranslatorUtil.findCause(ex))
            .success(Boolean.FALSE)
            .build()
    }

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBindingException(ex: BindException): ErrorModel {
        logger.error("received invalid parameter [{}] ", ex.message)
        return ErrorModel.Builder()
            .errorReasons(BindingFailureTranslatorUtil.buildErrorReasonInCaseOfBindingFailure(ex.bindingResult))
            .build()
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun jsonFormatIsIncorrectException(ex: HttpMessageNotReadableException): ErrorModel {
        logger.error(ex.message)
        return ErrorModel.Builder()
            .errorReason(MessageTranslatorUtil.getText("exception.handler.json.format"))
            .build()
    }
}