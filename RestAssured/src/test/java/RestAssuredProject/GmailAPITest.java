package RestAssuredProject;

import org.testng.annotations.Test;

public class GmailAPITest extends BaseClass {

//	@Test(enabled=false)
//	public void OauthRun() {
//		GmailAPI TS1 = new GmailAPI();
//		TS1.oAuthToken();
//	}
	
	@Test(enabled=false)
	public void OauthRun2() {
		GmailAPI TS1 = new GmailAPI();
		TS1.getUserProfile("me");
	}
	
	
	@Test(enabled=true)
	public void getCode() throws Exception {
		GmailAPI TS1 = new GmailAPI();
		TS1.constructAuthURL("https://accounts.google.com","/o/oauth2/v2/auth","https://mail.google.com/",
				"https://accounts.google.com/o/oauth2/auth",
				"745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com",
				"code","https://www.getpostman.com/oauth2/callback",
				"empty");
		BaseClass.driverInitialization();
		TS1.getCodeFromURLthroughBrowser(driver, wait, "kovidmehta10@gmail.com", "newhoneybees@93");
		TS1.getToken();
		TS1.getUserProfile("kovidmehta10@gmail.com");
	}
	
	
	
}
