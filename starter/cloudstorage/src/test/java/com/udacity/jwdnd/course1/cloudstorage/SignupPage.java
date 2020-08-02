package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "SignupButton")
    private WebElement SignupButton;

    private WebDriver driver;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void createUser(String firstname, String lastname, String username, String password){

        this.inputFirstName.sendKeys(firstname);
        this.inputLastName.sendKeys(lastname);
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.SignupButton.click();

    }
}


