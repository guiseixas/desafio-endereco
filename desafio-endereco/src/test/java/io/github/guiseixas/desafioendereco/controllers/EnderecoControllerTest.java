package io.github.guiseixas.desafioendereco.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.guiseixas.desafioendereco.entities.Endereco;
import io.github.guiseixas.desafioendereco.services.dtos.ResponseCepDTO;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnderecoController enderecoController;

    /*
    Teste utilizado para CEPS válidos e retornados corretamente pela API de CEP.
     */
    @Test
    void consultaEnderecoTeste() throws Exception {
        Endereco enderecoTest = new Endereco();
        enderecoTest.setCep("01001000");
        JSONObject requestBody = new JSONObject();
        requestBody.put("cep", enderecoTest.getCep());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody.toString()))
                        .andExpect(status().isOk());
    }

    /*
    Teste utilizado para CEPS inválidos (maior que 8 dígitos, nulo ou com caracteres) ou inexistentes.
     */
    @Test
    void consultaEnderecoTesteBadRequest() throws Exception {
        Endereco enderecoTest = new Endereco();
        enderecoTest.setCep("12345678");
        JSONObject requestBody = new JSONObject();
        requestBody.put("cep", enderecoTest.getCep());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody.toString()))
                .andExpect(status().isBadRequest());
    }
}
