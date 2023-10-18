package com.org.makgol.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;

public class Crawlling {
	
    public HashMap<String, Object> new_crawller(List<String> detailPages, StoreRequestVo storeRequestVo) throws Exception {
	    
    	StoreRequestMenuVo storeRequestMenuVo = new StoreRequestMenuVo();
    	
    	// 반환할 결과를 저장할 HashMap 생성
        HashMap<String, Object> hashMap = new HashMap<>();

        String driverPath = "D:\\SunhyeonSpring\\mak_gol\\src\\driver\\chromedriver.exe";
    	
    	List<JobThread> jobThreads = new ArrayList<>();
    	
    	
    	for(int i=0; i < detailPages.size(); i++) {
    		JobThread jobThread = new JobThread(driverPath, detailPages.get(i), storeRequestVo, storeRequestMenuVo, hashMap, i);
        	jobThread.start();
        	jobThreads.add(jobThread);
    	}
        
        for(JobThread jobThread: jobThreads) {
        	jobThread.join();
        }
        
        return hashMap;
    }
    
    private class JobThread extends Thread {
    	
    	private String driverPath;
    	private String detailPage;
    	private StoreRequestVo storeRequestVo;
    	private StoreRequestMenuVo storeRequestMenuVo;
    	private HashMap<String, Object> hashMap;
    	private int thread_count;
    	
    	public JobThread(String driverPath, String detailPage, StoreRequestVo storeRequestVo, StoreRequestMenuVo storeRequestMenuVo, HashMap<String, Object> hashMap,int thread_count) {
    		
    		this.driverPath = driverPath;
    		this.detailPage = detailPage;
    		this.storeRequestVo = storeRequestVo;
    		this.storeRequestMenuVo = storeRequestMenuVo;
    		this.hashMap = hashMap;
    		this.thread_count = thread_count;
    	}
    	
    	@Override
        public void run(){processCawller();}
    	
    	
    	public void processCawller() {
    		
    		// 반환할 결과를 저장할 HashMap 생성
            
    		StoreRequestMenuVo storeRequestMenuVo = new StoreRequestMenuVo();
    		
    		System.setProperty("webdriver.chrome.driver", driverPath); // 윈도우

            // 크롬 옵션 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("headless");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--blink-settings=imagesEnabled=false");
            
            WebDriver driver = new ChromeDriver(options);
            
            
            // 웹 페이지로 이동
            driver.get(detailPage);
            
            try {
            	Thread.sleep(1000);	
            } catch (Exception e) {
            	e.printStackTrace();
            }

            // 검색 버튼을 찾아서 클릭
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[3]/a"));
            searchButton.click();

            // 웹 페이지에서 업데이트된 정보 가져오기
            WebElement element 			= searchButton.findElement(By.xpath("//*[@id=\"mArticle\"]"))
            		;
            WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
            WebElement time_operation   = element.findElement(By.cssSelector("span.time_operation"));
            
            
            WebElement location_present;
            String site;
            
            try {
                location_present = element.findElement(By.cssSelector("div.details_placeinfo > div.placeinfo_default.placeinfo_homepage > div > div > a"));
                site = location_present.getAttribute("href");
                storeRequestVo.setSite(site);
    		} catch (Exception e) {
    			}
            WebElement txt_contact;
            String phone;
            
            try {
            	txt_contact = element.findElement(By.cssSelector("span.txt_contact"));
            	phone = txt_contact.getText();
    		} catch (Exception e) {
    			}
            
            WebElement update_getMenu;
            String getMenu_update;
            String date_revise;
            LocalDate update_date;
            try {
            	update_getMenu = element.findElement(By.cssSelector("span.txt_updated > span"));
            	getMenu_update = update_getMenu.getText();
            	date_revise = span_date_revise.getText();
            	
            	update_date = LocalDate.parse(date_revise.substring(0, date_revise.length() - 1), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            	storeRequestVo.setMenu_update(getMenu_update.substring(0, date_revise.length() - 1));
            	storeRequestVo.setUpdate_date(update_date);
    		} catch (Exception e) {
    			}
            
            
            

            
            String opening_hours  = time_operation.getText();
            
            
            

            // 날짜 문자열을 LocalDate 객체로 변환
            

            // StoreRequestVo 객체에 정보 저장
            
            storeRequestVo.setOpening_hours(opening_hours);
            
            
            System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getSite());
            System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getUpdate_date());
            System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getOpening_hours());
            System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getMenu_update());

            // 메뉴 정보 가져오기
            List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul > li"));

            for (WebElement element_menu_ : element_menu) {
                WebElement menu_name = element_menu_.findElement(By.cssSelector("div > span"));
                WebElement menu_price = element_menu_.findElement(By.cssSelector("div > em.price_menu"));

                String menu = menu_name.getText();
                String price = menu_price.getText();

                // StoreRequestMenuVo 객체에 메뉴 정보 저장
                storeRequestMenuVo.setMenu(menu);
                storeRequestMenuVo.setPrice(price);
                
                System.out.println("thread "+ thread_count +" : "+storeRequestMenuVo.getMenu());
                System.out.println("thread "+ thread_count +" : "+storeRequestMenuVo.getPrice());
            }
            
         // HashMap에 결과 저장
            hashMap.put("storeRequestMenuVo", storeRequestMenuVo);
            hashMap.put("storeRequestVo", storeRequestVo);

            // WebDriver 종료
            driver.quit();
    	}
    	
    }
    
}
