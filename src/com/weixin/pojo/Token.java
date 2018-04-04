package com.weixin.pojo;
/**
* @Classname: Token.java
* @author: yue
* @Time: 2018年3月30日--下午5:08:14
* @Todo: TODO
* @version V1.0 
**/
public class Token {
		// 接口访问凭证
		private String accessToken;
		// 凭证有效期，单位：秒
		private int expiresIn;

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public int getExpiresIn() {
			return expiresIn;
		}

		public void setExpiresIn(int expiresIn) {
			this.expiresIn = expiresIn;
		}
}
