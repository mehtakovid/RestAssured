package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class CalendarAPITest extends BaseClass {
	
	/**Creating the Instance of the Logic Class*/
	GoogleCalendarAPI TS1 = new GoogleCalendarAPI();
	
	/*****
	 *Generates the Bearer Token using Oauth2.0
	 * @throws Exception
	 */
	@BeforeTest
	public void gettingAuthToken() throws Exception {
		BaseClass.constructAuthenticationURL("https://accounts.google.com","/o/oauth2/v2/auth","https://www.googleapis.com/auth/calendar",
				"https://accounts.google.com/o/oauth2/auth",
				"745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com",
				"code","https://www.getpostman.com/oauth2/callback",
				"empty");
		BaseClass.driverInitialization();
		BaseClass.getCodeThroughBrowserAuthentication();
		BaseClass.driverTearDown();
		BaseClass.getBearerAccessToken();
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/calendarList/get
	 */
	@Test(priority=1,enabled=true)
	public void getCalendarListTest() {
		try {
		logger = extent.startTest("Get Calendar List Test.");
		TS1.getCalendarList(AccessToken,Username);
		logger.log(LogStatus.PASS, "Get Calendar List Test Passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Get Calendar List Failed.");
		}
	}
	
	/***
	 * Api Docs : https://developers.google.com/calendar/v3/reference/calendars/get
	 */
	@Test(priority=2,enabled=true)
	public void getCalendarTest() {
		try {
		logger = extent.startTest("Get Calendar Test Started.");
		TS1.getCalendar(AccessToken,Username);
		logger.log(LogStatus.PASS, "Get Calendar test passed.");
		}
		catch(Exception e) {
		logger.log(LogStatus.FAIL, "Get Calendar test failed.");
		}
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/colors/get
	 */
	@Test(priority=3,enabled=true)
	public void getCalendarColorsTest() {
		try {
		logger = extent.startTest("Get Calendar Colors Test.");
		TS1.getCalendarColors(AccessToken);
		logger.log(LogStatus.PASS, "Get Calendar colors Test Passed.");
		}
		catch(Exception e) {
		logger.log(LogStatus.FAIL, "Get Calendar colors Failed.");
		}
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/events/get
	 */
	@Test(priority=4,enabled=true)
	public void getCalendarEventListTest() {
		try {
		logger = extent.startTest("Get Calendar Event List Test.");
		TS1.getCalendarEventList(AccessToken,Username);
		logger.log(LogStatus.PASS, "Get Calendar Event List Passed.");
		}
		catch(Exception e) {
		logger.log(LogStatus.FAIL, "Get Calendar event list failed.");
		}
	}
	
	
	/*****
	 * API Docs : https://developers.google.com/calendar/v3/reference/settings/list
	 */
	@Test(priority=5,enabled=true)
	public void listUserCalendarSettingsTest() {
		try {
		logger=extent.startTest("List User Calendar Test.");
		TS1.listUserCalendarSettings(AccessToken);
		logger.log(LogStatus.PASS, "List user calendar Test passed.");
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "List user calendar Test failed.");
		}
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/events/insert
	 */
	@Test(priority=6,enabled=true)
	public void createAnEventTest() {
		try {
		logger=extent.startTest("Create an Event Test.");
		TS1.createAnEvent(AccessToken, Username,"Asia/Kolkata","2019-11-28", "2019-11-29");
		logger.log(LogStatus.PASS,"Create an Event Test Passed.");
		}
		catch(Exception e) {
		logger.log(LogStatus.FAIL, "Create an Event Test Failed.");
		}
	}
	
	
}
