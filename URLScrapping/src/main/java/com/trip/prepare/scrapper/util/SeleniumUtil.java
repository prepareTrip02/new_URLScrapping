package com.trip.prepare.scrapper.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtil {

	public static WebElement getWebElement(WebDriver driver, String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		if (!elements.isEmpty()) {
			return elements.get(0);
		} else {
			System.out.println(" element not found");
			return null;
		}
	}
	
	public static WebElement getWebElement2(SearchContext searchContext, String xpath) {
		List<WebElement> elements = searchContext.findElements(By.xpath(xpath));
		if (!elements.isEmpty()) {
			return elements.get(0);
		} else {
			System.out.println(" element not found");
			return null;
		}
	}
	
	public static String getElementText2(SearchContext searchContext, String xpath) {
		List<WebElement> elements = searchContext.findElements(By.xpath(xpath));
		if (!elements.isEmpty()) {
			return elements.get(0).getText();
		} else {
			System.out.println(" element not found");
			return "";
		}
	}
	
	public static List<WebElement> getAllWebElement(SearchContext searchContext, String xpath) {
		List<WebElement> elements = searchContext.findElements(By.xpath(xpath));
		/*
		 * if (!elements.isEmpty()) { return elements.get(0).getText(); } else {
		 * System.out.println(" element not found"); return ""; }
		 */
		return elements;
	}
	
	public static String getElementText(WebDriver driver, String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		if (!elements.isEmpty()) {
			return elements.get(0).getText();
		} else {
			System.out.println(" element not found");
			return "";
		}
	}

	public static String getElementAttribute(WebDriver driver, String xpath, String attribute) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));

		if (!elements.isEmpty()) {

			return elements.get(0).getAttribute(attribute);
		} else {
			System.out.println(" element not found");
			return "";
		}
	}

	public static String getElementAttribute2(SearchContext searchContext, String xpath, String attribute) {
		List<WebElement> elements = searchContext.findElements(By.xpath(xpath));

		if (!elements.isEmpty()) {
			return elements.get(0).getAttribute(attribute);
		} else {
			System.out.println(" element not found");
			return "";
		}
	}
	
	public static Map<String, List<String>> getEnabledText(WebElement webElement, String xpath) throws InterruptedException {
		Map<String, List<String>> servicesMap = new HashMap<String, List<String>>();
		//System.out.println("webElement= "+webElement.getAttribute("outerHTML"));
		//Thread.sleep(5000);
		List<WebElement> servicelist = webElement.findElements(By.xpath(xpath));
		
		if (servicelist.isEmpty()) {
			System.out.println(" servicesDiv not found");
			return servicesMap;
		}else { 
			for(WebElement serviceWebElemnt :servicelist) {
			List<String> serviceTextList= new ArrayList<String>();
			String key=getElementText2(serviceWebElemnt,".//h2");
			System.out.println("key2="+key);
			List<WebElement> valWebElementList = serviceWebElemnt.findElements(By.xpath(".//ul[@class='ZQ6we']//li[@class='hpLkke ']//span"));
			//List<WebElement> valWebElementList = webElement.findElements(By.xpath("//div[@role='region' and @aria-label='About Kids Play area']//ul//li//span"));

			if (valWebElementList.isEmpty()) {
				System.out.println(" valList not found");
				continue;
			}else { 
				for(WebElement val :valWebElementList) {
					String value=val.getText();
					System.out.println("value="+value);
					serviceTextList.add(value);
				}
					
			}
			
			if(!serviceTextList.isEmpty()) {
				servicesMap.put(key, serviceTextList);
				}
			}
			
		}
		return servicesMap;
		}
	
	public static Map<String, List<String>> getDisabledText(WebElement webElement, String xpath) throws InterruptedException {
		Map<String, List<String>> servicesMap = new HashMap<String, List<String>>();
		//System.out.println("webElement= "+webElement.getAttribute("outerHTML"));
		//Thread.sleep(5000);
		List<WebElement> servicelist = webElement.findElements(By.xpath(xpath));
		
		if (servicelist.isEmpty()) {
			System.out.println(" servicesDiv not found");
			return servicesMap;
		}else { 
			for(WebElement serviceWebElemnt :servicelist) {
			List<String> serviceTextList= new ArrayList<String>();
			String key=getElementText2(serviceWebElemnt,".//h2");
			System.out.println("key2="+key);
			List<WebElement> valWebElementList = serviceWebElemnt.findElements(By.xpath(".//ul[@class='ZQ6we']//li[@class='hpLkke WeoVJe']//span"));
			//List<WebElement> valWebElementList = webElement.findElements(By.xpath("//div[@role='region' and @aria-label='About Kids Play area']//ul//li//span"));

			if (valWebElementList.isEmpty()) {
				System.out.println(" valList not found");
				continue;
			}else { 
				for(WebElement val :valWebElementList) {
					String value=val.getText();
					System.out.println("value="+value);
					serviceTextList.add(value);
				}
					
			}
			if(!serviceTextList.isEmpty()) {
			servicesMap.put(key, serviceTextList);
			}
			}
			
		}
		return servicesMap;
		}
	
	public static Map<String, List<String>> getDisabledServicesHotel(WebElement webElement, String xpath) throws InterruptedException {
		Map<String, List<String>> servicesMap = new HashMap<String, List<String>>();
		List<String> serviceList= new ArrayList<String>();
		//System.out.println("webElement= "+webElement.getAttribute("outerHTML"));
		//Thread.sleep(5000);
		List<WebElement> servicelist = webElement.findElements(By.xpath(xpath));
		
		if (servicelist.isEmpty()) {
			System.out.println(" servicesDiv not found");
			return servicesMap;
		}else { 
			for(WebElement service :servicelist) {
					String value=service.getText();
					serviceList.add(value);
			}
			
			servicesMap.put("DisabledHotelServices", serviceList);
			
			
		}
		return servicesMap;
		}
	
	public static Map<String, List<String>> getEnabledServicesHotel(WebElement webElement, String xpath) throws InterruptedException {
		Map<String, List<String>> servicesMap = new HashMap<String, List<String>>();
		List<String> serviceList= new ArrayList<String>();
		//System.out.println("webElement= "+webElement.getAttribute("outerHTML"));
		//Thread.sleep(5000);
		List<WebElement> servicelist = webElement.findElements(By.xpath(xpath));
		
		if (servicelist.isEmpty()) {
			System.out.println(" servicesDiv not found");
			return servicesMap;
		}else { 
			for(WebElement service :servicelist) {
					String value=service.getText();
					serviceList.add(value);
			}
			
			servicesMap.put("EnabledHotelServices", serviceList);
			
			
		}
		return servicesMap;
		}

}
