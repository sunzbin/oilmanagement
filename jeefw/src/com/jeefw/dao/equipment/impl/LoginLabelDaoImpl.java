package com.jeefw.dao.equipment.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.equipment.LoginLabelDao;
import com.jeefw.model.equipment.AxisLabel;
import com.jeefw.model.equipment.LoginLabel;

import core.dao.BaseDao;

/**
 * @Title:
 * @Description:
 * @Date:2018-05-04 上午 11:16
 * @author:liangyingnan
 */
@Repository
public class LoginLabelDaoImpl extends BaseDao<LoginLabel> implements LoginLabelDao {

	public LoginLabelDaoImpl() {
		super(LoginLabel.class);
	}

	@Override
	public void batchSaveAxisLabel(List<LoginLabel> loginLabels) {
		if(null != loginLabels && loginLabels.size()>0) {
			Session  session = this.getSessionFactory().openSession();
			try {
				session.beginTransaction();//开启事务
				LoginLabel axisLabel = null;
				for (int i = 0; i < loginLabels.size(); i++) {  
					axisLabel = (LoginLabel) loginLabels.get(i); 
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
