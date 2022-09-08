package com.project.my.kurzurl.out

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ActionResult<T> {
    @JsonProperty("data")
    private var data: T? = null
    @JsonProperty("success")
    private var success: Boolean? = null
    @JsonProperty("message")
    private var message: String? = null


    private constructor(data: T?, success: Boolean?, message: String?) {
        this.data = data
        this.success = success
        this.message = message
    }

    private constructor(builder: Builder<T>) : this(builder.data, builder.success, builder.message)

    class Builder<T> {
        var data: T? = null
            private set

        var success: Boolean? = false
            private set

        var message: String? = null
            private set

        fun data(data: T) = apply { this.data = data }

        fun success(success: Boolean) = apply { this.success = success }

        fun message(message: String) = apply { this.message = message }

        fun build() = ActionResult(this)
    }
}