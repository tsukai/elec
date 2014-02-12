package cn.jiangxi.elec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.service.IElecUserService;

@SuppressWarnings("serial")
public class CheckLogonName extends HttpServlet {

	IElecUserService elecUserService = (IElecUserService)ServiceProvider
			.getService(IElecUserService.SERVICE_NAME);
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String logonName = request.getParameter("logonName");
		/**
		 * checkflag：判断当前登录名在数据库中是否有值
		 * 如果有值，chackflag==1，不能进行保存
		 * 如果没有值，checkflag==2
		 */
		String checkflag = elecUserService.checkLogonName(logonName);
		out.print(checkflag);
		out.flush();
		out.close();
	}

}
