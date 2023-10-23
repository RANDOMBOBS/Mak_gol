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
			throw new Error("?‚¤?›Œ?“œê°’ì´ ë¹„ì–´?žˆ?Šµ?‹ˆ?‹¤.");
		}

		if (!isValidKeyword(keyword)) {
			throw new Error("?Œ?‹ ?‚¤?›Œ?“œê°? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
		}

		if (0 > radius || 20000 < radius) {
			throw new Error("?œ ?š¨?•œ ë°˜ê²½?˜ ë²”ìœ„ë¥? ì¶©ì¡±?‹œ?‚¤ì§? ëª»í–ˆ?Šµ?‹ˆ?‹¤.");
		}

		if (1 > size || 15 < size) {
			throw new Error("?‹?‹¹ ê°œìˆ˜?˜ ë²”ìœ„ë¥? ì¶©ì¡±?‹œ?‚¤ì§? ëª»í–ˆ?Šµ?‹ˆ?‹¤.");
		}

		if (1 > page || 45 < page) {
			throw new Error("?Ž˜?´ì§? ê°œìˆ˜?˜ ë²”ìœ„ë¥? ì¶©ì¡±?‹œ?‚¤ì§? ëª»í–ˆ?Šµ?‹ˆ?‹¤.");
		}

	}

	private boolean isValidKeyword(String keyword) {
		return keyword.matches("?Œ?‹? |?•œ?‹|ì¤‘ì‹|?¼?‹|?–‘?‹");
	}
}
