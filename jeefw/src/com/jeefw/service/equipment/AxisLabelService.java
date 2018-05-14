package com.jeefw.service.equipment;

import java.util.List;

import com.jeefw.model.equipment.AxisLabel;

import core.service.Service;

public interface AxisLabelService extends Service<AxisLabel> {

	void batchSaveAxisLabel(List<AxisLabel> axisLabels);

}
