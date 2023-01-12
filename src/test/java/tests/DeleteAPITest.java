package tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import client.RestClient;
import util.TestUtil;

public class DeleteAPITest extends TestBase {


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
	public void deleteAPITest() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		closeableHttpResponse = restClient.delete(url);
		
		//getting status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204, "Status code is not 204");
	
		
	}
}
