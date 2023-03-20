package io.github.guiseixas.desafioendereco.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class EnderecoController {

    @GetMapping
    public String teste(){
        return "Rota teste.";
    }
}
