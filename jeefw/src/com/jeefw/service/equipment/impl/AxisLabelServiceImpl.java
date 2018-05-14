package com.jeefw.service.equipment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.equipment.AxisLabelDao;
import com.jeefw.dao.equipment.FillingMachineDao;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.service.equipment.AxisLabelService;

import core.service.BaseService;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:33
 * @author:liangyingnan
 */
@Service
public class AxisLabelServiceImpl extends BaseService<AxisLabel> implements AxisLabelService {

private AxisLabelDao axisLabelDao;
	
	@Resource
	public void setDictDao(AxisLabelDao axisLabelDao) {
		this.axisLabelDao = axisLabelDao;
		this.dao = axisLabelDao;
	}

	@Override
	public void batchSaveAxisLabel(List<AxisLabel> axisLabels) {
		axisLabelDao.batchSaveAxisLabel(axisLabels);
	}

	

	






}
