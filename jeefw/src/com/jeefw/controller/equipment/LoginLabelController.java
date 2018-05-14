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
import com.jeefw.model.equipment.LoginLabel;
import com.jeefw.service.equipment.LoginLabelService;

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
 * @Date:2018-05-04 上午 11:44
 * @author:liangyingnan
 */
@Controller
@RequestMapping("/labelLogin")
public class LoginLabelController extends JavaEEFrameworkBaseController<LoginLabel> implements Constant {


	@Resource
	private LoginLabelService loginLabelService;

	// 查询字典的表格，包括分页、搜索和排序
		@RequestMapping(value = "/getLoginLabelInfo", method = { RequestMethod.POST, RequestMethod.GET })
		public void getAxisInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Integer firstResult = Integer.valueOf(request.getParameter("page"));
			Integer maxResults = Integer.valueOf(request.getParameter("rows"));
			String sortedObject = request.getParameter("sidx");
			String sortedValue = request.getParameter("sord");
			String filters = request.getParameter("filters");
			LoginLabel loginLabel = new LoginLabel();
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
					loginLabel.setFlag("OR");
				} else {
					loginLabel.setFlag("AND");
				}
			}
			loginLabel.setFirstResult((firstResult - 1) * maxResults);
			loginLabel.setMaxResults(maxResults);
			Map<String, String> sortedCondition = new HashMap<String, String>();
			sortedCondition.put(sortedObject, sortedValue);
			loginLabel.setSortedConditions(sortedCondition);
			QueryResult<LoginLabel> queryResult = loginLabelService.doPaginationQuery(loginLabel);
			JqGridPageView<LoginLabel> dictListView = new JqGridPageView<LoginLabel>();
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
				List<LoginLabel> loginLabels = new LinkedList<>();
				String filePath = PathUtil.getClasspath() + "uploadFiles/file/";	//文件上传路径
				String fileName =  FileUpload.fileUp(file, filePath, "userexcel");//执行上传
				List<Map> list = null;
				if(ObjectExcelRead.isExcel2003(new FileInputStream(new File(filePath+fileName)))) {
					list = (List)ObjectExcelRead.readExcel2003(filePath, fileName, 0, 0, 0);
				}else if(ObjectExcelRead.isExcel2007(new FileInputStream(new File(filePath+fileName)))) {
					list = (List)ObjectExcelRead.readExcel2007(filePath, fileName, 0, 0, 0);
				}
				if(list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						LoginLabel loginLabel = new LoginLabel();
						loginLabel.setLabelType((String)list.get(i).get("var0"));
						loginLabel.setUserId((String)list.get(i).get("var1"));
						loginLabel.setIs_delete("1");
						loginLabel.setSpare_one((String)list.get(i).get("var2"));
						loginLabel.setSpare_two((String)list.get(i).get("var3"));
						loginLabel.setSpare_three((String)list.get(i).get("var4"));
						loginLabel.setSpare_four((String)list.get(i).get("var5"));
						loginLabel.setSpare_five((String)list.get(i).get("var6"));
						loginLabel.setSpare_six((String)list.get(i).get("var7"));
						loginLabel.setCheckoutBit((String)list.get(i).get("var8"));
						loginLabels.add(loginLabel);
					}
					if(loginLabels.size()>0) {
						loginLabelService.batchSaveAxisLabel(loginLabels);
					}
				}
			}
			return mv;
		}
		
}
