package RestAssuredProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	
	public static void driverInitialization() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\komehta\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		wait=new WebDriverWait(driver,10);
	}
	
}
