package com.akamai.akamaiconnect;

import org.springframework.boot.SpringApplication;

public class TestAkamaiconnectApplication {

	public static void main(String[] args) {
		SpringApplication.from(AkamaiconnectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
