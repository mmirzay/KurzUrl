package com.project.my.kurzurl.out

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Builder

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ActionResult<T> {
    private var data: T? = null
    private var success: Boolean? = null
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