package cn.jiangxi.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO持久层对象，对应数据库表Elecr_Role_Popedom
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class ElecRolePopedom implements Serializable{
	private String roleID;
	private String popedomcode;
	private String remark;
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String seqID) {
		this.roleID = seqID;
	}
	public String getPopedomcode() {
		return popedomcode;
	}
	public void setPopedomcode(String popedomcode) {
		this.popedomcode = popedomcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
