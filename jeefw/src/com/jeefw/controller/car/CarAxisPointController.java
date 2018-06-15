/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年6月13日
 */
package com.jeefw.controller.car;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.carmanagement.CarAxisPoint;
import com.jeefw.model.carmanagement.CarManagement;
import com.jeefw.service.car.CarAxisPointService;
import com.jeefw.service.car.CarService;

import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Author:梁英男
 * @Date:2018年6月13日
 */
@Controller
@RequestMapping("/car/axis")
public class CarAxisPointController extends JavaEEFrameworkBaseController<CarAxisPoint> implements Constant {

	@Resource
	private CarAxisPointService carAxisPointAxisService;
	
	@Resource
	private CarService carService;
	

	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getCarAxis", method = { RequestMethod.POST, RequestMethod.GET })
	public void getAscii(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		CarAxisPoint carAxisPoint = new CarAxisPoint();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
//				if (result.getString("field").equals("dictKey") && result.getString("op").equals("eq")) {
//					dict.set$eq_ascIIKey(result.getString("data"));
//				}
//				if (result.getString("field").equals("dictValue") && result.getString("op").equals("cn")) {
//					dict.set$like_ascIIValue(result.getString("data"));
//				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				carAxisPoint.setFlag("OR");
			} else {
				carAxisPoint.setFlag("AND");
			}
		}
		carAxisPoint.setFirstResult((firstResult - 1) * maxResults);
		carAxisPoint.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		carAxisPoint.setSortedConditions(sortedCondition);
		QueryResult<CarAxisPoint> queryResult = carAxisPointAxisService.doPaginationQuery(carAxisPoint);
		List<CarAxisPoint> resultList = new ArrayList<>();
		for (int i = 0; i < queryResult.getResultList().size(); i++) {
			if(StringUtil.isEmpty(queryResult.getResultList().get(i).getPid())) {
				CarManagement car = carService.get(queryResult.getResultList().get(i).getAscription_loco());
				if(!StringUtil.isNull(car)) {
					if(car.getCarType().equals("01")) {
						car.setCarType("SS7C");
					}else if(car.getCarType().equals("02")) {
						car.setCarType("SS7E");	
					}else if(car.getCarType().equals("03")) {
						car.setCarType("HXD1D");
					}else if(car.getCarType().equals("04")) {
						car.setCarType("HXD3D");
					}else if(car.getCarType().equals("05")) {
						car.setCarType("HXD3G");
					}
					queryResult.getResultList().get(i).setAscription_loco("车型:"+car.getCarType()+","+"车号:"+car.getCarNum());
				}
				resultList.add(queryResult.getResultList().get(i));
			}
		}
		JqGridPageView<CarAxisPoint> dictListView = new JqGridPageView<CarAxisPoint>();
		dictListView.setMaxResults(maxResults);
		dictListView.setRows(resultList);
		dictListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, dictListView);
	}

	// 保存字典的实体Bean
	@RequestMapping(value = "/saveCarAxis", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(CarAxisPoint entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		String[] findName = new String [2];
		findName[0] = "ascription_loco";
		findName[1] = "axis_name";
		Object[] findValue = new Object[2];
		findValue[0] = entity.getAscription_loco();
		findValue[1] = entity.getAxis_name();
		List<CarAxisPoint> findeResult = carAxisPointAxisService.queryByProerties(findName, findValue);
		if(findeResult.size()==0) {
			if (CMD_EDIT.equals(parameter.getCmd())) {
				carAxisPointAxisService.merge(entity);
			} else if (CMD_NEW.equals(parameter.getCmd())) {
				carAxisPointAxisService.persist(entity);
			}
			parameter.setSuccess(true);
			writeJSON(response, parameter);
		}else {
			parameter.setMessage("该轴位已存在");
			writeJSON(response, parameter);
		}
	}

	// 操作字典的删除、导出Excel、字段判断和保存
	@RequestMapping(value = "/operateCarAxis", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			deleteDict(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
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
			CarAxisPoint entity = new CarAxisPoint();
			entity.setAscription_loco(request.getParameter("ascription_loco"));
			entity.setAxis_name(request.getParameter("axis_name"));
			entity.setAxis_code(request.getParameter("axis_code"));
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
	@RequestMapping("/deleteAxis")
	public void deleteDict(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		String idsCheck = "";
		for (int i = 0; i < ids.length; i++) {
			List<CarAxisPoint> carAxisPoints = carAxisPointAxisService.queryByProerties("pid", ids[i]);
			if(carAxisPoints.size()==0) {
				idsCheck += ids[i]+",";
			}
		}
		String[] delIds = idsCheck.split(",");
		Long [] lids = new Long[delIds.length];
		for (int i = 0; i < delIds.length; i++) {
			lids[i] = Long.valueOf(delIds[i]);
		}
		boolean flag = carAxisPointAxisService.deleteByPK(lids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}
	
	@RequestMapping(value = "/findCarManagements", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Object findCarManagements() throws IOException {
		List<CarManagement> carManagements = carService.doQueryAll();
		for (int i = 0; i < carManagements.size(); i++) {
			if(!StringUtil.isNull(carManagements.get(i))) {
				if(carManagements.get(i).getCarType().equals("01")) {
					carManagements.get(i).setCarType("SS7C");
				}else if(carManagements.get(i).getCarType().equals("02")) {
					carManagements.get(i).setCarType("SS7E");	
				}else if(carManagements.get(i).getCarType().equals("03")) {
					carManagements.get(i).setCarType("HXD1D");
				}else if(carManagements.get(i).getCarType().equals("04")) {
					carManagements.get(i).setCarType("HXD3D");
				}else if(carManagements.get(i).getCarType().equals("05")) {
					carManagements.get(i).setCarType("HXD3G");
				}
			}
		}
		return new Gson().toJson(carManagements);
	}

	
	
}
