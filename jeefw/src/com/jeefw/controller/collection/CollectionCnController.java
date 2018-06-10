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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.collection.CollectionCn;
import com.jeefw.model.sys.Dict;
import com.jeefw.service.collection.CollectionCnService;

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
	private CollectionCnService collectionCnService;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	// 查询字典的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getColl", method = { RequestMethod.POST, RequestMethod.GET })
	public void getDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		CollectionCn coll = new CollectionCn();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
//				if (result.getString("field").equals("operatorId") && result.getString("op").equals("eq")) {
//					coll.set$eq_collectionKey(result.getString("data"));
//				}
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
		coll.setFirstResult((firstResult - 1) * maxResults);
		coll.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		coll.setSortedConditions(sortedCondition);
		QueryResult<CollectionCn> queryResult = collectionCnService.doPaginationQuery(coll);
		JqGridPageView<CollectionCn> dictListView = new JqGridPageView<CollectionCn>();
		dictListView.setMaxResults(maxResults);
		List<CollectionCn> dictWithSubList = collectionCnService.queryCollectiontWithSubList(queryResult.getResultList());
		dictListView.setRows(dictWithSubList);
		dictListView.setRecords(queryResult.getTotalCount());
		
		writeJSON(response, dictListView);
	}
	
//	@RequestMapping("/collection")
//	public String sysuser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		System.out.println("11111111111111111111111111111111111111");
//		return "back/collection/collectionCn";
//	}

}
