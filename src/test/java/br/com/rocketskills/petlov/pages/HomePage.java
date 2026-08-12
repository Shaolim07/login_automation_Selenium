package br.com.rocketskills.petlov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private String url = "https://petlov.vercel.app";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
    }

    public String getTitleText() {
        return getText(By.cssSelector("h1"));
    }
}
