package com.project.ringo.model.dto;

import java.sql.Date;

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
public class Plan {

	@NonNull
	private int plan_id;
	private String user_id;
	private String plan_title;
	private String plan_desc;
	private Date plan_start_date;
	private Date plan_end_date;
	private String plan_thumbnail;
	
	private String user_img;
	
	
}
