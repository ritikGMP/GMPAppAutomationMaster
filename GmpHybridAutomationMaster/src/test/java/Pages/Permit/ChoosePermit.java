package Pages.Permit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChoosePermit {
	WebDriver driver;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div[4]/div/div[2]/div[2]/div/button")
	WebElement permit;
	
	public ChoosePermit(WebDriver driver){
		this.driver = driver;		
	}
		
	public void choosePermit() {
		permit.click();     
	}
}