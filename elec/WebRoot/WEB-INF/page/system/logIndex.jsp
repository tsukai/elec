<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
 <script language="javascript"> 
  function checkchar(){
  	document.Form1.action="system/logAction_main.do";
  	document.Form1.submit();
  }
  function conDelete(){
  	var flag = window.confirm('你确定要删除所有的日志吗');
  	if(!flag){
  		return;
  	}
  	else{
  		document.Form2.action="system/elecLogAction_delete.do";
  		document.Form2.submit();
  	}
  }
  </script>

<HTML>
	<HEAD>
		<title>用户管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
	</HEAD>
		
	<body >
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath }/system/elecLogAction_home.do" method="post" style="margin:0px;"> 
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
				<TR height=10><td></td></TR>
				<tr>
					<td class="ta_01" colspan=2 align="center" background="../images/b-info.gif">
						<font face="宋体" size="2"><strong>日志信息管理</strong></font>
					</td>
					
				</tr>
				<tr>
					<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					操作人：</td>
					<td class="ta_01" >
					<s:textfield name="opeName" id="opeName" cssStyle="width:140px"></s:textfield>
					<!-- 
					<input type="text" name="opeName" value="" id="opeName" style="width:140px"/>
					 -->
					</td>
				</tr>
		    </table>	
		</form>
		<form id="Form2" name="Form2" action="${pageContext.request.contextPath }/system/logAction_main.do" method="post">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
				<tr>
				  	<td>
		                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
										<TR>
											<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
											<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">日志列表</TD>
										</TR>
			             </TABLE>
                   </td>
					<td class="ta_01" align="right">
					    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="查询" name="BT_find" 
						 onclick="document.forms[0].submit()">&nbsp;&nbsp;
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="删除所有日志" name="BT_Delete" 
						 onclick="conDelete()">
					</td>
				</tr>
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="25%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作人</td>
								<td align="center" width="25%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">ip地址</td>
								<td align="center" width="25%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作时间</td>
								<td align="center" width="25%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作情况</td>
							</tr>
								<s:if test="%{#request.logList!=null}">
									<s:iterator value="%{#request.logList}" var="log">
										<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
												<s:hidden name="logID" id="logID"/>
											 <!-- 
												<input type="hidden" name="logid" value="${log.logID }" id="logid"/>
											  -->
											<td style="HEIGHT:22px" align="center" width="20%">
												<s:property value="%{#log.opeName}"/>
											</td>
											<td style="HEIGHT:22px" align="center" width="20%">
												<s:property value="%{#log.ipAddress}"/>
											</td>
											<td style="HEIGHT:22px" align="center" width="20%">
												<s:property value="%{#log.opeTime}"/>
											</td>									
											<td style="HEIGHT:22px" align="center" width="40%">
												<s:property value="%{#log.details}"/>
											</td>
										</tr>
									</s:iterator>
								</s:if>
									<!-- 
									
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<input type="hidden" name="logid" value="ff80808132e8ceec0132e8d09d350003" id="logid"/>
										<td style="HEIGHT:22px" align="center" width="25%">
											&#36229;&#32423;&#31649;&#29702;&#21592;
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											127.0.0.1
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											2011-10-09
										</td>									
										<td style="HEIGHT:22px" align="center" width="25%">
											&#20462;&#25913;&#29992;&#25143;&#30000;&#19971;
										</td>
									</tr>
									
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<input type="hidden" name="logid" value="ff80808132e8ceec0132e8d0fdd40004" id="logid"/>
										<td style="HEIGHT:22px" align="center" width="25%">
											&#36229;&#32423;&#31649;&#29702;&#21592;
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											127.0.0.1
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											2011-10-09
										</td>									
										<td style="HEIGHT:22px" align="center" width="25%">
											&#21024;&#38500;&#29992;&#25143;null
										</td>
									</tr>
									
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<input type="hidden" name="logid" value="ff80808132e8ceec0132e8d3c5dd0006" id="logid"/>
										<td style="HEIGHT:22px" align="center" width="25%">
											&#36229;&#32423;&#31649;&#29702;&#21592;
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											127.0.0.1
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											2011-10-09
										</td>									
										<td style="HEIGHT:22px" align="center" width="25%">
											&#26032;&#22686;&#29992;&#25143;&#30475;&#30475;
										</td>
									</tr>
									
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<input type="hidden" name="logid" value="ff80808132e8ceec0132e8d3df310007" id="logid"/>
										<td style="HEIGHT:22px" align="center" width="25%">
											&#36229;&#32423;&#31649;&#29702;&#21592;
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											127.0.0.1
										</td>
										<td style="HEIGHT:22px" align="center" width="25%">
											2011-10-09
										</td>									
										<td style="HEIGHT:22px" align="center" width="25%">
											&#21024;&#38500;&#29992;&#25143;&#30475;&#30475;
										</td>
									</tr>
								 -->	
								
						</table>					
						
					</td>
				</tr>        
			</TBODY>
		</table>
		</form>




	</body>
</HTML>
