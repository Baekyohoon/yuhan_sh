package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.KakaoUser;

@Service
public class KakaoService {

	 @Value("8bae42d5714a64b82e4730f2dac59082")
	    private String kakaoApiKey;

	    private final String KAKAO_API_URL = "https://kapi.kakao.com/v2/user/me";

	    public KakaoUser getKakaoUserInfo(String accessToken) {
	        String url = KAKAO_API_URL;

	        // Set your Kakao API key in the request header
	        String authorizationHeader = "Bearer " + accessToken;

	        // Set up RestTemplate
	        RestTemplate restTemplate = new RestTemplate();

	        // Send GET request to Kakao API
	        KakaoUser kakaoUserInfo = restTemplate.getForObject(url, KakaoUser.class);

	        return kakaoUserInfo;
	    }
}
