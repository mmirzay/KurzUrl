package com.project.my.kurzurl.utility

import org.springframework.web.servlet.ModelAndView

object UrlRedirectionUtil {
    private const val REDIRECT = "redirect:"
    fun redirectTo(url: String): ModelAndView {
        return ModelAndView(java.lang.String.format("%s%s", REDIRECT, url))
    }
}