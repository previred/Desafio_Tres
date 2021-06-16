package com.desafio.tres.valoresuf;

import com.desafio.tres.valoresuf.dist.rest.DesafioController;
import com.desafio.tres.valoresuf.service.impl.DesafioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class ValoresufApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    DesafioController desafioController;

    @Autowired
    DesafioServiceImpl desafioService;

    @Test
    void contextLoads() {
        assertThat(desafioController).isNotNull();
        assertThat(desafioService).isNotNull();
    }

    @Test
    void implemtDesafio() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"));
        mockMvc = null;
    }
}
