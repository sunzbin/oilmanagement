package com.jeefw.model.carmanagement.param;

import core.support.ExtJSBaseParameter;

/**
 机车管理的参数类
 */
public class CarParameter extends ExtJSBaseParameter {

	private String $eq_carKey;
	private String $like_carValue;
	
	public String get$eq_carKey() {
		return $eq_carKey;
	}
	public void set$eq_carKey(String $eq_carKey) {
		this.$eq_carKey = $eq_carKey;
	}
	public String get$like_carValue() {
		return $like_carValue;
	}
	public void set$like_carValue(String $like_carValue) {
		this.$like_carValue = $like_carValue;
	}

	
}
