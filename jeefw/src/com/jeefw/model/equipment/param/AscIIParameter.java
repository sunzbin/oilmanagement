/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.model.equipment.param;

import core.support.ExtJSBaseParameter;

/**
 * @Author:梁英男
 * @Date:2018年5月9日
 */
public class AscIIParameter extends ExtJSBaseParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String $eq_ascIIKey;
	
	private String $like_ascIIValue;

	public String get$eq_ascIIKey() {
		return $eq_ascIIKey;
	}

	public void set$eq_ascIIKey(String $eq_ascIIKey) {
		this.$eq_ascIIKey = $eq_ascIIKey;
	}

	public String get$like_ascIIValue() {
		return $like_ascIIValue;
	}

	public void set$like_ascIIValue(String $like_ascIIValue) {
		this.$like_ascIIValue = $like_ascIIValue;
	}
	
	
	

}
