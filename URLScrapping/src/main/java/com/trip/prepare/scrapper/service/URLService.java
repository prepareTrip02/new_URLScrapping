package com.trip.prepare.scrapper.service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//	import org.bouncycastle.oer.its.etsi102941.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.trip.prepare.scrapper.entity.Destination;
import com.trip.prepare.scrapper.entity.DistrictDestination;
import com.trip.prepare.scrapper.entity.DistrictDestinationKey;
import com.trip.prepare.scrapper.entity.OverviewRepo;
import com.trip.prepare.scrapper.entity.Url;
import com.trip.prepare.scrapper.entity.UrlRepo;

@Component
public class URLService {

	private static final Logger logger = LogManager.getLogger(URLService.class);
	@Autowired
	OverviewRepo overviewRepo;
	@Autowired
	UrlRepo urlRepo;

	@Async
	public void getplaceInfo(DistrictDestination districtDestination) {

		DistrictDestinationKey sourceKey = districtDestination.getDistrictDestinationKey();
		System.out.println("Thread Name:" + Thread.currentThread().getName());
		logger.info("Thread Name:" + Thread.currentThread().getName());
		System.out.println("Time=" + LocalTime.now());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);

		//Pattern pattern = Pattern.compile(regex);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed

		try {

			String googleMapUrl = "https://maps.google.com/?q=";
			String textToSearch = "All Places to stay near india gate, delhi";
			//String textToSearch = "All bars and pubs in Agra";
			// String textToSearch = "Hotels in
			// "+districtDestination.getDistrictDestinationKey().getPlace_name();
			String scrapperUrl = googleMapUrl + textToSearch;
			driver.get(scrapperUrl);
			System.out.println(driver.getTitle());

			// WebElement scrollDiv =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='feed']")));
			WebElement scrollRootDiv = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@aria-label='Results for " + textToSearch + "']")));
			// WebElement mainPage =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='feed']//a[@class='hfpxzc']")));
			// WebElement mainPage =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Results
			// for "+textToSearch+"]"+"//a[@class='hfpxzc']")));
			// HeaderImageButton.click();
			// ExpectedConditions.element
			System.out.println("scroll");
			for (int i = 0; i < 500; i++) {
				// System.out.println("Scroll###");
				js.executeScript("arguments[0].scrollBy(0, arguments[1]);", scrollRootDiv, 500);
				// js.executeScript("arguments[0].scrollBy(0, arguments[1]);", scrollDiv2,
				// 5000);
			}

			List<WebElement> places = driver.findElements(
					By.xpath("//div[@aria-label='Results for " + textToSearch + "']" + "//a[@class='hfpxzc']"));

			System.out.println("places=" + places.size());
			logger.info("places=" + places.size());

			for (WebElement place : places) {
				Url url = new Url();

				url.setDestination(
						new Destination(sourceKey.getPlace_id(), sourceKey.getPlace_name(), sourceKey.getPlace_type()));

				String currentUrlFromHref = "";
				try {

					// DO not take URL from URL Search, take it from href
					currentUrlFromHref = place.getAttribute("href");
					url.setGoogle_map_url(currentUrlFromHref);
					
					 // Extract latitude and longitude using regular expressions
			        String latitude = extractLatLong(currentUrlFromHref, "!3d(-?\\d+\\.\\d+)");
			        String longitude = extractLatLong(currentUrlFromHref, "!4d(-?\\d+\\.\\d+)");
			        String cid=extractCID(currentUrlFromHref, "0x([a-zA-Z0-9]+)!");
			        
			        // Print the results
			        System.out.println("Latitude: " + latitude + "Longitude: " + longitude);
			        url.setLatitude(latitude);
			        url.setLongitude(longitude);
					
					
					// Url Used to get Coordinate, Image and Place_id
					System.out.println("cid Int= " + new BigInteger(cid.substring(2, cid.length() - 1), 16));
					url.setCid(new BigInteger(cid.substring(2, cid.length() - 1), 16) + "");

					Url responseUrl = urlRepo.save(url);
					logger.info("$$$$$$$$$$$$$$$$$$$$$$$$activityEntity=" + LocalTime.now());

				} catch (Exception e) {
					System.out.println("Exception to fatch URL and CID=" + e); // TODO: handle exception }

				}

			}
		} catch (Exception e) {
			System.out.println("Exception=" + e);
			// TODO: handle exception
		} finally {
			driver.close();
		}
	}
	 private String extractLatLong(String input, String pattern) {
	        Pattern regexPattern = Pattern.compile(pattern);
	        Matcher matcher = regexPattern.matcher(input);

	        if (matcher.find()) {
	            return matcher.group(1);
	        } else {
	            return "Not found";
	        }
	    }
	 
	 private String extractCID(String input, String pattern) {
		 Pattern regexPattern = Pattern.compile(pattern);
		 Matcher matcher = regexPattern.matcher(input);
		 if (matcher.find()) {
				return matcher.group();
			} else {
				logger.info("CID not found");
				return "Not Found";
			} 
		 
	 }
	 }