package com.project.my.kurzurl.`in`

import lombok.NoArgsConstructor
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data

@Data
@NoArgsConstructor
@AllArgsConstructor
class CreateShortUrlInDto {
    private val url: String? = null
}