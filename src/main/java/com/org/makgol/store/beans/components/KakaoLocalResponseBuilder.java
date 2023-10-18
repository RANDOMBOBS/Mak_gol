package com.org.makgol.store.beans.components;

import org.springframework.stereotype.Component;

import com.org.makgol.store.data.dto.KakaoLocalResponseDto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class KakaoLocalResponseBuilder<T> {

	private boolean success;
    private String message;
    private T result;


    public KakaoLocalResponseBuilder<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public KakaoLocalResponseBuilder<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public KakaoLocalResponseBuilder<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public KakaoLocalResponseDto<T> build() {
        return new KakaoLocalResponseDto<T>(success, message, result);
    }
	
}
