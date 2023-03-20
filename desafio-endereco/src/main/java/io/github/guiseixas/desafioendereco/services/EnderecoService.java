package io.github.guiseixas.desafioendereco.services;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    public boolean validarCep(String cep) {
        if(cep == null || cep.length() != 8 || cep.matches("\\\\d{8}")){
            return false;
        }
        return true;
    }
    public String mascaraCEP(String cep) {
        if(cep.length() > 5 && cep.charAt(5) == '-'){
            return cep.substring(0,5) + cep.substring(6);
        }
        return cep;
    }
}
