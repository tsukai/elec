package cn.jiangxi.elec.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import cn.jiangxi.elec.domain.ElecSystemDDL;
import cn.jiangxi.elec.domain.ElecText;

public interface IElecSystemDDLDao extends ICommonDao<ElecSystemDDL> {
	public final static String SERVICE_NAME = "cn.jiangxi.elec.dao.impl.ElecSystemDDLDaoImpl";

	public List<Object> findKeywork();

	public String findDDLName(String string, String sexID);

	
}
