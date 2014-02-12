package cn.jiangxi.elec.util;

import java.io.Serializable;

/**
 * 存放Function.xml文件中
 * 获取的权限，权限的code，权限 名称，父级权限code，父级权限名称
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class XmlObject implements Serializable{
	private String code;
	private String name;
	private String parentCode;
	private String parentName;
	/**
	 * 判断页面中权限的复选框是否被选中的标识
	 * 如果flag==0，标识该角色不具有的权限，则页面中的权限复选框不被选中
	 * 如果flag==1，表示该角色觉有此权限，则复选框选中
	 */
	private String flag;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
