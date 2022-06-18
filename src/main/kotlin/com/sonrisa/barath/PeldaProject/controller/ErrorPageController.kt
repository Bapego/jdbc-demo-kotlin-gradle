package com.sonrisa.barath.PeldaProject.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest

@Controller
class ErrorPageController : ErrorController {

    companion object {
        private const val ERR_PATH: String = "/error";
    }

    private var errorAttributes: ErrorAttributes? = null
        @Autowired
        set(value) {
            field = value
        }


    override fun getErrorPath(): String {
        return Companion.ERR_PATH
    }

    @RequestMapping(ERR_PATH)
    fun error(model: Model, request: WebRequest): String {
        var error: MutableMap<String, Any>? = this.errorAttributes?.getErrorAttributes(request, true)
        model.addAttribute("timestamp", error?.get("timestamp"))
        model.addAttribute("error", error?.get("error"))
        model.addAttribute("message", error?.get("message"))
        model.addAttribute("path", error?.get("path"))
        model.addAttribute("status", error?.get("status"))
        if (error?.get("status") == 404) {
            return notFound(model, request)
        }
        return "errors/detailedError"
    }

    fun notFound(model: Model, request: WebRequest): String {
        var error: MutableMap<String, Any>? = this.errorAttributes?.getErrorAttributes(request, true)
        model.addAttribute("message", error?.get("message"))
        return "errors/404"
    }
}