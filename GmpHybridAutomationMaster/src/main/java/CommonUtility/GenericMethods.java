/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: Common methods for the both selenium
	and appium, that is to perform the operation on mobile
	app using appium and on web app using selenium.
-------------------------------------------------------------

*/

package CommonUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

/**
 * Common methods for the selenium and appium

 */
public class GenericMethods {
	
	public GenericMethods(WebDriver driver) {
    }
	
	/**
     * method to takescreenshot and return the path of the image file
     *
     *@param driver to take the driver from where to take screenshot
     */
	public static String takeScreenShotFortheException(WebDriver driver) throws IOException {
        Date date = new Date();
        long time = date.getTime();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currenDir = System.getProperty("user.dir" + "/Output/Screenshot/screenshot" + time + ".png");
        FileUtils.copyFile(scrFile, new File(currenDir));
        return currenDir;
    }

	/**
     * method to define the explicit wait
     *
     *@param driver to define the driver
     *@param element to apply the explicit wait
     */
    public static void explicitWait(WebDriver driver, By targetElement,int timeOut){
    	try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
        }catch (TimeoutException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    
    public static void waitAndClick(WebDriver driver, WebElement targetElement,int timeOut) {
    	try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(targetElement));
            if(targetElement.isDisplayed()){
            	targetElement.click();
	  	    }
    	}catch (TimeoutException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public static void waitAndSendkeys(WebDriver driver, WebElement targetElement,String input,int timeOut){
    	try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(targetElement));
            if(targetElement.isDisplayed()){
            	targetElement.sendKeys(input);
	  	    }else{
            	//fail
            }
    	}catch (TimeoutException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public static void explicitWaitForWebElement(WebDriver driver, WebElement targetElement,int timeOut) {
    	try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(targetElement));
            if(targetElement.isDisplayed()){
            	targetElement.click();
	  	    }
    	}catch (TimeoutException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public static void sendKeys(WebDriver driver, WebElement targetElement,String data, int timeOut,boolean ismandetory, String onFailureMessage){
    	try{
    		WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(targetElement));
            Thread.sleep(2000);
            targetElement.clear();
            targetElement.sendKeys(data);    
    	}catch(Exception e){
    		if(ismandetory){
    			AutomationConfiguration.onFail(driver,onFailureMessage + " not found.");
    		}
    	}
    }
     
    public static void click(WebDriver driver, WebElement targetElement,boolean ismandetory, int timeOut, String onFailureMessage){
    	try{
    		WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(targetElement));
            targetElement.click(); 
    	}catch(Exception e){
    		if(ismandetory){
    			AutomationConfiguration.onFail(driver,onFailureMessage+ " not found.");
    		}
    	}
    }
    
    public static Boolean explicitWait(WebDriver driver, WebElement targetElement,int timeout) {
    	try{
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(targetElement));   
            return true;
    	}catch (TimeoutException e){
            return false;
        }  
    }
    
    public static void explicitWaitForWebElementOnly(WebDriver driver, WebElement targetElement,int timeout) {
    	try{
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(targetElement));   
    	}catch (TimeoutException e){
            throw e;
        }  
    }

    public static void valueValidation(String ActualValue, String ExpectedValue) {
		try{
			SoftAssert sa=new SoftAssert();
            sa.assertEquals(ActualValue,ExpectedValue);
			sa.assertAll();
		}catch (AssertionError e) {} 
	}
 
    /**
     * method to wait for an element until it is invisible
     *
     * @param targetElement element to be invisible
     * @return true if element gets invisible else throws TimeoutException
     */
    public boolean waitForInvisibility(WebDriver driver, By targetElement,int timeOut){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
            return true;
        }catch (TimeoutException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }   
}
