package cn.jiangxi.elec.service;

import java.util.List;

import cn.jiangxi.elec.util.XmlObject;
import cn.jiangxi.elec.web.form.ElecRoleForm;
import cn.jiangxi.elec.web.form.ElecUserForm;



public interface IElecRoleService {
	public static final String SERVICE_NAME = "cn.jiangxi.elec.service.impl.ElecRoleServiceImpl";

	public List<XmlObject> readXml();

	public List<XmlObject> readEditXml(String roleID);

	public List<ElecUserForm> fin1dElecUserListByRoleID(String roleID);

	public void saveRole(ElecRoleForm elecRoleForm);
}
