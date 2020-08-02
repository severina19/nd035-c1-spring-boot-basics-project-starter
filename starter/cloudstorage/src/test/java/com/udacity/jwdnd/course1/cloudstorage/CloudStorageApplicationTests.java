package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.HashService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private static final String FIRST_NAME = "Mustermann";
	private static final String LAST_NAME = "Max";
	private static final String USERNAME = "MaxM";
	private static final String PASSWORD = "12345";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private NotePage notePage;
	private LoginPage loginPage;
	private SignupPage signupPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getHomePage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	//Write a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	public void getSignupPage(){
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	//Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.
	@Test
	public void signupUser(){
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.createUser(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);

		//login
		LoginPage loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.loginUser(USERNAME, PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());

		WebElement logoutButton = driver.findElement(By.id("logout"));
		logoutButton.click();
		Assertions.assertNotEquals("Home", driver.getTitle());

	}
	@Test
	public void testNote()  throws InterruptedException{
		//Write a test that creates a note, and verifies it is displayed.

		//sign up
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.createUser(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);

		//login
		LoginPage loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.loginUser(USERNAME, PASSWORD);
		Assertions.assertEquals("Home", driver.getTitle());

		//create note
		NotePage notePage = new NotePage(driver);
		try{
			notePage.createNote("Today is Friday", "Thats great!");
		}
		catch (InterruptedException e) {
			throw e;
		}

		Assertions.assertEquals("Result", driver.getTitle());
		WebElement toHomeSuccess = driver.findElement(By.id("toHomeSuccess"));
		WebDriverWait wait = new WebDriverWait (this.driver, 30);
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(toHomeSuccess)).click();
		Thread.sleep(500);
		WebElement navBar = driver.findElement(By.id("nav-notes-tab"));
		Thread.sleep(500);
		navBar.click();

		WebElement savedNote = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("Today is Friday", savedNote.getAttribute("innerHTML"));

		savedNote = driver.findElement(By.cssSelector("td.note-description-row"));
		Assertions.assertEquals("Thats great!", savedNote.getAttribute("innerHTML"));

		//edit note
		try{notePage.editNote("Today is Monday", "Oh No");}catch (InterruptedException e){
			throw e;
		}
		Assertions.assertEquals("Result", driver.getTitle());
		toHomeSuccess = driver.findElement(By.id("toHomeSuccess"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(toHomeSuccess)).click();
		navBar = driver.findElement(By.id("nav-notes-tab"));
		Thread.sleep(500);
		navBar.click();

		savedNote = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("Today is Monday", savedNote.getAttribute("innerHTML"));

		savedNote = driver.findElement(By.cssSelector("td.note-description-row"));
		Assertions.assertEquals("Oh No", savedNote.getAttribute("innerHTML"));
		Assertions.assertEquals(false, driver.findElements(By.cssSelector("th.note-title-row")).isEmpty());
		//delete note
		WebElement deleteNote = driver.findElement(By.cssSelector("a.delete-note"));
		Thread.sleep(500);
		deleteNote.click();
		toHomeSuccess = driver.findElement(By.id("toHomeSuccess"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(toHomeSuccess)).click();
		Thread.sleep(500);
		navBar = driver.findElement(By.id("nav-notes-tab"));
		Thread.sleep(500);
		navBar.click();

		Assertions.assertEquals(true, driver.findElements(By.cssSelector("th.note-title-row")).isEmpty());


	}
	@Test
	public void testCredentials()  throws InterruptedException{
		WebDriverWait wait = new WebDriverWait (this.driver, 3);

		//sign up
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.createUser(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);

		//login
		LoginPage loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.loginUser(USERNAME, PASSWORD);

		WebElement navBar = driver.findElement(By.id("nav-credentials-tab"));
		Thread.sleep(500);
		navBar.click();
		CredentialPage credentialPage = new CredentialPage(driver);
		credentialPage.createCredential("as", "a", "a.com");

		WebElement toHome = driver.findElement(By.id("toHome"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(toHome)).click();
		navBar = driver.findElement(By.id("nav-credentials-tab"));
		Thread.sleep(500);
		navBar.click();

		//Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
		WebElement savedCredential = driver.findElement(By.cssSelector("th.saved-credential-url"));
		Assertions.assertEquals("a.com", savedCredential.getAttribute("innerHTML"));

		savedCredential = driver.findElement(By.cssSelector("td.saved-credential-username"));
		Assertions.assertEquals("as", savedCredential.getAttribute("innerHTML"));

		savedCredential = driver.findElement(By.cssSelector("td.saved-credential-password"));

		Assertions.assertNotEquals("a", savedCredential.getAttribute("innerHTML"));

		//Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
		WebElement editCredential = driver.findElement(By.id("editCredential"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(editCredential)).click();
		savedCredential = driver.findElement(By.id("credential-password"));
		Assertions.assertEquals("a", savedCredential.getAttribute("value"));

		WebElement closeEdit = driver.findElement(By.id("close-credential-edit"));
		Thread.sleep(500);
		closeEdit.click();

		//Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
		WebElement deleteCredential = driver.findElement(By.cssSelector("a.delete-credential"));
		Thread.sleep(500);
		deleteCredential.click();
		WebElement toHomeSuccess = driver.findElement(By.id("deleteCredentialHome"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(toHomeSuccess)).click();
		Thread.sleep(500);
		navBar = driver.findElement(By.id("nav-credentials-tab"));
		Thread.sleep(500);
		navBar.click();

		Assertions.assertEquals(true, driver.findElements(By.cssSelector("th.credential-url")).isEmpty());






	}

}
