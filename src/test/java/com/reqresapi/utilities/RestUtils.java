package com.reqresapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String userName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("akshay"+generatedString);
	}
	
	public static String jobRole() {
		String generatedString = RandomStringUtils.randomAlphanumeric(3);
		return ("Devops"+generatedString);
	}
}
