package cn.jiangxi.elec.dao;

import java.util.List;

import cn.jiangxi.elec.domain.ElecCommonMsg;

public interface IElecCommonMsgDao extends ICommonDao<ElecCommonMsg> {
	public final static String SERVICE_NAME = "cn.jiangxi.elec.dao.impl.ElecCommonMsgDaoImpl";

	public List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate);
	
}
