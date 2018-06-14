/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年6月13日
 */
package com.jeefw.service.car.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.car.CarAxisPointDao;
import com.jeefw.model.carmanagement.CarAxisPoint;
import com.jeefw.service.car.CarAxisPointService;

import core.service.BaseService;

/**
 * @Author:梁英男
 * @Date:2018年6月13日
 */
@Service
public class CarAxisPointServiceImpl extends BaseService<CarAxisPoint> implements CarAxisPointService{
	
private CarAxisPointDao carAxisPointDao;
	
	@Resource
	public void setDictDao(CarAxisPointDao carAxisPointDao) {
		this.carAxisPointDao = carAxisPointDao;
		this.dao = carAxisPointDao;
	}
	
}
