package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.rocketskills.petlov.pages.HomePage;
import br.com.rocketskills.petlov.base.BaseTest;

class Slogan extends BaseTest {

    @Test
    @DisplayName("Deve exibir o slogan do site")
    void showsSlogan() {
        HomePage home = new HomePage(driver);
        home.open();

        assertEquals("Conectando corações, mudando vidas!", home.getTitleText(), "Verificando o Slogan");
    }
}
