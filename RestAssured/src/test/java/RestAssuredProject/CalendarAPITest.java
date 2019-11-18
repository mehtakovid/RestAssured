package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalendarAPITest extends BaseClass {
	
	GoogleCalendarAPI TS1 = new GoogleCalendarAPI();
	
//	@BeforeTest
//	public void gettingAuthToken() throws Exception {
//		BaseClass.constructAuthenticationURL("https://accounts.google.com","/o/oauth2/v2/auth","https://www.googleapis.com/auth/calendar",
//				"https://accounts.google.com/o/oauth2/auth",
//				"745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com",
//				"code","https://www.getpostman.com/oauth2/callback",
//				"empty");
//		BaseClass.driverInitialization();
//		BaseClass.getCodeThroughBrowserAuthentication();
//		BaseClass.driverTearDown();
//		BaseClass.getBearerAccessToken();
//	}
	
	
	@Test(priority=1,enabled=false)
	public void getCalendarListTest() {
		TS1.getCalendarList("ya29.ImCxBz_c_kbi8axvz85GerV6QCxdxhABom4nhhUnWRGBjFT7M3rG2rLAR6sdZtTls9XLYtLo3BC76UrhyyXfGjDXFlrMyTxoMLJ0i_7txU_WWfdkvMhY2JVXJbVRQFQZBTM");
	}
	
	@Test(priority=2,enabled=false)
	public void getCalendarTest() {
		TS1.getCalendar("ya29.ImCxBz_c_kbi8axvz85GerV6QCxdxhABom4nhhUnWRGBjFT7M3rG2rLAR6sdZtTls9XLYtLo3BC76UrhyyXfGjDXFlrMyTxoMLJ0i_7txU_WWfdkvMhY2JVXJbVRQFQZBTM");
	}
	
	@Test(priority=3,enabled=false)
	public void getCalendarColorsTest() {
		TS1.getCalendarColors(AccessToken);
	}
	
	@Test(priority=4,enabled=false)
	public void getCalendarEventListTest() {
		TS1.getCalendarEventList("ya29.ImCxBz_c_kbi8axvz85GerV6QCxdxhABom4nhhUnWRGBjFT7M3rG2rLAR6sdZtTls9XLYtLo3BC76UrhyyXfGjDXFlrMyTxoMLJ0i_7txU_WWfdkvMhY2JVXJbVRQFQZBTM","kovidmehta10@gmail.com");
	}
	
	@Test(priority=5,enabled=true)
	public void createAnEventTest() {
		TS1.createAnEvent("ya29.ImCxB-NYGn9nCiqSh6cY6FDfJzu4sA4CKr28ywLjf8-64SXFSIdHDGmUAUc1LRfslp-YXGZtI9tmOZX6Eia_dxw0XmpmcBR4bZCpy5KoOZEc7yr_j5ftxLQqNMiCxlxVFPQ", "kovidmehta10@gmail.com","Asia/Kolkata","2019-11-28", "2019-11-29");
	}
	
	@Test(priority=6,enabled=true)
	public void listUserCalendarSettingsTest() {
		TS1.listUserCalendarSettings("ya29.ImCxB-NYGn9nCiqSh6cY6FDfJzu4sA4CKr28ywLjf8-64SXFSIdHDGmUAUc1LRfslp-YXGZtI9tmOZX6Eia_dxw0XmpmcBR4bZCpy5KoOZEc7yr_j5ftxLQqNMiCxlxVFPQ");
	}
	
	
}
