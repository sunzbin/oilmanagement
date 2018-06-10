package com.jeefw.service.collection.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeefw.dao.collection.CollectionDao;
import com.jeefw.dao.collection.impl.CollectionDaoImpl;
import com.jeefw.dao.sys.AttachmentDao;
import com.jeefw.model.collection.Collection;
import com.jeefw.service.collection.CollectionService;

import core.service.BaseService;

/**
 * 
 * @author sunzb
 *
 */
@Service("collectionService")
public class CollectionServiceImpl extends BaseService<Collection> implements CollectionService {

	private CollectionDao collectionDao;
	
	@Resource
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
		this.dao = collectionDao;
	}

	
	@Override
	public boolean saveCollectionInfo(String str) {
			if(str != null && !"".equals(str) && str.indexOf("fa") > -1){
				String[] element = str.split("\\s+");
				Collection coll = new Collection();
				for(int j = 0;  j<element.length; j++){
					if(element[j]!= null && !"".equals(element[j].trim())){
						switch (j) {
						case 0:
							coll.setStartBit("fa");
							break;
						case 1:
							coll.setOrderBit(element[j]);
							break;
						case 2:
							coll.setLogNum(element[j]);
							break;
						case 3:
							coll.setDataLength(element[j]);
							break;
						case 4:
							coll.setDataLength(element[j]);
							break;
						case 5:
							coll.setDuanNum(element[j]);
							break;
						case 6:
							coll.setEquNum(element[j]);
							break;
						case 7:
							coll.setCarTypeNum(element[j]);
							break;
						case 8:
							coll.setCarNum(element[j]);
							break;
						case 9:
							coll.setZhouWei(element[j]);
							break;
						case 10:
							coll.setJiaZhuPoint(element[j]);
							break;
						case 11:
							coll.setJiaZhuVol(element[j]);
							break;
						case 12:
							coll.setXiuCheng(element[j]);
							break;
						case 13:
							coll.setOilType(element[j]);
							break;
						case 14:
							coll.setJiazhuTime(element[j]);
							break;
						case 15:
							coll.setOperatorId(element[j]);
							break;
						case 16:
							coll.setElec(element[j]);
							break;
						case 17:
							coll.setCrchi(element[j]);
							break;
						case 18:
							coll.setCrcli(element[j]);
							break;
						default:
							break;
						}
					}
				}
				collectionDao.persist(coll);
			}
		return true;
	}

}
