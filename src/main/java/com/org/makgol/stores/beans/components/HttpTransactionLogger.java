package com.org.makgol.stores.beans.components;

import org.springframework.stereotype.Component;

import com.org.makgol.stores.data.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.data.vo.KakaoLocalRequestVo;

@Component
public class HttpTransactionLogger {

	public void logRequestDto(KakaoLocalRequestVo vo) {
		System.out.println("ê²??ƒ‰ ?š”ì²? ?°?´?„°");
		System.out.println("[?œ„?„]: " + vo.getX());
		System.out.println("[ê²½ë„]: " + vo.getY());
		System.out.println("[?‚¤?›Œ?“œ]: " + vo.getKeyword());
		System.out.println("[ë°˜ê²½]: " + vo.getRadius());
		System.out.println("[?‹?‹¹ ê°œìˆ˜]: " + vo.getSize());
		System.out.println("[?˜?´ì§? ê°œìˆ˜]: " + vo.getPage());
	}

	public void logResponseJson(KakaoLocalResponseJSON json) {
		System.out.println("ê²??ƒ‰ ?‘?‹µ ?°?´?„°");
		System.out.println("[ì´? ?‘?‹µ ê°œìˆ˜]: " + json.documents.size());
		json.documents.forEach((shop) -> System.out.println("[?‹?‹¹ ? •ë³?]: " + shop));
	}
}
