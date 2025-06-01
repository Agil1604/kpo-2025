package kpo.application.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API gateway")
                        .version("1.0")
                        .description("Это точка входа в данное микросервисное приложение. API gateway предоставляет API для выполнения требуемых задач."));
    }
}
