package RestAssuredProject;

import org.testng.annotations.Test;

public class AbusiveExperienceAPITest {
	
	@Test
	public void AbusiveStatusTestRun() {
		AbusiveExperienceAPI apiTest = new AbusiveExperienceAPI();
		apiTest.getAbusiveStatus();
	}
	
	@Test
	public void ViolatingSitesTestRun() {
		AbusiveExperienceAPI apiTest = new AbusiveExperienceAPI();
		apiTest.getViolatingSites();
	}

}
