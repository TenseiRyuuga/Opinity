package com.example.demoOpinity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.demoOpinity.storage.StorageProperties;
import com.example.demoOpinity.storage.StorageService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//enable the configuration StorageProperties
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableSwagger2
public class DemoOpinityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOpinityApplication.class, args);
	}

	// Each time we boot we delete all files stored and init the StorageService.
	// This might not be desired behaviour but should save storage space if the
	// server is rebooted regularly
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
