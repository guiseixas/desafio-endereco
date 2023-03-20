package io.github.guiseixas.desafioendereco.services;

import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseToUserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConverterService {
    public ResponseToUserDTO convertToResponseUserDTO(ResponseCepDTO response){
        Map<String, String[]> regioes = new HashMap<>();
        regioes.put("Norte", new String[]{"AC", "AP", "AM", "PA", "RO", "RR", "TO"});
        regioes.put("Nordeste", new String[]{"AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE"});
        regioes.put("Centro-Oeste", new String[]{"DF", "GO", "MT", "MS"});
        regioes.put("Sudeste", new String[]{"ES", "MG", "RJ", "SP"});
        regioes.put("Sul", new String[]{"PR", "RS", "SC"});

        ResponseToUserDTO responseToUserDTO = new ResponseToUserDTO();
        responseToUserDTO.setCep(response.getCep());
        responseToUserDTO.setRua(response.getLogradouro());
        responseToUserDTO.setComplemento(response.getComplemento());
        responseToUserDTO.setBairro(response.getBairro());
        responseToUserDTO.setCidade(response.getLocalidade());
        responseToUserDTO.setEstado(response.getUf());

        for (Map.Entry<String, String[]> entry : regioes.entrySet()) {
            String regiao = entry.getKey();
            String[] estados = entry.getValue();
            for (String estado : estados) {
                if (estado.equals(response.getUf())) {
                    switch (regiao) {
                        case "Norte":
                            responseToUserDTO.setFrete(BigDecimal.valueOf(20.83).setScale(2));
                            break;
                        case "Nordeste":
                            responseToUserDTO.setFrete(BigDecimal.valueOf(15.98).setScale(2));
                            break;
                        case "Centro-Oeste":
                            responseToUserDTO.setFrete(BigDecimal.valueOf(12.50).setScale(2));
                            break;
                        case "Sudeste":
                            responseToUserDTO.setFrete(BigDecimal.valueOf(7.85).setScale(2));
                            break;
                        case "Sul":
                            responseToUserDTO.setFrete(BigDecimal.valueOf(17.30).setScale(2));
                            break;
                    }
                    break;
                }
            }
        }
        return responseToUserDTO;
    }
}
