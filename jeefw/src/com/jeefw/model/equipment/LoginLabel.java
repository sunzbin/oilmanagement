package com.jeefw.model.equipment;

import com.jeefw.model.equipment.param.LoginLabelParameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Title: 登录标签
 * @Description:
 * @Date:2018-05-04 上午 10:49
 * @author:liangyingnan
 */
public class LoginLabel extends LoginLabelParameter {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
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
	private  Long userId;
	
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
	 * 
	 */
	public LoginLabel() {
		super();
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((labelType == null) ? 0 : labelType.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (labelType == null) {
			if (other.labelType != null)
				return false;
		} else if (!labelType.equals(other.labelType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
