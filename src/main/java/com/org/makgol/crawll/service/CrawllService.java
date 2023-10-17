package com.org.makgol.crawll.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import com.org.makgol.stores.vo.StoreVO;

import lombok.Data;


@Service
public class CrawllService {
	
	public void crawlling() {
		
		try {
			
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

	        	 driver.get("https://place.map.kakao.com/19197483");
	        	 
	             System.out.println(driver.getPageSource());
	           
	             
	             Thread.sleep(500);
	             // 업데이트 날자를 가져옵니다.
	             WebElement element = driver.findElement(By.xpath("//*[@id=\"mArticle\"]"));
	             System.out.println("mArticle");
	             
	             //System.out.println("엘리멘트 SIZE --> : " + elements.size());
	             System.out.println("엘리멘트 --> : " + element.getText());


	                 WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
	                 System.out.println(span_date_revise.getText());
	                 WebElement time_operation = element.findElement(By.cssSelector("span.time_operation"));
	                 System.out.println(time_operation.getText());
	                 WebElement location_present = element.findElement(By.cssSelector("div.details_placeinfo > div.placeinfo_default.placeinfo_homepage > div > div > a"));
	                 System.out.println(location_present.getAttribute("href"));
	                 WebElement txt_contact = element.findElement(By.cssSelector("span.txt_contact"));
	                 System.out.println(txt_contact.getText());
	        	
	                 
	                 System.out.println("test1");
	                 String date_revise = span_date_revise.getText();
	                 String opening_hours = time_operation.getText();
	                 String detail_Page = location_present.getAttribute("href");
	                 String phone = txt_contact.getText();
	                 LocalDate update_date = LocalDate.parse(date_revise.substring(0,date_revise.length()-1), DateTimeFormatter.ofPattern("yyyy.MM.dd")); // 날짜 문자열을 LocalDate 객체로 변환합니다.
	                 
	             System.out.println("test2");

	                 // Notice 객체 생성 및 저장
	                 StoreVO store = StoreVO.builder()
	                         .update_date(update_date)
	                         .opening_hours(opening_hours)
	                         .detail_Page(detail_Page)
	                         .phone(phone)
	                         .build();

	                 // Notice 객체를 DB에 저장합니다.
	                 //storeRepository.save(store);

	                 // 저장된 Notice 객체를 NoticeDto로 변환하여 리스트에 추가합니다.
	                 
	                List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul li"));
	                 
	                 for(WebElement menu : element_menu) {
	                	 
	                	 menus.add(menu.getText());
	                	 
	                	
	                 }
	                 System.out.println(menus.toString());
	                 
	                 System.out.println(store.toString());

	        // WebDriver 종료
	        driver.quit();
			
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
}
