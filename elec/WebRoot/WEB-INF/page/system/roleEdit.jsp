<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
 <tr>
  <td>
   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left">权限分配</legend>
 
     <table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">			 
			  <tr>
				 <td class="ta_01" colspan=2 align="left" width="100%" > 
				 	全选/全不选<input type="checkbox" name="selectOperAll" onclick="checkAllOper(this)">
				 	<s:set value="%{''}" scope="request" var="parentCode"></s:set>
					<s:if test="%{#request.xmlList!=null}">
						<s:iterator value="%{#request.xmlList}" var="xml">
							<s:if test="%{#request.parentCode == #xml.parentCode}">
								<s:if test="%{#xml.flag==1}">
									<input type="checkbox" checked="checked"  name="selectoper" value="<s:property value='%{#xml.code}'/>" >
								</s:if>
								<s:else>
									<input type="checkbox"  name="selectoper" value="<s:property value='%{#xml.code}'/>" >
								</s:else>
								<s:property value="%{#xml.name}"/>
							</s:if>
							<s:else>
								<s:set value="%{#xml.parentCode}" scope="request" var="parentCode"></s:set>
								<br/>
								<s:iterator begin="0" end="%{8-#xml.parentName.length()}" step="1">
									&nbsp;&nbsp;&nbsp;
								</s:iterator>
								<s:property value="%{#xml.parentName}"/>：
								<s:if test="%{#xml.flag==1}">
									<input type="checkbox" checked="checked"  name="selectoper" value="<s:property value='%{#xml.code}'/>" >
								</s:if>
								<s:else>
									<input type="checkbox"  name="selectoper" value="<s:property value='%{#xml.code}'/>" >
								</s:else>
								<s:property value="%{#xml.name}"/>
							</s:else>						
						</s:iterator>
					</s:if>
				 	<!-- 
				 	
				 	<br>
				          技术设施维护管理 : 
   	                         <input type="checkbox"  name="selectoper" value="a" checked>
				             仪器设备管理
				                &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="b" checked>
				             设备校准检修
				              &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="c" checked>
				             设备购置计划
				              &nbsp;&nbsp;&nbsp;
						<br>
				          技术资料图纸管理 : 
   	                         <input type="checkbox"  name="selectoper" value="d" checked>
				             资料图纸管理
				                &nbsp;&nbsp;&nbsp;
						<br>
				          站点设备运行管理 : 
   	                         <input type="checkbox"  name="selectoper" value="e" checked>
				             站点基本信息
				                &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="f" checked>
				             运行情况
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="g" checked>
				             维护情况
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
						<br>
				           &nbsp;&nbsp;&nbsp;
				          监测台建筑管理 : 
   	                         <input type="checkbox"  name="selectoper" value="k" >
				             监测台建筑管理
						<br>
				           &nbsp;&nbsp;&nbsp;
				           &nbsp;&nbsp;&nbsp;
				           &nbsp;&nbsp;&nbsp;
				           &nbsp;&nbsp;&nbsp;
				          系统管理 : 
   	                         <input type="checkbox"  name="selectoper" value="l" >
				             角色管理
				                &nbsp;&nbsp;&nbsp;
				                &nbsp;&nbsp;&nbsp;
				                &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="m" >
				             待办事宜
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="n" >
				             数据字典维护
				              &nbsp;&nbsp;&nbsp;
						<br>
				           &nbsp;&nbsp;&nbsp;
				           &nbsp;&nbsp;&nbsp;
				          操作权限分配 : 
   	                         <input type="checkbox"  name="selectoper" value="o" >
				             新增功能
				                &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="p" >
				             删除功能
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
   	                         <input type="checkbox"  name="selectoper" value="q" >
				             编辑功能
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
				              &nbsp;&nbsp;&nbsp;
	               -->
				 </td>
			  </tr>		
			  	<!-- 
				 <input type="hidden" name="roleStr" >
			  	 -->				
				 <input type="hidden" name="roleid" >						
		 </table>	
        </fieldset>
	  </td>
	</tr>
	<tr>
		<td height=10></td>
	</tr>
	
  <tr>
	<td>
	
   <fieldset style="width:100%; border : 0px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left">用户分配</legend>
 
	<table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
			    
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
				   <td class="ta_01"  align="center" width="20%" height=22 background="../images/tablehead.jpg">选中<input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)" ></td>
				   <td class="ta_01"  align="center" width="40%" height=22 background="../images/tablehead.jpg">登录名</td>
				   <td class="ta_01"  align="center" width="40%" height=22 background="../images/tablehead.jpg">用户姓名</td>
				</tr>
				<s:if test="%{#request.userList!=null}">
					<s:iterator value="%{#request.userList}" var="user">
						 <tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
							<td style="HEIGHT: 22px" class="ta_01" align="center" width="20%">
								<s:if test="%{#user.flag==1}">
									<input type="checkbox" name="selectuser" value="<s:property value='%{#user.userID}'/>" checked="checked">
								</s:if>
								<s:else>
									<input type="checkbox" name="selectuser" value="<s:property value='%{#user.userID}'/>">
								</s:else>
							</td>
							<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
								<s:property value='%{#user.logonName}'/>
							</td>
							<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
								<s:property value='%{#user.userName}'/>
							</td>
						</tr>
					</s:iterator>
				</s:if>
				
		</table>
    </fieldset>
	 </td>
	 </tr>
			<tr>
		   <td class="ta_01" align="center" colspan=3 width="100%"  height=20>
		   <input type="button" name="saverole" onclick="saveRole()" style="font-size:12px; color:black; height=20;width=50" value="保存">
		   </td>
		</tr>
   </table>
