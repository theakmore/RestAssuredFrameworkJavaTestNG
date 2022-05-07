package com.reqresapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqresapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Users extends TestBase {
	
	@BeforeClass
	void getAllUsers() throws InterruptedException
	{
	
	logger.info("*********Started TC001_Get_All_Users **********");
		
	RestAssured.baseURI = "https://reqres.in/api";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET,"/users?page=2");
	
	Thread.sleep(5);
	}
	
	
	@Test
	void checkResponseBody()
	{
		logger.info("***********  Checking Response Body **********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body-->"+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status Code is -->" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("***********  Checking Response Time **********");
		
		long responseTime = response.getTime(); 
		logger.info("Response Time is -->" + responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<20000);
		
			
	}
	
	@Test
	void checkstatusLine()
	{
		logger.info("***********  Checking Status Line **********");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is -->" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
		
	}
	
	@Test
	void checkContentType()
	{
		logger.info("***********  Checking Content Type **********");
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is -->" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkserverType()
	{
		logger.info("***********  Checking Server Type **********");
		
		String serverType = response.header("Server");
		logger.info("Server Type is -->" +serverType); 
		Assert.assertEquals(serverType, "cloudflare");
	
	}
	
	@Test
	void checkcontentEncoding()
	{
		logger.info("***********  Checking Content Encoding**********");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is-->" +contentEncoding); 
		Assert.assertEquals(contentEncoding, "gzip");
		
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_Get_All_Users **********");
	}
	

}
