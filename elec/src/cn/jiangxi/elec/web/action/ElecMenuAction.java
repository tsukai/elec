package cn.jiangxi.elec.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.domain.ElecUser;
import cn.jiangxi.elec.service.IElecCommonMsgService;
import cn.jiangxi.elec.service.IElecLogService;
import cn.jiangxi.elec.service.IElecUserService;
import cn.jiangxi.elec.util.LoginUtils;
import cn.jiangxi.elec.util.MD5keyBean;
import cn.jiangxi.elec.web.form.ElecCommonMsgForm;
import cn.jiangxi.elec.web.form.ElecMenuForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm>{
	//使用log4j
	private Log log = LogFactory.getLog(ElecMenuAction.class);
	
	private IElecLogService elecLogService = (IElecLogService)
			ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	private IElecCommonMsgService elecCommonMsgService = 
			(IElecCommonMsgService)ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	private IElecUserService elecUserService = (IElecUserService)ServiceProvider
			.getService(IElecUserService.SERVICE_NAME);
	private ElecMenuForm elecMenuForm = new ElecMenuForm();
	public ElecMenuForm getModel() {
		return elecMenuForm;
	}

	/**
	 * 从登录获取用户名和密码，验证是否合法
	 * 如果合法，则验证成功，跳转到home.jsp
	 * 如果不合法，则验证市失败，会退到index.jsp
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String home() throws UnsupportedEncodingException{
		//校验验证码
		if(!LoginUtils.checkNumber(request)){
			this.addFieldError("error", "验证码为空或者有误");
			return "error";
		}
		//获取当前登录名和密码
		String name = elecMenuForm.getName();
		String pwd = elecMenuForm.getPassword();
		MD5keyBean md5 = new MD5keyBean();
		String md5pwd = md5.getkeyBeanofStr(pwd);
		//使用登录名查询，获取用户详细信息
		ElecUser elecUser = elecUserService.findElecUserByLogonName(name);
		if(elecUser == null){
			this.addFieldError("error", "您当前输入的登录名不存在！");
			return "error";
		}
		if(pwd == null || "".equals(pwd) || !elecUser.getLogonPwd().equals(md5pwd)){
			this.addFieldError("error", "您当前输入的密码错误！");
			return "error";
		}
		request.getSession().setAttribute("globle_user", elecUser);
		//获取当前登录名所具有的角色
		Hashtable<String, String> roles = elecUserService.findElecRoleByLogonName(name);
		if(roles == null || roles.size() <= 0){
			this.addFieldError("error", "当前登录名没有分配角色，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("globle_role", roles);
		//获取当前登录名所具有的的权限
		String popedom  = elecUserService.findElecPopedomByLogonName(name);
		if(popedom == null || "".equals(popedom)){
			this.addFieldError("error", "当前登录名没有分配权限，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("globle_popedom", popedom);
		//记住我的功能，记住当前登录名和密码
		LoginUtils.remeberMeByCookie(request,response);
		
		//日志信息
		//使用log4j
		/*java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String d = date.toString();
		log.info("["+elecUser.getUserName()+"]登录系统,时间是："+d);
		*/
		//使用数据库
		elecLogService.saveElecLog(request,"登录模块：当前用户【"+elecUser.getUserName()+"】登录系统");
		return "home";
	}
	
	public String title(){
		return "title";
	}
	public String left(){
		return "left";
	}
	public String change1(){
		return "change1";
	}
	public String loading(){
		return "loading";
	}
	
	/**
	 * 查询当天站点运行情况
	 * @return 跳转到alermZD.jsp
	 */
	public String alermZD(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
	public String alermXZ(){
		return "alermXZ";
	}
	public String alermJX(){
		return "alermJX";
	}
	public String alermYS(){
		return "alermYS";
	}
	/**
	 * 查询当天的设备运行情况
	 * @return
	 */
	public String alermSB(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermSB";
	}
	
	/**
	 * 会退到登录页面
	 * @return
	 */
	public String logout(){
		//清空session
		request.getSession().invalidate();
		return "logout";
	}
	
}
