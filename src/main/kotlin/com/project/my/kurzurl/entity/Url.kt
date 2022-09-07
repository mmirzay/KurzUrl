package com.project.my.kurzurl.entity

import lombok.*
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    private val longUrl: String? = null

    @Column(nullable = false)
    private val shortUrl: String? = null
}