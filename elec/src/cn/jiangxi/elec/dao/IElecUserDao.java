package cn.jiangxi.elec.dao;

import java.util.List;

import cn.jiangxi.elec.domain.ElecUser;

public interface IElecUserDao extends ICommonDao<ElecUser> {
	public final static String SERVICE_NAME = "cn.jiangxi.elec.dao.impl.ElecUserDaoImpl";

	public List<Object> findElecPopedomByLOgonName(String name);

	public List<Object[]> findElecRoleByLogonName(String name);

	
}
