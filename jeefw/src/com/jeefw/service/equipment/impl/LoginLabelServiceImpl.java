package com.jeefw.service.equipment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.equipment.FillingMachineDao;
import com.jeefw.dao.equipment.LoginLabelDao;
import com.jeefw.model.equipment.LoginLabel;
import com.jeefw.service.equipment.LoginLabelService;

import core.service.BaseService;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:35
 * @author:liangyingnan
 */
@Service
public class LoginLabelServiceImpl extends BaseService<LoginLabel> implements LoginLabelService {

	private LoginLabelDao loginLabelDao;

	@Resource
	public void setDictDao(LoginLabelDao loginLabelDao) {
		this.loginLabelDao = loginLabelDao;
		this.dao = loginLabelDao;
	}

	@Override
	public void batchSaveAxisLabel(List<LoginLabel> loginLabels) {
		loginLabelDao.batchSaveAxisLabel(loginLabels);
		
	}

}
