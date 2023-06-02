package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.Plan;

public interface PlanService {

	List<Plan> getPlanList(String user_id) throws SQLException;

	//��ȹ �߰��ϱ�
	
	boolean insertPlan(Plan plan) throws SQLException;

	//��ȹ �����ϱ� 
	boolean deletePlan(int plan_id) throws SQLException;
	
	//��ȹ �����ϱ� 
	boolean updatePlan(Plan plan) throws SQLException;
	
	Plan getPlanDetail(int plan_id) throws SQLException;

}