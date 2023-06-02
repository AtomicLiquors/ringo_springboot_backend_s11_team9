package com.project.ringo.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetail {

	@NonNull
	private int plan_detail_id;
	private int plan_id;
	private int content_id;
	private int trip_no;
	private Date trip_date;
	private Timestamp arrival_time;
	private int isWaypoint;
	
	private String title;
	private String addr1;
	private String first_image;
	private int sido_code;
	private int gugun_code;
	
	private float latitude;
	private float longitude;
	/*
	private Integer prev_id;
	private Integer next_id;*/
	
}
