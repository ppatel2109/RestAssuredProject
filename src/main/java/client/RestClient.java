package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import base.TestBase;

public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		// get method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

		return closeableHttpResponse;

	}

	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		// get method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

		return closeableHttpResponse;

	}

	public CloseableHttpResponse post(String url, String entity, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(entity));

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

		return closeableHttpResponse;

	}

	public CloseableHttpResponse put(String url, String entity, HashMap<String, String> headerMap) throws Throwable {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entity));

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPut);

		return closeableHttpResponse;
	}

	public CloseableHttpResponse patch(String url, String entity, HashMap<String, String> headerMap) throws Throwable {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch(url);
		httpPatch.setEntity(new StringEntity(entity));

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPatch.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPatch);

		return closeableHttpResponse;
	}
	
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {

		// get method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpGet = new HttpDelete(url);
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

		return closeableHttpResponse;

	}
}
