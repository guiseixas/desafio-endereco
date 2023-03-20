package io.github.guiseixas.desafioendereco.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.guiseixas.desafioendereco.DesafioEnderecoApplication;
import io.github.guiseixas.desafioendereco.controllers.EnderecoController;
import io.github.guiseixas.desafioendereco.entities.Endereco;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CucumberContextConfiguration
@SpringBootTest(classes = DesafioEnderecoApplication.class)
public class StepDefinitions {

    private Endereco endereco;
    private ResponseEntity<?> response;

    @Autowired
    private EnderecoController enderecoController;

    @Before
    public void setUp() {
        endereco = null;
        response = null;
    }

    @Given("um endereço com o CEP {string}")
    public void um_endereco_com_o_cep(String cep) {
        endereco = new Endereco();
        endereco.setCep(cep);
    }

    @When("o usuário consulta o endereço")
    public void o_usuario_consulta_o_endereco() {
        response = enderecoController.consultaEndereco(endereco);
    }

    @Then("o serviço retorna o endereço")
    public void o_serviço_retorna_o_endereço() {
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Then("o serviço retorna erro")
    public void o_serviço_retorna_erro() {
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
