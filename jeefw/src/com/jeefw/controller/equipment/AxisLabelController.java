package com.jeefw.controller.equipment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.model.equipment.FillingMachine;
import com.jeefw.service.equipment.AxisLabelService;

import core.support.JqGridPageView;
import core.support.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:37
 * @author:liangyingnan
 */
@Controller
@RequestMapping("/labelAxis")
public class AxisLabelController extends JavaEEFrameworkBaseController<AxisLabel> implements Constant {
	
	@Resource
	private AxisLabelService axisLabelService;

	// 查询字典的表格，包括分页、搜索和排序
		@RequestMapping(value = "/getAxisInfo", method = { RequestMethod.POST, RequestMethod.GET })
		public void getAxisInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Integer firstResult = Integer.valueOf(request.getParameter("page"));
			Integer maxResults = Integer.valueOf(request.getParameter("rows"));
			String sortedObject = request.getParameter("sidx");
			String sortedValue = request.getParameter("sord");
			String filters = request.getParameter("filters");
			AxisLabel axisLabel = new AxisLabel();
			if (StringUtils.isNotBlank(filters)) {
				JSONObject jsonObject = JSONObject.fromObject(filters);
				JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject result = (JSONObject) jsonArray.get(i);
					/*if (result.getString("field").equals("fillingMachineKey") && result.getString("op").equals("eq")) {
						fillingMachine.set$eq_fillingMachineKey(result.getString("data"));
					}
					if (result.getString("field").equals("fillingMachineValue") && result.getString("op").equals("cn")) {
						fillingMachine.set$like_fillingMachineValue(result.getString("data"));
					}*/
				}
				if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
					axisLabel.setFlag("OR");
				} else {
					axisLabel.setFlag("AND");
				}
			}
			axisLabel.setFirstResult((firstResult - 1) * maxResults);
			axisLabel.setMaxResults(maxResults);
			Map<String, String> sortedCondition = new HashMap<String, String>();
			sortedCondition.put(sortedObject, sortedValue);
			axisLabel.setSortedConditions(sortedCondition);
			QueryResult<AxisLabel> queryResult = axisLabelService.doPaginationQuery(axisLabel);
			JqGridPageView<AxisLabel> dictListView = new JqGridPageView<AxisLabel>();
			dictListView.setMaxResults(maxResults);
			dictListView.setRows(queryResult.getResultList());
			dictListView.setRecords(queryResult.getTotalCount());
			writeJSON(response, dictListView);
		}






}
