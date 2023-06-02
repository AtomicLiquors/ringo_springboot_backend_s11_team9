package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.PlanDetailDAO;
import com.project.ringo.model.dto.PlanDetail;

@Service("planDetailService")
public class PlanDetailServiceImpl implements PlanDetailService{

	@Autowired
	private PlanDetailDAO planDetailDao;
	

	@Override
	public List<PlanDetail> getPlanDetailList(int plan_id) throws SQLException{
		return planDetailDao.getPlanDetailList(plan_id);
	}

	//��ȹ �߰��ϱ�

	@Override
	public boolean insertPlanDetail(PlanDetail planDetail) throws SQLException{
		return planDetailDao.insertPlanDetail(planDetail);
	}

	//��ȹ �����ϱ� 

	@Override
	public boolean deletePlanDetail(int plan_detail_id) throws SQLException{
		return planDetailDao.deletePlanDetail(plan_detail_id);
	}

	//��ȹ �����ϱ� 

	@Override
	public PlanDetail getPlanDetailDetail(int plan_detail_id) throws SQLException {
		return planDetailDao.getPlanDetailDetail(plan_detail_id);
	}
	
	

	@Override
	public boolean updatePlanDetail( PlanDetail planDetail ) throws SQLException{
		return planDetailDao.updatePlanDetail(planDetail);
	}

	@Override
	public boolean updatePlanDetailList( List<PlanDetail> list) throws SQLException{
		return planDetailDao.updatePlanDetailList(list);
	}
}
