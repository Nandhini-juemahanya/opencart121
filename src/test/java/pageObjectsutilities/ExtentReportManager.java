package pageObjectsutilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public  ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt= new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("opencart Automation report"); //title of report
		sparkReporter.config().setReportName("opencart functional testing");  //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OPerating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}	
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS,result.getName()+"got successfully executed");
		
		public void onTestFailure(ITestResult result) {
			
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());//to display groups in report
			
			test.log(Status.FAIL,result.getName()+"got failed");
			test.log(Status.INFO,result.getThrowable().getMessage());
			try {
				String imgPath=new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
				
			}catch(IOException e1) {
				e1.printStackTrace();
			}
	}
        public void onTestskipped(ITestResult result) {
			
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());//to display groups in report
			
			test.log(Status.SKIP,result.getName()+"got skipped");
			test.log(Status.INFO,result.getThrowable().getMessage());
        }
        
        public void onFinish(ITestResult result) {
        	extent.flush();
        	
        	String pathOfExtentReport= System.setProperty("user.dir")+"\\reports\\"+repName;
        	File extentReport=new File(pathofExtentReport);
        	
        	try {
        		Desktop.getDesktop().browse(extentReport.toURI());
        	}catch(IOException e) {
        		e.printStackTrace();
        	}
        }
		/*
		       URL url= new URL(file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		       
		    	   //create the email message
		    	   ImageHtmlEmail email=new ImageHtmlEmail();
		           email.setDataSourceResolver(new DataSourceurlResolver(url));
		           email.SethostName("smtp.googlemail,com");
		           email.SetSmtpPort(465);
		           email.SetAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
		           email.SetSSlOnConnect(true);
		           email.setSSLOnConnect(true);
		           */
				
						
						}
		

}