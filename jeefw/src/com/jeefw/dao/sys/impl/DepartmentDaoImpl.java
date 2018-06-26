package com.jeefw.dao.sys.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.DepartmentDao;
import com.jeefw.model.sys.Department;

import core.dao.BaseDao;
import core.support.PageBaseParameter;

/**
 * 部门的数据持久层的实现类
 */
@Repository
public class DepartmentDaoImpl extends BaseDao<Department> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}

	@Override
	public PageBaseParameter<Department> queryDepartmentInfo(PageBaseParameter<Department> param) {
		Criteria criteria = getSession().createCriteria(Department.class);
		Disjunction k = Restrictions.disjunction();
		Disjunction v = Restrictions.disjunction();
		Disjunction c = Restrictions.disjunction();
		boolean kFlag = false;
		boolean vFlag = false;
		if (param != null && param.getResultList() != null && param.getResultList().size() > 0) {
			for (int i = 0; i < param.getResultList().size(); i++) {
				if (param.getResultList().get(i).get$eq_departmentKey() != null) {//两个条件都不为空
					k.add(Restrictions.eq("departmentKey", param.getResultList().get(i).get$eq_departmentKey()));
					kFlag = true;
				}
				if(param.getResultList().get(i).get$like_departmentValue() != null){
					v.add(Restrictions.eq("departmentValue", param.getResultList().get(i).get$like_departmentValue()));
					vFlag = true;
				}
			}
		}
		
		
		if(param.getConditionMap() !=null){
			 for (Map.Entry<String, Object> e : param.getConditionMap().entrySet()){
				 Object value = e.getValue();
		          if ((value != null) && ((!(value instanceof String)) || (!"".equals((String)value)))){
		        	 String[] departs = value.toString().split(",");
		        	 for(String ele: departs){
		        		 c.add(Restrictions.eq("departmentKey", ele));
		        	 }
		          }
			 }
		}

		if(kFlag && vFlag){
			criteria.add(Restrictions.and(k,v,c));
		}else if(kFlag && !vFlag){
			criteria.add(Restrictions.and(k,c));
		}else if(!kFlag && vFlag){
			criteria.add(Restrictions.and(v,c));
		}else{
			criteria.add(c);
		}
		
		
//		param.setTotalRows(Long.valueOf(((Number) criteria.uniqueResult()).longValue()));
//		if (param.getTotalRows() > 0L) {
			List<Department> departList = criteria.setMaxResults(param.getMaxRows()).setFirstResult(param.getFirstRows()).list();
			System.out.println(departList.size());
			param.setTotalRows(departList.size());
			param.setResultList(departList);
//		}
		return param;
	}
}
