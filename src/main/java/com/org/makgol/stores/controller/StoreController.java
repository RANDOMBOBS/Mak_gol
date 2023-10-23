package com.org.makgol.stores.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.makgol.stores.beans.components.HttpTransactionLogger;
import com.org.makgol.stores.data.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.data.type.KakaoLocalResponseJSON.ShopInfo;
import com.org.makgol.stores.data.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.service.StoreService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

	private final StoreService storeService;
	private final HttpTransactionLogger logger;
	
	@GetMapping(value = "/kakao-local-api")
	public String callKakaoLocalApi(KakaoLocalRequestVo kakaoLocalRequestVo) {
		logger.logRequestDto(kakaoLocalRequestVo);
		kakaoLocalRequestVo.validateVo();

		KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);
		logger.logResponseJson(kakaoResponseJSON);
		
		List<ShopInfo> shops = kakaoResponseJSON.documents;

		return "store/store_list";
	}

}