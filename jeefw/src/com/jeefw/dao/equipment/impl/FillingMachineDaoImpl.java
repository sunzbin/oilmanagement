package com.jeefw.dao.equipment.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.equipment.FillingMachineDao;
import com.jeefw.model.equipment.FillingMachine;
import core.dao.BaseDao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:15
 * @author:liangyingnan
 */
@Repository
public class FillingMachineDaoImpl extends BaseDao<FillingMachine> implements FillingMachineDao {

	public FillingMachineDaoImpl() {
		super(FillingMachine.class);
	}


}
