package com.trip.prepare.scrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.prepare.scrapper.entity.DistrictDestination;
import com.trip.prepare.scrapper.entity.DistrictDestinationRepo;
import com.trip.prepare.scrapper.service.URLService;

@RestController
public class URLController {

	@Autowired
	URLService urlLservice;
	@Autowired
	DistrictDestinationRepo districtDestinationRepo;

	@GetMapping("createUrls")
	ResponseEntity<String> getUrls() {
		
		System.out.println("Command-line arguments:");
		List<DistrictDestination> districtDestinationList = districtDestinationRepo.findAll();
		System.out.println(districtDestinationList.size());
		// districtDestinationList.forEach(district->{System.out.println(district);});
		// HimachalPradeshDetailEntity obj=new HimachalPradeshDetailEntity();
		int i=0;
		while(i<10) {
		urlLservice.getplaceInfo(districtDestinationList.get(i));
		i++;
		}
		/*
		 * districtDestinationList.forEach(district -> {
		 * System.out.println("Calling district=" + district);
		 * urlLservice.getplaceInfo(district); });
		 */		 
		System.out.println("Completed");
		return new ResponseEntity<>("ok",HttpStatus.CREATED);


	}

}
