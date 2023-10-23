package com.org.makgol.stores.data.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequestMenuVo {
	
	//식당 메뉴
	String menu;
	//식당 메뉴 가격
	String price;
	
	
	@Builder
	public StoreRequestMenuVo (String menu, String price) {
		this.menu = menu;
		this.price = price;
	}

}
