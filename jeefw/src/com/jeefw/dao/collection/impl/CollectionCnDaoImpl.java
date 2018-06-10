package com.jeefw.dao.collection.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.collection.CollectionCnDao;
import com.jeefw.model.collection.CollectionCn;

import core.dao.BaseDao;

/**
 * 
 * @author sunzb
 *
 */
@Repository
public class CollectionCnDaoImpl extends BaseDao<CollectionCn> implements CollectionCnDao {

	public CollectionCnDaoImpl() {
		super(CollectionCn.class);
	}
}
