package com.jeefw.dao.sys.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.SysUserDao;
import com.jeefw.model.sys.Department;
import com.jeefw.model.sys.SysUser;

import core.dao.BaseDao;
import core.support.PageBaseParameter;

/**
 * 用户的数据持久层的实现类
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Repository
public class SysUserDaoImpl extends BaseDao<SysUser> implements SysUserDao {

	public SysUserDaoImpl() {
		super(SysUser.class);
	}

	@Override
	public String getRoleValueBySysUserId(Long sysUserId) {
		Query query = this.getSession().createSQLQuery("select role_value from sysuser_role,role where sysuser_role.role_id = role.id and sysuser_id = :sysUserId");
		query.setParameter("sysUserId", sysUserId);
		String roleValue = (String) query.uniqueResult() == null ? "" : (String) query.uniqueResult();
		return roleValue;
	}
	
	@Override
	public PageBaseParameter<SysUser> querySysUserInfo(PageBaseParameter<SysUser> param) {
		Criteria criteria = getSession().createCriteria(SysUser.class);
		Disjunction k = Restrictions.disjunction();
		Disjunction v = Restrictions.disjunction();
		Disjunction c = Restrictions.disjunction();
		boolean kFlag = false;
		boolean vFlag = false;
		if (param != null && param.getResultList() != null && param.getResultList().size() > 0) {
			for (int i = 0; i < param.getResultList().size(); i++) {
				if (param.getResultList().get(i).getEmail() != null) {//两个条件都不为空
					k.add(Restrictions.eq("email", param.getResultList().get(i).getEmail()));
					kFlag = true;
				}
				if(param.getResultList().get(i).getUserName() != null){
					v.add(Restrictions.eq("userName", param.getResultList().get(i).getUserName()));
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
			List<SysUser> departList = criteria.setMaxResults(param.getMaxRows()).setFirstResult(param.getFirstRows()).list();
			param.setTotalRows(departList.size());
			param.setResultList(departList);
//		}
		return param;
	}

}
