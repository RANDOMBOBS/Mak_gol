package com.org.makgol.stores.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.org.makgol.stores.data.vo.StoreRequestMenuVo;
import com.org.makgol.stores.data.vo.StoreRequestVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StoreDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public void insertStore(HashMap<String, Object> storeMap) throws Exception {
		
		for(int index = 0; index < storeMap.size(); index++) {
			StoreRequestVo storeRequestVo = (StoreRequestVo) storeMap.get("store_info_"+index);
			
			List<StoreRequestMenuVo> storeRequestMenuVoList = (List<StoreRequestMenuVo>) storeMap.get("store_menu_"+index);
			
			
			String insertStoresSql = "INSERT INTO Stores (name, likes, longitude, latitude, address, load_address, category, opening_hours, phone, site, menu_update, place_url, update_date) " +
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			int result = 0;
			jdbcTemplate.update(insertStoresSql, 
					storeRequestVo.getName(), 	  storeRequestVo.getLikes(), 	     storeRequestVo.getLongitude(), 
					storeRequestVo.getLatitude(), storeRequestVo.getAddress(),	     storeRequestVo.getLoad_address(), 
					storeRequestVo.getCategory(), storeRequestVo.getOpening_hours(), storeRequestVo.getPhone(), 
					storeRequestVo.getSite(),     storeRequestVo.getMenu_update(),   storeRequestVo.getPlace_url(), 
					storeRequestVo.getUpdate_date());
			
			String sql = "SELECT id FROM menu WHERE phone = ?";
			int store_id = jdbcTemplate.queryForObject(sql, new Object[] { storeRequestVo.getPhone() }, Integer.class);
			
			for(int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
				String insertStoresMenuSql = "IINSERT INTO Menu (store_id, item_name, price) \n"
						+ "VALUES (?, ?, ?);";
				
				jdbcTemplate.update(insertStoresMenuSql, 
						store_id,
						storeRequestMenuVoList.get(menuIndex).getMenu(),
						storeRequestMenuVoList.get(menuIndex).getPrice()
						);
			}
			
			
			
		}
		
		

	}
}
	
