/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.service.equipment;

import java.util.List;

import com.jeefw.model.equipment.AscII;
import com.jeefw.model.equipment.AxisLabel;

import core.service.Service;

/**
 * @Author:梁英男
 * @Date:2018年5月9日
 */
public interface AscIIService  extends Service<AscII>{

	void batchSaveAscII(List<AscII> ascIIs);
}
