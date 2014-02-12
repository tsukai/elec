package cn.jiangxi.elec.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jiangxi.elec.domain.ElecUser;
import cn.jiangxi.elec.web.form.ElecUserForm;


public interface IElecUserService {
	public static final String SERVICE_NAME = "cn.jiangxi.elec.service.impl.ElecUserServiceImpl";

	public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm,HttpServletRequest request);

	public void saveElecUser(ElecUserForm elecUserForm);

	public ElecUserForm findElecUser(ElecUserForm elecUserForm);

	public void deleteElecUser(ElecUserForm elecUserForm);

	public String checkLogonName(String logonName);

	public ElecUser findElecUserByLogonName(String name);

	public String findElecPopedomByLogonName(String name);

	public Hashtable<String, String> findElecRoleByLogonName(String name);

	public ArrayList getExcelFieldName();

	public ArrayList getExcelFieldDataList(ElecUserForm elecUserForm);

	public void saveElecUserWithExcel(ElecUserForm elecUserForm);

}
