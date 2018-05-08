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
public class LabelDetail extends LoginLabelParameter {

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
	 * 创建人id
	 */
	private  Long userId;

	/**
	 * 鞋柜编码
	 */
	private String code;

	/**
	 * 鞋位数
	 */
	private int number;

	/**
	 * 标签序列号
	 */
	private String  serial_number;

	/**
	 * ip地址
	 */
	private String ip_address;

	/**
	 * 端口号
	 */
	private String port_number;


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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getPort_number() {
		return port_number;
	}

	public void setPort_number(String port_number) {
		this.port_number = port_number;
	}
}
