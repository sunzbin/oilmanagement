package com.jeefw.dao.collection;

import com.jeefw.model.collection.Collection;

import core.dao.Dao;

/**
 * 
 * @author sunzb
 *
 */
public interface CollectionCnDao extends Dao<Collection> {

	public void testStr(String str);
	
	public void saveTest();
}