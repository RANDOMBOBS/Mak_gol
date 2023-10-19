package com.org.makgol.store.data.vo;

import lombok.Data;

@Data
public class KakaoLocalRequestVo {
	public String x;
	public String y;
	public String keyword;
	public int radius;
	public int size;
	public int page;

	public void validateDto() {
		if (keyword.isEmpty()) {
			throw new Error("키워드값이 비어있습니다.");
		}

		if (!isValidKeyword(keyword)) {
			throw new Error("음식 키워드가 일치하지 않습니다.");
		}

		if (0 > radius || 20000 < radius) {
			throw new Error("유효한 반경의 범위를 충족시키지 못했습니다.");
		}

		if (1 > size || 15 < size) {
			throw new Error("식당 개수의 범위를 충족시키지 못했습니다.");
		}

		if (1 > page || 45 < page) {
			throw new Error("페이지 개수의 범위를 충족시키지 못했습니다.");
		}

	}

	private boolean isValidKeyword(String keyword) {
		return keyword.matches("음식점|한식|중식|일식|양식");
	}
}
