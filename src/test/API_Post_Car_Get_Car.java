package test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.DataUtil;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class API_Post_Car_Get_Car extends BaseTest {
	public static String carid;;

	@Test(dataProvider = "getData", priority = 1)
	public void CreateCar(Hashtable<String, String> data) throws Exception {
		{

			String Userid = datatable.getCellData("Userid", "User", 2);
			System.out.println(Userid);

			String random = getAlphaNumericString(3);
			RequestSpecification res = RestAssured.given();
			res.headers("Content-Type", "application/json");
			JSONObject json = new JSONObject();
			json.put("manufacture", data.get("manufacture") + random);
			json.put("model", data.get("model") + random);
			json.put("userId", Userid);

			res.body(json.toString());
			Response response = res.post("http://99.80.111.168:8080/api/car");
			int code = response.getStatusCode();
			System.out.println(response.body().asString());
			String[] responsebody = response.body().asString().split("\"");
			System.out.println(responsebody[3]);
			carid = responsebody[3];
			datatable.setCellData("Userid", "CarID", 2, carid);

			System.out.println(code);
			Assert.assertEquals(code, 201);
		}
	}

	@Test(dataProvider = "getData", dependsOnMethods = { "CreateCar" })
	public void getCar(Hashtable<String, String> data) throws Exception {

		System.out.println("------get car--------");
		System.out.println("http://99.80.111.168:8080/api/car/" + carid);

		given().when().get("http://99.80.111.168:8080/api/car/" + carid).then().statusCode(200);
		System.out.println(given().when().get("http://99.80.111.168:8080/api/car/" + carid).statusCode());
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "TC_Car_Post", "API");

	}

}
