package com.project.ringo.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.Plan;
import com.project.ringo.model.dto.PlanDetail;
@Mapper
@Repository
public interface PlanDetailDAO {

	//���� ��ȹ ���� �߰� 
	boolean insertPlanDetail(PlanDetail planDetail) throws SQLException;

	// ��ȹ ���� ���� �ҷ����� 
	List<PlanDetail> getPlanDetailList(int plan_id) throws SQLException;


	boolean updatePlanDetail( PlanDetail planDetail ) throws SQLException;
	
	//��ȹ ���� ���� �����ϱ� 
	boolean updatePlanDetailList( List<PlanDetail> list) throws SQLException;

	//��ȹ ���� ���� �����ϱ� 
	boolean deletePlanDetail(int plan_detail_id) throws SQLException;
	
	PlanDetail getPlanDetailDetail(int plan_detail_id) throws SQLException;

}