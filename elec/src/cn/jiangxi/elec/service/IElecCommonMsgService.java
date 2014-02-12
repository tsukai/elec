package cn.jiangxi.elec.service;

import java.util.List;

import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.web.form.ElecCommonMsgForm;
import cn.jiangxi.elec.web.form.ElecTextForm;

public interface IElecCommonMsgService {
	public static final String SERVICE_NAME = "cn.jiangxi.elec.service.impl.ElecCommonMsgServiceImpl";

	public List<ElecCommonMsgForm> findElecCommonMsgList(
			ElecCommonMsgForm elecCommonMsgForm);

	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm);

	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate();
}
