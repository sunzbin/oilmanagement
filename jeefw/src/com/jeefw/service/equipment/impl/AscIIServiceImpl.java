/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.service.equipment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.equipment.AscIIDao;
import com.jeefw.dao.equipment.AxisLabelDao;
import com.jeefw.model.equipment.AscII;
import com.jeefw.service.equipment.AscIIService;

import core.service.BaseService;

/**
 * @Author:梁英男
 * @Date:2018年5月9日
 */
@Service
public class AscIIServiceImpl extends BaseService<AscII> implements AscIIService{

	private AscIIDao ascIIDao;
	
	@Resource
	public void setDictDao(AscIIDao ascIIDao) {
		this.ascIIDao = ascIIDao;
		this.dao = ascIIDao;
	}

	@Override
	public void batchSaveAscII(List<AscII> ascIIs) {
		ascIIDao.batchSaveAscII(ascIIs);
	}
	
	
}
