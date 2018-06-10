package com.jeefw.dao.collection.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.collection.CollectionDao;
import com.jeefw.model.collection.Collection;

import core.dao.BaseDao;

/**
 * 
 * @author sunzb
 *
 */
@Repository
public class CollectionDaoImpl extends BaseDao<Collection> implements CollectionDao {

	public CollectionDaoImpl() {
		super(Collection.class);
	}
	
	public void testStr(String str){
//		System.out.println("11111111111111111111111111");
	}

	@Override
	public void saveTest() {

		System.out.println("11111111111111111111111111");
	}

}
