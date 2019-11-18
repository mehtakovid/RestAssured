package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailAPITest extends BaseClass {

	/*****
	 * To be run before Each test case to get
	 * the AuthToken to complete the OAUTH2.0 
	 * @throws Exception
	 */
	@BeforeTest
	public void gettingAuthToken() throws Exception {
		BaseClass.constructAuthenticationURL("https://accounts.google.com","/o/oauth2/v2/auth","https://mail.google.com/",
				"https://accounts.google.com/o/oauth2/auth",
				"745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com",
				"code","https://www.getpostman.com/oauth2/callback",
				"empty");
		BaseClass.driverInitialization();
		BaseClass.getCodeThroughBrowserAuthentication();
		BaseClass.driverTearDown();
		BaseClass.getBearerAccessToken();
	}
	
	
	
	/*****
	 * This method tests the below Google API :
	 * https://www.googleapis.com/gmail/v1/users/userId/profile
	 * refer : https://developers.google.com/gmail/api/v1/reference/users/getProfile
	 */
	@Test(priority = 1,enabled=true)
	public void getCurrentUserProfile() {
		GmailAPI TS1 = new GmailAPI();
		TS1.getUserProfile("kovidmehta10@gmail.com",AccessToken);
	}
	
	
	@Test(priority=2,enabled=true)
	public void listUserMessages() {
		GmailAPI TS1 = new GmailAPI();
		TS1.listUserMessages("kovidmehta10@gmail.com",AccessToken);
	}
	
	@Test(priority=3,enabled=true)
	public void getUserMessages() {
		GmailAPI TS1 = new GmailAPI();
		TS1.getUserMessages("kovidmehta10@gmail.com", AccessToken);
	}
	
	@Test(priority=4,enabled=true)
	public void deleteMessageFromGmail() {
		GmailAPI TS1 = new GmailAPI();
		TS1.deletMessageFromGmail("kovidmehta10@gmail.com", AccessToken);
	}
	
	@Test(priority=5,enabled=true)
	public void sendEmailfromGmail() {
		GmailAPI TS1 = new GmailAPI();
		TS1.sendMessageFromGmail("kovidmehta10@gmail.com", "kovidmehta10@gmail.com", "This message is sent from Rest Assured.", AccessToken);
	}
	
	@Test(priority=6,enabled=true)
	public void listUserDraftMessage() {
		GmailAPI TS1 = new GmailAPI();
		TS1.listUserDraftMessages("kovidmehta10@gmail.com", AccessToken);
	}
	
	@Test(priority=7,enabled=true)
	public void getDraftMessage() {
		GmailAPI TS1 = new GmailAPI();
		TS1.getDraftFromGmail("kovidmehta10@gmail.com", AccessToken);
	}
	
	@Test(priority=8,enabled=true)
	public void deleteDraftMessage() {
		GmailAPI TS1 = new GmailAPI();
		TS1.deleteDraftFromGmail("kovidmehta10@gmail.com", AccessToken);
	}
	
	@Test(priority=9,enabled=true)
	public void createDraftinGmail() {
		GmailAPI TS1 = new GmailAPI();
		TS1.createDraftinGmail("kovidmehta10@gmail.com", "This is the draft message created from Rest Assured.", AccessToken);
	}
	
}
