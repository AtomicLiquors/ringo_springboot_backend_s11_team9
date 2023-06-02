package com.project.ringo.model.dto.attraction;

import lombok.Data;

@Data
public class AttractionDetail {

	private int content_id;
	private int content_type_id;
	private String title;
	private String addr1;
	private String first_image;
	private String overview;
	
	private String latitude;
	private String longitude;
	
	private int likes;
	private float rating;
	
	private int user_liked;
	private int user_rated;
	private float user_rating;

}
