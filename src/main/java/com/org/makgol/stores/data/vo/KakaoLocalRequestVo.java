package com.org.makgol.stores.data.vo;

import lombok.Data;

@Data
public class KakaoLocalRequestVo {
	public String x;
	public String y;
	public String keyword;
	public int radius;
	public int size;
	public int page;

	public void validateVo() {
		if (keyword.isEmpty()) {
			throw new Error("?��?��?��값이 비어?��?��?��?��.");
		}

		if (!isValidKeyword(keyword)) {
			throw new Error("?��?�� ?��?��?���? ?��치하�? ?��?��?��?��.");
		}

		if (0 > radius || 20000 < radius) {
			throw new Error("?��?��?�� 반경?�� 범위�? 충족?��?���? 못했?��?��?��.");
		}

		if (1 > size || 15 < size) {
			throw new Error("?��?�� 개수?�� 범위�? 충족?��?���? 못했?��?��?��.");
		}

		if (1 > page || 45 < page) {
			throw new Error("?��?���? 개수?�� 범위�? 충족?��?���? 못했?��?��?��.");
		}

	}

	private boolean isValidKeyword(String keyword) {
		return keyword.matches("?��?��?��|?��?��|중식|?��?��|?��?��");
	}
}
