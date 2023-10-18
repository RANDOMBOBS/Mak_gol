package com.org.makgol.store.beans.components;

import com.org.makgol.store.data.dto.KakaoLocalRequestDto;
import com.org.makgol.store.data.type.KakaoLocalResponseJSON;

import org.springframework.stereotype.Component;

@Component
public class HttpTransactionLogger {

	public void logRequestDto(KakaoLocalRequestDto dto) {
		System.out.println("검색 요청 데이터");
		System.out.println("[위도]: " + dto.getX());
		System.out.println("[경도]: " + dto.getY());
		System.out.println("[키워드]: " + dto.getKeyword());
		System.out.println("[반경]: " + dto.getRadius());
		System.out.println("[식당 개수]: " + dto.getSize());
		System.out.println("[페이지 개수]: " + dto.getPage());
	}

	public void logResponseJson(KakaoLocalResponseJSON json) {
		System.out.println("검색 응답 데이터");
		System.out.println("[총 응답 개수]: " + json.documents.size());
		json.documents.forEach((shop) -> System.out.println("[식당 정보]: " + shop));
	}
}
