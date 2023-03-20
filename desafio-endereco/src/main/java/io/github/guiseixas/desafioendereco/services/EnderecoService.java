package io.github.guiseixas.desafioendereco.services;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    public boolean validarCep(String cep) {
        if(cep == null || cep.length() != 8){
            return false;
        }
        return true;
    }
    public String mascaraCEP(String cep) {
        return cep.replaceAll("-", "");
    }
}
