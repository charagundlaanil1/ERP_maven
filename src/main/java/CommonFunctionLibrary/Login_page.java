package CommonFunctionLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_page {
 WebDriver driver;
	public Login_page(WebDriver driver){
	 this.driver=driver;
	}
	@FindBy(name="username")
	WebElement username;
	@FindBy(name="password")
	WebElement password;
	@FindBy(name="btnsubmit")
	WebElement clicklogin;	
	@FindBy(name="btnreset")
	WebElement clickresetbtn;
	public void verifylogin(String username,String password){
		this.clickresetbtn.click();;
		this.username.sendKeys("admin");
	    this.password.sendKeys("master");
        this.clicklogin.click();	
	}
}
