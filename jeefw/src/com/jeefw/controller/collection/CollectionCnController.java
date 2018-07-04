package com.jeefw.controller.collection;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.collection.CollectionCn;
import com.jeefw.model.sys.Dict;
import com.jeefw.service.collection.CollectionCnService;
import com.jeefw.service.sys.DictService;

import core.support.JqGridPageView;
import core.support.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户的控制层
 * 
 */
@Controller
@RequestMapping("/coll/collcn")
public class CollectionCnController extends JavaEEFrameworkBaseController<CollectionCn> implements Constant {

	private static final Log log = LogFactory.getLog(CollectionCnController.class);
	
	@Resource
	private DictService dictService;
	
	@Resource
	private CollectionCnService collectionCnService;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getColl", method = { RequestMethod.POST, RequestMethod.GET })
	public void getDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String xiuChengparam = request.getParameter("param");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		CollectionCn coll = new CollectionCn();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				if (result.getString("field").equals("operatorId") && result.getString("op").equals("cn")) {
					coll.set$like_collectionValue(result.getString("data"));
				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				coll.setFlag("OR");
			} else {
				coll.setFlag("AND");
			}
		}
		if("1".equals(xiuChengparam)){
			coll.set$like_xiuCheng("定修");
			coll.setFlag("AND");
		}
		coll.setFirstResult((firstResult - 1) * maxResults);
		coll.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
//		Map<String, Object> dynamicProperties = new HashMap<String, Object>();
		sortedCondition.put(sortedObject, sortedValue);
		coll.setSortedConditions(sortedCondition);
//		coll.setDynamicProperties(dynamicProperties);
		QueryResult<CollectionCn> queryResult = collectionCnService.doPaginationQuery(coll);
		JqGridPageView<CollectionCn> dictListView = new JqGridPageView<CollectionCn>();
		dictListView.setMaxResults(maxResults);
		List<CollectionCn> dictWithSubList = collectionCnService.queryCollectiontWithSubList(queryResult.getResultList());
		dictListView.setRows(dictWithSubList);
		dictListView.setRecords(queryResult.getTotalCount());
		
		//-------------------------获取预警参数信息------------------------------
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<Dict> dicts =  dictService.queryByProerties("parentDictkey", SESSION_WARNNING_PARAM);
		if(dicts != null ){
			for(int i = 0; i < dicts.size(); i++){
				if(SESSION_WARNNING_BLUE_PARAM.equals(dicts.get(i).getDictKey())){
					session.setAttribute(SESSION_WARNNING_BLUE_PARAM, dicts.get(i).getDictValue());
				}else if(SESSION_WARNNING_ORANGE_PARAM.equals(dicts.get(i).getDictKey())){
					session.setAttribute(SESSION_WARNNING_ORANGE_PARAM, dicts.get(i).getDictValue());
				}
			}
		}
		if(session.getAttribute(SESSION_WARNNING_BLUE_PARAM) == null || session.getAttribute(SESSION_WARNNING_ORANGE_PARAM) ==null){
			session.setAttribute(SESSION_WARNNING_BLUE_PARAM, 1*24);
			session.setAttribute(SESSION_WARNNING_ORANGE_PARAM, 3*24);
		}
		
		writeJSON(response, dictListView);
	}

}
