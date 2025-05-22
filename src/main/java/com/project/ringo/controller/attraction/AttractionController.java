package com.project.ringo.controller.attraction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionDetail;
import com.project.ringo.model.dto.attraction.PickAttractionDto;
import com.project.ringo.model.dto.attraction.SearchAttraction;
import com.project.ringo.model.service.attraction.AttractionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/attractions")
@RestController
public class AttractionController {

	@Autowired
	private AttractionService attractionService;



	@PostMapping("/detail")
	protected ResponseEntity<AttractionDetail> getViewAttractionDetail(@RequestBody PickAttractionDto pickAttractionDto)
			throws Exception {
		 
		AttractionDetail attraction = attractionService.getViewAttractionDetail(pickAttractionDto);
		if (attraction != null) {
			return new ResponseEntity<AttractionDetail>(attraction, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	protected ResponseEntity<List<Attraction>> getViewAttractionList(@RequestBody SearchAttraction searchAttraction)
			throws Exception {
		Integer sidoCode = searchAttraction.getSidoCode();
		String searchKeyword = searchAttraction.getSearchKeyword();
		int[] contentTypeIds = searchAttraction.getContentTypeIds();
		String sortType = searchAttraction.getSortType();
		if(contentTypeIds != null && contentTypeIds.length == 0) contentTypeIds = null;
		
		String user_id = searchAttraction.getUser_id();
		
		List<Attraction> attractions = attractionService.getViewAttractionList(sidoCode, searchKeyword, contentTypeIds, user_id, sortType);
		if (attractions != null) {
			return new ResponseEntity<List<Attraction>>(attractions, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
