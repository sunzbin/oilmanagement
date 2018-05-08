package com.jeefw.model.equipment.param;

import core.support.ExtJSBaseParameter;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:42
 * @author:liangyingnan
 */
public class FillingMachineParameter extends ExtJSBaseParameter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String $eq_fillingMachineKey;
	
	private String $like_fillingMachineValue;

	public String get$eq_fillingMachineKey() {
		return $eq_fillingMachineKey;
	}

	public void set$eq_fillingMachineKey(String $eq_fillingMachineKey) {
		this.$eq_fillingMachineKey = $eq_fillingMachineKey;
	}

	public String get$like_fillingMachineValue() {
		return $like_fillingMachineValue;
	}

	public void set$like_fillingMachineValue(String $like_fillingMachineValue) {
		this.$like_fillingMachineValue = $like_fillingMachineValue;
	}
	
}
