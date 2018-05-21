package com.jeefw.service.equipment;

import java.util.List;

import com.jeefw.model.equipment.LoginLabel;
import core.service.Service;

public interface LoginLabelService extends Service<LoginLabel> {

	/**
	 * @Title:batchSaveAxisLabel
	 * @Description:
	 * @Param:
	 * @Return:void
	 * @Throws:
	 * @Date:2018年5月11日上午11:15:36
	 * @Author:梁英男
	 */
	void batchSaveAxisLabel(List<LoginLabel> loginLabels);



}
