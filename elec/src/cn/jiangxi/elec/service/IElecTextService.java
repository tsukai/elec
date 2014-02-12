package cn.jiangxi.elec.service;

import java.util.List;

import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.web.form.ElecTextForm;

public interface IElecTextService {
	public static final String SERVICE_NAME = "cn.jiangxi.elec.service.impl.ElecTextServiceImpl";
	public void saveElecText(ElecText et);
	public List<ElecText> findCollectionByConditionNoPage(ElecTextForm etf);
}
