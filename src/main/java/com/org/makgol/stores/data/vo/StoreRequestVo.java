package com.org.makgol.stores.data.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StoreRequestVo {
	
	//?‹?‹¹?´ë¦?
	private String    name;
	//?‹?‹¹ ì¢‹ì•„?š” ê°œìˆ˜
    private int 	  likes;
    //ê²½ë„
    private double    longitude;
    //?œ„?„
    private double    latitude;
  //ì£¼ì†Œ
    private String    address;
  //ì£¼ì†Œ2
    private String    load_address;
    //ì¹´í…Œê³ ë¦¬
    private String    category;
    //?š´?˜?‹œê°?
    private String    opening_hours;
    //? „?™”ë²ˆí˜¸
    private String    phone;
    //?‹?‹¹?‚¬?´?Š¸
    private String    site;
    //ë©”ë‰´ ?—…?°?´?Š¸ ?‚ ì§?
    private LocalDate    menu_update;
    //?‹?‹¹ ?ƒ?„¸?˜?´ì§?
    private String    place_url ;
    //?‹?‹¹? •ë³? ?—…?°?´?Š¸ ?‚ ì§?
    private LocalDate update_date;
    
    
	@Builder
    public StoreRequestVo(String address_name, String category_name, String distance, String phone, String place_name,String place_url, String road_address_name, String x, String y) {
    	this.name = place_name;
    	this.likes = 0;
    	this.longitude = Double.parseDouble(x);
    	this.latitude = Double.parseDouble(y);
    	this.address = address_name;
    	this.load_address = road_address_name;
    	this.category = category_name;
    	this.phone = phone;
    	this.place_url = place_url;
    }
	
	@Builder
    public StoreRequestVo(String opening_hours, String site,  LocalDate menu_update, LocalDate update_date ) {
    	this.opening_hours = opening_hours;
    	this.site = site;
    	this.menu_update = menu_update;  
    	this.update_date = update_date;
    }
    
    
   
}
