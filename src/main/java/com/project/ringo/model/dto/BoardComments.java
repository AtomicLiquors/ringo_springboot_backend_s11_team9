package com.project.ringo.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class BoardComments {
	
	@NonNull
    private int board_comments_id;
    private int board_id;
    private String board_comments_content;
    private String user_id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Timestamp board_comments_regtime;

   
}