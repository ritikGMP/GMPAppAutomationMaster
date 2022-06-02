package Pages.Permit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChooseLocation {
	WebDriver driver;
	
	@FindBy(css="input[placeholder='Search for a location or city...']")
	WebElement locationele;
	
	@FindBy(className="MuiInputBase-input")
	WebElement enter;
	
	public ChooseLocation(WebDriver driver) {
			this.driver = driver;
	}

	public void enterLocation(String location) {
		locationele.sendKeys(location);
		enter.sendKeys(Keys.RETURN);		
	}
}
