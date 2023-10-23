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
			throw new Error("?€??κ°μ΄ λΉμ΄??΅??€.");
		}

		if (!isValidKeyword(keyword)) {
			throw new Error("?? ?€??κ°? ?ΌμΉνμ§? ??΅??€.");
		}

		if (0 > radius || 20000 < radius) {
			throw new Error("? ?¨? λ°κ²½? λ²μλ₯? μΆ©μ‘±??€μ§? λͺ»ν?΅??€.");
		}

		if (1 > size || 15 < size) {
			throw new Error("??Ή κ°μ? λ²μλ₯? μΆ©μ‘±??€μ§? λͺ»ν?΅??€.");
		}

		if (1 > page || 45 < page) {
			throw new Error("??΄μ§? κ°μ? λ²μλ₯? μΆ©μ‘±??€μ§? λͺ»ν?΅??€.");
		}

	}

	private boolean isValidKeyword(String keyword) {
		return keyword.matches("??? |??|μ€μ|?Ό?|??");
	}
}
