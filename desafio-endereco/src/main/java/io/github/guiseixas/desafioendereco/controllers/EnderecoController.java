package io.github.guiseixas.desafioendereco.controllers;

import io.github.guiseixas.desafioendereco.entities.Endereco;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1")
public class EnderecoController {

    @PostMapping("/consulta-endereco")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCepDTO consultaEndereco(@RequestBody Endereco endereco){
        if(validarCEP(endereco.getCep())){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResponseCepDTO> response =
                    restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", endereco.getCep()), ResponseCepDTO.class);
            return response.getBody();
        }
        return null;
    }

    private static boolean validarCEP(String cep){
        if(cep == null || cep.length() != 8){
            return false;
        }
        return true;
    }
}
