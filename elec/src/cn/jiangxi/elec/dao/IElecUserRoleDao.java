package cn.jiangxi.elec.dao;

import java.util.List;

import cn.jiangxi.elec.domain.ElecUserRole;

public interface IElecUserRoleDao extends ICommonDao<ElecUserRole> {
	public final static String SERVICE_NAME = "cn.jiangxi.elec.dao.impl.ElecUserRoleDaoImpl";

	public List<Object[]> fin1dElecUserListByRoleID(String roleID);


	
}
