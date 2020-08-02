package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {

    @FindBy(id = "addNote")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "saveNoteChanges")
    private WebElement submitButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navBar;

    @FindBy(id = "userTable")
    private WebElement userTable;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    private WebDriver driver;

    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void createNote(String title, String description)   throws InterruptedException{
        WebDriverWait wait = new WebDriverWait (this.driver, 30);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.navBar)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.addNoteButton)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).sendKeys(title);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).sendKeys(description);
        Thread.sleep(500);
        this.submitButton.click();
    }

    public void editNote(String newTitle, String newDescription) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait (this.driver, 30);
        Thread.sleep(500);

        wait.until(ExpectedConditions.elementToBeClickable(this.navBar)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.editNoteButton)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).clear();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitle)).sendKeys(newTitle);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).clear();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescription)).sendKeys(newDescription);
        this.submitButton.click();
    }

    public void deleteNote() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait (this.driver, 30);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(this.navBar)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.deleteNoteButton)).click();

    }

}