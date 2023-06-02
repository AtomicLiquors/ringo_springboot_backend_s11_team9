package com.project.ringo.model.dto.attraction;

import lombok.Data;

@Data
public class AttractionLikes {

	private int attraction_likes_id;
	private String user_id;
	private int content_id;

}
