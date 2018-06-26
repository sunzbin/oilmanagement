package core.support;

import java.util.List;
import java.util.Map;

/**
 * 分页封装参数的类
 * @author sunzb
 *
 */
public class PageBaseParameter<E> {

	//条件参数
	private Map<String, Object> param;
	//排序参数
	private Map<String, String> sortMap;

	//页数
	private int firstRows;
	
	//每页的数据行数
	private int maxRows;
	
	//返回的结果
	private List<E> resultList;
	
	//返回信息的总行数
	private long totalRows;
	
	//递归查询条件（可为null）
	private Map<String, Object> conditionMap;

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public Map<String, String> getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map<String, String> sortMap) {
		this.sortMap = sortMap;
	}

	public int getFirstRows() {
		return firstRows;
	}

	public void setFirstRows(int firstRows) {
		this.firstRows = firstRows;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public List<E> getResultList() {
		return resultList;
	}

	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public Map<String, Object> getConditionMap() {
		return conditionMap;
	}

	public void setConditionMap(Map<String, Object> conditionMap) {
		this.conditionMap = conditionMap;
	}

	
}
