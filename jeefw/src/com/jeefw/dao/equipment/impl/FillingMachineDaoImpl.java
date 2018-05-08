package com.jeefw.dao.equipment.impl;

import com.jeefw.dao.equipment.FillingMachineDao;
import com.jeefw.model.equipment.FillingMachine;
import core.dao.BaseDao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:15
 * @author:liangyingnan
 */
public class FillingMachineDaoImpl extends BaseDao<FillingMachine> implements FillingMachineDao {

	public FillingMachineDaoImpl() {
		super(FillingMachine.class);
	}


}
