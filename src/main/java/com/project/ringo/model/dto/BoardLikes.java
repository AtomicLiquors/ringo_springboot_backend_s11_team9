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
public class BoardLikes {
	
	@NonNull
    private int boardLikeId;
    private String userId;
    private int boardId;
    private boolean boardIsLiked;

    
}