package cn.jiangxi.elec.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jiangxi.elec.web.form.ElecLogForm;


public interface IElecLogService {
	public static final String SERVICE_NAME = "cn.jiangxi.elec.service.impl.ElecLogServiceImpl";

	public void saveElecLog(HttpServletRequest request, String details);

	public List<ElecLogForm> findElecLogListByCondition(ElecLogForm elecLogForm);

	public void deleteElecLogByLogIDs(ElecLogForm elecLogForm);
}
