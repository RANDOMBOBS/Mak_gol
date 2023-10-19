package com.org.makgol.store.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.makgol.store.beans.components.HttpTransactionLogger;
import com.org.makgol.store.data.dto.KakaoLocalRequestDto;
import com.org.makgol.store.data.type.KakaoLocalResponseJSON;
import com.org.makgol.store.data.type.KakaoLocalResponseJSON.ShopInfo;
import com.org.makgol.store.service.StoreService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

	private final StoreService storeService;
	private final HttpTransactionLogger logger;
	
	@GetMapping(value = "/kakao-local-api")
	public String callKakaoLocalApi(KakaoLocalRequestDto kakaoLocalRequestDto) {
		logger.logRequestDto(kakaoLocalRequestDto);
		kakaoLocalRequestDto.validateDto();

		KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestDto);
		logger.logResponseJson(kakaoResponseJSON);
		
		List<ShopInfo> shops = kakaoResponseJSON.documents;

		System.out.println(shops.toString());

		return "store/store_list";
	}

}