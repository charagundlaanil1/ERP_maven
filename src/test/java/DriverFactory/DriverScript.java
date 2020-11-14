package DriverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	String inputpath="C:\\Users\\Amar Ch\\Desktop\\ankesh\\ERP_Maven\\Testinputs\\InputSheet.xlsx";
	String outputpath="C:\\Users\\Amar Ch\\Desktop\\ankesh\\ERP_Maven\\Testoutputs\\hydrid.xlsx";
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	public void startTest()throws Throwable
	{
		////creating excel object to access excel utilities
		ExcelFileUtil xl= new ExcelFileUtil(inputpath);
		//iterate all rows in Mastertestcases sheet
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
		{
			String moduleStatus="";	
			if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				//store testcases in TCModule
				String TCModule=xl.getCellData("MasterTestCases", i, 1);
				//define path of extetnts Reports
report=new ExtentReports("./ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html");				
				//iterate all rows in TCModule
				for(int j=1; j<=xl.rowCount(TCModule);j++)
				{
					test=report.startTest(TCModule);
					//read all cells from TCModule
					String Description=xl.getCellData(TCModule, j, 0);
					String Functionname=xl.getCellData(TCModule, j, 1);
					String Locator_Type=xl.getCellData(TCModule, j, 2);
					String Locator_Value=xl.getCellData(TCModule, j, 3);
					String Test_Data=xl.getCellData(TCModule, j, 4);
					try {
						//call methods
						if(Functionname.equalsIgnoreCase("startBrowser"))	
						{
							driver=FunctionLibrary.startBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("stableValidation"))
						{
							FunctionLibrary.stableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("stockCategories"))
						{
							FunctionLibrary.stockCategories(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Functionname.equalsIgnoreCase("sttableValidation"))
						{
							FunctionLibrary.sttableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						//write as pass into status cell in TCModule
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						test.log(LogStatus.PASS, Description);
						moduleStatus="true";

					}catch(Exception e)
					{
						System.out.println(e.getMessage());
						//write as fail into status cell in TCModule
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						test.log(LogStatus.FAIL, Description);
						moduleStatus="false";
					}
					if(moduleStatus.equalsIgnoreCase("true"))
					{
						//write as pass into mastertestcases
						xl.setCellData("MasterTestCases", i, 3, "Pass", outputpath);	
					}
					if(moduleStatus.equalsIgnoreCase("False"))
					{
						xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);	
					}
					report.endTest(test);
					report.flush();
				}
			}
			else
			{
				//write as blocked into status cell in MasterTestCases sheet
				xl.setCellData("MasterTestCases", i, 3, "Blocked", outputpath);
			}
		}
	}

}
