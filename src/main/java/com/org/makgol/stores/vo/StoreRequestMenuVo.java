package com.org.makgol.stores.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequestMenuVo {
	
	int id;
	int store_id;
	String menu;
	String price;

}
