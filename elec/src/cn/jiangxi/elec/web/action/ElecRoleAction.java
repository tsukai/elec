package cn.jiangxi.elec.web.action;


import java.util.List;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.service.IElecRoleService;
import cn.jiangxi.elec.service.IElecSystemDDLService;
import cn.jiangxi.elec.util.XmlObject;
import cn.jiangxi.elec.web.form.ElecRoleForm;
import cn.jiangxi.elec.web.form.ElecSystemDDLForm;
import cn.jiangxi.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecRoleAction extends BaseAction implements ModelDriven<ElecRoleForm>{
	private ElecRoleForm elecRoleForm = new ElecRoleForm();
	private IElecRoleService elecRoleService = (IElecRoleService)ServiceProvider.getService(IElecRoleService.SERVICE_NAME);
	
	private IElecSystemDDLService elecSystemDDLService = 
			(IElecSystemDDLService)ServiceProvider.getService(IElecSystemDDLService.SERVICE_NAME);
	
	public ElecRoleForm getModel() {
		return elecRoleForm;
	}
	
	/**
	 * 查询所有的角色类型（在数据字典中获取）
	 * 从Function.xml中查询系统所有的功能权限
	 * @return
	 */
	public String home(){
		//获取所有的角色类型
		List<ElecSystemDDLForm> systemList = elecSystemDDLService.findElecSystemDDLListByKeyword("角色类型");
		request.setAttribute("systemList", systemList);
		//从Function.xml配置文件中获取权限集合
		List<XmlObject> xmlList = elecRoleService.readXml();
		request.setAttribute("xmlList", xmlList);
		return "home";
	}
	
	/**
	 * 1、使用角色ID查询该角色下具有的权限，并与系统中所哦哟偶的权限进行匹配
	 * 2、使用角色id查询该角色所拥有的用户
	 * @return 跳转到roleEdit.jsp
	 */
	public String edit(){
		String roleID = elecRoleForm.getRole();
		//查询权限集合
		List<XmlObject> xmlList = elecRoleService.readEditXml(roleID);
		request.setAttribute("xmlList", xmlList);
		//查询用户集合
		List<ElecUserForm> userList = elecRoleService.fin1dElecUserListByRoleID(roleID);
		request.setAttribute("userList", userList);
		return "edit";
	}
	
	/**
	 * 执行保存
	 * 保存角色和权限关联表
	 * 保存用户和角色关联表
	 * @return 重定向到roleIndex.jsp
	 */
	public String save(){
		elecRoleService.saveRole(elecRoleForm);
		return "list";
	}
}

