package com.project.ringo.controller.attraction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionLikes;
import com.project.ringo.model.dto.attraction.AttractionRating;
import com.project.ringo.model.service.attraction.AttractionLikesService;
import com.project.ringo.model.service.attraction.AttractionRatingService;
import com.project.ringo.model.service.attraction.AttractionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/attractions/likes")
@RestController
public class AttractionLikesController {

	@Autowired
	private AttractionLikesService attrLikesService;

	public AttractionLikesController(AttractionLikesService attrLikesService) {
		this.attrLikesService = attrLikesService;
	}

	@GetMapping
	protected ResponseEntity<Integer> getAttractionLikes(@RequestBody AttractionLikes attrLikes) throws Exception {
		Integer likes = attrLikesService.getAttractionLikes(attrLikes);
		return new ResponseEntity<Integer>(likes, HttpStatus.OK);			
	}

	@PostMapping
	protected ResponseEntity<?> addAttractionLikes(@RequestBody AttractionLikes attrLikes) throws Exception {
		boolean flag = attrLikesService.addAttractionLikes(attrLikes);
		if (flag) {
			return new ResponseEntity(HttpStatus.CREATED);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping
	protected ResponseEntity<?> deleteAttractionLikes(@RequestBody AttractionLikes attrLikes) throws Exception {
		int likes = attrLikesService.getAttractionLikes(attrLikes);
		if (likes > 0) {
			attrLikesService.deleteAttractionLikes(attrLikes);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
