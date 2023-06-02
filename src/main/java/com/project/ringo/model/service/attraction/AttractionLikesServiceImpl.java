package com.project.ringo.model.service.attraction;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.attraction.AttractionDAO;
import com.project.ringo.model.dao.attraction.AttractionLikesDAO;
import com.project.ringo.model.dao.attraction.AttractionRatingDAO;
import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionLikes;

@Service("attractionLikesService")
public class AttractionLikesServiceImpl implements AttractionLikesService {
	
	private AttractionLikesDAO attractionLikesDao;
	

	@Autowired
	public AttractionLikesServiceImpl(AttractionLikesDAO attractionLikesDAO) {
		this.attractionLikesDao = attractionLikesDAO;
	}
	
	
	// 좋아요 관리
	@Override
	public int getAttractionLikes(AttractionLikes attractionLikes) throws SQLException {
		return attractionLikesDao.getAttractionLikes(attractionLikes);
	}


	@Override
	public boolean addAttractionLikes(AttractionLikes attractionLikes) throws SQLException {
		return attractionLikesDao.addAttractionLikes(attractionLikes);
	}


	@Override
	public boolean deleteAttractionLikes(AttractionLikes attractionLikes) throws SQLException {
		return attractionLikesDao.deleteAttractionLikes(attractionLikes);
	}

}
