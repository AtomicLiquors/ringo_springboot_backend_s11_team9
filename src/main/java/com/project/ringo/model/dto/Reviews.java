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
public class Reviews {
	@NonNull
	private int review_id;
	private String user_id;
	private String user_img;
	private int content_id;
	private String review_title;
	private String review_content;
	private String review_img;
	private Date review_regtime;
}
