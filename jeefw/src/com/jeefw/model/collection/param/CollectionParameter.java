/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.model.collection.param;

import core.support.ExtJSBaseParameter;

/**
 * 
 * @author sunzb
 *
 */
public class CollectionParameter extends ExtJSBaseParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String $eq_collectionKey;
	
	private String $like_collectionValue;

	public String get$eq_collectionKey() {
		return $eq_collectionKey;
	}

	public void set$eq_collectionKey(String $eq_collectionKey) {
		this.$eq_collectionKey = $eq_collectionKey;
	}

	public String get$like_collectionValue() {
		return $like_collectionValue;
	}

	public void set$like_collectionValue(String $like_collectionValue) {
		this.$like_collectionValue = $like_collectionValue;
	}

	
	

}
