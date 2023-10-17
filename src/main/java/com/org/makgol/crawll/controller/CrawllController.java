package com.org.makgol.crawll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.org.makgol.crawll.service.CrawllService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CrawllController {
	
	private final CrawllService crawllService ; 
	
	
	@GetMapping("/crawll")
	public String crawlling() {
		
		crawllService.crawlling();
		
		return "home";
	}

}
