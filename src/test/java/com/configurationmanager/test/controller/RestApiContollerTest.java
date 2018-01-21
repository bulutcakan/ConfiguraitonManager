package com.configurationmanager.test.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.trendyol.configurationmanager.ConfigurationManagerApp;
import com.trendyol.configurationmanager.model.ConfigurationParameter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigurationManagerApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiContollerTest {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	
	@Test
	public void addParam() {

		ConfigurationParameter configParams=new ConfigurationParameter();
		configParams.setApplicationName("TestApplication");
		configParams.setIsActive(0);
		configParams.setName("Test");
		configParams.setType("String");
		configParams.setValue("Test");

		HttpEntity<ConfigurationParameter> entity = new HttpEntity<ConfigurationParameter>(configParams, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/ConfigurationManager/api/configuration/"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/ConfigurationManager/api/configuration/"));

	}
	
	@Test
	public void testRetrieveCorrectParam() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/ConfigurationManager/api/configuration/"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"id\":20,\"name\":\"Config\",\"type\":\"Boolean\",\"value\":\"False\",\"isActive\":1,\"applicationName\":\"Service_a\"}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
}
