package com.jeefw.controller.equipment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;
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
import com.jeefw.model.sys.Dict;
import com.jeefw.service.equipment.AscIIService;
import com.jeefw.service.equipment.LoginLabelService;
import com.jeefw.service.sys.DictService;

import core.support.ExtJSBaseParameter;
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
	
	@Resource
	private DictService dictService;
	
	@Resource
	private AscIIService ascIIService;

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
			List<LoginLabel> resultList =  queryResult.getResultList();
			for (int i = 0; i < resultList.size(); i++) {
				String[] findLabelTypeKey = new String [2];
				findLabelTypeKey[0] = "dictKey";
				findLabelTypeKey[1] = "parentDictkey";
				String[] findLabelTypeValue = new String [2];
				findLabelTypeValue[0] = resultList.get(i).getLabelType();
				findLabelTypeValue[1] = "LABEL_TYPE";
				Dict labelType = dictService.getByProerties(findLabelTypeKey,findLabelTypeValue);
				resultList.get(i).setLabelType(labelType.getDictValue());//设置标签类型
				String userId = ascIIService.getByProerties("hex", resultList.get(i).getUserId().split(",")[0]).getCharacters()+
						ascIIService.getByProerties("hex", resultList.get(i).getUserId().split(",")[1]).getCharacters()+
						ascIIService.getByProerties("hex", resultList.get(i).getUserId().split(",")[2]).getCharacters()+
						ascIIService.getByProerties("hex", resultList.get(i).getUserId().split(",")[3]).getCharacters();
				resultList.get(i).setUserId(userId);
				
			}
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
		    return "/back/label/upload2";  
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
				List<Map<String,String>> list = null;
				if(ObjectExcelRead.isExcel2003(new FileInputStream(new File(filePath+fileName)))) {
					list = (List<Map<String,String>>)ObjectExcelRead.readExcel2003(filePath, fileName, 1, 0, 0);
				}else if(ObjectExcelRead.isExcel2007(new FileInputStream(new File(filePath+fileName)))) {
					list = (List<Map<String,String>>)ObjectExcelRead.readExcel2007(filePath, fileName, 1, 0, 0);
				}
				if(list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						String[] labelContent = list.get(i).get("var1").split(" ");
						LoginLabel loginLabel = new LoginLabel();
						loginLabel.setLabelType(labelContent[1]);
						loginLabel.setUserId(labelContent[2]+","+labelContent[3]+","+labelContent[4]+","+labelContent[5]);
						loginLabel.setIs_delete("1");
						loginLabel.setSpare_one(labelContent[6]);
						loginLabel.setSpare_two(labelContent[7]);
						loginLabel.setSpare_three(labelContent[8]);
						loginLabel.setSpare_four(labelContent[9]);
						loginLabel.setSpare_five(labelContent[10]);
						loginLabel.setSpare_six(labelContent[11]);
						loginLabel.setCheckoutBit(labelContent[12]);
						loginLabel.setReadTime(list.get(i).get("var2"));
						loginLabels.add(loginLabel);
					}
					if(loginLabels.size()>0) {
						loginLabelService.batchSaveAxisLabel(loginLabels);
					}
				}
			}
			mv.setViewName("/back/label/login");
			return mv;
		}
		
		// 操作字典的删除、导出Excel、字段判断和保存
				@RequestMapping(value = "/operateLabelLogin", method = { RequestMethod.POST, RequestMethod.GET })
				public void operateDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
					String oper = request.getParameter("oper");
					String id = request.getParameter("id");
					if (oper.equals("del")) {
						String[] ids = id.split(",");
						deleteLabelLogin(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
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
						LoginLabel axisLabel = null;
						if (oper.equals("edit")) {
							axisLabel = loginLabelService.get(Long.valueOf(id));
						}
						LoginLabel entity = new LoginLabel(); 
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
				
				// 保存标签的实体Bean
				@RequestMapping(value = "/saveLabelLogin", method = { RequestMethod.POST, RequestMethod.GET })
				public void doSave(LoginLabel entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
					ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
					if (CMD_EDIT.equals(parameter.getCmd())) {
						loginLabelService.merge(entity);
					} else if (CMD_NEW.equals(parameter.getCmd())) {
						loginLabelService.persist(entity);
					}
					parameter.setSuccess(true);
					writeJSON(response, parameter);
				}
				
				// 删除字典
				@RequestMapping("/deleteLabelAxis")
				public void deleteLabelLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
					boolean flag = loginLabelService.deleteByPK(ids);
					if (flag) {
						writeJSON(response, "{success:true}");
					} else {
						writeJSON(response, "{success:false}");
					}
				}
		
}
