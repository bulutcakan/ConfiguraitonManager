package com.trendyol.configurationmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.configurationmanager.model.ConfigurationParameter;
import com.trendyol.configurationmanager.service.ConfigurationService;
import com.trendyol.configurationmanager.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ConfigurationService configurationService;

	@RequestMapping(value = "/configuration/", method = RequestMethod.GET)
	public ResponseEntity<List<ConfigurationParameter>> listAllConfigurationParameters() {
		List<ConfigurationParameter> configurationParameter = configurationService.findAllConfigurationParameters();
		if (configurationParameter.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ConfigurationParameter>>(configurationParameter, HttpStatus.OK);
	}

	@RequestMapping(value = "/configuration/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getConfigurationParameter(@PathVariable("id") long id) {
		logger.info("Fetching Param with id {}", id);
		ConfigurationParameter configurationParameter = configurationService.findById(id);
		if (configurationParameter == null) {
			logger.error("Param with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Param with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ConfigurationParameter>(configurationParameter, HttpStatus.OK);
	}

	@RequestMapping(value = "/configuration/", method = RequestMethod.POST)
	public ResponseEntity<?> createtConfigurationParameter(@RequestBody ConfigurationParameter configurationParameter,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating ConfigurationParameter : {}", configurationParameter);

		if (configurationService.isConfigurationParameterExist(configurationParameter)) {
			logger.error("Unable to create. A ConfigurationParameter with name {} already exist",
					configurationParameter.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A ConfigurationParameter with name "
					+ configurationParameter.getName() + " already exist."), HttpStatus.CONFLICT);
		}
		configurationService.saveConfigurationParameter(configurationParameter);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/api/configuration/{id}").buildAndExpand(configurationParameter.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}



	@RequestMapping(value = "/configuration/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatetConfigurationParameter(@PathVariable("id") long id,
			@RequestBody ConfigurationParameter configurationParameter) {
		logger.info("Updating ConfigurationParameter with id {}", id);

		ConfigurationParameter currentConfigurationParameter = configurationService.findById(id);

		if (currentConfigurationParameter == null) {
			logger.error("Unable to update. ConfigurationParameter with id {} not found.", id);
			return new ResponseEntity(
					new CustomErrorType("Unable to upate. ConfigurationParameter with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentConfigurationParameter.setApplicationName(configurationParameter.getApplicationName());
		currentConfigurationParameter.setIsActive(configurationParameter.getIsActive());
		currentConfigurationParameter.setName(configurationParameter.getName());
		currentConfigurationParameter.setType(configurationParameter.getType());
		currentConfigurationParameter.setValue(configurationParameter.getValue());

		configurationService.updateConfigurationParameter(currentConfigurationParameter);
		return new ResponseEntity<ConfigurationParameter>(currentConfigurationParameter, HttpStatus.OK);
	}



	@RequestMapping(value = "/configuration/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletetConfigurationParameter(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting ConfigurationParameter with id {}", id);

		ConfigurationParameter configurationParameter = configurationService.findById(id);
		if (configurationParameter == null) {
			logger.error("Unable to delete. ConfigurationParameter with id {} not found.", id);
			return new ResponseEntity(
					new CustomErrorType("Unable to delete. ConfigurationParameter with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		configurationService.deleteConfigurationParameterById(id);
		return new ResponseEntity<ConfigurationParameter>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/configuration/", method = RequestMethod.DELETE)
	public ResponseEntity<ConfigurationParameter> deleteAlltConfigurationParameter() {
		logger.info("Deleting All ConfigurationParameter");

		configurationService.deleteAllConfigurationParameters();
		return new ResponseEntity<ConfigurationParameter>(HttpStatus.NO_CONTENT);
	}

}