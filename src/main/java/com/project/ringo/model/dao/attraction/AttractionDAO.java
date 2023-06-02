package com.project.ringo.model.dao.attraction;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionDetail;
import com.project.ringo.model.dto.attraction.PickAttractionDto;

@Mapper	
@Repository
public interface AttractionDAO {

	//List<Attraction> getAttractionList(String sidoName, String searchKeyword) throws SQLException;
	AttractionDetail getViewAttractionDetail(PickAttractionDto getAttraction) throws SQLException;
	List<Attraction> getViewAttractionList(Integer sidoCode, String searchKeyword, int[] contentTypeIds, String user_id, String sortType) throws SQLException;

	boolean insertAttractionList(List<Attraction> attraction) throws SQLException;
	boolean updateAttraction(Attraction attraction) throws SQLException;
	String getSidoName(int sidoCode) throws SQLException;
	
	//insert, update, create, delete

}