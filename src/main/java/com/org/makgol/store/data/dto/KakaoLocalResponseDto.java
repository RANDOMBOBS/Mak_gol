package com.org.makgol.store.data.dto;

import lombok.Data;

@Data
public class KakaoLocalResponseDto<T> {
	public boolean success;
	public String message;
	public T result;

	public KakaoLocalResponseDto(boolean success, String message, T result) {
	        this.success = success;
	        this.message = message;
	        this.result = result;
	    }

}
