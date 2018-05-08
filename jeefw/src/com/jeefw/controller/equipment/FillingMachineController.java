package com.jeefw.controller.equipment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.equipment.FillingMachine;
import com.jeefw.service.equipment.FillingMachineService;

import core.support.JqGridPageView;
import core.support.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:43
 * @author:liangyingnan
 */
@Controller
@RequestMapping("/fillingMachine")
public class FillingMachineController extends JavaEEFrameworkBaseController<FillingMachine> implements Constant {

	@Autowired
	private FillingMachineService fillingMachineService;

	@RequestMapping(value = "/getMachineInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public void getRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		FillingMachine fillingMachine = new FillingMachine();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
		}
		fillingMachine.setFirstResult((firstResult - 1) * maxResults);
		fillingMachine.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		fillingMachine.setSortedConditions(sortedCondition);
		QueryResult<FillingMachine> queryResult = fillingMachineService.doPaginationQuery(fillingMachine);
		JqGridPageView<FillingMachine> roleListView = new JqGridPageView<FillingMachine>();
		roleListView.setMaxResults(maxResults);
		roleListView.setRows(queryResult.getResultList());
		roleListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, roleListView);
	}


	// 删除角色
	@RequestMapping("/deleteFillingMachine")
	public void deleteRole(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			fillingMachineService.deleteByPK(ids);
			flag = fillingMachineService.deleteByPK(id);
		}
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}









}
