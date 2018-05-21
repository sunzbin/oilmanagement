package com.jeefw.dao.equipment;

import java.util.List;

import com.jeefw.model.equipment.AxisLabel;
import core.dao.Dao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:10
 * @author:liangyingnan
 */
public interface AxisLabelDao extends Dao<AxisLabel> {

	/**
	 * @Title:batchSaveAxisLabel
	 * @Description:批量保存轴位标签
	 * @Param:
	 * @Return:void
	 * @Throws:
	 * @Date:2018年5月9日上午11:23:49
	 * @Author:梁英男
	 */
	void batchSaveAxisLabel(List<AxisLabel> axisLabels);

}
