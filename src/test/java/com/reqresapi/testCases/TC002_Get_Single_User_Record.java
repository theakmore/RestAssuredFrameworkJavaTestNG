package com.reqresapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqresapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_User_Record extends TestBase {
	
	@BeforeClass
	void getUserData() throws InterruptedException
	{
		logger.info("*********Started TC002_Get_Single_User_Record **********");
		
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users/"+userID);
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(userID), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime<5000);
		
	}
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
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
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC002_Get_Single_User_Record **********");
	}

}
