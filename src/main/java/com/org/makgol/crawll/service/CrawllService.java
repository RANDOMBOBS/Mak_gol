package com.org.makgol.crawll.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.util.Crawlling;

import lombok.Data;


@Service
public class CrawllService {
	
	
	/**
	 * crawller 설명
	 * detail_page를 리스트를 받으면 해당 리스트로 순차적으로 크롤링후 DB 저장하는 메소드
	 */
	/*
	public StoreRequestVo crawlling(String detail_page, StoreRequestVo storeRequestVo) throws Exception {
		
		
			StoreRequestMenuVo storeReqeustMenuVo = new StoreRequestMenuVo();
			
			System.out.println("crawlling");
			
			// 크롬 드라이버 설정
	        System.setProperty("webdriver.chrome.driver", "D:\\SunhyeonSpring\\mak_gol\\src\\driver\\chromedriver.exe"); // 윈도우
	        // System.setProperty("webdriver.chrome.driver", "./chromedriver"); // 리눅스, 맥
	        
	        // 크롬 옵션 설정
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");
	        options.addArguments("headless");
	        options.addArguments("--disable-popup-blocking");
	        options.addArguments("--blink-settings=imagesEnabled=false");
	        WebDriver driver = new ChromeDriver(options);
	       
	        List<String> menus = new ArrayList<>();

	        	 driver.get("https://place.map.kakao.com/785573131");
	        	 Thread.sleep(500);
	        	 WebElement searchButton = driver.findElement(By.cssSelector("#mArticle > div.cont_menu > a"));

	             // 검색 버튼 클릭
	             searchButton.click();
	           
	             Thread.sleep(500);
	             // 업데이트 날자를 가져옵니다.
	             WebElement element = searchButton.findElement(By.xpath("//*[@id=\"mArticle\"]"));
	             

	                 WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
	                 WebElement time_operation 	 = element.findElement(By.cssSelector("span.time_operation"));
	                 WebElement location_present = element.findElement(By.cssSelector("div.details_placeinfo > div.placeinfo_default.placeinfo_homepage > div > div > a"));
	                 WebElement txt_contact 	 = element.findElement(By.cssSelector("span.txt_contact"));
	                 WebElement update_getMenu   = element.findElement(By.cssSelector("span.txt_updated > span"));
	                 
	                 String date_revise    = span_date_revise.getText();
	                 String opening_hours  = time_operation.getText();
	                 String site 		   = location_present.getAttribute("href");
	                 String phone 		   = txt_contact.getText();
	                 String getMenu_update  = update_getMenu.getText();
	                 LocalDate update_date = LocalDate.parse(date_revise.substring(0,date_revise.length()-1), DateTimeFormatter.ofPattern("yyyy.MM.dd")); // 날짜 문자열을 LocalDate 객체로 변환합니다.

	                 // StoreRequestVo 객체 생성 및 저장
	                 storeRequestVo.setSite(site);
	                 storeRequestVo.setUpdate_date(update_date);
	                 storeRequestVo.setOpening_hours(opening_hours);
	                 storeRequestVo.setMenu_update(getMenu_update.substring(0,date_revise.length()-1));
	                 
	                 
	                 System.out.println(storeRequestVo.getSite());
	                 System.out.println(storeRequestVo.getUpdate_date());
	                 System.out.println(storeRequestVo.getOpening_hours());
	                 System.out.println(storeRequestVo.getMenu_update());
	                 
		
	                 // Notice 객체를 DB에 저장합니다.

	                 // 저장된 Notice 객체를 NoticeDto로 변환하여 리스트에 추가합니다.
	                 
	                 
	                Thread.sleep(500);
	                List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul > li"));
	                
	                //System.out.println(element_menu.get(1).getText());
	                
	                
	                for(WebElement element_menu_: element_menu) {
	                
	                WebElement menu_name = element_menu_.findElement(By.cssSelector("div > span"));
	                System.out.println("menu_name : "+menu_name.getText());
	                WebElement menu_price = element_menu_.findElement(By.cssSelector("div > em.price_menu"));
	                System.out.println("menu_price : "+menu_price.getText());
	                	 
	                //String menu 	  = menu_name.getText();
	                //int    menu_price = Integer.parseInt(menu_price_.getText().substring(0,date_revise.length()-1));
	                
	                //storeReqeustMenuVo.setMenu(menu);
	                //storeReqeustMenuVo.setPrice(menu_price);
	                //System.out.println(storeReqeustMenuVo.getMenu());
	                //System.out.println(storeReqeustMenuVo.getPrice());
	                }
	                	 
	                
	                 
	        // WebDriver 종료
	        driver.quit();
		
		return storeRequestVo;
	}
	
	
	*/
	public HashMap<String, Object> crawller(List<String> detailPages, StoreRequestVo storeRequestVo) throws Exception {
		
		Crawlling crawlling = new Crawlling();
		
		return crawlling.new_crawller(detailPages, storeRequestVo );
		
	}
}
