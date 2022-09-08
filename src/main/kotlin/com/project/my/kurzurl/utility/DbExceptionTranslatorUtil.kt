package com.project.my.kurzurl.utility

import org.postgresql.util.PSQLException
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils
import java.util.regex.Pattern

object DbExceptionTranslatorUtil {
    private const val NULL_CONSTRAINT_PREFIX = "ERROR: null value in column"
    private const val UNIQUE_CONSTRAINT_PREFIX = "ERROR: duplicate key value"

    private val logger = LoggerFactory.getLogger(javaClass)

    fun findCause(ex: PSQLException): String {
        var cause: String = checkUniqueConstraintViolation(ex.message)
        if (StringUtils.hasText(cause)) return cause
        cause = checkNullConstraintViolation(ex.message)
        if (StringUtils.hasText(cause)) return cause
        logger.warn("could not extract psql exception msg [{}] ", ex.message)
        return ex.message!!
    }

    private fun checkNullConstraintViolation(msg: String?): String {
        var violationReason = ""
        if (msg!!.startsWith(NULL_CONSTRAINT_PREFIX)) {
            val fieldName = msg.split("\"").toTypedArray()[1].replace("_", " ")
            violationReason = MessageTranslatorUtil.getText("db.exception.null.constraint.violation", fieldName)
        }
        return violationReason
    }

    private fun checkUniqueConstraintViolation(msg: String?): String {
        var res = msg
        var violationReason = ""
        if (res!!.startsWith(UNIQUE_CONSTRAINT_PREFIX)) {
            res = res.split("\"").toTypedArray()[2]
            violationReason = MessageTranslatorUtil.getText("db.exception.unique.constraint.violation", *getFields(res))
        }
        return violationReason
    }

    private fun getFields(msg: String): Array<String?> {
        val pattern = Pattern.compile("\\((.*?)\\)")
        val matcher = pattern.matcher(msg)
        val messages = arrayOfNulls<String>(2)
        var index = 0
        while (matcher.find()) {
            messages[index++] = matcher.group(1)
        }
        return messages
    }
}