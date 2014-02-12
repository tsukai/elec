package cn.jiangxi.elec.web.action;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.service.IElecCommonMsgService;
import cn.jiangxi.elec.service.IElecLogService;
import cn.jiangxi.elec.service.IElecSystemDDLService;
import cn.jiangxi.elec.service.IElecUserService;
import cn.jiangxi.elec.util.ExcelFileGenerator;
import cn.jiangxi.elec.web.form.ElecSystemDDLForm;
import cn.jiangxi.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm>{
	private ElecUserForm elecUserForm = new ElecUserForm();
	private IElecUserService elecUserService = (IElecUserService)ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	private IElecLogService elecLogService = (IElecLogService)
			ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	
	private IElecSystemDDLService elecSystemDDLService = 
			(IElecSystemDDLService)ServiceProvider.getService(IElecSystemDDLService.SERVICE_NAME);
	
	public ElecUserForm getModel() {
		return elecUserForm;
	}
	
	/**
	 * 查询用户列表信息
	 * @return 跳转到userIndex.jsp
	 */
	public String home(){
		//添加分页功能，需要request对象
		List<ElecUserForm> list = elecUserService. findElecUserList(elecUserForm,request); 
		request.setAttribute("userList", list);
		//使用initflag标识，判断当前跳转是userIndex.jsp还是userList.jsp
		String initflag = request.getParameter("initflag");
		if(initflag != null && initflag.equals("1")){
			return "page";
		}
		return "home";
	}
	
	/**
	 * 跳转到添加用户界面
	 * @return
	 */
	public String add(){
		this.initSystemDDL();
		//使用数据类型进行查询，胡哦哦去对应数据类型下的数据项编号和数据项名称
		//查询性别，所属单位，是否在职
		/*List<ElecSystemDDLForm> sexList = 
				elecSystemDDLService.findElecSystemDDLListByKeyword("性别");
		List<ElecSystemDDLForm> jctList = 
				elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		List<ElecSystemDDLForm> isDutyList = 
				elecSystemDDLService.findElecSystemDDLListByKeyword("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isDutyList", isDutyList);*/
		return "add";
	}
	
	/**
	 * 初始化新增和编辑用户页面中使用的数据字典的项
	 */
	private void initSystemDDL() {
		//使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称
		//查询性别，所属单位，是否在职
		List<ElecSystemDDLForm> sexList = 
				elecSystemDDLService.findElecSystemDDLListByKeyword("性别");
		List<ElecSystemDDLForm> jctList = 
				elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		List<ElecSystemDDLForm> isDutyList = 
				elecSystemDDLService.findElecSystemDDLListByKeyword("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isDutyList", isDutyList);
	}

	/**
	 * 保存用户信息
	 * @return 重定向到userIndex页面
	 */
	public String save(){
		elecUserService.saveElecUser(elecUserForm);
		//日志信息
		if(elecUserForm.getUserID() != null && !"".equals(elecUserForm.getUserID())){
			//修保存
			elecLogService.saveElecLog(request, "用户管理：修改用户【"+elecUserForm.getUserName()+"】的信息");
		}else{
			//新增保存
			elecLogService.saveElecLog(request, "用户管理：新增用户【"+elecUserForm.getUserName()+"】");
		}
		String roleflag = request.getParameter("roleflag");
		if(roleflag != null && "1".equals(roleflag)){
			return edit();
		}
		return "list";
	}
	
	/**
	 * 跳转到编辑页面userEdit.jsp
	 * @return
	 */
	public String edit(){
		ElecUserForm euf = elecUserService.findElecUser(elecUserForm);
		//使用值栈的形式传递elecUserForm
		ActionContext.getContext().getValueStack().push(euf);
		this.initSystemDDL();
		//使用viewflag判断当前用户操作的是编辑页面还是明细页面
		//viewflag==1为明细页面。否则为编辑页面
		String viewflag = elecUserForm.getViewflag();
		request.setAttribute("viewflag", viewflag);
		/**
		 * 判断编辑页面点击保存时重定向的位置
		 * 值为1，重定向的到userEdit.jsp
		 * 否则重定向到userIndex.jsp
		 */
		String roleflag = elecUserForm.getRoleflag();
		request.setAttribute("roleflag", roleflag);
		return "edit";
	}
	
	/**
	 * 删除用户信息
	 * @return 跳转到userIndex.jsp
	 */
	public String delete(){
		elecUserService.deleteElecUser(elecUserForm);
		return "list";
	}
	
	/**
	 * 导出Excel文件
	 * @return
	 */
	public String export(){
		//获取导出的表头和数据
		ArrayList fieldName = elecUserService.getExcelFieldName();
		ArrayList fieldData = elecUserService.getExcelFieldDataList(elecUserForm);
		try {
			//获取输出流
			OutputStream out = response.getOutputStream();
			//重置输出流
			response.reset();
			//设置导出Excel报表的形式
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator generator = new ExcelFileGenerator(fieldName, fieldData);
			generator.expordExcel(out);
			//设置输出形式
			System.out.println(new PrintStream(out));
			//刷新输出流
			out.flush();
			//关闭输出流
			if(out!=null){
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "export";
	}
	
	/**
	 * 跳转到代入excel的页面userImport.jsp
	 * @return
	 */
	public String importPage(){
		return 	"importPage";
	}
	
	/**
	 * 从excel读取数据，存放到数据库中
	 * @return
	 */
	public String importData(){
		elecUserService.saveElecUserWithExcel(elecUserForm);
		return 	"importData";
	}
}

