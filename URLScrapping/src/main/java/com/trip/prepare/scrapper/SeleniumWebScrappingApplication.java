package com.trip.prepare.scrapper;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.trip.prepare.scrapper.entity.DistrictDestination;
import com.trip.prepare.scrapper.entity.DistrictDestinationRepo;
import com.trip.prepare.scrapper.service.URLService;

@SpringBootApplication
@EnableAsync
public class SeleniumWebScrappingApplication {
	@Autowired
	URLService urlLservice;
	@Autowired
	DistrictDestinationRepo districtDestinationRepo;
	public static void main(String[] args) {
		SpringApplication.run(SeleniumWebScrappingApplication.class, args);
	}
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}

	/*
	 * @Override public void run(String... args) throws Exception { // Your
	 * command-line logic goes here System.out.println("Command-line arguments:");
	 * List<DistrictDestination> districtDestinationList
	 * =districtDestinationRepo.findAll();
	 * System.out.println(districtDestinationList.size());
	 * //districtDestinationList.forEach(district->{System.out.println(district);});
	 * //HimachalPradeshDetailEntity obj=new HimachalPradeshDetailEntity();
	 * districtDestinationList.forEach(district->{
	 * System.out.println("Calling district="+district);
	 * urlLservice.getplaceInfo(district); return; });
	 * //googleMapScrapperService.getplaceInfo();
	 * 
	 * 
	 * }
	 */
	 
	

}
