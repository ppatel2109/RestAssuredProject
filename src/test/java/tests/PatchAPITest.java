package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import base.TestBase;
import client.RestClient;
import data.Users;

public class PatchAPITest extends TestBase {
	
	TestBase testBase;
	String baseUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		baseUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		url = baseUrl + apiUrl + "/2";

	}
	
	@Test(priority = 1)
	public void patchAPITest() throws Throwable {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "developer");
		
		//objext to json
		mapper.writeValue(new File("C:\\QA\\Selenium_Workspace\\RestAssuredProject\\src\\main\\java\\data\\users.json"), users);
		
		//object to json in string
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.patch(url, userJsonString, headerMap);
		
		//check status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
		
		//json string check
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response from API: " + responseJson);
		
		Users usersObj = mapper.readValue(responseString, Users.class);
		System.out.println(usersObj);
		
		Assert.assertTrue(users.getName().equals(usersObj.getName()));
		
		Assert.assertTrue(users.getJob().equals(usersObj.getJob()));
		
		System.out.println(usersObj.getUpdatedAt());
	}
	
}
