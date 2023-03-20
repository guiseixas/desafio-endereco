package io.github.guiseixas.desafioendereco.configurations;

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
                        .title("Consulta Endereco API")
                        .description("Consulta endereco por CEP e retorna para o usuario dados relacionados a localizacao")
                        .version("1.0.0")
                );
    }

}
