package com.sonrisa.barath.PeldaProject.service

import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmailService {
    constructor(){}
    private val log = LogFactory.getLog(this.javaClass)

    @Value("\${spring.mail.username}")
    private val MESSAGE_FROM: String? = null

    @Value("\${mail.link}")
    private val MESSAGE_LINK: String? = null

    private var javaMailSender: JavaMailSender? = null
        @Autowired
        set(value) {
            field = value
        }


    fun sendMessage(email: String?, activeCode: String) {
        var message: SimpleMailMessage

        try {
            message = SimpleMailMessage()
            if (MESSAGE_FROM != null) {
                message.setFrom(MESSAGE_FROM)
            }
            message.setTo(email)
            message.setSubject("Sikeres regisztrálás")
            message.setText("Kedves $email! \n \n Köszönjük, hogy regisztráltál az oldalunkra!\nKérem kattints a következő linkre " +
                    "az aktiváláshoz: \n\n" + MESSAGE_LINK + activeCode)
            javaMailSender!!.send(message)

        } catch (e: Exception) {
            log.error("Hiba e-mail küldéskor az alábbi címre: $email  $e")
        }


    }
}