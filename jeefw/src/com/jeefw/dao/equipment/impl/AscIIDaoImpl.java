/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.dao.equipment.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.equipment.AscIIDao;
import com.jeefw.model.equipment.AscII;
import com.jeefw.model.equipment.AxisLabel;

import core.dao.BaseDao;

/**
 * @Author:梁英男
 * @Date:2018年5月9日
 */
@Repository
public class AscIIDaoImpl extends BaseDao<AscII> implements AscIIDao{

	/**
	 * @param entityClass
	 */
	public AscIIDaoImpl() {
		super(AscII.class);
	}

	@Override
	public void batchSaveAscII(List<AscII> ascIIs) {
		if(null != ascIIs && ascIIs.size()>0) {
			Session  session = this.getSessionFactory().openSession();
			try {
				session.beginTransaction();//开启事务
				AscII axisLabel = null;
				for (int i = 0; i < ascIIs.size(); i++) {  
					axisLabel = (AscII) ascIIs.get(i); 
		            session.save(axisLabel); // 
		            // 批插入的对象立即写入数据库并释放内存  
		            if (i %30 == 0) {  
		                session.flush();  
		                session.clear();  
		            }  
		        }
				session.getTransaction().commit(); // 提交事物  
			}catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();//回滚事务
			}finally {
				session.close();
			}
		}
	}
	
	

}
