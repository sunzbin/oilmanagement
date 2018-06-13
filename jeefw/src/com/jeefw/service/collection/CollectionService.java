package com.jeefw.service.collection;

import java.text.ParseException;

import com.jeefw.model.collection.Collection;

import core.service.Service;

/**
 * 
 * @author sunzb
 *
 */
public interface CollectionService extends Service<Collection> {

	boolean saveCollectionInfo(String str) throws ParseException;
}
