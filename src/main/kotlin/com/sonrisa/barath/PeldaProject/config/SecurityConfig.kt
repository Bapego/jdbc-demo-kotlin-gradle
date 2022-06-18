package com.sonrisa.barath.PeldaProject.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder






// Adott függvényeinket milyen szintű felhsználó érheti el
@EnableGlobalMethodSecurity(securedEnabled = true) // Ez csak a configuration miatt működhet
@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val userService: UserDetailsService? = null

    @Autowired
    @Throws(Exception::class)
    fun configureAuth(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userService)
    }

    /*@Autowired
    fun configureAuth(auth: AuthenticationManagerBuilder?) {
        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        auth
                ?.inMemoryAuthentication()
                ?.withUser("test")
                ?.password(passwordEncoder.encode("jelszó"))
                ?.roles("USER")
                ?.and()
                ?.withUser("testAdmin")
                ?.password(passwordEncoder.encode("jelszó"))
                ?.roles("ADMIN")

    }*/

    override fun configure(httpSec: HttpSecurity) {
        httpSec
                .authorizeRequests()
                .antMatchers("/css/**", "/font/**", "/img/**", "/js/**", "/owl-carousel/**").permitAll()
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/regisztracio", "/reg").permitAll()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/rolunk").permitAll()
                .antMatchers("/galeria").permitAll()
                .antMatchers("/kapcsolat").permitAll()
                .antMatchers("/activation/**").permitAll()
                .anyRequest().authenticated() // Bármilyen lekérdezéshez szükséges az autentikáció
                .and()
                .formLogin().loginPage("/login")
                .permitAll() // Mindenkinek elérhető legyen (autentikáció nélkül)
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .rememberMe()
    }
}