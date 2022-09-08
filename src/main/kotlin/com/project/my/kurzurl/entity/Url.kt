package com.project.my.kurzurl.entity

import com.project.my.kurzurl.configuration.Constants.LONG_URL_MAX_LENGTH
import com.project.my.kurzurl.configuration.Constants.SHORT_URL_MAX_LENGTH
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("shortUrl"), name = "url_short_url_unique_index")])
class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, length = LONG_URL_MAX_LENGTH)
    val longUrl: String?

    @Column(nullable = false, length = SHORT_URL_MAX_LENGTH)
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