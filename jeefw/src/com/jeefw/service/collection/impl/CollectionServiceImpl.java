package com.jeefw.service.collection.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.collection.CollectionCnDao;
import com.jeefw.dao.collection.CollectionDao;
import com.jeefw.model.collection.Collection;
import com.jeefw.model.collection.CollectionCn;
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
	private CollectionCnDao collectionCnDao;
	
	@Resource
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
		this.dao = collectionDao;
	}

	
	@Override
	public boolean saveCollectionInfo(String str) {
			if(str != null && !"".equals(str) && str.indexOf("fa") > -1){
				String[] element = str.split("\\s+");
				if(element != null && element.length > 0){
					if(!this.anaStr(element)){
						return false;
					}
				}
			}
		return true;
	}
	
	/**
	 * 解析采集到的数据信息
	 * @param strs
	 * @return
	 */
	private boolean anaStr(String[] strs){
		
		Collection coll = new Collection();
		CollectionCn collCn = new CollectionCn();
		String duanNum = "";
		String carNum = "";
		String carNo = "";
		String jiazhuVol = "";
		String jiazhuVolNum = "";
		String jiazhuTime = "";
		String operatorId = "";
		
		for(int i = 0; i < strs.length; i++){
			if(i == 0){//起始位
				
				coll.setStartBit(strs[i]);
				collCn.setStartBit(strs[i]);
			}else if(i == 1){//命令字
				
				coll.setOrderBit(strs[i]);
				collCn.setOrderBit(strs[i]);
			}else if(i == 2 ){//报文序号
				
				coll.setLogNum(strs[i]);
				collCn.setLogNum(strs[i]);
			}else if(i == 3){//数据长度
				
				coll.setDataLength(strs[i]);
			    int b = Integer.parseInt(strs[i], 16);
				collCn.setDataLength(b + "");
			}else if(i == 4){//段号为两个字节
				duanNum += strs[i];
				
			}else if(i == 5){
				
				duanNum += strs[i];
				coll.setDuanNum(duanNum);
			    int b = Integer.parseInt(duanNum, 16);
				collCn.setDuanNum(b + "");
				
			}else if(i == 6){//设备号
				
				coll.setEquNum(strs[i]);
			    int b = Integer.parseInt(strs[i], 16);
				collCn.setEquNum(b + "");
				
			}else if(i == 7){//车型代码
				
				if("01".equals(strs[i])){
					coll.setCarTypeNum(strs[i]);
					collCn.setCarTypeNum("SS7C");
				}else if("02".equals(strs[i])){
					coll.setCarTypeNum(strs[i]);
					collCn.setCarTypeNum("SS7E");
				}else if("03".equals(strs[i])){
					coll.setCarTypeNum(strs[i]);
					collCn.setCarTypeNum("HXD1C");
				}else if("04".equals(strs[i])){
					coll.setCarTypeNum(strs[i]);
					collCn.setCarTypeNum("HXD1D");
				}else if("05".equals(strs[i])){
					coll.setCarTypeNum(strs[i]);
					collCn.setCarTypeNum("HXD3D");
				}else if("06".equals(strs[i])){
					coll.setCarTypeNum(strs[i]);
					collCn.setCarTypeNum("HXD3G");
				}else{
					return false;
				}
			}else if(i == 8){//车号（4byte）--------------------------start-----------------------
				
				String b = asciiToString(strs[i]);
				carNo += b;
				carNum += strs[i];
			}else if(i == 9){
				
				String b = asciiToString(strs[i]);
				carNo += b;
				carNum = carNum +  strs[i] ;
			}else if(i == 10){
				
				String b = asciiToString(strs[i]);
				carNo += b;
				carNum = carNum +  strs[i] ;
			}else if(i == 11){//车号（4byte）--------------------------end-----------------------
				
				String b = asciiToString(strs[i]);
				carNo += b;
				carNum = carNum +  strs[i] ;
				coll.setCarNum(carNum);
				collCn.setCarNum(carNo);
			}else if(i == 12){//轴位
				
				coll.setZhouWei(strs[i]);
			    int b = Integer.parseInt(strs[i], 16);
				collCn.setZhouWei(b + "");
			}else if(i == 13){//加注点
				
				coll.setJiaZhuPoint(strs[i]);
			    int b = Integer.parseInt(strs[i], 16);
				collCn.setJiaZhuPoint(b + "");
			}else if(i == 14){//加注量（2byte）------------------------------start----------------------
				
				jiazhuVol += strs[i];
				jiazhuVolNum = jiazhuVolNum + "," + strs[i];
			}else if(i == 15){//加注量（2byte）------------------------------end------------------------
				
				jiazhuVol += strs[i];
				jiazhuVolNum = jiazhuVolNum + "," + strs[i];
				coll.setJiaZhuVol(jiazhuVolNum);
				collCn.setJiaZhuVol(Integer.parseInt(jiazhuVol, 16) + "");
				
			}else if(i == 16){//修程
				if("01".equals(strs[i])){
					
					coll.setXiuCheng(strs[i]);
					collCn.setXiuCheng("临修");
				}else if("02".equals(strs[i])){
					
					coll.setXiuCheng(strs[i]);
					collCn.setXiuCheng("定修");
				}else{
					return false;
				}
			}else if(i == 17){//油脂类型
				
				coll.setOilType(strs[i]);
				collCn.setOilType(strs[i]);
				
			}else if(i == 18){//电量
				
				coll.setElec(strs[i]);
			    int b = Integer.parseInt(strs[i], 16);
				collCn.setElec(b + "");
				
			}else if(i == 19 || i == 20 || i == 21 || i == 22 || i == 23 || i == 24){//加注时间（6byte）-------------------------------------------------------
				
				if(i == 19){
					jiazhuTime = "20" +  strs[i];
				}else{
					jiazhuTime += strs[i];
				}
				
				if(i == 24){
					coll.setJiazhuTime(jiazhuTime);
					collCn.setJiazhuTime(jiazhuTime);
				}
				
			}else if(i == 25 || i == 26 || i == 27 || i == 28){//操作人id（4byte）-------------------------------------------------------
				
				int b =  Integer.parseInt(strs[i], 16);
				operatorId += b;
				if(i == 28){
					coll.setOperatorId(operatorId);
					collCn.setOperatorId(operatorId);
				}
				
			}else if(i == 32){//CRCHi
				
				coll.setCrchi(strs[i]);
				collCn.setCrchi(strs[i]);
				
			}else if(i == 33){//CRCLi
				
				coll.setCrcli(strs[i]);
				collCn.setCrcli(strs[i]);
				
			}
		}
		collectionDao.persist(coll);
		collectionCnDao.persist(collCn);
		return true;
	}
	
	/**
	 * 将ASCII转成字符串的java方法
	 * @param value
	 * @return
	 */
	public static String asciiToString(String value)  
	{  
	        char ch = (char)Integer.parseInt(value, 16);  
	        return ch + "";  
	}  

}
