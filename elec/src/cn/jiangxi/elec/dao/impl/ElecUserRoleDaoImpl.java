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
import cn.jiangxi.elec.dao.IElecUserRoleDao;
import cn.jiangxi.elec.domain.ElecUserRole;

@Repository(IElecUserRoleDao.SERVICE_NAME)
public class ElecUserRoleDaoImpl extends CommonDaoImpl<ElecUserRole> implements IElecUserRoleDao {

	/**
	 * 查询用户列表集合，获取系统比重所有的用户，并与该角色具有的用户进行匹配
	 * 匹配成功设置flag==1，页面复选框选中
	 * 匹配不成功设置flag==0，页面复选框不选中
	 */
	public List<Object[]> fin1dElecUserListByRoleID(final String roleID) {
		final String sql = "select distinct case eur.roleId " +
				"when ? then '1' else '0' end as flag," +
				"eu.userid as userID, " +
				"eu.userName as userName, " +
				"eu.logonName as logonName " +
				"from elec_user eu " +
				"left join elec_user_role eur " +
				"on eu.userid = eur.userid " +
				"and eur.roleid = ? " +
				"where eu.isduty = '1'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
						.addScalar("flag",Hibernate.STRING)
						.addScalar("userID", Hibernate.STRING)
						.addScalar("userName",Hibernate.STRING)
						.addScalar("logonName",Hibernate.STRING);
				query.setParameter(0, roleID);
				query.setParameter(1, roleID);
				return query.list();
			}
		});
		return list;
	}

	
}
