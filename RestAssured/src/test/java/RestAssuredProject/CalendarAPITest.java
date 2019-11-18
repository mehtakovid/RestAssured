package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalendarAPITest extends BaseClass {
	
	GoogleCalendarAPI TS1 = new GoogleCalendarAPI();
	
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
	
	
	@Test(priority=1,enabled=false)
	public void getCalendarListTest() {
		TS1.getCalendarList(AccessToken);
	}
	
	@Test(priority=2,enabled=false)
	public void getCalendarTest() {
		TS1.getCalendar(AccessToken);
	}
	
	@Test(priority=3,enabled=false)
	public void getCalendarColorsTest() {
		TS1.getCalendarColors(AccessToken);
	}
	
	@Test(priority=4,enabled=false)
	public void getCalendarEventListTest() {
		TS1.getCalendarEventList(AccessToken,"kovidmehta10@gmail.com");
	}
	
	@Test(priority=5,enabled=false)
	public void createAnEventTest() {
		TS1.createAnEvent(AccessToken, "kovidmehta10@gmail.com","Asia/Kolkata","2019-11-28", "2019-11-29");
	}
	
	@Test(priority=6,enabled=false)
	public void listUserCalendarSettingsTest() {
		TS1.listUserCalendarSettings(AccessToken);
	}
	
	
}
