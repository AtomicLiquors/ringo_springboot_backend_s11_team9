package com.project.ringo.model.dao.attraction;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.attraction.AttractionLikes;

public interface AttractionLikesDAO {

	int getAttractionLikes(AttractionLikes attractionLikes) throws SQLException;
	
	boolean addAttractionLikes(AttractionLikes attractionLike) throws SQLException;

	boolean deleteAttractionLikes(AttractionLikes attractionLikes) throws SQLException;
	
//	List<AttractionLikes> getAttractionLikesBycontentId(int contentId) throws SQLException;

}