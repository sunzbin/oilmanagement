package com.jeefw.service.sys;

import java.util.List;

import com.jeefw.model.sys.Department;

import core.service.Service;
import core.support.PageBaseParameter;

/**
 * 部门的业务逻辑层的接口
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface DepartmentService extends Service<Department> {

	// 获取包含部门中文名称的列表
	List<Department> queryDepartmentCnList(List<Department> resultList);
	
	//根据本部门departmentKey获取下属所有部门信息
	public String queryRecursionDepartment(String departmentKey);
	
	//获取分页部门信息
	public PageBaseParameter<Department> queryDepartmentInfo(PageBaseParameter<Department> param);

}
