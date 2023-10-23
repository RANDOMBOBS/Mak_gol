package com.org.makgol.stores.data.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequestMenuVo {
	
	int store_id;
	//?��?�� 메뉴
	String menu;
	//?��?�� 메뉴 �?�?
	String price;
	
	
	@Builder
	public StoreRequestMenuVo (String menu, String price ,String category) {
		this.menu = menu;
		this.price = price;
	}

}
