package com.trendyol.configurationmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.configurationmanager.model.ConfigurationParameter;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationParameter, Long> {

	ConfigurationParameter findByName(String name);
}
