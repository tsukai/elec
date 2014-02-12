package cn.jiangxi.elec.web.form;

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
public class ElecLogForm implements Serializable{
	private String logID;
	private String ipAddress;
	private String opeName;
	private String opeTime;
	private String details;
	//定义String[] 用于获取待删除的日志id
	private String[] logid;
	
	public String[] getLogid() {
		return logid;
	}
	public void setLogid(String[] logid) {
		this.logid = logid;
	}
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getOpeName() {
		return opeName;
	}
	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
