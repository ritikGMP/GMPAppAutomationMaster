/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: Common methods for the Excel file
	reading purpose, for the test data. 
	
-------------------------------------------------------------

*/

package DataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import CommonUtility.AutomationConfiguration;

/**
 * Implementaion of the Apache POI API for the test data read from excel file.
 *
 */

public class ExcelDriven {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	/**
     * method to read excel file
     *
     *@param path to take the excel file to read
     *@param sheetName to read the particular sheet
     */
	public static void readExcelFile(String path, String sheetName) throws Exception{
		try {
			FileInputStream file = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(file);
			int countofsheet = workbook.getNumberOfSheets();
	        ArrayList<String> sheetname=new ArrayList<String>();
	        for(int i=0;i<countofsheet;i++){
	    	   sheetname.add(workbook.getSheetName(i));
	    	}
	        if(sheetname.contains(sheetName)){
	     	  sheet= workbook.getSheet(sheetName);
	        }else {
	        	AutomationConfiguration.logInfo("Sheet Name not fount"); 	   
	        }
		}catch(Exception e){
			AutomationConfiguration.logInfo("Error in reading excel file."+ e.toString());
		}
	}
		
	/**
     * method to read the actual data from excel
     *
     *@param columnname to define take data from which column
     *@param rowindex to define the index of the data
     */
	public static String readData( String columnname,int  rowindex){		 
       if(sheet!= null){
    	  XSSFRow row= sheet.getRow(0);
    	  
    	  Map<String,Integer> headings=new HashMap<String,Integer>();
    	  ArrayList<String> headingarray=new ArrayList<String>();
    	  for(int i=0;i<row.getLastCellNum();i++){
    		  headings.put(row.getCell(i).getStringCellValue().toString(), i);
    		  headingarray.add(row.getCell(i).getStringCellValue().toString());	  
    	  }
    	  if(headingarray.contains(columnname)){
    		  XSSFRow mainrow=sheet.getRow(rowindex);
    		  String actualdata= mainrow.getCell(headings.get(columnname)).getStringCellValue().toString();
    		  return actualdata;
    	  }else {
    		  AutomationConfiguration.logInfo("Column Name not found: "+columnname);
    		  return "";
    	  }  
       }else{
    	   AutomationConfiguration.logInfo("Sheet not found.");
 		   return "";
 	  }
 	}
	
	public static String readDataRowandColumn(String sheetname, String columnname, String rowname){     
       if(sheet!= null){
    	  XSSFRow row= sheet.getRow(0);
    	  
    	  Map<String,Integer> headings=new HashMap<String,Integer>();
    	  ArrayList<String> headingarray=new ArrayList<String>();
    	  for(int i=0;i<row.getLastCellNum();i++){
    		  headings.put(row.getCell(i).getStringCellValue().toString(), i);
    		  headingarray.add(row.getCell(i).getStringCellValue().toString());    		  
    	  }
    	  if(headingarray.contains(columnname)){
    		  XSSFRow mainrow=getRowWithName(sheetname,rowname);
    		  String actualdata= mainrow.getCell(headings.get(columnname)).getStringCellValue().toString();
    		  return actualdata;
    	  }else{
    		  AutomationConfiguration.logInfo("Column Name not found: "+ columnname);
    		  return "";
    	  }    	  
       }else{
    	   AutomationConfiguration.logInfo("Sheet not found: "+ sheetname);
 		   return "";
 	  }        
	}
	
	/**
     * method to get the entire column
     *
     *@param sheetName to define the sheetname
     *@param columnname to define take data from which column
     */
	public static ArrayList<String> getColumn(String sheetName,String columnname){
		ArrayList<String> columncontent=new ArrayList<String>();
		int countofsheet = workbook.getNumberOfSheets();
        ArrayList<String> sheetname=new ArrayList<String>();
        for(int i=0;i<countofsheet;i++){
    	   sheetname.add(workbook.getSheetName(i));
    	}
       
       if(sheetname.contains(sheetName)){
    	  XSSFSheet sheet= workbook.getSheet(sheetName);
    	  XSSFRow row= sheet.getRow(0);
    	  
    	  Map<String,Integer> headings=new HashMap<String,Integer>();
    	  ArrayList<String> headingarray=new ArrayList<String>();
    	  for(int i=0;i<row.getLastCellNum();i++){
    		  headings.put(row.getCell(i).getStringCellValue().toString(), i);
    		  headingarray.add(row.getCell(i).getStringCellValue().toString());  		  
    	  }
    	  if(headingarray.contains(columnname)){
    		  for(int i=0;i<row.getLastCellNum();i++){
	    		  XSSFRow mainrow=sheet.getRow(i);
	    		  String actualdata= mainrow.getCell(headings.get(columnname)).getStringCellValue().toString();
	    		  columncontent.add(actualdata);
    		  }
    	  }else {
    		  AutomationConfiguration.logInfo("Column Name not found. "+ columnname);  
    	  }
       }else {
    	   AutomationConfiguration.logInfo("Sheet Name is invalid: "+ sheetName);
       }
		return columncontent;
	}
	
	
	/**
     * method to get the row column
     *
     *@param sheetName to define the sheetname
     *@param indexOfRow to define the index of particular row
     */
	public static ArrayList<String> getRow(String sheetName,int indexOfRow){
		 ArrayList<String> rowcontent=new ArrayList<String>();  
		 int countofsheet = workbook.getNumberOfSheets();
	     ArrayList<String> sheetname=new ArrayList<String>();
	     for(int i=0;i<countofsheet;i++){
	    	   sheetname.add(workbook.getSheetName(i));
	     }
	     if(sheetname.contains(sheetName)){ 
	    	 try{
		    	  XSSFSheet sheet= workbook.getSheet(sheetName);
		    	  XSSFRow row= sheet.getRow(indexOfRow);
		    	  for(int i=0;i<row.getLastCellNum();i++){
		    		  rowcontent.add(row.getCell(i).getStringCellValue().toString());
		    	  }
	    	 }catch(Exception e){
	    		 AutomationConfiguration.logInfo("Row not found: "+e.toString());
	    	 }
	      }else{
	    	 AutomationConfiguration.logInfo("Sheet name not found: "+ sheetName);
	     }
	     return rowcontent;
		
	}
	
	public static XSSFRow getRowWithName(String sheetName,String rowname){
		 XSSFRow mainrow = null;
		 int countofsheet = workbook.getNumberOfSheets();
	     ArrayList<String> sheetname=new ArrayList<String>();
	     for(int i=0;i<countofsheet;i++){
	    	   sheetname.add(workbook.getSheetName(i));
	     }
	     if(sheetname.contains(sheetName)){ 
	    	 try{
		    	  XSSFSheet sheet= workbook.getSheet(sheetName);
		    	 
		    	  int rowno = sheet.getLastRowNum();
		    	  for(int i=0;i<=rowno; i++){
		    		  XSSFRow row= sheet.getRow(i);
		    		  if(row.getCell(0).getStringCellValue().toString().equals(rowname)){
		    			  mainrow=row;
		    		  }	    		  
		    	  }		    	  
	    	 }catch(Exception e){
	    		 AutomationConfiguration.logInfo("Row not found: "+rowname+": "+e.toString());
	    	 }
	      }else{
	    	 AutomationConfiguration.logInfo("Sheet name not found: "+ sheetName);
	     }
	     return mainrow;		
	}	
}
