package com.org.makgol.stores.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequestMenuVo {
	
	String menu;
	String price;
	
	
	@Builder
	public StoreRequestMenuVo (String menu, String price) {
		this.menu = menu;
		this.price = price;
	}

}
