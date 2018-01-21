package com.trendyol.configurationmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.trendyol.configurationmanager.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.trendyol.configurationmanager"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class ConfigurationManagerApp {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationManagerApp.class, args);
	}
}
