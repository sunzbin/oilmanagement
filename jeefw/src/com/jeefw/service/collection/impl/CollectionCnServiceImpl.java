package com.jeefw.service.collection.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.collection.CollectionCnDao;
import com.jeefw.model.collection.CollectionCn;
import com.jeefw.service.collection.CollectionCnService;

import core.service.BaseService;

/**
 * 
 * @author sunzb
 *
 */
@Service("collectionCnService")
public class CollectionCnServiceImpl extends BaseService<CollectionCn> implements CollectionCnService {

	private CollectionCnDao collectionCnDao;
	
	@Resource
	public void setCollectionCnDao(CollectionCnDao collectionCnDao) {
		this.collectionCnDao = collectionCnDao;
		this.dao = collectionCnDao;
	}

	@Override
	public List<CollectionCn> queryCollectiontWithSubList(List<CollectionCn> resultList) {
		List<CollectionCn> collList = new ArrayList<CollectionCn>();
		for (CollectionCn entity : resultList) {
			CollectionCn coll = new CollectionCn();
			coll.setId(entity.getId());
			coll.setStartBit(entity.getStartBit());
			coll.setOrderBit(entity.getOrderBit());
			coll.setLogNum(entity.getLogNum());
			coll.setDataLength(entity.getDataLength());
			coll.setDuanNum(entity.getDuanNum());
			coll.setEquNum(entity.getEquNum());
			coll.setCarTypeNum(entity.getCarTypeNum());
			coll.setCarNum(entity.getCarNum());
			coll.setZhouWei(entity.getZhouWei());
			coll.setJiaZhuPoint(entity.getJiaZhuPoint());
			coll.setJiaZhuVol(entity.getJiaZhuVol());
			coll.setXiuCheng(entity.getXiuCheng());
			coll.setOilType(entity.getOilType());
			coll.setJiazhuTime(entity.getJiazhuTime());
			coll.setOperatorId(entity.getOperatorId());
			coll.setElec(entity.getElec());
			collList.add(coll);
		}
		return collList;
	}

}
