/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年6月13日
 */
package com.jeefw.model.carmanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeefw.model.carmanagement.param.CarAxisPointParameter;


/**
 * 机车轴位点位信息
 * @Author:梁英男
 * @Date:2018年6月13日
 */
@Entity
@Table(name = "car_axis_point")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class CarAxisPoint extends CarAxisPointParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 所属机车
	 */
	@Column(name = "ascription_loco")
	private String  ascription_loco ;
	
	/**
	 * 轴位名称
	 */
	@Column(name = "axis_name")
	private String  axis_name ;
	
	/**
	 * 轴位代码
	 */
	@Column(name = "axis_code")
	private String  axis_code ;
	
	/**
	 * 父id
	 */
	@Column(name = "pid")
	private Long pid;
	
	/**
	 * 点位名称
	 */
	@Column(name = "point_name")
	private String  point_name ;
	
	/**
	 * 点位代码
	 */
	@Column(name = "point_code")
	private String  point_code ;
	
//	private String  car_type ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAscription_loco() {
		return ascription_loco;
	}

	public void setAscription_loco(String ascription_loco) {
		this.ascription_loco = ascription_loco;
	}

	public String getAxis_name() {
		return axis_name;
	}

	public void setAxis_name(String axis_name) {
		this.axis_name = axis_name;
	}

	public String getAxis_code() {
		return axis_code;
	}

	public void setAxis_code(String axis_code) {
		this.axis_code = axis_code;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPoint_name() {
		return point_name;
	}

	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}

	public String getPoint_code() {
		return point_code;
	}

	public void setPoint_code(String point_code) {
		this.point_code = point_code;
	}

//	public String getCar_type() {
//		return car_type;
//	}
//
//	public void setCar_type(String car_type) {
//		this.car_type = car_type;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ascription_loco == null) ? 0 : ascription_loco.hashCode());
		result = prime * result + ((axis_code == null) ? 0 : axis_code.hashCode());
		result = prime * result + ((axis_name == null) ? 0 : axis_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((point_code == null) ? 0 : point_code.hashCode());
		result = prime * result + ((point_name == null) ? 0 : point_name.hashCode());
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
		CarAxisPoint other = (CarAxisPoint) obj;
		if (ascription_loco == null) {
			if (other.ascription_loco != null)
				return false;
		} else if (!ascription_loco.equals(other.ascription_loco))
			return false;
		if (axis_code == null) {
			if (other.axis_code != null)
				return false;
		} else if (!axis_code.equals(other.axis_code))
			return false;
		if (axis_name == null) {
			if (other.axis_name != null)
				return false;
		} else if (!axis_name.equals(other.axis_name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (point_code == null) {
			if (other.point_code != null)
				return false;
		} else if (!point_code.equals(other.point_code))
			return false;
		if (point_name == null) {
			if (other.point_name != null)
				return false;
		} else if (!point_name.equals(other.point_name))
			return false;
		return true;
	}

	/**
	 * 
	 */
	public CarAxisPoint() {
		super();
	}
}
