package com.project.ringo.model.service.attraction;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.attraction.AttractionDAO;
import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionDetail;
import com.project.ringo.model.dto.attraction.PickAttractionDto;

@Service("attractionService")
public class AttractionServiceImpl implements AttractionService {
	
	private AttractionDAO attractionDao;


	@Autowired
	public AttractionServiceImpl(AttractionDAO attractionDao) {
		this.attractionDao = attractionDao;
	}	


	@Override
	public List<Attraction> getViewAttractionList(Integer sidoCode, String searchKeyword, int[] contentTypeIds, String user_id, String sortType) throws Exception {
		return attractionDao.getViewAttractionList(sidoCode, searchKeyword, contentTypeIds, user_id, sortType);
	}
	
	@Override
	public String getSidoName(int sidoCode) throws Exception {
		return attractionDao.getSidoName(sidoCode);
	}


	@Override
	public AttractionDetail getViewAttractionDetail(PickAttractionDto getAttraction) throws SQLException {
		return attractionDao.getViewAttractionDetail(getAttraction);
	}
	
	

}
