package com.project.ringo.controller;

import java.net.URI;
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

import com.project.ringo.model.dto.Reviews;
import com.project.ringo.model.service.ReviewsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {
	
	@Autowired
	ReviewsService reviewsService;
	
	@GetMapping("/{review_id}")
	protected ResponseEntity<Reviews> getReviews(@PathVariable int review_id) throws Exception {
		Reviews reviews = reviewsService.getReviews(review_id);
		
		if(reviews!=null) {
			return new ResponseEntity<Reviews>(reviews,HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/list/{content_id}")
	protected ResponseEntity<List<Reviews>> getReviewsByCotentId(@PathVariable int content_id) throws Exception {
		return new ResponseEntity<List<Reviews>>(reviewsService.getReviewsByCotentId(content_id),HttpStatus.OK);
	}
	
	@PostMapping
	protected ResponseEntity<?> addReviews(@RequestBody Reviews reviews) throws Exception {
		boolean flag = reviewsService.addReviews(reviews);
		if(flag) {
			return ResponseEntity.created(URI.create("/api/reviews/"+reviews.getReview_id())).build();
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/{review_id}")
	protected ResponseEntity<?> updateReviews(@RequestBody Reviews reviews) throws Exception {
		reviewsService.updateReviews(reviews);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{review_id}")
	protected ResponseEntity<?> deleteReviews(@PathVariable("review_id") int review_id) throws Exception {
		Reviews reviews = reviewsService.getReviews(review_id);
		if(reviews!=null) {
			reviewsService.deleteReviews(review_id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
