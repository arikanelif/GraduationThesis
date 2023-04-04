package com.shopping.optimization.productservice.config


import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

// https://stackoverflow.com/a/61412233
@Configuration
class FlywayConfig (
    private val env: Environment
) {

    @Bean(initMethod = "migrate")
    fun flyway(): Flyway {
        val url = "jdbc:" + env.getRequiredProperty("postgresql://localhost:5432/postgres")
        val user = env.getRequiredProperty("spring.r2dbc.username")
        val password = env.getRequiredProperty("spring.r2dbc.password")
        val config = Flyway
            .configure()
            .dataSource(url, user, password)
        return Flyway(config)
    }
}