/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月4日
 */
package com.jeefw.model.equipment;

import com.jeefw.model.equipment.param.FillingMachineParameter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 加注机信息
 * @Author:梁英男
 * @Date:2018年5月4日
 */
@Entity
@Table(name = "filling_machine")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class FillingMachine extends FillingMachineParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	/**
	 * 所属机构
	 */
	@Column(name = "affiliated_institutions")
	private Long  affiliatedInstitutions ;
	
	/**
	 * 产品型号
	 */
	@Column(name = "product_model", length = 30, nullable = false, unique = true)
	private String  productModel ;
	
	/**
	 * 产品编码
	 */
	@Column(name = "product_coding", length = 20)
	private String  productCoding ;
	
	/**
	 * 设备号
	 */
	@Column(name = "equipment_number", length = 20)
	private String   equipmentNumber;
	
	/**
	 * 电量
	 */
	@Column(name = "electric_quantity", length = 20)
	private String  electricQuantity;
	
	/**
	 * 运行状态: 1 运行状态 2 停止状态
	 */
	@Column(name = "running_state", length = 20)
	private String  runningState; 
	
	/**
	 * 设备状态: 1 启用 2 停用
	 */
	@Column(name = "equipment_state", length = 20)
	private String  equipmentState;


	public FillingMachine() {
	}

	public FillingMachine(Long affiliatedInstitutions, String productModel, String productCoding, String equipmentNumber, String electricQuantity, String runningState, String equipmentState) {
		this.affiliatedInstitutions = affiliatedInstitutions;
		this.productModel = productModel;
		this.productCoding = productCoding;
		this.equipmentNumber = equipmentNumber;
		this.electricQuantity = electricQuantity;
		this.runningState = runningState;
		this.equipmentState = equipmentState;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAffiliatedInstitutions() {
		return affiliatedInstitutions;
	}

	public void setAffiliatedInstitutions(Long affiliatedInstitutions) {
		this.affiliatedInstitutions = affiliatedInstitutions;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductCoding() {
		return productCoding;
	}

	public void setProductCoding(String productCoding) {
		this.productCoding = productCoding;
	}

	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	public String getElectricQuantity() {
		return electricQuantity;
	}

	public void setElectricQuantity(String electricQuantity) {
		this.electricQuantity = electricQuantity;
	}

	public String getRunningState() {
		return runningState;
	}

	public void setRunningState(String runningState) {
		this.runningState = runningState;
	}

	public String getEquipmentState() {
		return equipmentState;
	}

	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
}
