package com.project.my.kurzurl.entity

import javax.persistence.*

@Entity
class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    val longUrl: String?

    @Column(nullable = false)
    val shortUrl: String?

    private constructor(longUrl: String?, shortUrl: String?) {
        this.longUrl = longUrl
        this.shortUrl = shortUrl
    }

    private constructor(builder: Builder) : this(builder.longUrl, builder.shortUrl)

    class Builder {
        var longUrl: String? = null
            private set

        var shortUrl: String? = null
            private set


        fun longUrl(longUrl: String) = apply { this.longUrl = longUrl }

        fun shortUrl(shortUrl: String) = apply { this.shortUrl = shortUrl }


        fun build() = Url(this)
    }
}