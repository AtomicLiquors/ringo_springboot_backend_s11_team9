package com.project.ringo.controller.attraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.attraction.AttractionRating;
import com.project.ringo.model.service.attraction.AttractionRatingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/attractions/rating")
@RestController
public class AttractionRatingController {

	@Autowired
	private AttractionRatingService attractionRatingService;

	// 평점 관리
	@GetMapping
	protected ResponseEntity<Float> getAttractionRating(@RequestBody AttractionRating attrRating) throws Exception {
		Float rating = attractionRatingService.getAttractionRating(attrRating);
		return new ResponseEntity<Float>(rating, HttpStatus.OK);
	}

	@PostMapping
	protected ResponseEntity<?> insertAttractionRating(@RequestBody AttractionRating attractionRating)
			throws Exception {
		boolean flag = attractionRatingService.insertAttractionRating(attractionRating);
		if (flag) {
			return new ResponseEntity(HttpStatus.CREATED);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	protected ResponseEntity<?> modifyAttractionRating(@RequestBody AttractionRating attrRating) throws Exception {
		attractionRatingService.modifyAttractionRating(attrRating);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	protected ResponseEntity<?> deleteUser(@RequestBody AttractionRating attrRating) throws Exception {
		boolean likes = attractionRatingService.deleteAttractionRating(attrRating);
		if (likes) {
			attractionRatingService.deleteAttractionRating(attrRating);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
