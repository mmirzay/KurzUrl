package com.project.my.kurzurl.out

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ErrorModel {

    @JsonProperty("errorReason")
    private val errorReason: String?

    @JsonProperty("errorReasons")
    private val errorReasons: List<String>?

    constructor(errorReason: String?, errorReasons: List<String>?) {
        this.errorReasons = errorReasons
        this.errorReason = errorReason
    }

    private constructor(builder: Builder) : this(builder.errorReason, builder.errorReasons)

    class Builder {
        var errorReasons: List<String>? = null
        var errorReason: String? = null

        fun errorReasons(errorReasons: List<String>) = apply { this.errorReasons = errorReasons }

        fun errorReason(errorReason: String) = apply { this.errorReason = errorReason }

        fun build() = ErrorModel(this)
    }
}