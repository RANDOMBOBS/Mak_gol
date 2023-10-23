package com.org.makgol.stores.data.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StoreRequestVo {
	
	//??Ή?΄λ¦?
	private String    name;
	//??Ή μ’μ? κ°μ
    private int 	  likes;
    //κ²½λ
    private double    longitude;
    //??
    private double    latitude;
  //μ£Όμ
    private String    address;
  //μ£Όμ2
    private String    load_address;
    //μΉ΄νκ³ λ¦¬
    private String    category;
    //?΄??κ°?
    private String    opening_hours;
    //? ?λ²νΈ
    private String    phone;
    //??Ή?¬?΄?Έ
    private String    site;
    //λ©λ΄ ??°?΄?Έ ? μ§?
    private LocalDate    menu_update;
    //??Ή ??Έ??΄μ§?
    private String    place_url ;
    //??Ή? λ³? ??°?΄?Έ ? μ§?
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
