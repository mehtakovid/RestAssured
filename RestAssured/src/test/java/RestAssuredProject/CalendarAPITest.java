package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
		TS1.getCalendarList(AccessToken,Username);
	}
	
	/***
	 * Api Docs : https://developers.google.com/calendar/v3/reference/calendars/get
	 */
	@Test(priority=2,enabled=true)
	public void getCalendarTest() {
		TS1.getCalendar(AccessToken,Username);
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/colors/get
	 */
	@Test(priority=3,enabled=true)
	public void getCalendarColorsTest() {
		TS1.getCalendarColors(AccessToken);
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/events/get
	 */
	@Test(priority=4,enabled=true)
	public void getCalendarEventListTest() {
		TS1.getCalendarEventList(AccessToken,Username);
	}
	
	
	/*****
	 * API Docs : https://developers.google.com/calendar/v3/reference/settings/list
	 */
	@Test(priority=5,enabled=true)
	public void listUserCalendarSettingsTest() {
		TS1.listUserCalendarSettings(AccessToken);
	}
	
	/****
	 * API Docs : https://developers.google.com/calendar/v3/reference/events/insert
	 */
	@Test(priority=6,enabled=true)
	public void createAnEventTest() {
		TS1.createAnEvent(AccessToken, Username,"Asia/Kolkata","2019-11-28", "2019-11-29");
	}
	
	
}
