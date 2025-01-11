package testCases;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;

public class Tc001AccountRegistrationTest extends BaseClass {

	
	
    @Test(groups= {"Regression","Master"})
	public void verify_account_registration()
	{
    	
    	logger.info("*****starting TC001-accounting registratin test****");
    	
    	try
    	{
		Homepage hp=new Homepage(driver);
		hp.clickmyaccount();
		logger.info("clicke on myaccount link");
		
		hp.clickregister();
		logger.info("clicke on Register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("providing customer details");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmpassword(password);
		
		regpage.setPrivacypolicy();
		regpage.clickContinue();
		
		logger.info("validating expected message");
		String confmsg=regpage.getconfirmationMsg();
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
    	}
    	catch(Exception e)
    	{
    		logger.error("test failed...");
    		logger.error("debug logs...");
    		Assert.fail();
    	}
    	
    	logger.info("****Finished  TC001 Account Registration Ttest****");
    	
    	
	}
    
	
	
	
	
	
	
	
	
	
	
	
}
