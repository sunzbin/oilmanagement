package com.jeefw.service.car.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.car.CarDao;
import com.jeefw.dao.collection.CollectionDao;
import com.jeefw.model.carmanagement.CarManagement;
import com.jeefw.service.car.CarService;

import core.service.BaseService;

/**
 * 机车的业务逻辑层的实现
 */
@Service
public class CarServiceImpl extends BaseService<CarManagement> implements CarService {

	private CarDao carDao;

	@Resource
	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
		this.dao = carDao;
	}

	// 获取包含部门中文名称的列表
	public List<CarManagement> queryDepartmentCnList(List<CarManagement> resultList) {
		List<CarManagement> cartList = new ArrayList<CarManagement>();
		for (CarManagement entity : resultList) {
			CarManagement carManagement = new CarManagement();
			carManagement.setId(entity.getId());
			carManagement.setCarType(entity.getCarType());
			carManagement.setCarTypeCode(entity.getCarTypeCode());
			carManagement.setCarNum(entity.getCarNum());
			carManagement.setDepartmentId(entity.getDepartmentId());
//			if (StringUtils.isNotBlank(department.getParentDepartmentkey())) {
//				Department d = carDao.getByProerties("departmentKey", carDao.getParentDepartmentkey());
//				if (d != null) {
//					department.setParentDepartmentValue(d.getDepartmentValue());
//				}
//			}
			cartList.add(carManagement);
		}
		return cartList;
	}

}
