package com.jeefw.dao.equipment.impl;

import com.jeefw.dao.equipment.LabelDetailDao;
import com.jeefw.model.equipment.LabelDetail;
import core.dao.BaseDao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:16
 * @author:liangyingnan
 */
public class LabelDetailDaoImpl extends BaseDao<LabelDetail> implements LabelDetailDao {

	public LabelDetailDaoImpl() {
		super(LabelDetail.class);
	}

}
