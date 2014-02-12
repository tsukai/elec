package cn.jiangxi.elec.web.form;

import java.io.Serializable;

/**
 * VO值对象，对应页面表单的属性值
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class ElecSystemDDLForm implements Serializable{
	private String seqID;	//主键ID
	private String keyword;	//查询关键字
	private String ddlCode;	//数据字典的code
	private String ddlName;		//数据字典的value
	
	//保存数据字典的关键字
	private String keywordname;
	
	/**
	 * 保存数据字典时的状态标识
	 * new：新建一种数据类型，并添加数据项保存
	 * add：在原有数据类型基础上，进行修改和编辑对应的数据项，保存
	 */
	private String typeflag;
	
	//保存数据字典的数据项名称
	private String[] itemname;
	public String getSeqID() {
		return seqID;
	}
	public void setSeqID(String seqID) {
		this.seqID = seqID;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(String ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	public String getKeywordname() {
		return keywordname;
	}
	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}
	public String getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}
	public String[] getItemname() {
		return itemname;
	}
	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}
	
}
