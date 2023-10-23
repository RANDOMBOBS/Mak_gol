package com.org.makgol.stores.data.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StoreRequestVo {
	
	//?��?��?���?
	private String    name;
	//?��?�� 좋아?�� 개수
    private int 	  likes;
    //경도
    private double    longitude;
    //?��?��
    private double    latitude;
  //주소
    private String    address;
  //주소2
    private String    load_address;
    //카테고리
    private String    category;
    //?��?��?���?
    private String    opening_hours;
    //?��?��번호
    private String    phone;
    //?��?��?��?��?��
    private String    site;
    //메뉴 ?��?��?��?�� ?���?
    private LocalDate    menu_update;
    //?��?�� ?��?��?��?���?
    private String    place_url ;
    //?��?��?���? ?��?��?��?�� ?���?
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
