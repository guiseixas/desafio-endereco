package io.github.guiseixas.desafioendereco.controllers;

import io.github.guiseixas.desafioendereco.entities.Endereco;
import io.github.guiseixas.desafioendereco.services.ConverterService;
import io.github.guiseixas.desafioendereco.services.EnderecoService;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseToUserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/v1")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ConverterService dtoConverterService;

    private final String BASE_URL = "https://viacep.com.br/ws/%s/json/";

    @ApiOperation(value = "Obter dados com base no CEP enviado via requisição", response = Endereco.class)
    @PostMapping("/consulta-endereco")
    public ResponseEntity<?> consultaEndereco(@RequestBody Endereco endereco){
        String cep = enderecoService.mascaraCEP(endereco.getCep());
        if(enderecoService.validarCep(cep)){
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<ResponseCepDTO> response =
                        restTemplate.getForEntity(String.format(this.BASE_URL, cep), ResponseCepDTO.class);
                ResponseToUserDTO responseToUserDTO = dtoConverterService.convertToResponseUserDTO(response.getBody());

                if(isNull(responseToUserDTO.getCep())){
                    return ResponseEntity.badRequest().body("O CEP informado não foi encontrado.");
                }
                return ResponseEntity.ok(responseToUserDTO);
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body("Não foi possível obter o endereço para o CEP informado.");
            }
        }else {
            return ResponseEntity.badRequest().body("O CEP informado é inválido.");
        }
    }
}
