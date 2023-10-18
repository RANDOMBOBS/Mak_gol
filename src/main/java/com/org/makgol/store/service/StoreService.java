package com.org.makgol.store.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.org.makgol.store.data.type.KakaoLocalResponseJSON;
import com.org.makgol.store.data.dto.KakaoLocalRequestDto;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreService {
	
	private final RestTemplate restTemplate;
	private final HttpHeaders headers;
	
	public KakaoLocalResponseJSON callKakaoLocalAPI(KakaoLocalRequestDto searchRequestDto) {
		String x = searchRequestDto.getX();
		String y = searchRequestDto.getY();
		String keyword = searchRequestDto.getKeyword();
		int radius = searchRequestDto.getRadius();
		int size = searchRequestDto.getSize();
		int page = searchRequestDto.getPage();

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
	
}
