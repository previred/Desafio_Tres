package com.desafio.tres.valoresuf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SwaggerConfiguration.class
})
@ComponentScan({
        "com.desafio.tres.valoresuf.dist.rest",
        "com.desafio.tres.valoresuf.service.impl"
})
public class DefaultConfiguration {

}
