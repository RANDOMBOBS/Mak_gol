package com.org.makgol.stores.beans.components;

import org.springframework.stereotype.Component;

import com.org.makgol.stores.data.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.data.vo.KakaoLocalRequestVo;

@Component
public class HttpTransactionLogger {

	public void logRequestDto(KakaoLocalRequestVo vo) {
		System.out.println("�??�� ?���? ?��?��?��");
		System.out.println("[?��?��]: " + vo.getX());
		System.out.println("[경도]: " + vo.getY());
		System.out.println("[?��?��?��]: " + vo.getKeyword());
		System.out.println("[반경]: " + vo.getRadius());
		System.out.println("[?��?�� 개수]: " + vo.getSize());
		System.out.println("[?��?���? 개수]: " + vo.getPage());
	}

	public void logResponseJson(KakaoLocalResponseJSON json) {
		System.out.println("�??�� ?��?�� ?��?��?��");
		System.out.println("[�? ?��?�� 개수]: " + json.documents.size());
		json.documents.forEach((shop) -> System.out.println("[?��?�� ?���?]: " + shop));
	}
}
