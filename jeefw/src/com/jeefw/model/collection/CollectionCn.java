package com.jeefw.model.collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.jeefw.model.collection.param.CollectionCnParameter;

/**
 * 
 * @author sunzb
 *
 */
@Entity
@Table(name = "collection_cn")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class CollectionCn  extends CollectionCnParameter {

	// 各个字段的含义请查阅文档的数据库结构部分
	private static final long serialVersionUID = 6019103858711599150L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;
	@Column(name = "start_bit", length = 50, nullable = false)
	private String startBit;
	@Column(name = "order_bit", length = 50, nullable = false)
	private String orderBit;
	@Column(name = "log_num", length = 50, nullable = false)
	private String logNum;
	@Column(name = "data_length", length = 50, nullable = false)
	private String dataLength;
	@Column(name = "duan_num", length = 50, nullable = false)
	private String duanNum;
	@Column(name = "equ_num", length = 50, nullable = false)
	private String equNum;
	@Column(name = "cartype_num", length = 50, nullable = false)
	private String carTypeNum;
	@Column(name = "car_num", length = 50, nullable = false)
	private String carNum;
	@Column(name = "zhouwei", length = 50, nullable = false)
	private String zhouWei;
	@Column(name = "jiazhu_point", length = 50, nullable = false)
	private String jiaZhuPoint;
	@Column(name = "jiazhu_vol", length = 50, nullable = false)
	private String jiaZhuVol;
	@Column(name = "xiucheng", length = 50, nullable = false)
	private String xiuCheng;
	@Column(name = "oil_type", length = 50, nullable = false)
	private String oilType;
	@Column(name = "jiazhu_time", length = 50)
	private String jiazhuTime;
	@Column(name = "operator_id", length = 50)
	private String operatorId;
	@Column(name = "elec", length = 50)
	private String elec;
	@Column(name = "crchi", length = 50)
	private String crchi;
	@Column(name = "crcli", length = 50)
	private String crcli;

	public CollectionCn() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartBit() {
		return startBit;
	}

	public void setStartBit(String startBit) {
		this.startBit = startBit;
	}

	public String getOrderBit() {
		return orderBit;
	}

	public void setOrderBit(String orderBit) {
		this.orderBit = orderBit;
	}

	public String getLogNum() {
		return logNum;
	}

	public void setLogNum(String logNum) {
		this.logNum = logNum;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getDuanNum() {
		return duanNum;
	}

	public void setDuanNum(String duanNum) {
		this.duanNum = duanNum;
	}

	public String getEquNum() {
		return equNum;
	}

	public void setEquNum(String equNum) {
		this.equNum = equNum;
	}

	public String getCarTypeNum() {
		return carTypeNum;
	}

	public void setCarTypeNum(String carTypeNum) {
		this.carTypeNum = carTypeNum;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getZhouWei() {
		return zhouWei;
	}

	public void setZhouWei(String zhouWei) {
		this.zhouWei = zhouWei;
	}

	public String getJiaZhuPoint() {
		return jiaZhuPoint;
	}

	public void setJiaZhuPoint(String jiaZhuPoint) {
		this.jiaZhuPoint = jiaZhuPoint;
	}

	public String getJiaZhuVol() {
		return jiaZhuVol;
	}

	public void setJiaZhuVol(String jiaZhuVol) {
		this.jiaZhuVol = jiaZhuVol;
	}

	public String getXiuCheng() {
		return xiuCheng;
	}

	public void setXiuCheng(String xiuCheng) {
		this.xiuCheng = xiuCheng;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public String getJiazhuTime() {
		return jiazhuTime;
	}

	public void setJiazhuTime(String jiazhuTime) {
		this.jiazhuTime = jiazhuTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getElec() {
		return elec;
	}

	public void setElec(String elec) {
		this.elec = elec;
	}

	public String getCrchi() {
		return crchi;
	}

	public void setCrchi(String crchi) {
		this.crchi = crchi;
	}

	public String getCrcli() {
		return crcli;
	}

	public void setCrcli(String crcli) {
		this.crcli = crcli;
	}


}
