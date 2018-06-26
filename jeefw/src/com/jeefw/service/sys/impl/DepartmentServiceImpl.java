package com.jeefw.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jeefw.dao.sys.DepartmentDao;
import com.jeefw.model.sys.Department;
import com.jeefw.service.sys.DepartmentService;

import core.service.BaseService;
import core.support.PageBaseParameter;

/**
 * 部门的业务逻辑层的实现
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department> implements DepartmentService {

	private DepartmentDao departmentDao;

	@Resource
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
		this.dao = departmentDao;
	}

	// 获取包含部门中文名称的列表
	public List<Department> queryDepartmentCnList(List<Department> resultList) {
		List<Department> departmentList = new ArrayList<Department>();
		for (Department entity : resultList) {
			Department department = new Department();
			department.setId(entity.getId());
			department.setDepartmentKey(entity.getDepartmentKey());
			department.setDepartmentValue(entity.getDepartmentValue());
			department.setParentDepartmentkey(entity.getParentDepartmentkey());
			if (StringUtils.isNotBlank(department.getParentDepartmentkey())) {
				Department d = departmentDao.getByProerties("departmentKey", department.getParentDepartmentkey());
				if (d != null) {
					department.setParentDepartmentValue(d.getDepartmentValue());
				}
			}
			department.setDescription(entity.getDescription());
			departmentList.add(department);
		}
		return departmentList;
	}

	/**
	 * 递归获取所有部门及下属部门
	 */
	@Override
	public String queryRecursionDepartment(String departmentKey) {
		List<Department> departments = departmentDao.queryByProerties("parentDepartmentkey", departmentKey);
		String departKeyStr = departmentKey + ",";
		 if (null != departments && departments.size()>0) {  
             for (int i = 0; i < departments.size(); i++) {
            	 Department department = departments.get(i);  
            	 String subDepartKeyStr = queryRecursionDepartment(department.getDepartmentKey());  
                 if(subDepartKeyStr != null && !"".equals(subDepartKeyStr)){
                	 departKeyStr += subDepartKeyStr;
                 }
             }
         }  
		return departKeyStr;
	}

	@Override
	public PageBaseParameter<Department> queryDepartmentInfo(PageBaseParameter<Department> param) {
		
		return departmentDao.queryDepartmentInfo(param);
	}

}
