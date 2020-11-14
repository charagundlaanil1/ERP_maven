package CommonFunctionLibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Customerpage {
    WebDriver driver;
    WebDriverWait wait;
    public Customerpage(WebDriver driver){
    	
    	this.driver=driver;
    }	
    @FindBy(linkText="Customers")	
    WebElement clickCustomers;  
    @FindBy(xpath="//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")	
    WebElement clickaddicon;
    @FindBy(name="x_Customer_Number")	
    WebElement Customernumber;
    @FindBy(name="x_Customer_Name")	
    WebElement Customername;
    @FindBy(name="x_Address")	
    WebElement address ;
    @FindBy(name="x_City")	
    WebElement city ;
    @FindBy(name="x_Country")	
    WebElement country ;
    @FindBy(name="x_Contact_Person")	
    WebElement contactperson ;
    @FindBy(name="x_Phone_Number")	
    WebElement phonenumber ;
    @FindBy(name="x__Email")	
    WebElement email ;
    @FindBy(name="x_Mobile_Number")	
    WebElement mobilenumber ;
    @FindBy(name="x_Notes")	
    WebElement notes ;
    @FindBy(name="btnAction")
    WebElement clickaddbtn;
    @FindBy(xpath="//button[contains(text(),'OK!')]")
    WebElement clickokconfirnbtn;
    @FindBy(xpath="//body/div[17]/div[2]/div[1]/div[4]/div[2]/button[1]")
    WebElement clickokalertbtn;
    @FindBy(xpath="//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")
    WebElement clicksearchpanel; 
    @FindBy(xpath="psearch]")
    WebElement clicksearchtextbox ;
    @FindBy(xpath="btnsubmit")
    WebElement clicksearchbtn;
    @FindBy(xpath="///form[@id='fa_customerslist']")
    WebElement ctable;
    public void verifyCustomerpage(String Cname,String address,String city,String country,String contperson,String email,String mnumber,String notes){
    	
    	wait =new WebDriverWait(driver,30);
    	wait.until(ExpectedConditions.elementToBeClickable(clickCustomers));
    	this.clickCustomers.click();
    	wait.until(ExpectedConditions.visibilityOf(clickaddicon));
    	this.clickaddicon.click();
    	wait.until(ExpectedConditions.visibilityOf(Customernumber));
        String Expectedcnumber=this.Customernumber.getAttribute("value");
        this.Customername.sendKeys(Cname);
         this.address.sendKeys(address);
        this.city.sendKeys(city);    
        this.country.sendKeys(country);
        this.contactperson.sendKeys(contperson);;
        this.email.sendKeys(email);
        this.mobilenumber.sendKeys(mnumber);
        this.notes.sendKeys(notes);  
        this.clickaddbtn.sendKeys(Keys.ENTER); 
        wait.until(ExpectedConditions.elementToBeClickable(clickokconfirnbtn));
        this.clickokconfirnbtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(clickokalertbtn));
        this.clickokalertbtn.click();
       if(!clicksearchtextbox.isDisplayed())
    	   this.clicksearchpanel.click();
       wait.until(ExpectedConditions.visibilityOf(clicksearchtextbox));
       this.clicksearchtextbox.clear();
       this.clicksearchtextbox.sendKeys(Expectedcnumber);
       this.clicksearchbtn.click();
       wait.until(ExpectedConditions.visibilityOf(ctable));
       List<WebElement> rows=ctable.findElements(By.tagName("tr"));
       System.out.println("No of rows are::"+rows.size());
    String actualcnumber=driver.findElement(By.xpath("//span[@id='el1_a_customers_Customer_Number']/span")).getText();
    Assert.assertEquals(actualcnumber, Expectedcnumber,"Supplier number Not Matching with table");
    System.out.println(actualcnumber +  "   "+ Expectedcnumber);
    
    }
}
