package Pages.Permit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendForApproval {
	WebDriver driver;
	
	@FindBy(xpath="//p[contains(text(),'PERMIT_K')]")
	WebElement permit;
	
	@FindBy(xpath="//p[contains(text(),'Continue')]")
	WebElement continuele;
	
	@FindBy(xpath="//span[contains(text(),'Agree')]")
	WebElement agree;
	
	@FindBy(xpath="//span[contains(text(),'Send for approval')]")
	WebElement approval;
	
	@FindBy(xpath="//span[contains(text(),'Go to My Permits')]")
	WebElement mypermits;
	
	
	public SendForApproval(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectPermit() {
		permit.click();
	}
	
	public void clickContinue() {
		continuele.click();
		agree.click();
	}
	
	public void sendForApproval() {
		  approval.click();
	}
	public void myPermits() {
		mypermits.click();		
	}
}