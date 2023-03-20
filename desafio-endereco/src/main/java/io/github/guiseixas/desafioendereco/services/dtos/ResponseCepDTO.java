package io.github.guiseixas.desafioendereco.services.dtos;

import lombok.Data;

@Data
public class ResponseCepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
