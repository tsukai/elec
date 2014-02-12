package cn.jiangxi.elec.web.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.service.IElecTextService;
import cn.jiangxi.elec.util.StringHelper;
import cn.jiangxi.elec.web.form.ElecTextForm;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.message.StringHeader;

public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm>{
	private ElecTextForm elecTestForm = new ElecTextForm();
	private IElecTextService elecService = (IElecTextService)ServiceProvider.getService(IElecTextService.SERVICE_NAME);
	public ElecTextForm getModel() {
		return elecTestForm;
	}
	
	public String save(){
		//System.out.println(elecTestForm.getTextName());
//		System.out.println(request.getParameter("textName"));
		//VO对象转换成PO对象
		//实例化PO对象
		ElecText et = new ElecText();
		et.setTextName(elecTestForm.getTextName());
		et.setTextRemark(elecTestForm.getTextRemark());
		et.setTextDate(StringHelper.stringConvertDate(elecTestForm.getTextDate()));
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		IElecTextService elecService = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		elecService.saveElecText(et);
		return "save";
	}
}

