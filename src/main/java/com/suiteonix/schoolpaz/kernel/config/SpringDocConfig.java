package com.suiteonix.schoolpaz.kernel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:${server.port}", description = "Dev"),
                @Server(url = "http://localhost:${management.server.port}", description = "Dev Actuator"),
                @Server(url = "http://funaab.localhost:${server.port}", description = "Dev"),
                @Server(url = "http://192.168.137.1:${server.port}", description = "Lan"),
                @Server(url = "https://${app.base.url}", description = "Staging"),
                @Server(url = "https://funaab.${app.base.url}", description = "Staging"),
        },
        info = @Info(
                title = "${spring.application.name}",
                description = "All endpoints for ${spring.application.name} Backend",
                contact = @Contact(url = "https://***.com", name = "Shellrim Dev Team", email = "dev@schoolpaz.com"),
                version = "v1"
        ),
        security = {
                @SecurityRequirement(name = "jwt"),
                @SecurityRequirement(name = "api-key"),
        }
)
@SecurityScheme(name = "jwt", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, scheme = "bearer", bearerFormat = "JWT", description = "Auth header")
//@SecurityScheme(name = "cookie", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.COOKIE, scheme = "bearer", paramName = "auth", description = "Auth cookie")
@SecurityScheme(name = "api-key", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, description = "API Key")
class SpringDocConfig {
}
