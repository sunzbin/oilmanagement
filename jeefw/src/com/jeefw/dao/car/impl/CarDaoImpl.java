package com.jeefw.dao.car.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.car.CarDao;
import com.jeefw.model.carmanagement.CarManagement;

import core.dao.BaseDao;

/**
 * 机车的数据持久层的实现类
 */
@Repository
public class CarDaoImpl extends BaseDao<CarManagement> implements CarDao {

	public CarDaoImpl() {
		super(CarManagement.class);
	}

}
