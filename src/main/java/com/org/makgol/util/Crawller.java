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
	 * 				??Ή ? λ³? : "store_info_" +index
	 * 				??Ή λ©λ΄ : "store_menu_" +index
	 * 				-----------------------------
	 * @throws Exception
	 */
    public HashMap<String, Object> new_crawller(List<StoreRequestVo> storeRequestVos) throws Exception {
    	
    	// κ²°κ³Όλ₯? ???₯?  HashMap ??±
        HashMap<String, Object> hashMap = new HashMap<>();

        // ??Ό?΄λ²? κ²½λ‘ ???° 
        String driverPath = "D:\\SunhyeonSpring\\makgol\\src\\driver\\chromedriver.exe";
        // ??Ό?΄λ²? ? ??κ²½λ‘ λ§?
        //String driverPath = "/Volumes/Data/developer/eclipse-workspace/makgol/src/driver/chromedriver";
        
        //?€? ?λ₯? μ’λ£?κΈ°μ? List
    	List<JobThread> jobThreads = new ArrayList<>();
    	
    	//storeRequestVos? ?¬?΄μ¦? λ§κΈ?€? ?λ₯? ??±?κ² λ€.
    	for(int i=0; i < storeRequestVos.size(); i++) {
    		//?€? ?? μ£Όμκ°μ ?κ²¨μ€
    		JobThread jobThread = new JobThread(driverPath, storeRequestVos.get(i).getPlace_url(), storeRequestVos.get(i), hashMap, i);
        	jobThread.start();
        	jobThreads.add(jobThread);
    	}
        
    	// ?€? ? μ’λ£
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
    		
    		// ??Ή? λ©λ΄?€λ₯? ???₯?  List
    		List<StoreRequestMenuVo> storeRequestMenuVos = new ArrayList<StoreRequestMenuVo>();
    		
    		
    		
    		//chrome driver κ²½λ‘ ?Έ?
    		System.setProperty("webdriver.chrome.driver", "D:\\SunhyeonSpring\\makgol\\src\\driver\\chromedriver.exe"); 
            // ?¬λ‘? ?΅? ?€? 
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");	//λΈλΌ?°??κ°? ?€λ₯? μΆμ²(origin)? λ¦¬μ?€? ??? ?μ²?? ???  ???.
            options.addArguments("headless"); 					//λΈλΌ?°?? μ°½μ ???μ§? ?κ³? λ°±κ·Έ?Ό?΄??? ?Ή ??΄μ§?λ₯? ?€??? λͺ¨λ.
            options.addArguments("--disable-popup-blocking");	//?? μ°¨λ¨? λΉν?±??? ?΅?.
            options.addArguments("--blink-settings=imagesEnabled=false");//?Ή ??΄μ§??? ?΄λ―Έμ? λ‘λ©? λΉν?±?.
            WebDriver driver = new ChromeDriver(options);
            
            // ?Ή ??΄μ§?λ‘? ?΄? ? μ½λ κ°?? Έ?΄
            driver.get(detailPage);
            
            // λ‘λ© κΈ°λ€λ¦¬κΈ°
            try {
            	Thread.sleep(1000);	
            } catch (Exception e) {
            	e.printStackTrace();
            }

            // κ²?? λ²νΌ? μ°Ύμ? ?΄λ¦?
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[3]/a"));
            searchButton.click();

            // ?Ή ??΄μ§??? ?κ·Έλ?? μ°Ύμ? ?°?΄?° κ°?? Έ?€κΈ?
            WebElement element 			= searchButton.findElement(By.xpath("//*[@id=\"mArticle\"]"));
            WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
            WebElement time_operation   = element.findElement(By.cssSelector("span.time_operation"));
            
            
            // ?¬λ‘€λ§ λ©μΆ€ λ°©μ??© try catch 
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
            
            //?°?΄?° ??Έ
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getSite());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getUpdate_date());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getOpening_hours());
            //System.out.println("thread "+ thread_count +" : "+ storeRequestVo.getMenu_update());

            // λ©λ΄ ? λ³? κ°?? Έ?€κΈ?
            List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul > li"));

            for (WebElement element_menu_ : element_menu) {
            	//λ©λ΄ ?΄λ¦μ΄ ?΄κΈ? ?κ·? ??κ²ν
                WebElement menu_name = element_menu_.findElement(By.cssSelector("div > span"));
                //λ©λ΄ κ°?κ²©μ΄ ?΄κΈ? ?κ·? ??κ²ν
                WebElement menu_price = element_menu_.findElement(By.cssSelector("div > em.price_menu"));

                //λ©λ΄ ?΄λ¦? κ°?? Έ?€κΈ?
                String menu = menu_name.getText();
                //λ©λ΄ κ°?κ²? κ°?? Έ?€κΈ?
                String price = menu_price.getText();
                
                //??Ή? λ©λ΄λ₯? ???₯?  κ°μ²΄
        		StoreRequestMenuVo storeRequestMenuVo = new StoreRequestMenuVo();
                
                //λ©λ΄ κ°μ²΄? ?΄κΈ?
                storeRequestMenuVo.setMenu(menu);
                storeRequestMenuVo.setPrice(price);
                
                storeRequestMenuVos.add(storeRequestMenuVo);
            }
            
         // HashMap? κ²°κ³Ό ???₯
            synchronized (this) {
            	hashMap.put("store_menu_"+thread_count, storeRequestMenuVos);
            	hashMap.put("store_info_"+thread_count, storeRequestVo);
            }
            

            // WebDriver μ’λ£
            driver.quit();
    	}
    	
    }
    
}
