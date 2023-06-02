package com.project.ringo.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//import com.ssafy.bookapp.dto.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
	int board_id;
	String board_subject;
	String board_content;
	@NonNull
	String user_id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	Date board_regtime;
	int board_views;
	String board_type;


}
