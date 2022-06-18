package com.sonrisa.barath.PeldaProject.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionGeneral {

    private var errorAttributes: ErrorAttributes? = null
        @Autowired
        set(value) {
            field = value
        }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(model: Model, request: WebRequest, ex: Exception): String {
        var error: MutableMap<String, Any>? = this.errorAttributes?.getErrorAttributes(request, true)
        model.addAttribute("timestamp", error?.get("timestamp"))
        model.addAttribute("error", error?.get("error"))
        model.addAttribute("message", error?.get("message"))
        model.addAttribute("path", error?.get("path"))
        model.addAttribute("status", error?.get("status"))
        model.addAttribute("errMessage", ex.message)
        return "exception"
    }
}