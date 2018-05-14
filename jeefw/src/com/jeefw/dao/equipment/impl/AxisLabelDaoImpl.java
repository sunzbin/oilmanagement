package com.jeefw.dao.equipment.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.equipment.AxisLabelDao;
import com.jeefw.model.equipment.AxisLabel;

import core.dao.BaseDao;
import net.sf.ehcache.hibernate.HibernateUtil;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:13
 * @author:liangyingnan
 */
@Repository
public class AxisLabelDaoImpl extends BaseDao<AxisLabel> implements AxisLabelDao {
	
	public AxisLabelDaoImpl() {
		super(AxisLabel.class);
	}

	@Override
	public void batchSaveAxisLabel(List<AxisLabel> axisLabels) {
		if(null != axisLabels && axisLabels.size()>0) {
			Session  session = this.getSessionFactory().openSession();
			try {
				session.beginTransaction();//开启事务
				AxisLabel axisLabel = null;
				for (int i = 0; i < axisLabels.size(); i++) {  
					axisLabel = (AxisLabel) axisLabels.get(i); 
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
