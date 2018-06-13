package com.jeefw.controller.car;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.carmanagement.CarManagement;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.model.sys.Department;
import com.jeefw.service.car.CarService;
import com.jeefw.service.sys.DepartmentService;

import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 机车的控制层
 */
@Controller
@RequestMapping("/car/carManager")
public class CarController extends JavaEEFrameworkBaseController<CarManagement> implements Constant {

	@Resource
	private CarService carService;
	
	@Resource
	private DepartmentService departmentService;

	// 查询部门的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getCar", method = { RequestMethod.POST, RequestMethod.GET })
	public void getDepartment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		CarManagement car = new CarManagement();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
//				JSONObject result = (JSONObject) jsonArray.get(i);
//				if (result.getString("field").equals("departmentKey") && result.getString("op").equals("eq")) {
//					department.set$eq_departmentKey(result.getString("data"));
//				}
//				if (result.getString("field").equals("departmentValue") && result.getString("op").equals("cn")) {
//					department.set$like_departmentValue(result.getString("data"));
//				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				car.setFlag("OR");
			} else {
				car.setFlag("AND");
			}
		}
		car.setFirstResult((firstResult - 1) * maxResults);
		car.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		car.setSortedConditions(sortedCondition);
		QueryResult<CarManagement> queryResult = carService.doPaginationQuery(car);
		for (int i = 0; i < queryResult.getResultList().size(); i++) {
			Department department = departmentService.get(Long.valueOf(queryResult.getResultList().get(i).getDepartmentId()));
			queryResult.getResultList().get(i).setDepartmentId(department.getDepartmentValue());
		}
//		JqGridPageView<CarManagement> departmentListView = new JqGridPageView<CarManagement>();
//		departmentListView.setMaxResults(maxResults);
//		List<CarManagement> departmentCnList = carService.queryDepartmentCnList(queryResult.getResultList());
//		departmentListView.setRows(departmentCnList);
//		departmentListView.setRecords(queryResult.getTotalCount());
		JqGridPageView<CarManagement> carManagementListView = new JqGridPageView<CarManagement>();
		carManagementListView.setMaxResults(maxResults);
		carManagementListView.setRows(queryResult.getResultList());
		carManagementListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, carManagementListView);
	}

	// 保存部门的实体Bean

	@RequestMapping(value = "/saveCar", method = { RequestMethod.POST, RequestMethod.GET })
	public void saveCar(CarManagement entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			carService.merge(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			carService.persist(entity);
		}
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	// 操作部门的删除、导出Excel、字段判断和保存
	@RequestMapping(value = "/operateCar", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateCar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			deleteCar(request, response,ids);
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
			String carType = request.getParameter("carType");
			String carTypeCode = request.getParameter("carTypeCode");
			String carNum = request.getParameter("carNum");
			String departmentId = request.getParameter("departmentId");
			CarManagement entity = new CarManagement();
			entity.setCarNum(carNum);
			entity.setCarType(carType);
			entity.setCarTypeCode(carTypeCode);
			entity.setDepartmentId(departmentId);
			if (oper.equals("edit")) {
				entity.setId(id);
				entity.setCmd("edit");
				saveCar(entity, request, response);
			} else if (oper.equals("add")) {
				entity.setCmd("new");
				saveCar(entity, request, response);
		}
		}
	}

	// 删除部门
	@RequestMapping("/deleteCar")
	public void deleteCar(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") String[] ids) throws IOException {
		boolean flag = carService.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}



}
