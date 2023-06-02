package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.PlanDetail;

public interface PlanDetailService {

	List<PlanDetail> getPlanDetailList(int plan_id) throws SQLException;

	boolean insertPlanDetail(PlanDetail planDetail) throws SQLException;

	boolean deletePlanDetail(int plan_detail_id) throws SQLException;

	boolean updatePlanDetail( PlanDetail planDetail ) throws SQLException;

	boolean updatePlanDetailList( List<PlanDetail> list) throws SQLException;

	PlanDetail getPlanDetailDetail(int plan_detail_id) throws SQLException;
}