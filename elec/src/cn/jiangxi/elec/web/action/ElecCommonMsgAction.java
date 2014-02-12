package cn.jiangxi.elec.web.action;


import java.util.List;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.service.IElecCommonMsgService;
import cn.jiangxi.elec.web.form.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService)ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	public ElecCommonMsgForm getModel() {
		return elecCommonMsgForm;
	}
	
	/**
	 * 查询待办事宜列表
	 * 跳转到actingIndex.jsp
	 */
	public String home(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgList(elecCommonMsgForm);
		request.setAttribute("commonList", list);
		return "home";
	}
	/**
	 * 保存待办事宜信息
	 * @return 重定向到actingIndex.jsp
	 */
	public String save(){
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}
}

