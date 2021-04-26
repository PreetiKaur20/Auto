package test;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.tools.xjc.model.SymbolSpace;

import BasePage.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.DataUtil;
import util.Xls_Reader;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class API_Post_User_Get_User extends BaseTest {

	public static String userid;
	private static final Writer FileOutputStream = null;

	@Test(dataProvider = "getData", priority = 1)
	public void CreateUser(Hashtable<String, String> data) throws Exception {
		{

			String random = getAlphaNumericString(3);
			RequestSpecification res = RestAssured.given();
			res.headers("Content-Type", "application/json");
			JSONObject json = new JSONObject();
			json.put("email", data.get("email") + random);
			json.put("name", data.get("name") + random);

			res.body(json.toString());
			Response response = res.post("http://99.80.111.168:8080/api/user");
			int code = response.getStatusCode();
			System.out.println(response.body().asString());
			String[] responsebody = response.body().asString().split("\"");
			System.out.println(responsebody[3]);
			userid = responsebody[3];
			// Status code should be 201 in post
			System.out.println("UserType:" + userid.getClass());
			datatable.setCellData("Userid", "User", 2, userid);

			System.out.println(code);
			Assert.assertEquals(code, 201);
		}
	}

	@Test(dataProvider = "getData", dependsOnMethods = { "CreateUser" })
	public void getUser(Hashtable<String, String> data) throws Exception {

		System.out.println("------get car--------");
		System.out.println("http://99.80.111.168:8080/api/user/" + userid);
		// Status code should be 200 in get
		given().when().get("http://99.80.111.168:8080/api/user/" + userid).then().statusCode(200);

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "TC_User_Post", "API");

	}

}
