package com.project.ringo.model.service.attraction;


import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionDetail;
import com.project.ringo.model.dto.attraction.PickAttractionDto;

public interface AttractionService {

	//List<Attraction> getAttractionList(String sidoName, String searchKeyword) throws Exception;
	AttractionDetail getViewAttractionDetail(PickAttractionDto getAttraction) throws SQLException;
	List<Attraction> getViewAttractionList(Integer sidoCode, String searchKeyword, int[] contentTypeIds, String user_id, String sortType)
			throws Exception;
	
	String getSidoName(int sidoCode) throws Exception;

}