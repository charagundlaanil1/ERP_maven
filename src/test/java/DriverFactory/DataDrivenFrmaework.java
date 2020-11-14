package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.Login_page;
import CommonFunctionLibrary.LogoutPage;
import CommonFunctionLibrary.Suppilerpage;
import Utilities.ExcelFileUtil;

public class DataDrivenFrmaework {
 WebDriver driver;
 File screen;
 ExtentReports report;
 ExtentTest test;
 String inputpath="C:\\Users\\Amar Ch\\Desktop\\ankesh\\ERP_Maven\\Testinputs\\TestData.xlsx";
String outputpath="C:\\Users\\Amar Ch\\Desktop\\ankesh\\ERP_Maven\\Testoutputs\\DataDriven.xlsx";
@BeforeTest
public void setup(){
	report =new ExtentReports("./ExtentReports/Datadriven.html");
   System.setProperty("webdriver.gecko.driver", "C:\\Users\\Amar Ch\\Desktop\\ankesh\\ERP_Maven\\Drivers\\geckodriver.exe");
   driver=new FirefoxDriver();
    driver.get("http://projects.qedgetech.com/webapp");
    driver.manage().window().maximize();
    Login_page login=PageFactory.initElements(driver, Login_page.class);
    login.verifylogin("admin", "master");
}
@Test
public void verfiysupplier() throws Throwable{
	Suppilerpage addsuppiler=PageFactory.initElements(driver, Suppilerpage.class);
	ExcelFileUtil	xl= new ExcelFileUtil(inputpath);
	//count no of rows in a sheet
	int rc=xl.rowCount("supplier");
	//count no of cells in a row
	int cc=xl.CellCount("supplier");
	Reporter.log("No of rows::"+rc+"   "+"No of cells in first row::"+cc,true);
	for(int i=1; i<=rc; i++)
	{
	test=report.startTest("SupplierCreation");
	test.assignAuthor("Ranga");
	test.assignCategory("DataDriven");
	String suppliername=xl.getCellData("supplier", i, 0);
	String address=xl.getCellData("supplier", i, 1);
	String city=xl.getCellData("supplier", i, 2);
	String country=xl.getCellData("supplier", i, 3);
	String contactperson=xl.getCellData("supplier", i, 4);
	String phonenumber=xl.getCellData("supplier", i, 5);
	String email=xl.getCellData("supplier", i, 6);
	String mobilenumber=xl.getCellData("supplier", i, 7);
	String notes=xl.getCellData("supplier", i, 8);
	addsuppiler.verifyAddsupplier(suppliername, address, city, country, contactperson, phonenumber, email, mobilenumber	, notes);
	String expected="supplierslist";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected))
	{
	Reporter.log("Supplier added Success",true);
	xl.setCellData("supplier", i, 9, "Pass", outputpath);
	test.log(LogStatus.PASS, "Supplier added Success");
	}
	else
	{
	//take screen shot
	screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screen, new File("./Screens/Iteration/"+i+"supplier.png"));
	Reporter.log("Supplier added Fail",true);
	xl.setCellData("supplier", i, 9, "Fail", outputpath);
	test.log(LogStatus.FAIL, "Supplier added Fail");
	}
	report.endTest(test);
	report.flush();
	}
	}
	@AfterTest
	public void tearDown()
	{
		LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
		logout.verifyLogout();
		driver.close();
	}	
}







