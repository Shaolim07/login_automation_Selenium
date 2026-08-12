package br.com.rocketskills.petlov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {
    private String url = "https://petlov.vercel.app/signup";

    private static final By TITLE = By.cssSelector("h1");
    private static final By NAME = By.cssSelector("input[placeholder^='Nome do ponto']");
    private static final By EMAIL = By.cssSelector("input[name=email]");
    private static final By CEP = By.cssSelector("input[name=cep]");
    private static final By CEP_BUTTON = By.cssSelector("input[value='Buscar CEP']");
    private static final By ADDRESS_NUMBER = By.cssSelector("input[name=addressNumber]");
    private static final By CACHORROS = By.xpath("//span[text()=\"Cachorros\"]/..");
    private static final By SUBMIT = By.className("button-register");
    private static final By SUCCESS_TITLE = By.cssSelector("#success-page h1");
    private static final By SUCCESS_MESSAGE = By.cssSelector("#success-page p");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
    }

    public String getTitleText() {
        return getText(TITLE);
    }

    public void setName(String name) {
        type(NAME, name);
    }

    public void setEmail(String email) {
        type(EMAIL, email);
    }

    public void setCep(String cep) {
        type(CEP, cep);
    }

    public void clickBuscarCep() {
        click(CEP_BUTTON);
    }

    public void setAddressNumber(String number) {
        type(ADDRESS_NUMBER, number);
    }

    public void selectCachorros() {
        click(CACHORROS);
    }

    public void submit() {
        click(SUBMIT);
    }

    public String getSuccessTitleText() {
        return getText(SUCCESS_TITLE);
    }

    public String getSuccessMessageText() {
        return getText(SUCCESS_MESSAGE);
    }
}
