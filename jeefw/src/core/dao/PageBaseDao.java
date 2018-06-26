package core.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import core.support.PageBaseParameter;

/**
 * 分页组件
 * @author sunzb
 *
 */
public class PageBaseDao<E> {

	protected final Logger log = Logger.getLogger(PageBaseDao.class);
	private SessionFactory sessionFactory;
	protected Class<E> entityClass;

	public PageBaseParameter<E> getPaginationQuery(Criteria criteria, PageBaseParameter<E> param){
//		criteria.setProjection(Projections.rowCount());
		return null;
	}
}
