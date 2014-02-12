package cn.jiangxi.elec.service;

import java.util.List;

import cn.jiangxi.elec.web.form.ElecSystemDDLForm;


public interface IElecSystemDDLService {
	public static final String SERVICE_NAME = "cn.jiangxi.elec.service.impl.ElecSystemDDLServiceImpl";

	public List<ElecSystemDDLForm> findKeyword();

	public List<ElecSystemDDLForm> findElecSystemDDLListByKeyword(String keyword);

	public void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm);
}
