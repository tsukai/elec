package cn.jiangxi.elec.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class LoginUtils {
	/**
	 * 首页登录添加验证码功能
	 * @param request
	 * @return true 验证成功
	 * false 验证失败
	 */
	public static boolean checkNumber(HttpServletRequest request) {
		//从session中获取验证码
		if(request.getSession(false) == null){
			return false;
		}
		String genCheckCode = (String)request.getSession(false).getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(genCheckCode)){
			return false;
		}
		//从登陆页面获取验证码
		String inputCode = request.getParameter("checkNumber");
		if(StringUtils.isBlank(inputCode)){
			return false;
		}
		return genCheckCode.equalsIgnoreCase((inputCode));
	}
	
	/**
	 * 首页登录中添加记住我的功能
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	public static void remeberMeByCookie(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		//获取登录名和密码
		String logonName = request.getParameter("name");
		String pwd = request.getParameter("password");
		//处理cookie中存在中文字符的问题
		String codeName = URLEncoder.encode(logonName, "UTF-8");
		String codePwd = URLEncoder.encode(pwd, "UTF-8");
		//创建cookie
		Cookie nameCookie = new Cookie("name",codeName);
		Cookie pwdCookie = new Cookie("password",codePwd);
		//设置cookie有效路径
		nameCookie.setPath(request.getContextPath()+"/");
		pwdCookie.setPath(request.getContextPath()+"/");
		//是否选中记住我
		if(request.getParameter("remeberMe") != null && 
				"yes".equals(request.getParameter("remeberMe"))){
			//设置cookie有效时长
			nameCookie.setMaxAge(7*24*60*60);
			pwdCookie.setMaxAge(7*24*60*60);
		}else{
			//清空cookie有效时长
			pwdCookie.setMaxAge(0);
			nameCookie.setMaxAge(0);
		}
		//将cookie存放到response中
		response.addCookie(nameCookie);
		response.addCookie(pwdCookie);
	}


}
