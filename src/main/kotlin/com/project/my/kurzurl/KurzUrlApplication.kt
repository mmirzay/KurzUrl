package com.project.my.kurzurl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KurzUrlApplication

fun main(args: Array<String>) {
    runApplication<KurzUrlApplication>(*args)
    println("Kurz URL Simple APIs")
}
