package com.org.makgol.stores.vo;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
public class StoreVO {
	
	private int id;
    private String name;
    private int likes;
    private double longitude;
    private double latitude;
    private String address;
    private String category;
    private LocalDate update_date;
    private String opening_hours;
    private String detail_Page;
    private String phone;
    
    
    @Builder
    public StoreVO(String opening_hours, String detail_Page, LocalDate update_date, String phone) {
        this.opening_hours = opening_hours;
        this.detail_Page = detail_Page;
        this.update_date = update_date;
        this.phone = phone;
    }
   
}
