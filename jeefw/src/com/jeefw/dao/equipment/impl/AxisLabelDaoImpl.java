package com.jeefw.dao.equipment.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.equipment.AxisLabelDao;
import com.jeefw.model.equipment.AxisLabel;
import core.dao.BaseDao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:13
 * @author:liangyingnan
 */
@Repository
public class AxisLabelDaoImpl extends BaseDao<AxisLabel> implements AxisLabelDao {

	public AxisLabelDaoImpl() {
		super(AxisLabel.class);
	}


}
