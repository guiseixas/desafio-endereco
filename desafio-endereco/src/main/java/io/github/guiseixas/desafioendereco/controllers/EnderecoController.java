package io.github.guiseixas.desafioendereco.controllers;

import io.github.guiseixas.desafioendereco.entities.Endereco;
import io.github.guiseixas.desafioendereco.services.ConverterService;
import io.github.guiseixas.desafioendereco.services.EnderecoService;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseToUserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/v1")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ConverterService dtoConverterService;

    @ApiOperation(value = "Obter dados com base no CEP enviado via requisição", response = Endereco.class)
    @PostMapping("/consulta-endereco")
    public ResponseEntity<?> consultaEndereco(@RequestBody Endereco endereco){
        String cep = enderecoService.mascaraCEP(endereco.getCep());
        if(enderecoService.validarCep(cep)){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResponseCepDTO> response =
                    restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", cep), ResponseCepDTO.class);
            ResponseToUserDTO responseToUserDTO = dtoConverterService.convertToResponseUserDTO(response.getBody());
            if(isNull(responseToUserDTO.getCep())){
                return ResponseEntity.badRequest().body("O CEP informado não foi encontrado.");
            }
            return ResponseEntity.ok(responseToUserDTO);
        }else {
            return ResponseEntity.badRequest().body("O CEP informado é inválido.");
        }
    }
}
