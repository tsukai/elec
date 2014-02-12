package cn.jiangxi.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.jiangxi.elec.dao.IElecCommonMsgDao;
import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecCommonMsg;
import cn.jiangxi.elec.domain.ElecText;

@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao {

	/**
	 * 通过当前日期查询待办事宜列表
	 */
	public List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate) {
		final String sql = "select o.stationRun as stationRun,o.devRun as devRun " +
				"from elec_commonmsg o " +
				" where o.createDate = '" + currentDate + "'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
						.addScalar("stationRun", Hibernate.STRING)
						.addScalar("devRun", Hibernate.STRING);
				return query.list();
			}
		});
		return list;
	}
	
}
