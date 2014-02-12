package cn.jiangxi.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO持久层对象，对应数据库表Elec_CommonMsg
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class ElecCommonMsg implements Serializable{
	private String comID;//主键ID
	private String stationRun;//站点运行情况
	
	private Date createDate;//设备运行情况
	private String devRun;//创建日期
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDevRun() {
		return devRun;
	}
	public void setDevRun(String devRun) {
		this.devRun = devRun;
	}
	
}
