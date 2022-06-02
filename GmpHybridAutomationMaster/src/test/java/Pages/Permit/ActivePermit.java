package Pages.Permit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActivePermit {
	WebDriver driver;
	
	@FindBy(xpath="//span[contains(text(),'Active')]")
	WebElement active;
	
	public ActivePermit(WebDriver driver) {
	this.driver=driver;
	}
	
	public void active() {
     	active.click();
	}	
}
