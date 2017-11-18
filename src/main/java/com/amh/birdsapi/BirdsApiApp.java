package com.amh.birdsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.amh.birdsapi.configuration.CORSFilter;
import com.amh.birdsapi.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.amh.birdsapi"})
public class BirdsApiApp {
	public static void main(String[] args) {
        SpringApplication.run(BirdsApiApp.class, args);
    }
}
