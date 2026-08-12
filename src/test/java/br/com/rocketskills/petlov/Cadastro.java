package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.rocketskills.petlov.pages.SignupPage;
import br.com.rocketskills.petlov.base.BaseTest;

class Cadastro extends BaseTest {

    @Test
    @DisplayName("Deve realizar o cadastro de um ponto de doação")
    void createPoint() {
        SignupPage signup = new SignupPage(driver);
        signup.open();

        assertEquals("Cadastro de ponto de doação", signup.getTitleText(), "Verificando o Slogan");

        signup.setName("Brenno");
        signup.setEmail("slbrenno@uorak.com");
        signup.setCep("06192220");
        signup.clickBuscarCep();
        signup.setAddressNumber("499");
        signup.selectCachorros();
        signup.submit();

        assertEquals("Você fez a diferença!", signup.getSuccessTitleText(), "Verificando o Slogan pós cadastro");

        String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
        assertEquals(target, signup.getSuccessMessageText(), "Verificando o texto da página pós cadastro");
    }
}
