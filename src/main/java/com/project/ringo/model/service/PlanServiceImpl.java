package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.project.ringo.model.dao.PlanDAO;
import com.project.ringo.model.dto.Plan;

@Service("planService")
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDAO planDao;
	
	@Override
	public List<Plan> getPlanList(String user_id) throws SQLException{
		return planDao.getPlanList(user_id);
	}

	//��ȹ �߰��ϱ�
	@Transactional(isolation = Isolation.SERIALIZABLE) 
	@Override
	public boolean insertPlan(Plan plan) throws SQLException{
		//int plan_id = planDao.insertPlan(plan);
		//System.out.println(plan_id);
		//return plan_id  > 0;
		return planDao.insertPlan(plan);
	}

	//��ȹ �����ϱ� 
	@Override
	public boolean deletePlan(int plan_id) throws SQLException{
		return planDao.deletePlan(plan_id);
	}

	//��ȹ �����ϱ� 
	@Override
	public boolean updatePlan(Plan plan) throws SQLException{
		return planDao.updatePlan(plan);
	}

	@Override
	public Plan getPlanDetail(int plan_id) throws SQLException {
		return planDao.getPlanDetail(plan_id);
	}
	
	
}
