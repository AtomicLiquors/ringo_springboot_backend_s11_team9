package com.project.ringo.model.dto.attraction;

import lombok.Data;

@Data
public class SearchAttraction {
	private Integer sidoCode;
	private String searchKeyword; 
	private int[] contentTypeIds; 
	private String user_id;
	private String sortType; 
}
