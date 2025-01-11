package pageObjectsutilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data provider
	
	@DataProvider(name="LoginData")
	public String getData() throws IOException
	{
		String path=".\\testData\\opencart.xlsx"; //taking  xl file from testdata
		
		ExcelUtility xlutil= new ExcelUtility(path);  //creating  an object for xlutility
		
		int totalrows=xlutil.getRowcount("sheet1");
		int totalcols=xlutil.getCellcount("sheet1", 1);
		
		 LoginData=new String[totalrows][totalcols]; //created for two dimensional array which can store
		
		for(int i=1;i<=totalrows;i++) //1  //read the data from xl storing in 2 dimensional array
		{
			for(int j=0;j<totalcols;j++) //0 i is row , j is col
			{
				LoginData[i-1][j]=xlutil.getCellData("sheet1", i, j) //1,0
			}
		}
		
		return LoginData; // returning two dimension array
	}
	
	//Datapovider 2
	
	//Dataprovider 3
}
