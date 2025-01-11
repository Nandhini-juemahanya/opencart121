package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyaccountPage;

public class Tc003LoginDDT  extends BaseClass{
	
	@Test(dataProvider="LoginData,dataProviderClass=DataProviders.class,groups="Datadriven")  //getting data provider from different package
	public void verifyloginDDT(String email,String pwd,String exp) throws InterruptedException
	{
		logger.info("****starting Tc003 loginDDT****");
		
		
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
		
		
		/*Data is valid- login success-test pass- logout
		 *               login failed-test fail
		 *
		 *Data is invalid- login success-test fail- logout
		 *               login failed-test pass
		 *                          
		 */
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	}catch(Exception e)
	{
		
	  Assert.fail();
	}
	logger.info("Finished Tc003 login***");

}
}
