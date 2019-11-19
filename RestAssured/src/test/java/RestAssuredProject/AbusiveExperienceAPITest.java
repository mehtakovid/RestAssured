package RestAssuredProject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AbusiveExperienceAPITest {
	
	
	/*****Views Abusive Experience Report data, and 
	 * gets a list of sites that have a significant 
	 * number of abusive experiences.
	 *****
	 */
	
	AbusiveExperienceAPI apiTest = new AbusiveExperienceAPI();
	
	@DataProvider
	public Object[][] AbusiveStatusTestData(){
		Object[][] data = new Object[1][1];
		data[0][0] = "amazon";
		return data;
	}
	
	@Test(priority=1,dataProvider="AbusiveStatusTestData")
	public void AbusiveStatusTestRun(String Website) {
		apiTest.getAbusiveStatus(Website);
	}
	
	@Test(priority=1)
	public void ViolatingSitesTestRun() {
		apiTest.getViolatingSites();
	}

}
