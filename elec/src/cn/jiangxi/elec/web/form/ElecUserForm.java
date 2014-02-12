package cn.jiangxi.elec.web.form;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

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
public class ElecUserForm implements Serializable{
	private String userID;
	private String jctID;
	private String userName;
	private String logonName;
	private String logonPwd;
	private String sexID;
	private String birthday;
	private String address;
	private String contactTel;
	private String email;
	private String mobile;
	private String isDuty;
	private String onDutyDate;
	private String offDutyDate;
	private String remark;
	private String viewflag;//判断单签用户操作的是编辑页面还是明细页面viewflag==null为编辑页面
	/**
	 *  使用flag判断角色编辑的页面中该用户是否被选中
	 *  flag==1，表示选中
	 *  flag==0，不被选中
	 */
	private String flag;
	/**
	 * 处理当前用户是否修改了密码
		未修改则设置md5flag为1，不需再次加密
		 否则设置md5flag为null，需加密
	 */
	private String md5flag;
	
	/**
	 * 用于判断单签操作人具有的角色是否是系统管理员和高级管理员的标识
	 * 如果值为1，则不是，编辑页面点击保存时需要重定向到userEdit.jsp
	 * 否则重定向到userIndex.jsp
	 */
	private String roleflag;
	
	//使用jxl进行报表导入时使用
	private File file;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getRoleflag() {
		return roleflag;
	}
	public void setRoleflag(String roleflag) {
		this.roleflag = roleflag;
	}
	public String getMd5flag() {
		return md5flag;
	}
	public void setMd5flag(String md5flag) {
		this.md5flag = md5flag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getJctID() {
		return jctID;
	}
	public void setJctID(String jctID) {
		this.jctID = jctID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public String getLogonPwd() {
		return logonPwd;
	}
	public void setLogonPwd(String logonPwd) {
		this.logonPwd = logonPwd;
	}
	public String getSexID() {
		return sexID;
	}
	public void setSexID(String sexID) {
		this.sexID = sexID;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsDuty() {
		return isDuty;
	}
	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}
	public String getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public String getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(String offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getViewflag() {
		return viewflag;
	}
	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}
	
}
