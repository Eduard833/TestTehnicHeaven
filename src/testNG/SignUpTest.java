package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.support.ui.Select;

public class SignUpTest {
	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("https://politrip.com/account/sign-up");

	}

	@Test(priority = 1)
	public void EmptyInputTest() throws InterruptedException {

		driver.findElement(By.name("first-name")).sendKeys("", Keys.TAB);
		driver.findElement(By.name("last-name")).sendKeys("", Keys.TAB);
		driver.findElement(By.name("email")).sendKeys("", Keys.TAB);
		driver.findElement(By.id("sign-up-password-input")).sendKeys("", Keys.TAB);
		driver.findElement(By.id("sign-up-confirm-password-input")).sendKeys("", Keys.TAB);
		WebElement dropdown = driver.findElement(By.id("sign-up-heard-input"));
		Select s = new Select(dropdown);
		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void FormatFirstName() throws InterruptedException {
		driver.findElement(By.name("first-name")).sendKeys("1234", Keys.TAB);
		String actErrMsg = driver
				.findElement(By.xpath("//*[@id=\"sign-up-first-name-div\"]/app-form-control-error-message/em/span"))
				.getText();
		String expErrMsg = "Wrong characters or format";
		Assert.assertEquals(actErrMsg, expErrMsg);
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void FormatLastName() throws InterruptedException {
		driver.findElement(By.name("last-name")).sendKeys("1234", Keys.TAB);
		String actErrMsg = driver
				.findElement(By.xpath("//*[@id=\"sign-up-last-name-div\"]/app-form-control-error-message")).getText();
		String expErrMsg = "Wrong characters or format";
		Assert.assertEquals(actErrMsg, expErrMsg);
		Thread.sleep(2000);
	}

	@Test(priority = 4)

	public void EmailFormatTest() throws InterruptedException {
		driver.findElement(By.name("email")).sendKeys("a", Keys.TAB);
		String actualErrorMessage = driver
				.findElement(By.xpath("//*[@id=\"sign-up-email-div\"]/app-form-control-error-message")).getText();
		String expectedErrorMessage = "Please enter a valid email address";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void PasswordFormatTest() throws InterruptedException {

		driver.findElement(By.id("sign-up-password-input")).sendKeys("A123", Keys.TAB);
		String actErrMsg = driver
				.findElement(By.xpath("//*[@id=\"sign-up-password-div\"]/app-form-control-error-message")).getText();
		String expErrMsg = "Password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit";
		Assert.assertEquals(actErrMsg, expErrMsg);
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void PasswordConfirmFormatTest() throws InterruptedException {

		driver.findElement(By.id("sign-up-password-input")).sendKeys("A123", Keys.TAB);
		driver.findElement(By.id("sign-up-confirm-password-input")).sendKeys("A123456a", Keys.TAB);
		String actErrMsg = driver
				.findElement(By.xpath("//*[@id=\"sign-up-confirm-password-div\"]/app-form-control-error-message"))
				.getText();
		String expErrMsg = "Passwords must match";
		Assert.assertEquals(actErrMsg, expErrMsg);
		Thread.sleep(2000);
	}

	@Test(priority = 7)
	public void PasswordFieldTest() throws InterruptedException {
		
		WebElement password = driver.findElement(By.id("sign-up-password-input"));

		if (password.getAttribute("type") == "password") {
			System.out.println("Password is masked");
		} else {
			System.out.println("Password is text");
		}
		Thread.sleep(2000);
	}

	@Test(priority = 8)
	public void ValidRegistrationTest() throws InterruptedException {

		driver.findElement(By.name("first-name")).sendKeys("Ion", Keys.TAB);
		driver.findElement(By.name("last-name")).sendKeys("Popescu", Keys.TAB);
		driver.findElement(By.name("email")).sendKeys("IonPopescu@gmail.com", Keys.TAB);
		driver.findElement(By.id("sign-up-password-input")).sendKeys("A123456a", Keys.TAB);
		driver.findElement(By.id("sign-up-confirm-password-input")).sendKeys("A123456a", Keys.TAB);
		WebElement dropdown = driver.findElement(By.id("sign-up-heard-input"));
		Select s = new Select(dropdown);
		s.selectByVisibleText("Web-Search");
		WebElement elem = driver.findElement(By.xpath("//*[@id=\" qa_loader-button\"]"));

		elem.click();
		String expectedURL = "https://politrip.com/account/sign-up/type-select";
		String actualURL = driver.getCurrentUrl();
		Assert.assertNotEquals(actualURL, expectedURL);
		Thread.sleep(2000);

	}
	
	
	@Test(priority=9)
	public void TestLogInLink() throws InterruptedException{
		driver.findElement(By.partialLinkText("Log")).click();
		Thread.sleep(2000);

	}
	
	@AfterMethod
	public void CloseBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();

	}

}
