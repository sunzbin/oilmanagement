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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliatedInstitutions == null) ? 0 : affiliatedInstitutions.hashCode());
		result = prime * result + ((electricQuantity == null) ? 0 : electricQuantity.hashCode());
		result = prime * result + ((equipmentNumber == null) ? 0 : equipmentNumber.hashCode());
		result = prime * result + ((equipmentState == null) ? 0 : equipmentState.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productCoding == null) ? 0 : productCoding.hashCode());
		result = prime * result + ((productModel == null) ? 0 : productModel.hashCode());
		result = prime * result + ((runningState == null) ? 0 : runningState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FillingMachine other = (FillingMachine) obj;
		if (affiliatedInstitutions == null) {
			if (other.affiliatedInstitutions != null)
				return false;
		} else if (!affiliatedInstitutions.equals(other.affiliatedInstitutions))
			return false;
		if (electricQuantity == null) {
			if (other.electricQuantity != null)
				return false;
		} else if (!electricQuantity.equals(other.electricQuantity))
			return false;
		if (equipmentNumber == null) {
			if (other.equipmentNumber != null)
				return false;
		} else if (!equipmentNumber.equals(other.equipmentNumber))
			return false;
		if (equipmentState == null) {
			if (other.equipmentState != null)
				return false;
		} else if (!equipmentState.equals(other.equipmentState))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productCoding == null) {
			if (other.productCoding != null)
				return false;
		} else if (!productCoding.equals(other.productCoding))
			return false;
		if (productModel == null) {
			if (other.productModel != null)
				return false;
		} else if (!productModel.equals(other.productModel))
			return false;
		if (runningState == null) {
			if (other.runningState != null)
				return false;
		} else if (!runningState.equals(other.runningState))
			return false;
		return true;
	}
	
	
	
	
}
