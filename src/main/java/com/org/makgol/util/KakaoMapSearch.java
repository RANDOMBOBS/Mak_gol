package com.org.makgol.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.org.makgol.stores.beans.components.HttpTransactionLogger;
import com.org.makgol.stores.data.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.data.type.KakaoLocalResponseJSON.ShopInfo;
import com.org.makgol.stores.data.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.data.vo.StoreRequestVo;
import com.org.makgol.stores.service.StoreService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoMapSearch {

	private final StoreService storeService;

	public void search(String[] categories, KakaoLocalRequestVo kakaoLocalRequestVo) {
		List<StoreRequestVo> storeRequestVoList = new ArrayList<StoreRequestVo>();

		// 음식 카테고리 매핑
		for (int i = 0; i < categories.length; i++) {

			kakaoLocalRequestVo.setKeyword(categories[i]);
			KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);
			// ShopInfo 리스트를 가져온다
			List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
			// logger.logResponseJson(kakaoResponseJSON);
			//HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
			//httpTransactionLogger.logResponseJson(kakaoResponseJSON);

			// ShopInfo를 StoreRequestVo로 매핑
			List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(ShopInfo::mapToStoreRequestVo)
					.collect(Collectors.toList());

			storeRequestVoList.addAll(storeRequestVoListAdd);

			for (StoreRequestVo storeRequestVo : storeRequestVoList) {
				storeRequestVo.setKeyword(categories[i]);
			}
		}
		
		restApiCrawller(storeRequestVoList);
	}

	public void searchMenu(String[] categories, KakaoLocalRequestVo kakaoLocalRequestVo) {

		List<StoreRequestVo> storeRequestVoList = new ArrayList<StoreRequestVo>();

		// 음식 카테고리 매핑
		for (int i = 1; i < categories.length; i++) {

			kakaoLocalRequestVo.setKeyword(categories[i]);
			KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);
			// ShopInfo 리스트를 가져온다
			List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
			// logger.logResponseJson(kakaoResponseJSON);
			//HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
			//httpTransactionLogger.logResponseJson(kakaoResponseJSON);

			// ShopInfo를 StoreRequestVo로 매핑
			List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(ShopInfo::mapToStoreRequestVo)
					.collect(Collectors.toList());

			storeRequestVoList.addAll(storeRequestVoListAdd);

			for (StoreRequestVo storeRequestVo : storeRequestVoList) {
				storeRequestVo.setKeyword(categories[0]);
			} 
		}
		System.out.println(storeRequestVoList.size());
		restApiCrawller(storeRequestVoList);
	}
	
	public void restApiCrawller(List<StoreRequestVo> storeRequestVoList) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8090/api/v1/crawl/kakaoStoreCrwall";
		
		// HTTP 요청 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HTTP 요청 본문에 List<StoreRequestVo> 객체 추가
		HttpEntity<List<StoreRequestVo>> request = new HttpEntity<>(storeRequestVoList, headers);

		// 서버로 HTTP GET 요청 보내기
		restTemplate.exchange(
			    url,
			    HttpMethod.POST,
			    request,
			    new ParameterizedTypeReference<HashMap<String, Object>>() {}
			);

	}
}
