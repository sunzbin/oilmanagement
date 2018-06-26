package com.jeefw.dao.sys;

import com.jeefw.model.sys.Department;

import core.dao.Dao;
import core.support.PageBaseParameter;

/**
 * 部门的数据持久层的接口
 */
public interface DepartmentDao extends Dao<Department> {

	//获取分页部门信息
		public PageBaseParameter<Department> queryDepartmentInfo(PageBaseParameter<Department> param);
}
