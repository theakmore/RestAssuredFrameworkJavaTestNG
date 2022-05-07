package com.reqresapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqresapi.base.TestBase;
import com.reqresapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_User_Record extends TestBase{
	
	
	String userName=RestUtils.userName();
	String jobrole="sdetEngineer";
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("*********Started TC003_Post_User_Record **********");
		
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"akshay","job":"tester"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", userName); // Cast
		requestParams.put("job", jobrole);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/users");
		
		Thread.sleep(5000);

	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(userName), true);
		Assert.assertEquals(responseBody.contains(jobrole), true);
		
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "cloudflare");
	}
	

	
	@Test
	void checkContentLenght()
	{
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<1000);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_Post_User_Record **********");
	}

}
