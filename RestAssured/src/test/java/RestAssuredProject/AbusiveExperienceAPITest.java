package RestAssuredProject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AbusiveExperienceAPITest {
	
	
	/*****Views Abusive Experience Report data, and 
	 * gets a list of sites that have a significant 
	 * number of abusive experiences.
	 *****
	 */
	
	@DataProvider
	public Object[][] AbusiveStatusTestData(){
		Object[][] data = new Object[1][1];
		data[0][0] = "amazon";
		return data;
	}
	
	@Test(dataProvider="AbusiveStatusTestData")
	public void AbusiveStatusTestRun(String Website) {
		AbusiveExperienceAPI apiTest = new AbusiveExperienceAPI();
		apiTest.getAbusiveStatus(Website);
	}
	
	@Test
	public void ViolatingSitesTestRun() {
		AbusiveExperienceAPI apiTest = new AbusiveExperienceAPI();
		apiTest.getViolatingSites();
	}

}
