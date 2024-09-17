package stepdefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

	WebDriver driver = new ChromeDriver();

	@After
	public void clean() {
		driver.quit();
	}

	@Given("User is on Home page")
	public void user_is_on_home_page() {

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("User enters username as {string} and password as {string}")
	public void user_enters_username_as_and_password_as(String userName, String passWord) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(passWord);
		
	}
	
	@And("Click on login button")
	public void Click_on_login_button()
	{
		driver.findElement(By.tagName("button")).click();
	}
	

	@Then("User should be able to login successfully")
	public void user_should_be_able_to_login_successfully() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/h6[text()='Dashboard']")));
		String expectedText = "Dashboard";
		String actualText = driver.findElement(By.xpath("//span/h6[text()='Dashboard']")).getText();

		Assert.assertEquals("Login failed with valid credentials.", expectedText, actualText);
	}

	@Then("User should be able to see error message {string}")
	public void user_should_be_able_to_see_error_message(String string) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Invalid credentials']")));
		String expectedText = "Invalid credentials";
		String actualText = driver.findElement(By.xpath("//p[text()='Invalid credentials']")).getText();

		Assert.assertEquals("Error: Login passed with valid credentials", expectedText, actualText);
	}
}
