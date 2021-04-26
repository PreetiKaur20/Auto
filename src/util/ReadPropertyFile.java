package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	// Read the values from properties file
	public static String readValue(String strparameter) throws IOException {
		FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\Constants.properties");

		Properties p = new Properties();
		p.load(fileInput);
		String propertyvalue = p.getProperty(strparameter);
		return (propertyvalue);

	}

}
