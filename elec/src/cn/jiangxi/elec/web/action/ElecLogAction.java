package cn.jiangxi.elec.web.action;


import java.util.List;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.service.IElecLogService;
import cn.jiangxi.elec.web.form.ElecLogForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecLogAction extends BaseAction implements ModelDriven<ElecLogForm>{
	private ElecLogForm elecLogForm = new ElecLogForm();
	private IElecLogService elecLogService = (IElecLogService)ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	public ElecLogForm getModel() {
		return elecLogForm;
	}
	
	/**
	 * 查询日志列表信息
	 * @return 跳转到roleIndex.jsp
	 */
	public String home(){
		List<ElecLogForm> list = elecLogService.findElecLogListByCondition(elecLogForm);
		request.setAttribute("logList", list);
		return "home";
	}
	
	/**
	 * 删除查询得到的日志列表信息
	 * @return 重定向到logIndex.jsp
	 */
	public String delete(){
		elecLogService.deleteElecLogByLogIDs(elecLogForm);
		return "delete";
	}
}

