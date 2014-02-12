package cn.jiangxi.elec.web.action;


import java.util.List;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.service.IElecSystemDDLService;
import cn.jiangxi.elec.web.form.ElecSystemDDLForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecSystemDDLAction extends BaseAction implements ModelDriven<ElecSystemDDLForm>{
	private ElecSystemDDLForm elecSystemDDLForm = new ElecSystemDDLForm();
	private IElecSystemDDLService esdService = (IElecSystemDDLService)ServiceProvider.getService(IElecSystemDDLService.SERVICE_NAME);
	public ElecSystemDDLForm getModel() {
		return elecSystemDDLForm;
	}
	
	/**
	 * 查询数据类型，且去掉重复值
	 * @return
	 */
	public String home(){
		List<ElecSystemDDLForm> list = esdService.findKeyword();
		request.setAttribute("systemList", list);
		return "home";
	}
	
	/**
	 * 获取页面传递的数据类型
	 * 通过数据类型查询数据字典表，获取对应数据类型的数据项的值
	 * PO对象转换成VO对象
	 * 根据选中的数据类型，跳转到编辑数据类型的页面
	 * @return
	 */
	public String edit(){
		//获取数据类型
		String keyword = elecSystemDDLForm.getKeyword();
		List<ElecSystemDDLForm> list = 
				esdService.findElecSystemDDLListByKeyword(keyword);
		request.setAttribute("systemList", list);
		return "edit";
	}
	
	/**
	 * 保存数据字典
	 * 1、传递的参数
	 * 		隐藏域keywordname，存放数据类型，即数据字典的keyword
	 * 		隐藏域typeflag，判断标识，值为new和add，new表示新建数据类型，add表示在已有的数据类型中进行编辑
	 * 		name="itemname",存放数据项的名称
	 * 2、操作步骤
	 * 		获取页面的参数值
	 * 		获取页面的typeflag
	 * 			*当typeflag=new（新建数据类型）
	 * 			   将获取的页面的数据字典的参数放置PO对象中，执行保存操作
	 * 			*当typeflag=add（在已有的数据类型中进行编辑和修改）
	 * 			 根据数据类型先删除原数据中的数据项，然后保存新的数据项
	 * @return 重定向到dictionaryIndex.jsp
	 */
	public String save(){
		esdService.saveElecSystemDDL(elecSystemDDLForm);
		return "save";
	}
}

