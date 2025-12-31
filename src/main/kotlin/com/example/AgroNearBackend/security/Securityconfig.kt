package com.example.AgroNearBackend.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {

                it.requestMatchers("/auth/**").permitAll()

                it.requestMatchers(HttpMethod.POST, "/products/**")
                    .hasRole("FARMER")

                it.requestMatchers("/wishlist/**")
                    .hasAnyRole("BUYER", "FARMER")

                it.requestMatchers(HttpMethod.GET, "/products/**")
                    .hasAnyRole("FARMER", "BUYER")

                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .httpBasic { it.disable() }
            .formLogin { it.disable() }

        return http.build()
    }
}
