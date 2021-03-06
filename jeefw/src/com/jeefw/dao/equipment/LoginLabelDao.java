package com.jeefw.dao.equipment;

import java.util.List;

import com.jeefw.model.equipment.LoginLabel;
import core.dao.Dao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:11
 * @author:liangyingnan
 */
public interface LoginLabelDao extends Dao<LoginLabel> {

	/**
	 * @Title:batchSaveAxisLabel
	 * @Description:
	 * @Param:
	 * @Return:void
	 * @Throws:
	 * @Date:2018年5月11日下午1:34:17
	 * @Author:梁英男
	 */
	void batchSaveAxisLabel(List<LoginLabel> loginLabels);




}
