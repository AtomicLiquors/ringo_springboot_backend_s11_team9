package com.project.ringo.model.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface JwtService {

	Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

	<T> String createAccessToken(String key, T data);

	<T> String createRefreshToken(String key, T data);

	//Token 발급
	/**
	 * key : Claim에 셋팅될 key 값
	 * data : Claim에 셋팅 될 data 값
	 * subject : payload에 sub의 value로 들어갈 subject값
	 * expire : 토큰 유효기간 설정을 위한 값
	 * jwt 토큰의 구성 : header + payload + signature
	 */

	<T> String create(String key, T data, String subject, long expire);

	boolean checkToken(String jwt);

	Map<String, Object> get(String key);

	String getUserId();

}