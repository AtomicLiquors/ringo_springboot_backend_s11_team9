package com.project.ringo.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.Board;
import com.project.ringo.model.dto.Plan;
import com.project.ringo.model.dto.PlanDetail;
import com.project.ringo.model.service.PlanDetailService;
import com.project.ringo.model.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/plans/details")
@RestController
public class PlanDetailController {
	@Autowired
	PlanDetailService planDetailService;
	
	public static final Logger logger = LoggerFactory.getLogger(PlanDetailController.class);

	//////////////////////////////////////////////////////////////////////////////////////////
	//아래는 PlanDetail//
	//               // 
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	@GetMapping("/list/{plan_id}")
	protected ResponseEntity<List<PlanDetail>> getPlanDetialList(@PathVariable int plan_id) throws Exception{
		return new ResponseEntity<List<PlanDetail>>(planDetailService.getPlanDetailList(plan_id),HttpStatus.OK);
	}
	
	@PostMapping
	protected ResponseEntity<?> insertPlanDetail(@RequestBody PlanDetail planDetail) throws Exception {
		boolean result = planDetailService.insertPlanDetail(planDetail);
		if(result) {
			return ResponseEntity.created(URI.create("/api/plans/"+planDetail.getPlan_id()+"/"+planDetail.getPlan_detail_id())).build();
		}else {
			return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@PutMapping("/{plan_detail_id}")
	protected ResponseEntity<?> updatePlanDetail(@RequestBody PlanDetail planDetail) throws Exception {
		planDetailService.updatePlanDetail(planDetail);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	protected ResponseEntity<?> updatePlanDetailList(@RequestBody List<PlanDetail> list) throws Exception {
	//	logger.debug("리스트", list);
		planDetailService.updatePlanDetailList(list);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{plan_detail_id}")
	protected ResponseEntity<?> deletePlanDetail(@PathVariable("plan_detail_id") int plan_detail_id) throws Exception {
		PlanDetail planDetail = planDetailService.getPlanDetailDetail(plan_detail_id);
		if(planDetail!=null) {
			planDetailService.deletePlanDetail(plan_detail_id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{plan_detail_id}")
	protected ResponseEntity<PlanDetail> getPlanDetailDetail(@PathVariable int plan_detail_id) throws Exception {
		PlanDetail planDetail = planDetailService.getPlanDetailDetail(plan_detail_id);
		if(planDetail!=null) {
			return new ResponseEntity<PlanDetail>(planDetail,HttpStatus.OK);

		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
