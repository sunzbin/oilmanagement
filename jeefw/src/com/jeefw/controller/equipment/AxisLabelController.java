package com.jeefw.controller.equipment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.Region;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.equipment.AscII;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.model.sys.Dict;
import com.jeefw.service.equipment.AscIIService;
import com.jeefw.service.equipment.AxisLabelService;
import com.jeefw.service.sys.DictService;

import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.DownloadBase;
import core.util.FileUpload;
import core.util.HexAccumulation;
import core.util.ObjectExcelRead;
import core.util.PathUtil;
import core.util.StringUtil;
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
	
	Logger logger = Logger.getLogger(AxisLabelController.class);
	
	@Resource
	private AxisLabelService axisLabelService;
	
	@Resource
	private DictService dictService;
	
	@Resource
	private AscIIService ascIIService;

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
			List<AxisLabel> resultList =  queryResult.getResultList();
			logger.info(resultList.size());
			logger.info("~~~~~~~~~~~"+new Gson().toJson(resultList).toString());
			for (int i = 0; i < resultList.size(); i++) {
				String[] findLabelTypeKey = new String [2];
				findLabelTypeKey[0] = "dictKey";
				findLabelTypeKey[1] = "parentDictkey";
				String[] findLabelTypeValue = new String [2];
				findLabelTypeValue[0] = resultList.get(i).getLabelType();
				findLabelTypeValue[1] = "LABEL_TYPE";
				Dict labelType = dictService.getByProerties(findLabelTypeKey,findLabelTypeValue);
				resultList.get(i).setLabelType(labelType.getDictValue());//设置标签类型
				
				String[] findCarCodeKey = new String [2];
				findCarCodeKey[0] = "dictKey";
				findCarCodeKey[1] = "parentDictkey";
				String[] findCarCodeValue = new String [2];
				findCarCodeValue[0] = resultList.get(i).getCarCode();
				findCarCodeValue[1] = "CAR_CODE";
				Dict carCode = dictService.getByProerties(findCarCodeKey,findCarCodeValue);
				resultList.get(i).setCarCode(carCode.getDictValue());//设置车型代码
				if(!StringUtil.isEmpty(resultList.get(i).getCarNumber())) {
					String[] carNumberArray = resultList.get(i).getCarNumber().split(",");
					String carNumber = "";
					for (int j = 0; j < carNumberArray.length; j++) {
//						carNumber += ascIIService.getByProerties("hex", carNumberArray[j]).getCharacters();
						carNumber += carNumberArray[j];
					}
					resultList.get(i).setCarNumber(carNumber);
				}
				String[] findAxialPositionKey = new String [2];
				findAxialPositionKey[0] = "dictKey";
				findAxialPositionKey[1] = "parentDictkey";
				String[] findAxialPositionValue = new String [2];
				findAxialPositionValue[0] = resultList.get(i).getAxialPosition();
				findAxialPositionValue[1] = "AXIAL_POSITION";
				Dict axialPosition = dictService.getByProerties(findAxialPositionKey,findAxialPositionValue);
				resultList.get(i).setAxialPosition(axialPosition.getDictValue());//设置加注轴位
				
				String[] findPointPositionKey = new String [2];
				findPointPositionKey[0] = "dictKey";
				findPointPositionKey[1] = "parentDictkey";
				String[] findPointPositionValue = new String [2];
				findPointPositionValue[0] = "point_position:"+resultList.get(i).getPointPosition();
				findPointPositionValue[1] = "POINT_POSITION";
				Dict pointPosition = dictService.getByProerties(findPointPositionKey,findPointPositionValue);
				resultList.get(i).setPointPosition(pointPosition.getDictValue());//设置加注点位
				
				resultList.get(i).setGreaseTypeCode("无");//设置油脂类型
				
			}
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
					list = (List<Map<String,String>>)ObjectExcelRead.readExcel2003(filePath, fileName, 1, 0, 0);
				}else if(ObjectExcelRead.isExcel2007(new FileInputStream(new File(filePath+fileName)))) {
					list = (List<Map<String,String>>)ObjectExcelRead.readExcel2007(filePath, fileName, 1, 0, 0);
				}
				if(list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						String[] labelContent = list.get(i).get("var1").split(" ");
						AxisLabel axisLabel = new AxisLabel();
						axisLabel.setLabelType(labelContent[1]);
						axisLabel.setCarCode(labelContent[2]);
						axisLabel.setCarNumber(labelContent[3]+","+labelContent[4]+","+labelContent[5]+","+labelContent[6]);
						axisLabel.setAxialPosition(labelContent[7]);
						axisLabel.setPointPosition(labelContent[8]);
						axisLabel.setGreaseTypeCode(labelContent[9]);
						axisLabel.setSpare_one(labelContent[10]);
						axisLabel.setSpare_two(labelContent[11]);
						axisLabel.setCheckoutBit(labelContent[12]);
						axisLabel.setReadTime(list.get(i).get("var2"));
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
		
		// 操作字典的删除、导出Excel、字段判断和保存
		@RequestMapping(value = "/operateLabelAxis", method = { RequestMethod.POST, RequestMethod.GET })
		public void operateLabelAxis(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String oper = request.getParameter("oper");
			String id = request.getParameter("id");
			if (oper.equals("del")) {
				String[] ids = id.split(",");
				deleteLabelAxis(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
			} else if (oper.equals("excel")) {
				String ids = request.getParameter("ids");
				String[] ids1 = ids.split(",");
				String[] tetle = new String [2];
				tetle[0]= "序号";
				tetle[1]= "标签内容";
				List<AxisLabel> varList = new ArrayList<AxisLabel>();
				for(int i=0;i<ids1.length;i++){
					AxisLabel axisLabel = axisLabelService.get(Long.valueOf(ids1[i]));
					if(null != varList && !"".equals(varList)) {
						varList.add(axisLabel);
					}
				}
				print(varList,response);
			} else {
				Map<String, Object> result = new HashMap<String, Object>();
				AxisLabel axisLabel = null;
				if (oper.equals("edit")) {
					axisLabel = axisLabelService.get(Long.valueOf(id));
				}
				AxisLabel entity = new AxisLabel();
				entity.setLabelType(request.getParameter("labelType"));
				entity.setCarCode(request.getParameter("carCode"));
				entity.setCarNumber(request.getParameter("carNumber"));
				entity.setAxialPosition(request.getParameter("axialPosition"));
				entity.setPointPosition(request.getParameter("pointPosition"));
				entity.setSpare_one("00");
				entity.setSpare_two("00");
				entity.setGreaseTypeCode(request.getParameter("greaseTypeCode"));
				if (oper.equals("edit")) {
					entity.setId(Long.valueOf(id));
					entity.setCmd("edit");
					doSave(axisLabel, request, response);
				} else if (oper.equals("add")) {
					entity.setCmd("new");
					doSave(entity, request, response);
				}
			}
		}
		
		// 保存标签的实体Bean
		@RequestMapping(value = "/saveLabelAxis", method = { RequestMethod.POST, RequestMethod.GET })
		public void doSave(AxisLabel entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
			logger.info("*******************"
					+ "labelType:"+entity.getLabelType()+"------------"
					+ "carCode:"+entity.getCarCode()+"------------"
					+ "carNumber:"+entity.getCarNumber()+"------------"
					+ "axialPosition:"+entity.getAxialPosition()+"------------"
					+ "pointPosition:"+entity.getPointPosition()+"------------"
					+ "greaseTypeCode:"+entity.getGreaseTypeCode()+"------------"
					+ "***************************");
			char[] charCarNumber = entity.getCarNumber().toCharArray();
			String carNumber = "";
			String carNumberChar = "";
			String checkbit = entity.getLabelType()+entity.getCarCode();
			logger.info("################"+charCarNumber.length);
			for (int i = 0; i < charCarNumber.length; i++) {
				carNumberChar += charCarNumber[i]+",";
				AscII ascII = ascIIService.getByProerties("characters", charCarNumber[i]+"");
				String hex = ascII.getHex();
				carNumber += hex+",";
				checkbit+=hex;
			}
			entity.setCarNumber(carNumberChar);
			checkbit += entity.getAxialPosition()+entity.getPointPosition()+entity.getGreaseTypeCode()+entity.getSpare_one()+entity.getSpare_two();
			checkbit = HexAccumulation.getCheckCode(checkbit);
			entity.setCheckoutBit(checkbit.substring(checkbit.length()-2));
			ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
			if (CMD_EDIT.equals(parameter.getCmd())) {
				logger.info("start edit info");
				axisLabelService.merge(entity);
			} else if (CMD_NEW.equals(parameter.getCmd())) {
				logger.info("start sava info");
				axisLabelService.persist(entity);
			}
			parameter.setSuccess(true);
			writeJSON(response, parameter);
		}
		
		// 删除字典
		@RequestMapping("/deleteLabelAxis")
		public void deleteLabelAxis(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
			boolean flag = axisLabelService.deleteByPK(ids);
			if (flag) {
				writeJSON(response, "{success:true}");
			} else {
				writeJSON(response, "{success:false}");
			}
		}
		public  void print(List<AxisLabel> prints, HttpServletResponse response) throws FileNotFoundException, IOException, ParseException{
			String fileName = "label";
			
			HSSFWorkbook wb = new HSSFWorkbook(); // 新建工作簿
			
			int rowNo = 1;				//行号
			short colNo = 0;			//列号
			
			HSSFSheet sheet = wb.createSheet(fileName);		//得到第一个工作表
			
			HSSFRow nRow = sheet.createRow(0);

			HSSFCell nCell = nRow.createCell(colNo++);
			nCell.setCellValue("序号");
		
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue("标签内容");
			
			for(int i=0;i<prints.size();i++){
				colNo = 0;//初始化
				nRow = sheet.createRow(rowNo++);
				nRow.setHeightInPoints(24);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(i);
				String[]  carNumberArray = prints.get(i).getCarNumber().split(",");
				String carNumber = "";
				for (int j = 0; j < carNumberArray.length; j++) {
					carNumber += ascIIService.getByProerties("characters", carNumberArray[j]).getHex()+" ";
				}
				String comment= prints.get(i).getLabelType()+" "+prints.get(i).getCarCode()+" "
				+carNumber+" "
				+prints.get(i).getAxialPosition()+" "+prints.get(i).getPointPosition()+" "+ prints.get(i).getGreaseTypeCode()
				+" "+ prints.get(i).getSpare_one()+" "+ prints.get(i).getSpare_two()+" "+prints.get(i).getCheckoutBit();
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(comment);
				
			}
			
			//7.生成excel文件
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();			//生成流对象
			wb.write(byteArrayOutputStream);								//将excel写入流

			//工具类，封装弹出下载框：		
			String outFile = new StringBuffer(fileName).append(".xls").toString();
			DownloadBase down = new DownloadBase();
			down.download(byteArrayOutputStream, response, outFile);
		}		
}
