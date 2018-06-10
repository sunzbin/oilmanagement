package com.jeefw.service.car;

import java.util.List;

import com.jeefw.model.carmanagement.CarManagement;

import core.service.Service;

/**
 * 机车的业务逻辑层的接口
 */
public interface CarService extends Service<CarManagement> {

	// 获取包含部门中文名称的列表
	List<CarManagement> queryDepartmentCnList(List<CarManagement> resultList);

}
