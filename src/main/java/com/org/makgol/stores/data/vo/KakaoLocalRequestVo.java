package com.org.makgol.stores.data.vo;

import lombok.Data;

@Data
public class KakaoLocalRequestVo {
	public String x;
	public String y;
	public String keyword="";
	public int radius = 10000;
	public int size = 5;
	public int page = 1;
	
}
