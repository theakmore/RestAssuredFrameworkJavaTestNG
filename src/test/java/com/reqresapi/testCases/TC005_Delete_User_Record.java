package com.reqresapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqresapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_User_Record extends TestBase{
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		logger.info("*********Started TC005_Delete_User_Record **********");
		
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/users?page=2");
				
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
			 
		//Capture id
		String userid=jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/users/"+userid); //Pass ID to delete record
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(""), true);

	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
		
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
		logger.info("*********  Finished TC005_Delete_User_Record **********");
	}

}
