package cn.jiangxi.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO持久层对象，对应数据库表Elec_Log
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class ElecLog implements Serializable{
	private String logID;
	private String ipAddress;
	private String opeName;
	private Date opeTime;
	private String details;
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
	public Date getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
