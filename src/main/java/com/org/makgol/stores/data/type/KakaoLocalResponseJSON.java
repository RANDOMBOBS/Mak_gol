package com.org.makgol.stores.data.type;

import java.util.List;

public class KakaoLocalResponseJSON {

	public static class ShopInfo {
		public String address_name;
		public String category_group_code;
		public String category_group_name;
		public String category_name;
		public String distance;
		public String id;
		public String phone;
		public String place_name;
		public String place_url;
		public String road_address_name;
		public String x;
		public String y;

		@Override
		public String toString() {
			return "ShopInfo{" + "address_name='" + address_name + '\'' + ", category_group_code='"
					+ category_group_code + '\'' + ", category_group_name='" + category_group_name + '\''
					+ ", category_name='" + category_name + '\'' + ", distance='" + distance + '\'' + ", id='" + id
					+ '\'' + ", phone='" + phone + '\'' + ", place_name='" + place_name + '\'' + ", place_url='"
					+ place_url + '\'' + ", road_address_name='" + road_address_name + '\'' + ", x='" + x + '\''
					+ ", y='" + y + '\'' + '}';
		}
	}

	public List<ShopInfo> documents;
}
