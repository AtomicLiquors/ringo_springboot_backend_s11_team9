package com.project.ringo.model.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class User {

	private int user_no;
	@ApiModelProperty(value = "회원 아이디")
	private String user_id;
	@ApiModelProperty(value = "회원 비밀번호")
	private String user_pw;
	private String user_name;
	private Timestamp user_regTime;
	private String user_email;
	private String user_address_basic;
	private String user_address_detail;
	private int user_grade;
	private String user_img;
	private String token;
	
	
		
	/*
	 * public User(String user_id, String user_pw, String user_name, Timestamp
	 * user_regTime, String user_email, String user_address_basic, String
	 * user_address_detail, int user_grade, String user_img) {
	 * 
	 * super(); this.user_id = user_id; this.user_pw = user_pw; this.user_name =
	 * user_name; this.user_regTime = user_regTime; this.user_email = user_email;
	 * this.user_address_basic = user_address_basic; this.user_address_detail =
	 * user_address_detail; this.user_grade = user_grade; this.user_img = user_img;
	 * }
	 * 
	 * public User(String user_id, String user_pw, String user_name, String
	 * user_email, String user_address_basic, String user_address_detail, String
	 * user_img) { super(); this.user_id = user_id; this.user_pw = user_pw;
	 * this.user_name = user_name; this.user_email = user_email;
	 * this.user_address_basic = user_address_basic; this.user_address_detail =
	 * user_address_detail; this.user_img = user_img; }
	 */

	/*
	 * public String getUser_id() { return user_id; } public void setUser_id(String
	 * user_id) { this.user_id = user_id; } public String getUser_pw() { return
	 * user_pw; } public void setUser_pw(String user_pw) { this.user_pw = user_pw; }
	 * public String getUser_name() { return user_name; } public void
	 * setUser_name(String user_name) { this.user_name = user_name; } public
	 * Timestamp getUser_regTime() { return user_regTime; } public void
	 * setUser_regTime(Timestamp user_regTime) { this.user_regTime = user_regTime; }
	 * public String getUser_email() { return user_email; } public void
	 * setUser_email(String user_email) { this.user_email = user_email; } public
	 * String getUser_address_basic() { return user_address_basic; } public void
	 * setUser_address_basic(String user_address_basic) { this.user_address_basic =
	 * user_address_basic; } public String getUser_address_detail() { return
	 * user_address_detail; } public void setUser_address_detail(String
	 * user_address_detail) { this.user_address_detail = user_address_detail; }
	 * public int getUser_grade() { return user_grade; } public void
	 * setUser_grade(int user_grade) { this.user_grade = user_grade; } public String
	 * getUser_img() { return user_img; } public void setUser_img(String user_img) {
	 * this.user_img = user_img; }
	 */
	
	
	
	
}
