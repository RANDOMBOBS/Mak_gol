package com.org.makgol.crawll.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.org.makgol.crawll.service.CrawllService;
import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CrawllController {
	
	private final CrawllService crawllService ; 
	
	
	@GetMapping("/crawll")
	public String crawlling() throws Exception{
		List<String> detailPages = new ArrayList<String>();
		detailPages.add("https://place.map.kakao.com/785573131");
		detailPages.add("https://place.map.kakao.com/11493134");
		
		
		StoreRequestVo storeRequestVo = new StoreRequestVo();
		
		storeRequestVo.setAddress("setAddress");
		storeRequestVo.setCategory("setCategory");
		storeRequestVo.setPhone("setPhone");
		storeRequestVo.setName("setName");
		storeRequestVo.setLikes(0);
		storeRequestVo.setLatitude(127.027589);
		storeRequestVo.setLatitude(37.498102);
		
		HashMap<String, Object> hashMap = crawllService.crawller(detailPages, storeRequestVo);
		
		StoreRequestMenuVo storeRequestMenuVo = (StoreRequestMenuVo) hashMap.get("storeRequestMenuVo");
		
		
		System.out.println(storeRequestMenuVo.getMenu());
		System.out.println(storeRequestMenuVo.getPrice());
		
		//crawllService.crawlling("https://place.map.kakao.com/785573131", storeRequestVo);
		
		return "home";
	}

}
