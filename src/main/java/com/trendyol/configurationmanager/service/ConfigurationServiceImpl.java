package com.trendyol.configurationmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.configurationmanager.model.ConfigurationParameter;
import com.trendyol.configurationmanager.repositories.ConfigurationRepository;

@Service("configurationParameterService")
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Override
	public ConfigurationParameter findById(Long id) {
		// TODO Auto-generated method stub
		return configurationRepository.findOne(id);
	}

	@Override
	public ConfigurationParameter findByName(String name) {
		// TODO Auto-generated method stub
		return configurationRepository.findByName(name);
	}

	@Override
	public void saveConfigurationParameter(ConfigurationParameter configurationParameter) {
		// TODO Auto-generated method stub
		configurationRepository.save(configurationParameter);
	}

	@Override
	public void updateConfigurationParameter(ConfigurationParameter configurationParameter) {
		// TODO Auto-generated method stub
		configurationRepository.save(configurationParameter);
		
	}

	@Override
	public void deleteConfigurationParameterById(Long id) {
		// TODO Auto-generated method stub
		configurationRepository.delete(id);
	}

	@Override
	public void deleteAllConfigurationParameters() {
		// TODO Auto-generated method stub
		configurationRepository.deleteAll();
	}

	@Override
	public List<ConfigurationParameter> findAllConfigurationParameters() {
		// TODO Auto-generated method stub
		return configurationRepository.findAll();
	}

	@Override
	public boolean isConfigurationParameterExist(ConfigurationParameter configurationParameter) {
		// TODO Auto-generated method stub
	 return findByName(configurationParameter.getName()) != null;
	}

}
