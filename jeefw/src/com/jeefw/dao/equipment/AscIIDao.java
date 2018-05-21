/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.dao.equipment;

import java.util.List;

import com.jeefw.model.equipment.AscII;

import core.dao.Dao;

/**
 * @Author:梁英男
 * @Date:2018年5月9日
 */
public interface AscIIDao extends Dao<AscII>{

	/**
	 * @Title:batchSaveAscII
	 * @Description:
	 * @Param:
	 * @Return:void
	 * @Throws:
	 * @Date:2018年5月11日上午10:44:11
	 * @Author:梁英男
	 */
	void batchSaveAscII(List<AscII> ascIIs);

}
