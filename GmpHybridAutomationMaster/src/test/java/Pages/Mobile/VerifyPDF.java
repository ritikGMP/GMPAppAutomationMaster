package Pages.Mobile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.asserts.SoftAssert;




public class VerifyPDF {
	
	
	
	public void pullSDCardFolder()
	{   System.out.println("pulling SDCard");
		String command1="adb pull sdcard";
		try{Process p = Runtime.getRuntime().exec(command1);}catch(Exception e) {System.out.println(e.toString());}
		
	}
	
	public String SendPDF(String PDFName) throws IOException, InterruptedException
	{   pullSDCardFolder();
	    Thread.sleep(5000);
		URL url =new URL("file:////home/ritik/eclipse-workspace/GmpHybridAutomationMaster/sdcard/Download/"+PDFName);
		InputStream is=url.openStream();
		BufferedInputStream fileParse=new BufferedInputStream(is);
		PDDocument document=null;
		
		document=PDDocument.load(fileParse);
		String pdfContent=new PDFTextStripper().getText(document);
		System.out.println(pdfContent);
		
		return pdfContent;
	}
	
	
	

	 
}
