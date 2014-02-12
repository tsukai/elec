<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
   <title>
   	<s:if test="#request.viewflag==null">
 	  编辑用户
   	</s:if>
   	<s:else>
   		查看明细
   	</s:else>
   </title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
   <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
   <Script language="javascript">
	var roleFlag = <%=request.getParameter("roleflag")%>
	function check_null(){
	
		var theForm=document.Form1;
	    
	  
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
	    if(theForm.jctID.value=="")
		{
			alert("请选择所属单位");
			theForm.jctId.focus();
			return false;
		}
	
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.contactTel)){
         if(!checkPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
		
	    if(checkNull(theForm.mobile)){
         if(!checkMobilPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
			return false;
		  }
		}
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	   if(theForm.remark.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.remark.focus();
			return false; 
        }
		 //处理当前用户是否修改了密码
		 //未修改则设置md5flag为1，不需再次加密
		 //否则设置md5flag为null，需加密
		 var password = document.getElementById("logonPwd").value;
		 var defaultpwd = document.getElementById("logonPwd").defaultValue;
		 if(defaultpwd == password){
		 	document.getElementById("md5flag").value = "1";
		 }
	   document.Form1.action="system/elecUserAction_save.do";
	   document.Form1.submit();
	   if(roleFlag == null || roleFlag == ""){
	   	refreshOpener();
	   }
	}
	
	window.onload = function(){
		var pwd = document.getElementById("logonPwd").value;
		document.getElementById("passwordconfirm").value = pwd;
		document.getElementById("roleflag").value = roleFlag;
	}
	
	
</script>
</head>

  
 <body>
    <s:form name="Form1" method="post">	
    <br>
    
    <table cellSpacing="1" cellPadding="5" width="90%" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	 <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2">
		 	<strong>
		 		<s:if test="#request.viewflag==null">
			 	  编辑用户
			   	</s:if>
		 		<s:else>
			   		查看明细
			   	</s:else>
		 	</strong>
		 </font>
		</td>
    </tr>
    	<s:hidden name="userID" id="userID"></s:hidden>
    	<s:hidden name="md5flag" id="md5flag"></s:hidden>
    	<s:hidden name="roleflag" id="roleflag"></s:hidden>
    	<!-- 
       <input name="userid"  type="hidden" value="ff808081110677790111070ccffe0001">
       <input name="isAdmin" type="hidden" value="1">
    	 -->
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：
         <td class="ta_01" bgColor="#ffffff">
         	<s:textfield name="logonName" id="logonName" maxlength="25" id="logonName" size="20" readonly="true"></s:textfield>
         	<!-- 
         	<input name="logonName" type="text" maxlength="25" id="logonName"  value="sunhy"  size=20 readonly="true">
         	 -->
          	<font color="#FF0000">*</font>
     	</td>
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：


         <td class="ta_01" bgColor="#ffffff">
         	<s:textfield name="userName" id="userName" maxlength="25" id="userName" size="20" ></s:textfield>
         	<!-- 
         	<input name="userName" type="text"  value="孙鸿洋" maxlength="25" id="userName"  size=20> 
         	 -->
          	<font color="#FF0000">*</font>
         </td>
    </tr>
<tr>
	<td align="center" bgColor="#f5fafe" class="ta_01">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:select list="#request.sexList" name="sexID" id="sexID"
				  listKey="ddlCode" listValue="ddlName"
				  headerKey="" headerValue=""
				  cssStyle="width:155px">
		
		</s:select>
		<!-- 
		<select name="sexId" style="width:155px">
		<option value=""></option>
		<option value="男" selected>男</option>
		<option value="女">女</option>
		</select>
		 -->
	</td>
	
	<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:select list="#request.jctList" name="jctID" id="jctID"
				  listKey="ddlCode" listValue="ddlName"
				  headerKey="" headerValue=""
				  cssStyle="width:155px">
		
		</s:select>
		<!-- 
		<select name="jctId" style="width:155px">
		<option value=""></option>
		<option value="北京">北京</option>
		<option value="深圳" selected>深圳</option>
		<option value="厦门">厦门</option>
		<option value="上海">上海</option>
		<option value="广州">广州</option>
		</select> 
		 -->
		<font color="#FF0000">*</font>
	</td>

</tr>
<tr>
	<td align="center" bgColor="#f5fafe" class="ta_01">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:password name="logonPwd" id="logonPwd" maxlength="25"  size="22" showPassword="true"></s:password>
		<!-- 
		<input name="logonPwd" type="password" value="sunhy" maxlength="25"  size=22>
		 -->
	</td>
	<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:password name="passwordconfirm" id="passwordconfirm" maxlength="25"  size="22"></s:password>
		<!-- 
		<input name="passwordconfirm" type="password" value="sunhy" maxlength="25" size=22>
		 -->
	</td>
</tr>

<tr>
	<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="birthday" id="birthday" maxlength="50"  size="20" onclick="JavaScript:calendar(document.Form1.birthday)"></s:textfield>
		<!-- 
		<input name="birthday" type="text" maxlength="50"  size=20 value="" onclick="JavaScript:calendar(document.Form1.birthday)">
		 -->
	</td>
	<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield  name="address" id="address" maxlength="50"  size="20"></s:textfield>
		<!-- 
		<input name="adress" type="text" maxlength="50"  size=20 value="">
		 -->
	</td>
</tr>

<tr>
	<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="contactTel" id="contactTel" maxlength="25"  size="20"></s:textfield>				
		<!-- 
		<input name="contactTel" type="text" maxlength="25" size=20 value=""></td>
		 -->
	<td align="center" bgColor="#f5fafe" class="ta_01">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="mobile" id="mobile" maxlength="25"  size="20"></s:textfield>
		<!-- 
		<input name="mobile" type="text" maxlength="25"  size=20 value="">
		 -->
	</td>
</tr>

<tr>
	<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="email" id="email" maxlength="50"  size="20"></s:textfield>
		<!-- 
		<input name="email" type="text" maxlength="50"  size=20 value="">
		 -->
	</td>
	<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:select list="#request.isDutyList" name="isDuty" id="isDuty"
				  listKey="ddlCode" listValue="ddlName"
				  cssStyle="width:155px">
		
		</s:select>
		<!-- 
		<select name="isDuty"  style="width:155px">
			<option value="是" selected>是</option>
			<option value="否">否</option>
		</select>
		 -->
	</td>
</tr>

<tr>
	<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="onDutyDate" id="onDutyDate" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.onDutyDate)"></s:textfield>
		<!-- 
		<input name="ondutydate" type="text" maxlength="50" size=20 value="" onclick="JavaScript:calendar(document.Form1.ondutydate)">
		 -->
	</td>
	<td align="center" bgColor="#f5fafe" class="ta_01">离职日期：</td>
	<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="offDutyDate" id="offDutyDate" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.offDutyDate)"></s:textfield>
		<!-- 
		<input name="offDutyDate" type="text" maxlength="50" size=20 value="" onclick="JavaScript:calendar(document.Form1.offDutyDate)">
		 -->
	</td>
</tr>

<TR>
	<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
	<TD class="ta_01" bgColor="#ffffff" colSpan="3">
		<s:textarea name="remark" id="remark" cssStyle="WIDTH:95%"  rows="4" cols="52"></s:textarea>
		<!-- 
		<textarea name="empRemark"  style="WIDTH:92%;"  rows="4" cols="52">系统管理员</textarea>
		 -->
	</TD>
</TR>
<TR>
<td  align="center"  colSpan="4"  class="sep1"></td>
</TR>
<tr>
	<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
		<s:if test="#request.viewflag==null">
			<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"  onClick="check_null()">
		</s:if>
		<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		 <s:if test="%{#request.roleflag != 1}">
		   	<input style="font-size:12px; color:black; height=22;width=55" type="button" value="关闭"  name="Reset1"  onClick="window.close()">
		 </s:if>
	      
	</td>
</tr>
</table>　
</s:form>

</body>
</html>
