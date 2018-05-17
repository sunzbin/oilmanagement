package com.jeefw.controller.equipment;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.equipment.AscII;
import com.jeefw.model.equipment.FillingMachine;
import com.jeefw.service.equipment.FillingMachineService;

import core.support.ExtJSBaseParameter;
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
@RequestMapping("/filling")
public class FillingMachineController extends JavaEEFrameworkBaseController<FillingMachine> implements Constant {

	@Resource
	private FillingMachineService fillingMachineService;

	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getMachineInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public void getMachineInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		FillingMachine fillingMachine = new FillingMachine();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				if (result.getString("field").equals("fillingMachineKey") && result.getString("op").equals("eq")) {
					fillingMachine.set$eq_fillingMachineKey(result.getString("data"));
				}
				if (result.getString("field").equals("fillingMachineValue") && result.getString("op").equals("cn")) {
					fillingMachine.set$like_fillingMachineValue(result.getString("data"));
				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				fillingMachine.setFlag("OR");
			} else {
				fillingMachine.setFlag("AND");
			}
		}
		fillingMachine.setFirstResult((firstResult - 1) * maxResults);
		fillingMachine.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		fillingMachine.setSortedConditions(sortedCondition);
		QueryResult<FillingMachine> queryResult = fillingMachineService.doPaginationQuery(fillingMachine);
		for (int i = 0; i < queryResult.getResultList().size(); i++) {
			if(queryResult.getResultList().get(i).getRunningState().equals("1")) {
				queryResult.getResultList().get(i).setRunningState("运行");
			}else {
				queryResult.getResultList().get(i).setRunningState("停止");
			}
			if(queryResult.getResultList().get(i).getEquipmentState().equals("1")) {
				queryResult.getResultList().get(i).setEquipmentState("启用");
			}else {
				queryResult.getResultList().get(i).setEquipmentState("停用");
			}
		}
		JqGridPageView<FillingMachine> dictListView = new JqGridPageView<FillingMachine>();
		dictListView.setMaxResults(maxResults);
		dictListView.setRows(queryResult.getResultList());
		dictListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, dictListView);
	}

	// 操作字典的删除、导出Excel、字段判断和保存
	@RequestMapping(value = "/operateFillingMachine", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			deleteFillingMachine(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
		} else if (oper.equals("excel")) {
			response.setContentType("application/msexcel;charset=UTF-8");
			try {
				response.addHeader("Content-Disposition", "attachment;filename=file.xls");
				OutputStream out = response.getOutputStream();
				out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Map<String, Object> result = new HashMap<String, Object>();
			FillingMachine dict = null;
			if (oper.equals("edit")) {
				dict = fillingMachineService.get(Long.valueOf(id));
			}
			FillingMachine entity = new FillingMachine();
			entity.setAffiliatedInstitutions(request.getParameter("affiliatedInstitutions"));
			entity.setProductModel(request.getParameter("productModel"));
			entity.setProductCoding(request.getParameter("productCoding"));
			entity.setEquipmentNumber(request.getParameter("equipmentNumber"));
			entity.setElectricQuantity(request.getParameter("electricQuantity"));
			entity.setRunningState(request.getParameter("runningState"));
			entity.setEquipmentState(request.getParameter("equipmentState"));
			if (oper.equals("edit")) {
				entity.setId(Long.valueOf(id));
				entity.setCmd("edit");
				doSave(entity, request, response);
			} else if (oper.equals("add")) {
				entity.setCmd("new");
				doSave(entity, request, response);
			}
		}
	}
	
	// 删除字典
	@RequestMapping("/deleteFillingMachine")
	public void deleteFillingMachine(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = fillingMachineService.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

	@RequestMapping(value = "/saveFillingMachine", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(FillingMachine entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			fillingMachineService.merge(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			fillingMachineService.persist(entity);
		}
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}





}
