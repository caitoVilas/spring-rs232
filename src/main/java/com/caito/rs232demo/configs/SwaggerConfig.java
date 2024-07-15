package com.caito.rs232demo.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "RS-232 - USB Demo",
                version = "${application.version}",
                description = "RS-232 and USB communication tests for Local Injector",
                contact = @Contact(name = "caito Vilas", email = "caitocd@gmail.com")
        )
        //servers = @Server(url = "https://localhost:3000"
)
@SecurityScheme(
        name = "security token",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "Bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfig {
}
