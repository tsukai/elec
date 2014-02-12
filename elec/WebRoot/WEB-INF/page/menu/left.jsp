<%@page import="java.util.Hashtable"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String popedom = (String)request.getSession()
		.getAttribute("globle_popedom"); 
	Hashtable ht = (Hashtable)request.getSession()
		.getAttribute("globle_role");
%>
<HTML>
	<HEAD>
		<TITLE>Left</TITLE>
		<STYLE type="text/css">BODY {
	MARGIN: 0px; BACKGROUND-COLOR: #8ba7e3
}
BODY {
	COLOR: #000000
}
TD {
	COLOR: #000000
}
TH {
	COLOR: #000000
} 
		</STYLE>
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<SCRIPT language="JavaScript">
		a="";
		NS4 = (document.layers) ? 1 : 0;
		IE4 = (document.all) ? 1 : 0;
		ver4 = (NS4 || IE4) ? 1 : 0;

		if (ver4) {
			with (document) {
				write("<STYLE TYPE='text/css'>");
				if (NS4) {
					write(".parent {position:absolute; visibility:visible}");
					write(".child {position:absolute; visibility:visible}");
					write(".regular {position:absolute; visibility:visible}")
				}
				else {
					write(".child {display:none}")
				}
				write("</STYLE>");
			}
		}
		function getIndex(el) {
			ind = null;
			for (i=0; i<document.layers.length; i++) {
				whichEl = document.layers[i];
				if (whichEl.id == el) {
					ind = i;
					break;
				}
			}
			return ind;
		}
		function arrange() {
			nextY = document.layers[firstInd].pageY +document.layers[firstInd].document.height;
			for (i=firstInd+1; i<document.layers.length; i++) {
				whichEl = document.layers[i];
				if (whichEl.visibility != "hide") {
					whichEl.pageY = nextY;
					nextY += whichEl.document.height;
				}
			}
		}

		function initIt(){
			if (!ver4) return;
			if (NS4) {
				for (i=0; i<document.layers.length; i++) {
					whichEl = document.layers[i];
					if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "hide";
			}
				arrange();
			}
			else {
				divColl = document.all.tags("DIV");
				for (i=0; i<divColl.length; i++) {
					whichEl = divColl(i);
					if (whichEl.className == "child") whichEl.style.display = "none";
				}
			}
		}
		function expand(bb)
		{
			
		if(a!="")
		{
		expandIt(a)
		}

		expandIt(bb)

//		a=bb;

		}
		
		function expandIt(el) {
		
			//debugger ;
			if (!ver4) return;
			if (IE4) {
				whichEl = eval(el + "Child");
				whichimg = eval("img" + el)
				if (whichEl.style.display == "none") {
					whichEl.style.display = "block";
					whichimg.src="${pageContext.request.contextPath }/images/open.gif";
		            
				}
				else {
					whichEl.style.display = "none";
					whichimg.src="${pageContext.request.contextPath }/images/add.gif";
				}
			}
			else {
				whichEl = eval("document." + el + "Child");
				if (whichEl.visibility == "hide") {
					whichEl.visibility = "show";
				}
				else {
					whichEl.visibility = "hide";
				}
				arrange();
			}
		}
		
		
		function linkcolorchange(objLink)
		{
			for(var i=0;i<document.links.length;i++)
			{
				document.links[i].style.color = "" ;
			}
				objLink.style.color = "red" ;
		}
		function backgroundColorChange(objLink,strColor)
		{
			objLink.style.backgroundColor = strColor ;
		}

		onload = initIt;
		</SCRIPT>
	</HEAD>
	<BODY scroll="no" MS_POSITIONING="GridLayout" scroll="auto" class="bodyscroll">
		<TABLE height="100%" cellSpacing="0" cellPadding="0" width="143" border="0">
			<TBODY>
				<TR>
					<TD vAlign="top" bgColor="#F6F6F6" height="100%">
                     
                     
                     	<%if(popedom.contains("a") || popedom.contains("b") || popedom.contains("c")){ %>
						<DIV class="parent" id="KB0Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
								<TBODY>
									<TR height=25 >
										<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB0'); return false" href="#">&nbsp;技术设施维护管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						
						<DIV class="child" id="KB0Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
								   <%if(popedom.contains("a")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/equapment/equapmentIndex.jsp" target="mainFrame">仪器设备管理</A>
										</TD>
									</TR>
									<%} if(popedom.contains("b")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/equapment/adjustIndex.jsp" target="mainFrame">设备校准检修</A>
										</TD>
									</TR>
									<%} if(popedom.contains("c")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/equapment/planIndex.jsp" target="mainFrame">设备购置计划</A>
										</TD>
									</TR>
									<%} %>
									
								</TBODY>
							</TABLE>
						</DIV>
                       <%} if(popedom.contains("d")){%>

                      
						<DIV class="parent" id="KB1Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB1" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB1'); return false" href="#">&nbsp;技术资料图纸管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB1Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									 <%if(popedom.contains("d")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/dataChart/dataChartIndex.jsp" target="mainFrame">资料图纸管理</A>
										</TD>
									</TR>
									<%} %>
								</TBODY>
							</TABLE>
						</DIV>
                      <%} if(popedom.contains("e") || popedom.contains("f") || popedom.contains("g")){%>
                      
                      
						<DIV class="parent" id="KB2Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB2" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB2'); return false" href="#">&nbsp;站点设备运行管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB2Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
								     <%if(popedom.contains("e")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/siteEquapment/siteInfoIndex.jsp" target="mainFrame">站点基本信息</A>
										</TD>
									</TR>
									<%} if(popedom.contains("f")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/siteEquapment/siteRunIndex.jsp" target="mainFrame">运行情况</A>
										</TD>
									</TR>
									<%} if(popedom.contains("g")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/siteEquapment/siteMaintainIndex.jsp" target="mainFrame">维护情况</A>
										</TD>
									</TR>
								   <%} %>
								</TBODY>
							</TABLE>
						</DIV>
                       <%}if(popedom.contains("h")){ %>


                      
						<DIV class="parent" id="KB3Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB3" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB3'); return false" href="#">&nbsp;监测台建筑管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB3Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
								<% if(popedom.contains("h")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/building/buildingIndex.jsp" target="mainFrame">监测台建筑管理</A>
										</TD>
									</TR>
									<%} %>
								</TBODY>
							</TABLE>
						</DIV>
                      <%} %>
 
                   
						<DIV class="parent" id="KB4Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB4" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB4'); return false" href="#">&nbsp;系统管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB4Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<%if(ht.containsKey("1") || ht.containsKey("2")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/elecUserAction_home.do" target="mainFrame">用户管理</A>
										</TD>
									</TR>
									<%} else{%>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/elecUserAction_edit.do?userID=${globle_user.userID}&roleflag=1" target="mainFrame">用户管理</A>
										</TD>
									</TR>
									<%} %>
									<% if(popedom.contains("i")){ %>
									
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/elecRoleAction_home.do" target="mainFrame">角色管理</A>
										</TD>
									</TR>
									
									<%} if(popedom.contains("j")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/elecCommonMsgAction_home.do" target="mainFrame">待办事宜</A>
										</TD>
									</TR>
									<%} if(popedom.contains("k")){ %>
									
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/elecSystemDDLAction_home.do" target="mainFrame">数据字典维护</A>
										</TD>
									</TR>
									<%} %>
									<TR>
										<TD class="box06" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/elecLogAction_home.do" target="mainFrame">日志管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
					
						
	</BODY>