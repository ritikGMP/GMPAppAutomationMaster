package Pages.Permit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPermit {
	WebDriver driver;
	
	@FindBy(css="button[aria-label='menu']")
	WebElement menu;
	
	@FindBy(xpath="//span[contains(text(),'My Profile')]")
	WebElement profile;
	
	public MyPermit(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	public void mypermits() {
	    menu.click();
		profile.click();
	}	
}
