package com.project.my.kurzurl.repository

import com.project.my.kurzurl.entity.Url
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : BaseJpaRepository<Url, String>