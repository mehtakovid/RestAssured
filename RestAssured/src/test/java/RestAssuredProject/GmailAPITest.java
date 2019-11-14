package RestAssuredProject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GmailAPITest extends BaseClass {

	/*****
	 * To be run before Each test case to get
	 * the AuthToken to complete the OAUTH2.0 
	 * @throws Exception
	 */
	@BeforeMethod
	public void gettingAuthToken() throws Exception {
		BaseClass.constructAuthenticationURL("https://accounts.google.com","/o/oauth2/v2/auth","https://mail.google.com/",
				"https://accounts.google.com/o/oauth2/auth",
				"745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com",
				"code","https://www.getpostman.com/oauth2/callback",
				"empty");
		BaseClass.driverInitialization();
		BaseClass.getCodeThroughBrowserAuthentication("YourUsername", "yourpassword");
		BaseClass.driverTearDown();
		BaseClass.getBearerAccessToken();
	}
	
	
	
	/*****
	 * This method tests the below Google API :
	 * https://www.googleapis.com/gmail/v1/users/userId/profile
	 * refer : https://developers.google.com/gmail/api/v1/reference/users/getProfile
	 * @throws Exception
	 */
	@Test(enabled=true)
	public void getCode() throws Exception {
		GmailAPI TS1 = new GmailAPI();
		TS1.getUserProfile("kovidmehta10@gmail.com",AccessToken);
	}
	
	
	
}
