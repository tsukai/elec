<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
  <head> 
    <title>导入文件</title> 
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <SCRIPT language="javascript">
       
     
    </SCRIPT>
    
  </head>
  
  <body>
    <s:form action="system/elecUserAction_importData.do" method="post" enctype="multipart/form-data">
      <br>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
      	<tr>
      		<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif" colspan="4">
				<font face="宋体" size="2"><strong>Excel文件数据导入</strong></font>
			</td>
		</tr>
		<tr>
          <td width="1%" height=30></td>
          <td width="20%"></td>
          <td width="78%"></td>
          <td width="1%"></td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td width="15%" align="center">请选择文件:</td>
          <td width="83%" align="left">
          <s:file name="file" cssStyle="width:365px"></s:file>
          </td>
          <td width="1%"></td>
        </tr>
        <tr height=50><td colspan=4 ></td></tr>
	    <tr height=2><td colspan=4 background="${pageContext.request.contextPath }/images/b-info.gif"></td></tr>
	    <tr height=10><td colspan=4 ></td></tr>
        <tr>
          <td align="center" colspan=4>
          	  <s:submit name="import" value="导入" cssStyle="width: 60px; font-size:12px; color:black; height=22"></s:submit>
	          <INPUT type="button"  name="Reset1" id="save"  value="关闭"  onClick="window.opener.reload(); window.close();" style="width: 60px; font-size:12px; color:black; height=22">
          </td>
        </tr>
      </table>
    </s:form>
  </body>
</html>
