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

import com.org.makgol.stores.data.vo.StoreRequestMenuVo;
import com.org.makgol.stores.data.vo.StoreRequestVo;


public class Crawller {
	/**
	 * @param List<StoreRequestVo> storeRequestVos
	 * 				------StoreRequestVo---------
	 * 				...
	 * 				...
	 * 				...
	 * 				...
	 * 
	 * @return HashMap<String, Object>
	 * 				---------HashMap KEY--------
	 * 				?��?�� ?���? : "store_info_" +index
	 * 				?��?�� 메뉴 : "store_menu_" +index
	 * 				-----------------------------
	 * @throws Exception
	 */
    public HashMap<String, Object> new_crawller(List<StoreRequestVo> storeRequestVos) throws Exception {
    	
    	// 결과�? ???��?�� HashMap ?��?��
        HashMap<String, Object> hashMap = new HashMap<>();

        // ?��?��?���? 경로 ?��?��?�� 
        String driverPath = "D:\\SunhyeonSpring\\makgol\\src\\driver\\chromedriver.exe";
        // ?��?��?���? ?��??경로 �?
        //String driverPath = "/Volumes/Data/developer/eclipse-workspace/makgol/src/driver/chromedriver";
        
        //?��?��?���? 종료?��기위?�� List
    	List<JobThread> jobThreads = new ArrayList<>();
    	
    	//storeRequestVos?�� ?��?���? 많금?��?��?���? ?��?��?��겠다.
    	for(int i=0; i < storeRequestVos.size(); i++) {
    		//?��?��?��?�� 주소값을 ?��겨줌
    		JobThread jobThread = new JobThread(driverPath, storeRequestVos.get(i).getPlace_url(), storeRequestVos.get(i), hashMap, i);
        	jobThread.start();
        	jobThreads.add(jobThread);
    	}
        
    	// ?��?��?�� 종료
        for(JobThread jobThread: jobThreads) { jobThread.join(); }
        
        return hashMap;
    }
    
    private class JobThread extends Thread {
    	
    	private String driverPath;
    	private String detailPage;
    	private StoreRequestVo storeRequestVo;
    	private HashMap<String, Object> hashMap;
    	private int thread_count;
    	
    	public JobThread(String driverPath, String detailPage, StoreRequestVo storeRequestVo, HashMap<String, Object> hashMap,int thread_count) {
    		
    		this.driverPath = driverPath;
    		this.detailPage = detailPage;
    		this.storeRequestVo = storeRequestVo;
    		this.hashMap = hashMap;
    		this.thread_count = thread_count;
    	}
    	
    	@Override
        public void run(){processCawller();}
    	
    	
    	public void processCawller() {
    		
    		// ?��?��?�� 메뉴?���? ???��?�� List
    		List<StoreRequestMenuVo> storeRequestMenuVos = new ArrayList<StoreRequestMenuVo>();
    		
    		
    		
    		//chrome driver 경로 ?��?��
    		System.setProperty("webdriver.chrome.driver", "D:\\SunhyeonSpring\\makgol\\src\\driver\\chromedriver.exe"); 
            // ?���? ?��?�� ?��?��
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");	//브라?��??�? ?���? 출처(origin)?�� 리소?��?�� ???�� ?���??�� ?��?��?�� ?��?��?��.
            options.addArguments("headless"); 					//브라?��?? 창을 ?��?��?���? ?���? 백그?��?��?��?��?�� ?�� ?��?���?�? ?��?��?��?�� 모드.
            options.addArguments("--disable-popup-blocking");	//?��?�� 차단?�� 비활?��?��?��?�� ?��?��.
            options.addArguments("--blink-settings=imagesEnabled=false");//?�� ?��?���??��?�� ?��미�? 로딩?�� 비활?��?��.
            WebDriver driver = new ChromeDriver(options);
            
            // ?�� ?��?���?�? ?��?�� ?�� 코드 �??��?��
            driver.get(detailPage);
            
            // 로딩 기다리기
            try {
            	Thread.sleep(1000);	
            } catch (Exception e) {
            	e.printStackTrace();
            }

            // �??�� 버튼?�� 찾아?�� ?���?
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[3]/a"));
            searchButton.click();

            // ?�� ?��?���??��?�� ?��그�?? 찾아?�� ?��?��?�� �??��?���?
            WebElement element 			= searchButton.findElement(By.xpath("//*[@id=\"mArticle\"]"));
            WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
            WebElement time_operation   = element.findElement(By.cssSelector("span.time_operation"));
            
            
            // ?��롤링 멈춤 방�??�� try catch 
            WebElement location_present;
            String site;
            
            try {
                location_present = element.findElement(By.cssSelector("div.details_placeinfo > div.placeinfo_default.placeinfo_homepage > div > div > a"));
                site = location_present.getAttribute("href");
                storeRequestVo.setSite(site);
    		} catch (Exception e) {}
            
            WebElement txt_contact;
            String phone;
            
            try {
            	txt_contact = element.findElement(By.cssSelector("span.txt_contact"));
            	phone = txt_contact.getText();
    		} catch (Exception e) {}
            
            WebElement update_getMenu;
            String getMenu_update;
            String date_revise;
            LocalDate update_date;
            LocalDate menu_update;
            
            try {
            	update_getMenu = element.findElement(By.cssSelector("span.txt_updated > span"));
            	getMenu_update = update_getMenu.getText();
            	date_revise = span_date_revise.getText();
            	
            	update_date = LocalDate.parse(date_revise.substring(0, date_revise.length() - 1), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            	menu_update = LocalDate.parse(getMenu_update.substring(0, date_revise.length() - 1),  DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            	storeRequestVo.setMenu_update(menu_update);
            	storeRequestVo.setUpdate_date(update_date);
    		} catch (Exception e) {}
            
            
            String opening_hours  = time_operation.getText();
            storeRequestVo.setOpening_hours(opening_hours);
            
            //?��?��?�� ?��?��
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getSite());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getUpdate_date());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getOpening_hours());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getMenu_update());

            // 메뉴 ?���? �??��?���?
            List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul > li"));

            for (WebElement element_menu_ : element_menu) {
            	//메뉴 ?��름이 ?���? ?���? ??겟팅
                WebElement menu_name = element_menu_.findElement(By.cssSelector("div > span"));
                //메뉴 �?격이 ?���? ?���? ??겟팅
                WebElement menu_price = element_menu_.findElement(By.cssSelector("div > em.price_menu"));

                //메뉴 ?���? �??��?���?
                String menu = menu_name.getText();
                //메뉴 �?�? �??��?���?
                String price = menu_price.getText();
                
                //?��?��?�� 메뉴�? ???��?�� 객체
        		StoreRequestMenuVo storeRequestMenuVo = new StoreRequestMenuVo();
                
                //메뉴 객체?�� ?���?
                storeRequestMenuVo.setMenu(menu);
                storeRequestMenuVo.setPrice(price);
                
                storeRequestMenuVos.add(storeRequestMenuVo);
            }
            
         // HashMap?�� 결과 ???��
            synchronized (this) {
            	hashMap.put("store_menu_"+thread_count, storeRequestMenuVos);
            	hashMap.put("store_info_"+thread_count, storeRequestVo);
            }
            

            // WebDriver 종료
            driver.quit();
    	}
    	
    }
    
}
