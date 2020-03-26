package RestAssuredProject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class AbusiveExperienceAPITest extends BaseClass {
	
	
	/*****Views Abusive Experience Report data, and 
	 * gets a list of sites that have a significant 
	 * number of abusive experiences
	 *****
	 */
	
	AbusiveExperienceAPI apiTest = new AbusiveExperienceAPI();
	
	@DataProvider
	public Object[][] AbusiveStatusTestData(){
		Object[][] data = new Object[1][1];
		data[0][0] = "amazon";
		return data;
	}
	
	@Test(priority=1,dataProvider="AbusiveStatusTestData",enabled=true)
	public void AbusiveStatusTestRun(String Website) {
		try {
		logger=extent.startTest("Get Abusive Status for Given API");
		apiTest.getAbusiveStatus(Website);
		logger.log(LogStatus.PASS, "Abusive Status API is working as Expected.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Abusive Status API Test Failed.");
		}
	}
	
	@Test(priority=1,enabled=true)
	public void ViolatingSitesTestRun() {
		try {
		logger=extent.startTest("List all the Violating Sites.");
		apiTest.getViolatingSites();
		logger.log(LogStatus.PASS, "List all violating sites Test passed successfully.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "List all violating sites Test failed.");
		}
	}

}
