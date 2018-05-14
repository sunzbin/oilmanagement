package com.jeefw.model.equipment;

import com.jeefw.model.equipment.param.AxisLabelParameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * @Title: 轴位标签
 * @Description:
 * @Date:2018-05-04 上午 10:19
 * @author:liangyingnan
 */
@Entity
@Table(name = "axis_label")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class AxisLabel extends AxisLabelParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * 标签类型
	 */
	@Column(name = "label_type")
	private String  labelType ;

	/**
	 *车型代码
	 */
	@Column(name = "car_code")
	private String  carCode ;

	/**
	 *车号
	 */
	@Column(name = "car_number")
	private String  carNumber ;

	/**
	 *轴位 一轴：1；二轴：2；三轴：3；四轴：4；五轴：5；六轴：6
	 */
	@Column(name="axial_position")
	private String  axialPosition;

	/**
	 *点位 牵引电机传动端:1;抱轴体:2;牵引电机非传动端:3
	 */
	@Column(name="point_position")
	private String  pointPosition;

	/**
	 *油脂类型代码
	 */
	@Column(name="grease_type_code")
	private String greaseTypeCode;

	/**
	 * 创建人id
	 */
	@Column(name="create_id")
	private String createId;

	/**
	 * 鞋柜编码
	 */
	@Column(name="shoes_code")
	private String shoesCode;

	/**
	 * 鞋位数
	 */
	@Column(name="shoes_number")
	private String shoesNumber;

	/**
	 * 标签序列号
	 */
	@Column(name="shoes_serialnumber")
	private String  shoesSerialNumber;

	/**
	 * ip地址
	 */
	@Column(name="ip_address")
	private String ipAddress;

	/**
	 * 端口号
	 */
	@Column(name="port_number")
	private String portNumber;
	
	/**
	 * 是否删除  1 是 2 否
	 */
	@Column(name="is_delete")
	private String is_delete;
	
	/**
	 * 保留位一
	 */
	@Column(name="spare_one")
	private String spare_one;
	
	/**
	 * 保留位二
	 */
	@Column(name="spare_two")
	private String spare_two; 
	
	/**
	 * 校验位
	 */
	@Column(name="checkout_bit")
	private String  checkoutBit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getAxialPosition() {
		return axialPosition;
	}

	public void setAxialPosition(String axialPosition) {
		this.axialPosition = axialPosition;
	}

	public String getPointPosition() {
		return pointPosition;
	}

	public void setPointPosition(String pointPosition) {
		this.pointPosition = pointPosition;
	}

	public String getGreaseTypeCode() {
		return greaseTypeCode;
	}

	public void setGreaseTypeCode(String greaseTypeCode) {
		this.greaseTypeCode = greaseTypeCode;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getShoesCode() {
		return shoesCode;
	}

	public void setShoesCode(String shoesCode) {
		this.shoesCode = shoesCode;
	}

	public String getShoesNumber() {
		return shoesNumber;
	}

	public void setShoesNumber(String shoesNumber) {
		this.shoesNumber = shoesNumber;
	}

	public String getShoesSerialNumber() {
		return shoesSerialNumber;
	}

	public void setShoesSerialNumber(String shoesSerialNumber) {
		this.shoesSerialNumber = shoesSerialNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getSpare_one() {
		return spare_one;
	}

	public void setSpare_one(String spare_one) {
		this.spare_one = spare_one;
	}

	public String getSpare_two() {
		return spare_two;
	}

	public void setSpare_two(String spare_two) {
		this.spare_two = spare_two;
	}

	public String getCheckoutBit() {
		return checkoutBit;
	}

	public void setCheckoutBit(String checkoutBit) {
		this.checkoutBit = checkoutBit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((axialPosition == null) ? 0 : axialPosition.hashCode());
		result = prime * result + ((carCode == null) ? 0 : carCode.hashCode());
		result = prime * result + ((carNumber == null) ? 0 : carNumber.hashCode());
		result = prime * result + ((checkoutBit == null) ? 0 : checkoutBit.hashCode());
		result = prime * result + ((createId == null) ? 0 : createId.hashCode());
		result = prime * result + ((greaseTypeCode == null) ? 0 : greaseTypeCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((is_delete == null) ? 0 : is_delete.hashCode());
		result = prime * result + ((labelType == null) ? 0 : labelType.hashCode());
		result = prime * result + ((pointPosition == null) ? 0 : pointPosition.hashCode());
		result = prime * result + ((portNumber == null) ? 0 : portNumber.hashCode());
		result = prime * result + ((shoesCode == null) ? 0 : shoesCode.hashCode());
		result = prime * result + ((shoesNumber == null) ? 0 : shoesNumber.hashCode());
		result = prime * result + ((shoesSerialNumber == null) ? 0 : shoesSerialNumber.hashCode());
		result = prime * result + ((spare_one == null) ? 0 : spare_one.hashCode());
		result = prime * result + ((spare_two == null) ? 0 : spare_two.hashCode());
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
		AxisLabel other = (AxisLabel) obj;
		if (axialPosition == null) {
			if (other.axialPosition != null)
				return false;
		} else if (!axialPosition.equals(other.axialPosition))
			return false;
		if (carCode == null) {
			if (other.carCode != null)
				return false;
		} else if (!carCode.equals(other.carCode))
			return false;
		if (carNumber == null) {
			if (other.carNumber != null)
				return false;
		} else if (!carNumber.equals(other.carNumber))
			return false;
		if (checkoutBit == null) {
			if (other.checkoutBit != null)
				return false;
		} else if (!checkoutBit.equals(other.checkoutBit))
			return false;
		if (createId == null) {
			if (other.createId != null)
				return false;
		} else if (!createId.equals(other.createId))
			return false;
		if (greaseTypeCode == null) {
			if (other.greaseTypeCode != null)
				return false;
		} else if (!greaseTypeCode.equals(other.greaseTypeCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (is_delete == null) {
			if (other.is_delete != null)
				return false;
		} else if (!is_delete.equals(other.is_delete))
			return false;
		if (labelType == null) {
			if (other.labelType != null)
				return false;
		} else if (!labelType.equals(other.labelType))
			return false;
		if (pointPosition == null) {
			if (other.pointPosition != null)
				return false;
		} else if (!pointPosition.equals(other.pointPosition))
			return false;
		if (portNumber == null) {
			if (other.portNumber != null)
				return false;
		} else if (!portNumber.equals(other.portNumber))
			return false;
		if (shoesCode == null) {
			if (other.shoesCode != null)
				return false;
		} else if (!shoesCode.equals(other.shoesCode))
			return false;
		if (shoesNumber == null) {
			if (other.shoesNumber != null)
				return false;
		} else if (!shoesNumber.equals(other.shoesNumber))
			return false;
		if (shoesSerialNumber == null) {
			if (other.shoesSerialNumber != null)
				return false;
		} else if (!shoesSerialNumber.equals(other.shoesSerialNumber))
			return false;
		if (spare_one == null) {
			if (other.spare_one != null)
				return false;
		} else if (!spare_one.equals(other.spare_one))
			return false;
		if (spare_two == null) {
			if (other.spare_two != null)
				return false;
		} else if (!spare_two.equals(other.spare_two))
			return false;
		return true;
	}

	/**
	 * 
	 */
	public AxisLabel() {
		super();
	}

}
