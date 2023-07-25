package com.management.users.config;

import com.management.users.domain.constants.Global;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI usersMicroserviceOpenApi() {
    return new OpenAPI()
        .info(new Info()
            .title(Global.SWAGGER_TITLE)
            .description(Global.SWAGGER_DESCRIPTION)
            .version(Global.SWAGGER_VERSION)
        );
  }
  
}
