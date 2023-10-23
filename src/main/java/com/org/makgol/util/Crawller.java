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
	 * 				?ãù?ãπ ?†ïÎ≥? : "store_info_" +index
	 * 				?ãù?ãπ Î©îÎâ¥ : "store_menu_" +index
	 * 				-----------------------------
	 * @throws Exception
	 */
    public HashMap<String, Object> new_crawller(List<StoreRequestVo> storeRequestVos) throws Exception {
    	
    	// Í≤∞Í≥ºÎ•? ???û•?ï† HashMap ?Éù?Ñ±
        HashMap<String, Object> hashMap = new HashMap<>();

        // ?ìú?ùº?ù¥Î≤? Í≤ΩÎ°ú ?úà?èÑ?ö∞ 
        String driverPath = "D:\\SunhyeonSpring\\makgol\\src\\driver\\chromedriver.exe";
        // ?ìú?ùº?ù¥Î≤? ?†à??Í≤ΩÎ°ú Îß?
        //String driverPath = "/Volumes/Data/developer/eclipse-workspace/makgol/src/driver/chromedriver";
        
        //?ä§?†à?ìúÎ•? Ï¢ÖÎ£å?ïòÍ∏∞ÏúÑ?ïú List
    	List<JobThread> jobThreads = new ArrayList<>();
    	
    	//storeRequestVos?ùò ?Ç¨?ù¥Ï¶? ÎßéÍ∏à?ä§?†à?ìúÎ•? ?Éù?Ñ±?ïòÍ≤†Îã§.
    	for(int i=0; i < storeRequestVos.size(); i++) {
    		//?ä§?†à?ìú?óê Ï£ºÏÜåÍ∞íÏùÑ ?ÑòÍ≤®Ï§å
    		JobThread jobThread = new JobThread(driverPath, storeRequestVos.get(i).getPlace_url(), storeRequestVos.get(i), hashMap, i);
        	jobThread.start();
        	jobThreads.add(jobThread);
    	}
        
    	// ?ä§?†à?ìú Ï¢ÖÎ£å
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
    		
    		// ?ãù?ãπ?ùò Î©îÎâ¥?ì§Î•? ???û•?ï† List
    		List<StoreRequestMenuVo> storeRequestMenuVos = new ArrayList<StoreRequestMenuVo>();
    		
    		
    		
    		//chrome driver Í≤ΩÎ°ú ?Ñ∏?åÖ
    		System.setProperty("webdriver.chrome.driver", "D:\\SunhyeonSpring\\makgol\\src\\driver\\chromedriver.exe"); 
            // ?Å¨Î°? ?òµ?Öò ?Ñ§?†ï
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");	//Î∏åÎùº?ö∞??Í∞? ?ã§Î•? Ï∂úÏ≤ò(origin)?ùò Î¶¨ÏÜå?ä§?óê ???ïú ?öîÏ≤??ùÑ ?àò?ñâ?ï† ?àò?ûà?ùå.
            options.addArguments("headless"); 					//Î∏åÎùº?ö∞?? Ï∞ΩÏùÑ ?ëú?ãú?ïòÏß? ?ïäÍ≥? Î∞±Í∑∏?ùº?ö¥?ìú?óê?Ñú ?õπ ?éò?ù¥Ïß?Î•? ?ã§?ñâ?ïò?äî Î™®Îìú.
            options.addArguments("--disable-popup-blocking");	//?åù?óÖ Ï∞®Îã®?ùÑ ÎπÑÌôú?Ñ±?ôî?ïò?äî ?òµ?Öò.
            options.addArguments("--blink-settings=imagesEnabled=false");//?õπ ?éò?ù¥Ïß??óê?Ñú ?ù¥ÎØ∏Ï? Î°úÎî©?ùÑ ÎπÑÌôú?Ñ±?ôî.
            WebDriver driver = new ChromeDriver(options);
            
            // ?õπ ?éò?ù¥Ïß?Î°? ?ù¥?èô ?õÑ ÏΩîÎìú Í∞??†∏?ò¥
            driver.get(detailPage);
            
            // Î°úÎî© Í∏∞Îã§Î¶¨Í∏∞
            try {
            	Thread.sleep(1000);	
            } catch (Exception e) {
            	e.printStackTrace();
            }

            // Í≤??Éâ Î≤ÑÌäº?ùÑ Ï∞æÏïÑ?Ñú ?Å¥Î¶?
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[3]/a"));
            searchButton.click();

            // ?õπ ?éò?ù¥Ïß??óê?Ñú ?ÉúÍ∑∏Î?? Ï∞æÏïÑ?Ñú ?ç∞?ù¥?Ñ∞ Í∞??†∏?ò§Í∏?
            WebElement element 			= searchButton.findElement(By.xpath("//*[@id=\"mArticle\"]"));
            WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
            WebElement time_operation   = element.findElement(By.cssSelector("span.time_operation"));
            
            
            // ?Å¨Î°§ÎßÅ Î©àÏ∂§ Î∞©Ï??ö© try catch 
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
            
            //?ç∞?ù¥?Ñ∞ ?ôï?ù∏
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getSite());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getUpdate_date());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getOpening_hours());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getMenu_update());

            // Î©îÎâ¥ ?†ïÎ≥? Í∞??†∏?ò§Í∏?
            List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul > li"));

            for (WebElement element_menu_ : element_menu) {
            	//Î©îÎâ¥ ?ù¥Î¶ÑÏù¥ ?ã¥Í∏? ?ÉúÍ∑? ??Í≤üÌåÖ
                WebElement menu_name = element_menu_.findElement(By.cssSelector("div > span"));
                //Î©îÎâ¥ Í∞?Í≤©Ïù¥ ?ã¥Í∏? ?ÉúÍ∑? ??Í≤üÌåÖ
                WebElement menu_price = element_menu_.findElement(By.cssSelector("div > em.price_menu"));

                //Î©îÎâ¥ ?ù¥Î¶? Í∞??†∏?ò§Í∏?
                String menu = menu_name.getText();
                //Î©îÎâ¥ Í∞?Í≤? Í∞??†∏?ò§Í∏?
                String price = menu_price.getText();
                
                //?ãù?ãπ?ùò Î©îÎâ¥Î•? ???û•?ï† Í∞ùÏ≤¥
        		StoreRequestMenuVo storeRequestMenuVo = new StoreRequestMenuVo();
                
                //Î©îÎâ¥ Í∞ùÏ≤¥?óê ?ã¥Í∏?
                storeRequestMenuVo.setMenu(menu);
                storeRequestMenuVo.setPrice(price);
                
                storeRequestMenuVos.add(storeRequestMenuVo);
            }
            
         // HashMap?óê Í≤∞Í≥º ???û•
            synchronized (this) {
            	hashMap.put("store_menu_"+thread_count, storeRequestMenuVos);
            	hashMap.put("store_info_"+thread_count, storeRequestVo);
            }
            

            // WebDriver Ï¢ÖÎ£å
            driver.quit();
    	}
    	
    }
    
}
