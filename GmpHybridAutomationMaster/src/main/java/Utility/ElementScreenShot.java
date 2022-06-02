package Utility;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ElementScreenShot {
 
 
 
 
 public static String elementScreenshot(WebDriver driver,WebElement element)
 {
 
 File screenshotLocation = null;
 try{
 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 
 BufferedImage  fullImg = ImageIO.read(scrFile);
 //Get the location of element on the page
 Point point = element.getLocation();
 //Get width and height of the element
 int eleWidth = element.getSize().getWidth();
 int eleHeight = element.getSize().getHeight();
 //Crop the entire page screenshot to get only element screenshot
 BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,
     eleHeight);
 ImageIO.write(eleScreenshot, "png", scrFile);

  String path = "screenshots/" + UUID.randomUUID() + "" + ".png";
 
 screenshotLocation = new File(System.getProperty("user.dir") + "/" + path);
 FileUtils.copyFile(scrFile, screenshotLocation);
 
      System.out.println(screenshotLocation.toString());
  
  
 } catch (IOException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }
 return screenshotLocation.toString();


  }

}