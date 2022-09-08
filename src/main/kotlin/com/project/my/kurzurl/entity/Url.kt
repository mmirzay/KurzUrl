package com.project.my.kurzurl.entity

import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("shortUrl"), name = "url_short_url_unique_index")])
class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, length = 2048)
    val longUrl: String?

    @Column(nullable = false, length = 8)
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