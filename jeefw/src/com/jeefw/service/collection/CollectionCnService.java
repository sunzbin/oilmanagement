package com.jeefw.service.collection;

import java.util.List;

import com.jeefw.model.collection.CollectionCn;

import core.service.Service;

/**
 * 
 * @author sunzb
 *
 */
public interface CollectionCnService extends Service<CollectionCn> {

	List<CollectionCn> queryCollectiontWithSubList(List<CollectionCn> resultList);
}
