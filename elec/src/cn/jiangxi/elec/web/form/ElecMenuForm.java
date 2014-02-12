package cn.jiangxi.elec.web.form;

import java.io.Serializable;

/**
 * VO值对象，首页显示
 * @author wyzk
 *
 */
@SuppressWarnings("serial")
public class ElecMenuForm implements Serializable{
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
