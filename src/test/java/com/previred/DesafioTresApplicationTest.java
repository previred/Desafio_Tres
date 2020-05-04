package com.previred;

import com.previred.controllers.DesafioTresController;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.services.DesafioTresService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DesafioTresApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DesafioTresController controller;

    @Autowired
    private DesafioTresService service;

    @Test
    public void healthCheckShouldReturnDefaultMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/healthCheck",
                String.class)).isEqualTo("I'm here!");
    }

    @Test
    public void controllerLoaded() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void serviceLoaded() {
        assertThat(service).isNotNull();
    }

    @Test
    public void checkFirstElement() {
        Valores valores = new Valores();
        Ufs ufs = valores.getRango();

        assertThat(service.getUfs(ufs).first().getDate().getTime()).isEqualTo(ufs.getInicio().getTime());
    }

    @Test
    public void checkLastElement() {
        Valores valores = new Valores();
        Ufs ufs = valores.getRango();

        assertThat(service.getUfs(ufs).last().getDate().getTime()).isEqualTo(ufs.getFin().getTime());
    }

}
