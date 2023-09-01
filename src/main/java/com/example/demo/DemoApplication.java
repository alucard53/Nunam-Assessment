package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DemoApplication {

	private static DataService dataService;
	private static StatService statService;

	//Injection of dataService and statService beans
	public DemoApplication(DataService dataService, StatService statService) {
		this.dataService = dataService;
		this.statService = statService;
	}

	public static void main(String[] args) {
		var ctx = SpringApplication.run(DemoApplication.class, args);

//		Uncomment the line below to generate sample data
//		dataService.genNew();

		statService.addCurrDay(dataService.getStat());
		SpringApplication.exit(ctx);
	}
}
