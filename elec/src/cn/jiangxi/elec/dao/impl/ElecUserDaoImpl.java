package cn.jiangxi.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.jiangxi.elec.dao.IElecUserDao;
import cn.jiangxi.elec.domain.ElecUser;

@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao {

	/**
	 * 使用登录名获取当前登录名所具有的的权限
	 */
	public List<Object> findElecPopedomByLOgonName(final String name) {
		final String sql = "select erp.popedomcode as popedom " +
				"from elec_role_popedom erp " +
				"left join elec_user_role eur on erp.roleid = eur.roleid " +
				"inner join elec_user eu on eur.userid = eu.userid " +
				"and eu.logonname = ? " +
				"where eu.isduty = '1'";
		List<Object> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
						.addScalar("popedom", Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
		});
		return list;
	}

	/**
	 * 使用当前登录名获取所具有的的角色
	 */
	public List<Object[]> findElecRoleByLogonName(final String name) {
		final String sql = "select  es.ddlcode as ddlcode ,es.ddlname as ddlname "+
			"from elec_user_role eur "+ 
			"inner join elec_user eu on eur.userid = eu.userid "+
			"and eu.logonname = ? "+
			"left join elec_systemddl es on eur.roleid = es.ddlcode "+
			"and es.keyword = '角色类型' "+
			"where eu.isduty = '1'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
						.addScalar("ddlcode", Hibernate.STRING)
						.addScalar("ddlname",Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
		});
		return list;
	}

	
}
