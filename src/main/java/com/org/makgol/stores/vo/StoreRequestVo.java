package com.org.makgol.stores.vo;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StoreRequestVo {
	
    
	private String name;
    private int likes;
    private double longitude;
    private double latitude;
    private String address;
    private String category;
    private LocalDate update_date;
    private String opening_hours;
    private String phone;
    private String site;
    private String menu_update;
    
    
    
   
}
