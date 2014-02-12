package cn.jiangxi.elec.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogonFilter implements Filter {

	private List<String> list = new ArrayList<String>();
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 1、获取项目中房访问的路径连接
		 * 	*与定义的需要房子那个的连接进行对比
		 * ·	*页面中范文的连接与定义的需要放行的一致， 则需要放行
		 * 		*反之不放行，返回到登录页面
		 * 2、从session对象中互殴去当前登录的用户
		 * 	*如果对象不为空，则放行
		 * 	*否则不放行，返回到登录页面
		 */
		HttpServletRequest request = (HttpServletRequest)srequest;
		HttpServletResponse response = (HttpServletResponse)sresponse;
		String path = request.getServletPath();
		if(list != null && list.contains(path)){
			chain.doFilter(request, response);
			return;
		}
		Object user = (Object)request.getSession().getAttribute("globle_user");
		if(user != null){
			chain.doFilter(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath());
	}

	public void init(FilterConfig arg0) throws ServletException {
		//定义需要放行的连接，用List<String>存放
		
		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_home.do");
	}

	public void destroy() {

	}
}
