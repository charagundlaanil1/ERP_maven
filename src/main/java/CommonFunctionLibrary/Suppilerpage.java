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

public class Suppilerpage {
	WebDriver driver;
	WebDriverWait wait;
	public Suppilerpage(WebDriver driver){
		this.driver=driver;
	}
	@FindBy(linkText="Suppliers")
	WebElement Clicksuppiler;
	@FindBy(xpath="//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")
    WebElement ClickAddIcon;
    @FindBy(name="x_Supplier_Number")
    WebElement suppliernumber;
    @FindBy(name="x_Supplier_Name")
    WebElement suppliername;
    @FindBy(name="x_Address")
    WebElement address;
    @FindBy(name="x_City")
    WebElement city;
    @FindBy(name="x_Country")
    WebElement country;
    @FindBy(name="x_Contact_Person")
    WebElement contactperson;
    @FindBy(name="x_Phone_Number")
    WebElement phonenumber;
    @FindBy(name="x__Email")
    WebElement email;
    @FindBy(name="x_Mobile_Number")
    WebElement mobilenumber;
    @FindBy(name="x_Notes")
    WebElement notes;
    @FindBy(name="btnAction")
    WebElement clickaddbtn;
    @FindBy(xpath="//button[contains(text(),'OK!')]")
    WebElement clickokcnfirnbtn;
    @FindBy(xpath="//body/div[17]/div[2]/div[1]/div[4]/div[2]/button[1]")
    WebElement clickAlertokbtn;
    @FindBy(xpath="//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")
    WebElement clicksearchpanel; 
    @FindBy(name="psearch")
    WebElement clicksearchtextbox ;
    @FindBy(name="btnsubmit")
    WebElement clicksearchbtn;
    @FindBy(xpath="//table[@id='tbl_a_supplierslist']")
    WebElement stable;
    public void verifyAddsupplier(String sname,String address,String city,String country,
    		String cperson,String pnumber,String email,String mnumber,String notes ){
    wait =new WebDriverWait(driver,30);	
    wait.until(ExpectedConditions.elementToBeClickable(Clicksuppiler))	;
    this.Clicksuppiler.click();	
    wait.until(ExpectedConditions.visibilityOf(ClickAddIcon));
    this.ClickAddIcon.click();
    wait.until(ExpectedConditions.visibilityOf(suppliernumber));
    String Expectedsnumber=this.suppliernumber.getAttribute("value"); 
    this.suppliername.sendKeys(sname);
    this.address.sendKeys(address);
    this.city.sendKeys(city);
    this.country.sendKeys(country);	
    this.contactperson.sendKeys(cperson);;	
    this.phonenumber.sendKeys(pnumber);	
    this.email.sendKeys(email);
    this.mobilenumber.sendKeys(mnumber);
    this.notes.sendKeys(notes);
    this.clickaddbtn.sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.elementToBeClickable(clickokcnfirnbtn));
    this.clickokcnfirnbtn.click();
    wait.until(ExpectedConditions.elementToBeClickable(clickAlertokbtn));
    this.clickAlertokbtn.click();
   if(!clicksearchtextbox.isDisplayed())
	   this.clicksearchpanel.click();
   wait.until(ExpectedConditions.visibilityOf(clicksearchtextbox));
   this.clicksearchtextbox.clear();
   this.clicksearchtextbox.sendKeys(Expectedsnumber);
   this.clicksearchbtn.click();
   wait.until(ExpectedConditions.visibilityOf(stable));
   List<WebElement> rows=stable.findElements(By.tagName("tr"));
   System.out.println("No of rows are::"+rows.size());
   String actualsnumber=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr/td[6]/div/span/span")).getText();
   Assert.assertEquals(actualsnumber, Expectedsnumber,"Supplier number Not Matching with table");
   System.out.println(actualsnumber +  "   "+ Expectedsnumber);
    
    }
}
