package com.jeefw.controller.equipment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.service.equipment.AxisLabelService;

import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.FileUpload;
import core.util.ObjectExcelRead;
import core.util.PathUtil;
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
		
		 /** 
		 * 转到Excel上传页面 
		 * @return 
		 * @author lp 
		 */  
		@RequestMapping(value="/read")  
		public String addExcel(){
		    return "/back/label/upload";  
		}  
		
		/**  
		 * 读取Excel数据到数据库  
		 * @param file  
		 * @param request  
		 * @return  
		 * @throws IOException  
		 * @author lp  
		 */  
		@RequestMapping(value="/readExcel")   
		public ModelAndView readExcel(@RequestParam(value="excel",required=false) MultipartFile file,HttpServletRequest request,HttpSession session) throws IOException{  
			ModelAndView mv = new ModelAndView();
			if (null != file && !file.isEmpty()) {
				List<AxisLabel> axisLabels = new LinkedList<>();
				String filePath = PathUtil.getClasspath() + "uploadFiles/file/";	//文件上传路径
				String fileName =  FileUpload.fileUp(file, filePath, "userexcel");//执行上传
				List<Map<String,String>> list = null;
				if(ObjectExcelRead.isExcel2003(new FileInputStream(new File(filePath+fileName)))) {
					list = (List<Map<String,String>>)ObjectExcelRead.readExcel2003(filePath, fileName, 0, 0, 0);
				}else if(ObjectExcelRead.isExcel2007(new FileInputStream(new File(filePath+fileName)))) {
					list = (List<Map<String,String>>)ObjectExcelRead.readExcel2007(filePath, fileName, 0, 0, 0);
				}
				if(list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						AxisLabel axisLabel = new AxisLabel();
						axisLabel.setLabelType((String)list.get(i).get("var0"));
						axisLabel.setCarCode((String)list.get(i).get("var1"));
						axisLabel.setCarNumber((String)list.get(i).get("var2"));
						axisLabel.setAxialPosition((String)list.get(i).get("var3"));
						axisLabel.setPointPosition((String)list.get(i).get("var4"));
						axisLabel.setGreaseTypeCode((String)list.get(i).get("var5"));
						axisLabel.setSpare_one((String)list.get(i).get("var6"));
						axisLabel.setSpare_two((String)list.get(i).get("var7"));
						axisLabel.setCheckoutBit((String)list.get(i).get("var8"));
						axisLabels.add(axisLabel);
					}
					if(axisLabels.size()>0) {
						axisLabelService.batchSaveAxisLabel(axisLabels);
					}
				}
			}
			mv.setViewName("/back/label/axis");
			return mv;
		}
}
