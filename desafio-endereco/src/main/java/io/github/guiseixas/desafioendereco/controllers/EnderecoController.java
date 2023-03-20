package io.github.guiseixas.desafioendereco.controllers;

import io.github.guiseixas.desafioendereco.entities.Endereco;
import io.github.guiseixas.desafioendereco.services.dtos.DtoConverterService;
import io.github.guiseixas.desafioendereco.services.dtos.EnderecoService;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseToUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private DtoConverterService dtoConverterService;
    
    @PostMapping("/consulta-endereco")
    public ResponseEntity<?> consultaEndereco(@RequestBody Endereco endereco){
        if(enderecoService.validarCep(endereco.getCep())){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResponseCepDTO> response =
                    restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", endereco.getCep()), ResponseCepDTO.class);
            ResponseToUserDTO responseToUserDTO = dtoConverterService.convertToResponseUserDTO(response.getBody());
            if(responseToUserDTO == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CEP informado não foi encontrado.");
            }
            return ResponseEntity.ok(responseToUserDTO);
        }
        return ResponseEntity.badRequest().body("O CEP informado não foi encontrado.");
    }
}
