package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {

    @FindBy(id = "addCredential")
    private WebElement addCredentialButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navBar;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "saveCredentialChanges")
    private WebElement submitButton;

    private WebDriver driver;

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void createCredential(String username, String password, String url)   throws InterruptedException {
        WebDriverWait wait = new WebDriverWait (this.driver, 30);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.navBar)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.addCredentialButton)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialUsername)).sendKeys(username);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialUrl)).sendKeys(url);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialPassword)).sendKeys(password);
        Thread.sleep(500);
        this.submitButton.click();

    }


    }
