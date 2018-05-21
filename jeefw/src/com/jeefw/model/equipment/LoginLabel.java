package com.jeefw.model.equipment;

import com.jeefw.model.equipment.param.LoginLabelParameter;

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
 * @Title: 登录标签
 * @Description:
 * @Date:2018-05-04 上午 10:49
 * @author:liangyingnan
 */
@Entity
@Table(name = "login_label")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class LoginLabel extends LoginLabelParameter {

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
	 * 用户id
	 */
	@Column(name = "user_id")
	private  String userId;
	
	/**
	 * 是否删除
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
	 * 保留位三
	 */
	@Column(name="spare_three")
	private String spare_three; 
	
	/**
	 * 保留位四
	 */
	@Column(name="spare_four")
	private String spare_four; 
	
	/**
	 * 保留位五
	 */
	@Column(name="spare_five")
	private String spare_five; 
	
	/**
	 * 保留位六
	 */
	@Column(name="spare_six")
	private String spare_six; 
	
	/**
	 * 校验位
	 */
	@Column(name="checkout_bit")
	private String  checkoutBit;
	
	/**
	 * 读取时间
	 */
	@Column(name="read_time")
	private String readTime;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSpare_three() {
		return spare_three;
	}

	public void setSpare_three(String spare_three) {
		this.spare_three = spare_three;
	}

	public String getSpare_four() {
		return spare_four;
	}

	public void setSpare_four(String spare_four) {
		this.spare_four = spare_four;
	}

	public String getSpare_five() {
		return spare_five;
	}

	public void setSpare_five(String spare_five) {
		this.spare_five = spare_five;
	}

	public String getSpare_six() {
		return spare_six;
	}

	public void setSpare_six(String spare_six) {
		this.spare_six = spare_six;
	}

	public String getCheckoutBit() {
		return checkoutBit;
	}

	public void setCheckoutBit(String checkoutBit) {
		this.checkoutBit = checkoutBit;
	}

	public String getReadTime() {
		return readTime;
	}

	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkoutBit == null) ? 0 : checkoutBit.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((is_delete == null) ? 0 : is_delete.hashCode());
		result = prime * result + ((labelType == null) ? 0 : labelType.hashCode());
		result = prime * result + ((readTime == null) ? 0 : readTime.hashCode());
		result = prime * result + ((spare_five == null) ? 0 : spare_five.hashCode());
		result = prime * result + ((spare_four == null) ? 0 : spare_four.hashCode());
		result = prime * result + ((spare_one == null) ? 0 : spare_one.hashCode());
		result = prime * result + ((spare_six == null) ? 0 : spare_six.hashCode());
		result = prime * result + ((spare_three == null) ? 0 : spare_three.hashCode());
		result = prime * result + ((spare_two == null) ? 0 : spare_two.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		LoginLabel other = (LoginLabel) obj;
		if (checkoutBit == null) {
			if (other.checkoutBit != null)
				return false;
		} else if (!checkoutBit.equals(other.checkoutBit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (readTime == null) {
			if (other.readTime != null)
				return false;
		} else if (!readTime.equals(other.readTime))
			return false;
		if (spare_five == null) {
			if (other.spare_five != null)
				return false;
		} else if (!spare_five.equals(other.spare_five))
			return false;
		if (spare_four == null) {
			if (other.spare_four != null)
				return false;
		} else if (!spare_four.equals(other.spare_four))
			return false;
		if (spare_one == null) {
			if (other.spare_one != null)
				return false;
		} else if (!spare_one.equals(other.spare_one))
			return false;
		if (spare_six == null) {
			if (other.spare_six != null)
				return false;
		} else if (!spare_six.equals(other.spare_six))
			return false;
		if (spare_three == null) {
			if (other.spare_three != null)
				return false;
		} else if (!spare_three.equals(other.spare_three))
			return false;
		if (spare_two == null) {
			if (other.spare_two != null)
				return false;
		} else if (!spare_two.equals(other.spare_two))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/**
	 * 
	 */
	public LoginLabel() {
		super();
	}

}
