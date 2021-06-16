package com.desafio.tres.valoresuf.dist.rest;

import com.desafio.tres.valoresuf.service.DesafioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * REST controller del {@link DesafioService}.
 *
 * @author Victor
 * @since 0.1.0
 */
@RestController
@RequestMapping("/")
public class DesafioController {

    private final DesafioService desafioService;

    public DesafioController(DesafioService desafioService) {
        this.desafioService = desafioService;
    }

    @GetMapping
    @ApiOperation(value = "Implementacion de algoritmo del desafio", response = ModelAndView.class)
    public ModelAndView desafio() {
        var mav = new ModelAndView();
        //agregando json al modelAndView
        mav.addObject("json", desafioService.desafio());
        mav.setViewName("Index.html");
        return mav;
    }

}
