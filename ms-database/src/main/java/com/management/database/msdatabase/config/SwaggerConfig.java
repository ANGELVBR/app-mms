package com.management.database.msdatabase.config;

import com.management.database.msdatabase.domain.constans.Portal;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title(Portal.SWAGGER_TITLE)
                    .description(Portal.SWAGGER_DESCRIPTION)
                    .version(Portal.SWAGGER_VERSION)
                );
    }

}
