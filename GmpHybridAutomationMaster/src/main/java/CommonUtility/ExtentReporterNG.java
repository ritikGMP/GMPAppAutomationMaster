/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: Implementation of the reporting that is
	Extent report, used to get the object of ExtendReports 
	class
-------------------------------------------------------------

*/

package CommonUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Implementation for the Extent html Report
 *
 */
public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	/**
     * method to return ExtentReports object
     *
     */
	public static ExtentReports getReportObject(){
	
		String path = System.getProperty("user.dir").toString()+"/Output/Reports/"+"/ExtentReport" + new SimpleDateFormat("yyyy_MM_dd___hh_mm_ss'.html'").format(new Date()).toString();
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		AutomationConfiguration.logInfo("Extent Report File path: "+path);
		reporter.config().setReportName("GMP Automation Result ");
		reporter.config().setDocumentTitle("Automation");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Karan Agarwal");
		return extent;		
	}
}
