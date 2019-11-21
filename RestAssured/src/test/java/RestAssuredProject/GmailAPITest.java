package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

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
		try {
		logger = extent.startTest("Get Current User Profile.");
		GmailAPI TS1 = new GmailAPI();
		TS1.getUserProfile(Username,AccessToken);
		logger.log(LogStatus.PASS, "Get Current User profile Test Passed.");
		}
		catch(Exception e) {
		logger.log(LogStatus.FAIL, "Get Current User profile Test Failed.");
		}
	}
	
	/****
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/messages/list
	 */
	@Test(priority=2,enabled=true)
	public void listUserMessages() {
		try {
		logger=extent.startTest("List User Messages Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.listUserMessages(Username,AccessToken,"from:greetings@travel.e-redbus.in");
		logger.log(LogStatus.PASS, "List User Messages Test Passed.");;
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "List User Messages Test Passed.");;
		}
	}
	
	/****
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/messages/get
	 */
	@Test(priority=3,enabled=true)
	public void getUserMessages() {
		try {
		logger=extent.startTest("Get User Messages Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.getUserMessages(Username, AccessToken);
		logger.log(LogStatus.PASS, "Get User Messages Test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Get User Messages Test Failed.");
		}
	}
	
	/*****
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/messages/trash
	 */
	@Test(priority=4,enabled=true)
	public void deleteMessageFromGmail() {
		try {
		logger = extent.startTest("Delete Message from Gmail Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.deletMessageFromGmail(Username, AccessToken);
		logger.log(LogStatus.PASS, "Delete Message from Gmail Test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Delete Message from Gmail Test Failed.");	
		}
	}
	
	/*****
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/messages/send
	 */
	@Test(priority=5,enabled=true)
	public void sendEmailfromGmail() {
		try {
		logger = extent.startTest("Send Email From Gmail Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.sendMessageFromGmail(Username, Username, "This message is sent from Rest Assured.", AccessToken);
		logger.log(LogStatus.PASS, "Send Email From Gmail Test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Send Email From Gmail Test Passed.");	
		}
	}
	
	
	/***
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/messages/send
	 */
	@Test(priority=6,enabled=true)
	public void listUserDraftMessage() {
		try {
		logger = extent.startTest("List User Draft Message Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.listUserDraftMessages(Username, AccessToken);
		logger.log(LogStatus.PASS, "List User Draft message test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "List User Draft message test Passed.");	
		}
	}
	
	/***
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/drafts/get
	 */
	@Test(priority=7,enabled=true)
	public void getDraftMessage() {
		try {
		logger=extent.startTest("Get Draft Message Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.getDraftFromGmail(Username, AccessToken);
		logger.log(LogStatus.PASS, "Get Draft Message Test Passed.");
		}
		catch(Exception e) {
		logger.log(LogStatus.FAIL, "Get Draft Message Test Failed.");	
		}
	}
	
	/***
	 * API Docs : https://developers.google.com/gmail/api/v1/reference/users/drafts/delete
	 */
	@Test(priority=8,enabled=true)
	public void deleteDraftMessage() {
		try {
		logger =extent.startTest("Delete Draft Message Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.deleteDraftFromGmail(Username, AccessToken);
		logger.log(LogStatus.PASS, "Delete draft Message Test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Delete draft Message Test Failed.");
		}
	}
	
	/****
	 * API Docs : 
	 */
	@Test(priority=9,enabled=true)
	public void createDraftinGmail() {
		try {
		logger=extent.startTest("Create Draft in Gmail Test.");
		GmailAPI TS1 = new GmailAPI();
		TS1.createDraftinGmail(Username, "This is the draft message created from Rest Assured.", AccessToken);
		logger.log(LogStatus.PASS, "Create Draft in Gmail Test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.PASS, "Create Draft in Gmail Test Failed.");	
		}
	}
	
}
