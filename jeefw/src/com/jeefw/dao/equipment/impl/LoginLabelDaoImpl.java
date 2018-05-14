package com.jeefw.dao.equipment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.equipment.LoginLabelDao;
import com.jeefw.model.equipment.LoginLabel;

import core.dao.BaseDao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:16
 * @author:liangyingnan
 */
@Repository
public class LoginLabelDaoImpl extends BaseDao<LoginLabel> implements LoginLabelDao {

	public LoginLabelDaoImpl() {
		super(LoginLabel.class);
	}

	@Override
	public void batchSaveAxisLabel(List<LoginLabel> loginLabels) {
		
	}

	
}
