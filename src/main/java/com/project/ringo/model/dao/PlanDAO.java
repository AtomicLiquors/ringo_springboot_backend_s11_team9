package com.project.ringo.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.Plan;
@Mapper
@Repository
public interface PlanDAO {

	//������� ��ȹ ��� �ҷ����� 
	List<Plan> getPlanList(String user_id) throws SQLException;

	//��ȹ �߰��ϱ�
	boolean insertPlan(Plan plan) throws SQLException;

	//��ȹ �����ϱ� 
	boolean deletePlan(int plan_id) throws SQLException;

	//��ȹ �����ϱ� 
	boolean updatePlan(Plan plan) throws SQLException;

	Plan getPlanDetail(int plan_id) throws SQLException;
}