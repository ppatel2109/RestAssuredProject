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

public class GetAPITest extends TestBase {

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

		url = baseUrl + apiUrl;

		
	}
	
	@Test(priority = 1)
	public void getAPITest() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//getting status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		
		//getting response as json
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON:" + responseJson);
		
		//get per page value 
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Per page value: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		
		//get total value s
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Total value: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//getting value from json array	
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("Last name:" + lastName);
		System.out.println("Id:" + id);
		System.out.println("Avatar:" + avatar);
		System.out.println("First name:" + firstName);
		
		Assert.assertEquals(lastName, "Bluth");
		
		
		//getting headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers: " + allHeaders);
		
		
	}

	
	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		HashMap< String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("username", "xyz@gmail.com");
		headerMap.put("password", "abc123");
		closeableHttpResponse = restClient.get(url, headerMap);
		
		
		//getting status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		
		//getting response as json
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON:" + responseJson);
		
		//get per page value 
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Per page value: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		
		//get total value s
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Total value: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//getting value from json array	
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("Last name:" + lastName);
		System.out.println("Id:" + id);
		System.out.println("Avatar:" + avatar);
		System.out.println("First name:" + firstName);
		
		Assert.assertEquals(lastName, "Bluth");
		
		
	
		
		
	}
}
