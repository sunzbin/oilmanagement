package com.jeefw.service.equipment.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.equipment.FillingMachineDao;
import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.model.equipment.FillingMachine;
import com.jeefw.service.equipment.AxisLabelService;
import com.jeefw.service.equipment.FillingMachineService;
import core.service.BaseService;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:36
 * @author:liangyingnan
 */
@Service
public class FillingMachineServiceImpl extends BaseService<FillingMachine> implements FillingMachineService {

	private FillingMachineDao fillingMachineDao;
	
	@Resource
	public void setDictDao(FillingMachineDao fillingMachineDao) {
		this.fillingMachineDao = fillingMachineDao;
		this.dao = fillingMachineDao;
	}

















}
