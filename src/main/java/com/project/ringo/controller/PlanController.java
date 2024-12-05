package com.project.ringo.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.Plan;
import com.project.ringo.model.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/plans")
@RestController
public class PlanController {
	
	@Autowired
	PlanService planService;
	
	
	@GetMapping("/list/{user_id}")
	protected ResponseEntity<List<Plan>> getPlanList(@PathVariable String user_id) throws Exception{
		return new ResponseEntity<List<Plan>>(planService.getPlanList(user_id),HttpStatus.OK);
	}
	
	@PostMapping
	protected ResponseEntity<Integer> insertPlan(@RequestBody Plan plan) throws Exception {
		boolean result = planService.insertPlan(plan);
		if(result) {
			return new ResponseEntity<Integer>(plan.getPlan_id(),HttpStatus.OK);
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	//("/{plan_id}")
	protected ResponseEntity<?> updatePlan(@RequestBody Plan plan) throws Exception {
		planService.updatePlan(plan);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{plan_id}")
	protected ResponseEntity<?> deletePlan(@PathVariable("plan_id") int plan_id) throws Exception {
		Plan plan = planService.getPlanDetail(plan_id);
		if(plan!=null) {
			planService.deletePlan(plan_id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{plan_id}")
	protected ResponseEntity<Plan> getPlanDetail(@PathVariable int plan_id) throws Exception {
		Plan plan = planService.getPlanDetail(plan_id);
		if(plan!=null) {
			return new ResponseEntity<Plan>(plan,HttpStatus.OK);

		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
