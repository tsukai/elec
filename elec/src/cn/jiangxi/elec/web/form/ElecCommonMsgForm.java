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
public class ElecCommonMsgForm implements Serializable{
	private String comID;
	private String stationRun;
	private String devRun;
	private String createDate;
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getStationRun() {
		return stationRun;
	}
	public void setStationRun(String stationRun) {
		this.stationRun = stationRun;
	}
	public String getDevRun() {
		return devRun;
	}
	public void setDevRun(String devRun) {
		this.devRun = devRun;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
