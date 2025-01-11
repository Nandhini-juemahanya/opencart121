package testCases;

import pageObjects.LoginPage;
import pageObjects.MyaccountPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;

public class Tc002LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("****Starting Tc_002_ loginTest***");
		try 
		{
		//homepage
		Homepage hp=new Homepage(driver);
		hp.clickmyaccount();
		hp.clickregister();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//myaccountpage
		
		MyaccountPage macc= new MyaccountPage(driver);
		boolean targetPage=macc.isMyaccountPageExists();
		
		//assert.assertEquals(targetPage,true,"Login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*******finished tc002 logintest****");
		
	}

}
