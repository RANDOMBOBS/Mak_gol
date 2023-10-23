package com.org.makgol.stores.data.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequestMenuVo {
	
	int store_id;
	//?ãù?ãπ Î©îÎâ¥
	String menu;
	//?ãù?ãπ Î©îÎâ¥ Í∞?Í≤?
	String price;
	
	
	@Builder
	public StoreRequestMenuVo (String menu, String price ,String category) {
		this.menu = menu;
		this.price = price;
	}

}
