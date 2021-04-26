package BasePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import util.FBConstants;
import util.Xls_Reader;

public class BaseTest {

	public static final String run_env = "API";
	public static Properties config = null;
	public static Properties OR = null;
	
	public WebDriver driver = null;

	
	public static Xls_Reader datatable = null;

	public static final String LOG_PATH = FBConstants.REPORTS_PATH + "WEB\\";
	private static final String FileUtils = null;
	

	@BeforeSuite
	public void initialize() throws IOException {

		System.out.println(System.getProperty("user.dir"));
		
		config = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\config.properties");
		config.load(fp);
	

		OR = new Properties();
		fp = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\OR.properties");
		OR.load(fp);
		datatable = new Xls_Reader(System.getProperty("user.dir") + "\\src\\data\\" + run_env + "_Data.xlsx");
	
	

	}

	// function to generate a random string of length n
	public String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}