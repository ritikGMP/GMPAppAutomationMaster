package Pages.Permit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Details {
	WebDriver driver;
	
	@FindBy(name="phoneNumber")
	WebElement number;
	
	@FindBy(name="addressFirstLine")
	WebElement addressel;
	
	@FindBy(name="city")
	WebElement area;
	
	@FindBy(name="state")
	WebElement statel;
	
	@FindBy(name="country")
	WebElement nation;
	
	@FindBy(name="zipCode")
	WebElement code;
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	WebElement save;
		
	public Details(WebDriver driver) {
	this.driver=driver;
	} 
	public void setContactnumber(String contact) {
		number.sendKeys(contact);
	}
	
	public void setAddress(String address) {
		addressel.sendKeys(address);
	}
	
	public void setCity(String city) {
		area.sendKeys(city);
	}
	
	public void setState(String state) {
		statel.sendKeys(state);
	}
	public void setCountry(String country) {
		nation.sendKeys(country);
	}
	
	public void setZipcode(String zipcode) {
		code.sendKeys(zipcode);
		save.click();
	}
}
