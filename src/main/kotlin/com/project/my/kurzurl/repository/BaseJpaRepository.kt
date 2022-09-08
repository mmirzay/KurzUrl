package com.project.my.kurzurl.repository

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

@NoRepositoryBean
interface BaseJpaRepository<E, ID : Serializable> : JpaRepository<E, ID>