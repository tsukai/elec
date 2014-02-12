package cn.jiangxi.elec.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.jiangxi.elec.dao.ICommonDao;
import cn.jiangxi.elec.domain.ElecUser;
import cn.jiangxi.elec.util.GenericSuperClass;
import cn.jiangxi.elec.util.PageInfo;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {
	//泛型转换
	
	@SuppressWarnings("rawtypes")
	private Class entity = (Class)GenericSuperClass
			.getClass(this.getClass());
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Resource(name="sessionFactory")
	public final void setSessionFactoryBasic(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectById(Serializable id) {
		
		return (T)this.getHibernateTemplate().get(entity, id);
	}

	/**
	 * 通过id数组删除对象
	 */
	@Override
	public void deleteObjectByIds(Serializable... ids) {
		for(int i = 0;i < ids.length;i++){
			Serializable id = ids[i];
			Object obj = findObjectById(id);
			this.getHibernateTemplate().delete(obj);
		}
	}

	/**
	 * 通过集合的形式删除对象
	 */
	public void deleteObjectByCollection(Collection<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	/**
	 * 使用查询条件查询列表（不分页）
	 */
	public List<T> findCollectionByConditionNoPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby) {
		String hql = "from "+ entity.getSimpleName() + " o where 1 = 1";
		//组织排序条件
		String hqlOrderby = orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderby;
		final String finalHql = hql;
		List<T> list = (List<T>)this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException,
							SQLException {
						Query query = session.createQuery(finalHql);
						setParams(params, query);
						return query.list();
					}

					
				});
		return list;
	}

	private void setParams(Object[] params, Query query) {
		for(int i = 0;params != null && i < params.length;i++){
			query.setParameter(i, params[i]);
		}
	}
	/**
	 * 组织排序条件
	 * @param orderby
	 * @return
	 */
	private String orderByCondition(LinkedHashMap<String, String> orderby) {
		StringBuffer hqlOrderby = new StringBuffer("");
		if(orderby != null){
			hqlOrderby.append(" order by ");
			for(Map.Entry<String, String> entry : orderby.entrySet()){
				hqlOrderby.append( " " +entry.getKey() + " " + entry.getValue() + ",");
			}
			hqlOrderby.deleteCharAt(hqlOrderby.length() - 1);
		}
		return hqlOrderby.toString();
	}

	/**
	 * 使用集合的形式进行批量保存
	 */
	public void saveObjectByCollection(Collection<T> list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	/**
	 * 使用查询条件查询列表（分页）
	 */
	public List<T> findCollectionByConditionWithPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby,
			final PageInfo pageInfo) {
		String hql = "from "+ entity.getSimpleName() + " o where 1 = 1";
		//组织排序条件
		String hqlOrderby = orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderby;
		final String finalHql = hql;
		List<T> list = (List<T>)this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException,
							SQLException {
						Query query = session.createQuery(finalHql);
						setParams(params, query);
						//添加分页功能
						pageInfo.setTotalResult(query.list().size());//通过pageInfo对象设置总记录数
						query.setFirstResult(pageInfo.getBeginResult());//当前野种的数据从第几条开始查询
						//当前页显示多少条记录
						query.setMaxResults(pageInfo.getPageSize());
						return query.list();
					}
				});
		return list;
	}
}
