package Pages.Permit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id="email")
	WebElement emailele;
	
	@FindBy(id="password")
	WebElement passwordele;
	
	@FindBy(className="MuiButton-label")
	WebElement login;
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
	}
	public void setUserCredentials(String email,String password ) {	
		emailele.sendKeys(email);
		passwordele.sendKeys(password);
		
	}
	public void clickLogin() {
		login.click();
	}
	public boolean checklogin() {
		String expectedUrl = "https://global-qa-copy1.di8ln77nkrd5v.amplifyapp.com/app/v2";
		try {
			 WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("headingTextSmall")));
			Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,"Login Failed");
		}catch(Throwable err) {
			return false;
		}
		return true;	
	}
}