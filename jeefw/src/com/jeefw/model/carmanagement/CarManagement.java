package com.jeefw.model.carmanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Objects;
import com.jeefw.model.carmanagement.param.CarParameter;
import com.jeefw.model.sys.Department;

/**
 * 部门的实体类
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Entity
@Table(name = "car_management")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class CarManagement extends CarParameter {

	// 各个字段的含义请查阅文档的数据库结构部分
	private static final long serialVersionUID = -2240384171336800410L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;
	@Column(name = "car_type", length = 50)
	private String carType;
	@Column(name = "car_type_code", length = 50)
	private String carTypeCode;
	@Column(name = "car_num", length = 50)
	private String carNum;
	@Column(name = "departmentId", length = 200)
	private String departmentId;

	public CarManagement() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarTypeCode() {
		return carTypeCode;
	}

	public void setCarTypeCode(String carTypeCode) {
		this.carTypeCode = carTypeCode;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


}
