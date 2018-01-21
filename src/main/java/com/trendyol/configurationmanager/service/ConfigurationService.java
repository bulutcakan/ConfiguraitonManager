package com.trendyol.configurationmanager.service;

import java.util.List;

import com.trendyol.configurationmanager.model.ConfigurationParameter;

public interface ConfigurationService {

	ConfigurationParameter findById(Long id);

	ConfigurationParameter findByName(String name);

	void saveConfigurationParameter(ConfigurationParameter configurationParameter);

	void updateConfigurationParameter(ConfigurationParameter configurationParameter);

	void deleteConfigurationParameterById(Long id);

	void deleteAllConfigurationParameters();

	List<ConfigurationParameter> findAllConfigurationParameters();

	boolean isConfigurationParameterExist(ConfigurationParameter configurationParameter);
}
