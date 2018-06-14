/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年6月13日
 */
package com.jeefw.dao.car.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.car.CarAxisPointDao;
import com.jeefw.model.carmanagement.CarAxisPoint;

import core.dao.BaseDao;

/**
 * @Author:梁英男
 * @Date:2018年6月13日
 */
@Repository
public class CarAxisPointDaoImpl extends BaseDao<CarAxisPoint> implements CarAxisPointDao{

	public CarAxisPointDaoImpl() {
		super(CarAxisPoint.class);
	}

}
