package io.github.guiseixas.desafioendereco.controllers;

import io.github.guiseixas.desafioendereco.entities.Endereco;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseToUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1")
public class EnderecoController {

    @PostMapping("/consulta-endereco")
    public ResponseEntity<?> consultaEndereco(@RequestBody Endereco endereco){
        if(validarCEP(endereco.getCep())){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResponseCepDTO> response =
                    restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", endereco.getCep()), ResponseCepDTO.class);
            ResponseToUserDTO responseToUserDTO = new ResponseToUserDTO();
            return ResponseEntity.ok(responseToUserDTO.convertToResponseUserDTO(response.getBody()));
        }
        return ResponseEntity.badRequest().body("O CEP informado não foi encontrado.");
    }
    private static boolean validarCEP(String cep){
        if(cep == null || cep.length() != 8){
            return false;
        }
        return true;
    }
}
