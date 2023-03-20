package io.github.guiseixas.desafioendereco.services.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseToUserDTO {
    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private BigDecimal frete;
}
