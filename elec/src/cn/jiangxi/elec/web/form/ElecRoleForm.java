package cn.jiangxi.elec.web.form;

import java.io.Serializable;

/**
 * VO值对象，对应页面表单的属性值
 * VO对象与PO对象的关系:
 * 	相同点：都是JavaBean
 *	不同点：PO对象中的属性关联数据库的字段
 *		   VO对象中的属性可以随意增加、修改、删除，对应页面表单属性
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class ElecRoleForm implements Serializable{
	private String role;//角色ID
	private String roleid;//角色id
	private String[] selectoper;//权限编号
	private String[] selectuser;//用户集合
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String[] getSelectoper() {
		return selectoper;
	}

	public void setSelectoper(String[] selectoper) {
		this.selectoper = selectoper;
	}

	public String[] getSelectuser() {
		return selectuser;
	}

	public void setSelectuser(String[] selectuser) {
		this.selectuser = selectuser;
	}
	
}
