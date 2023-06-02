package com.project.ringo.model.service.attraction;

import java.sql.SQLException;

import com.project.ringo.model.dto.attraction.AttractionLikes;

public interface AttractionLikesService {

	// 좋아요 관리
	int getAttractionLikes(AttractionLikes attractionLikes) throws SQLException;

	boolean addAttractionLikes(AttractionLikes attractionLikes) throws SQLException;

	boolean deleteAttractionLikes(AttractionLikes attractionLikes) throws SQLException;

}