package com.org.makgol.stores.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.org.makgol.stores.dao.StoreDao;
import com.org.makgol.stores.data.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.data.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.data.vo.StoreRequestMenuVo;
import com.org.makgol.stores.data.vo.StoreRequestVo;
import com.org.makgol.util.Crawller;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreService {
	
	private final RestTemplate restTemplate;
	private final HttpHeaders headers;
	private final StoreDao storeDao;
	
	public KakaoLocalResponseJSON callKakaoLocalAPI(KakaoLocalRequestVo searchRequestVo) {
		String x = searchRequestVo.getX();
		String y = searchRequestVo.getY();
		String keyword = searchRequestVo.getKeyword();
		int radius = searchRequestVo.getRadius();
		int size = searchRequestVo.getSize();
		int page = searchRequestVo.getPage();

		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("https://dapi.kakao.com/v2/local/search/keyword.json")
				.queryParam("y", y)
				.queryParam("x", x)
				.queryParam("query", keyword)
				.queryParam("radius", radius)
				.queryParam("size", size)
				.queryParam("page", page)
				.queryParam("category_group_code", "FD6")
				.build();

		headers.set("Authorization", "KakaoAK e2a97497252d13a304751d99a85ea67c");

		HttpEntity<String> request = new HttpEntity<>(headers);
		
		
		return restTemplate.exchange(uri.toString(), HttpMethod.GET, request, KakaoLocalResponseJSON.class).getBody();
	}

	public void getMenu(List<StoreRequestVo> storeRequestVoList) {
		
		
		Crawller crawller = new Crawller();
		HashMap<String, Object> storeMap = new HashMap<String, Object>();
		
		try {
			storeMap = crawller.new_crawller(storeRequestVoList);
			
		} catch(Exception e ) {e.getStackTrace();}
		 
		
		for(int index=0; index < storeMap.size()-1; index ++) {
			
			StoreRequestVo storeRequestVo = (StoreRequestVo) storeMap.get("store_info_"+index);
			List<StoreRequestMenuVo> storeRequestMenuVoList = (List<StoreRequestMenuVo>) storeMap.get("store_menu_"+index);
				
				System.err.println("-----------------" + ++index + "------------------------");
				System.out.println("식당 이름  -->  :" +storeRequestVo.getName());
				System.out.println("식당 주소 -->  :" +storeRequestVo.getAddress());
				System.out.println("식당 카테고리 -->  :" +storeRequestVo.getCategory());
				System.out.println("식당 좋아요 -->  :" +storeRequestVo.getLikes());
				System.out.println("식당 도로명주소  -->  :" +storeRequestVo.getLoad_address());
				System.out.println("식당 경도  -->  :" +storeRequestVo.getLongitude());
				System.out.println("식당 위도  -->  :" +storeRequestVo.getLatitude());
				System.out.println("식당 전화번호  -->  :" +storeRequestVo.getPhone());
				System.out.println("식당 상세페이지  -->  :" +storeRequestVo.getPlace_url());
				System.out.println("식당 운영시간  -->  :" +storeRequestVo.getOpening_hours());
				System.out.println("식당 사이트  -->  :" +storeRequestVo.getSite());
				System.out.println("식당 업데이트날자  -->  :" +storeRequestVo.getUpdate_date());
				System.out.println("식당 메뉴없데이트 날자  -->  :" +storeRequestVo.getMenu_update());
				
				for(int menuIndex = 0; menuIndex < storeRequestVoList.size(); menuIndex++) {
					System.out.println("메뉴 :  " + storeRequestMenuVoList.get(menuIndex).getMenu() + ",   가격  :  "+ storeRequestMenuVoList.get(menuIndex).getPrice());
				}
			}
			
		try {
			storeDao.insertStore(storeMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
}
