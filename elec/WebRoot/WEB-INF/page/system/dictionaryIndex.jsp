<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<title>系统设置</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript">
		
			
			
		function changetype(){
		
		  if(document.Form1.keyword.value=="jerrynew"){
		    
		     
		  	 var textStr="<input type=\"text\" name=\"keywordname\" maxlength=\"50\" size=\"24\"> ";
		     document.getElementById("newtypename").innerHTML="类型名称：";
		     document.getElementById("newddlText").innerHTML=textStr;
		     
		     /**
		     *使用Ajax框架进行异步操作
		     *	使用ajax引擎的send方法，发送的是form1表单中的属性值
		     *	通过链接system/elecSystemDDLAction_edit.do访问后台数据，获取结果放到dictiontoryEdit.jsp中,
		     	用dictionnaryEdit.jsp的内容替换到dictionnaryIndex.jsp的form2中
		     */
		     
		     Pub.submitActionWithForm('Form2','system/elecSystemDDLAction_edit.do','Form1');
		    
		  }else{
		    
		    var textStr="";
		    document.getElementById("newtypename").innerHTML="";
		    document.getElementById("newddlText").innerHTML=textStr;
		     
		    Pub.submitActionWithForm('Form2','system/elecSystemDDLAction_edit.do','Form1');
		  }  
	   }
	   
     function saveDict(){
	      
	      if(document.Form1.keyword.value=="jerrynew"){
	          if(Trim(document.Form1.keywordname.value)==""){
	             alert("请输入类型名称");
	             return false;
	          }
	          
	         var allkeywords= document.Form1.keyword;
	         for(var i=0;i<allkeywords.length;i++){
	    
	            if(allkeywords[i].value==Trim(document.Form1.keywordname.value)){           

	               alert("已存在此类型名称,请重新输入");
	               return false;
	             }
	             
	         }
	         
	          document.Form2.keywordname.value=document.Form1.keywordname.value;
	          document.Form2.typeflag.value="new";
	          
	      }else{
	      
	          document.Form2.keywordname.value=document.Form1.keyword.value;
	          document.Form2.typeflag.value="add";	
	      }
	      var tbl=document.getElementById("dictTbl");
	      for (i=1;i<tbl.rows.length;i++){        
		   	  	var name = tbl.rows[i].cells[1].children.item(0).value;
		   	  	//alert(name);
		   	  	if(Trim(name)==""){
		   	  	    alert("名称不能为空！");
		   	  	    
		   	  	    return false;
	   	  	    }
	   	  }
	   	  for(k=1;k<=tbl.rows.length-2;k++)
		  {
	 	  	for(m=k+1;m<=tbl.rows.length-1;m++)
	 	  	{     
		  	  	var name1 = tbl.rows[k].cells[1].children.item(0).value;
		  	  	var name2 = tbl.rows[m].cells[1].children.item(0).value;
		  	  	if(name1 == name2){
		  	  		alert("名称不能相同！"); 
		  	  		 return false;
		        }	
		    }
		  }
	      document.Form2.action="system/elecSystemDDLAction_save.do";
	      document.Form2.submit();     
	}
	  
  
       
  function removecheck(seqid){
     if( !confirm("你确定删除吗?")) return false;
      
      document.Form2.keywordname.value=document.Form1.keyword.value;
      document.Form2.action="removeDict.do?seqid="+seqid;
      document.Form2.submit();
  }       
  
     
     
     
       

 function insertRows(){ 

  var tempRow=0; 
  var tbl=document.getElementById("dictTbl");
  tempRow=tbl.rows.length; 
  var Rows=tbl.rows;//类似数组的Rows 
  var newRow=tbl.insertRow(tbl.rows.length);//插入新的一行 
  var Cells=newRow.cells;//类似数组的Cells 
  for (i=0;i<3;i++)//每行的3列数据 
  { 
     var newCell=Rows(newRow.rowIndex).insertCell(Cells.length); 
     newCell.align="center"; 
     switch (i) 
    { 
      case 0 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"15%\">"+tempRow+"</td>";break; 
      case 1 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"60%\"><input name=\"itemname\" type=\"text\" id=\""+tempRow+"\" size=\"45\" maxlength=25></td>";break; 
      case 2 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"25%\"><a href='javascript:delTableRow(\""+tempRow+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a></td>";break;

    } 
  } 
 } 


function delTableRow(rowNum){ 

   var tbl=document.getElementById("dictTbl");
    
    if (tbl.rows.length >rowNum){ 
      
       tbl.deleteRow(rowNum); 
     
      for (i=rowNum;i<tbl.rows.length;i++)
       {
         tbl.rows[i].cells[0].innerText=i;
         tbl.rows[i].cells[2].innerHTML="<a href='javascript:delTableRow(\""+i+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>";      
      }
   }
} 
    
	 </script>
 </HEAD>
		
	<body>
	 <s:form name="Form1" id="Form1"  method="post" cssStyle="margin:0px;">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" colspan=3 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
						<font face="宋体" size="2"><strong>数据字典维护</strong></font>
					</td>
				</tr>
				<TR height=10><td colspan=3></td></TR>		
				<tr>
					<td class="ta_01" align="right" width="35%" >类型列表：</td>
					<td class="ta_01" align="left"  width="30%" >
						<s:select list="#request.systemList"
								  listKey="keyword" listValue="keyword"
								  headerKey="jerrynew" headerValue=""
								  id="keyword" name="keyword"
								  cssStyle="width:180px" cssClass="bg"
								  onchange="changetype()"
									>
						
						</s:select>
						<!-- 
						<select name="keyword" class="bg" style="width:180px" onchange="changetype()">
						 <option value="jerrynew"></option>
						 
						 <option value="故障类型">故障类型</option>
						 
						 <option value="建筑类型">建筑类型</option>
						 
						 <option value="角色类型">角色类型</option>
						 
						 <option value="设备类型">设备类型</option>
						 
						 <option value="设备状态">设备状态</option>
						 
						 <option value="所属单位">所属单位</option>
						 
						 <option value="图纸类别">图纸类别</option>
						 
						 <option value="项目级别">项目级别</option>
						 
						 <option value="项目类型">项目类型</option>
						 
						 <option value="站点类别">站点类别</option>
						 
						</select>
						 -->
					</td>
						
					 <td class="ta_01"  align="right" width="35%" >					 	    
				    </td>	  		
				</tr>
				
				
				
			    <tr>
			       <td class="ta_01" align="right" width="35%" id="newtypename">类型名称：</td>
				   <td class="ta_01"  align="left" width="30%"  height=20 id="newddlText">
				    <input type="text" name="keywordname" maxlength="25" size=24>	
				   </td>
				   <td class="ta_01"  align="right" width="35%" ></td>
				</tr>
				
				
				<TR height=10><td colspan=3 align="right">
				   <input type="button" name="saveitem" value="添加选项" style="font-size:12px; color:black; height=20;width=80" onClick="insertRows()">
				 </td></TR>   
			</TBODY>
		</table>
		</s:form>
		
 <s:form name="Form2" id="Form2"  method="post" cssStyle="margin:0px;">
    <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0" >
    <tr>
     <td >
	   <table cellspacing="0"   cellpadding="1" rules="all" bordercolor="gray" border="1" id="dictTbl"
		    style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
			
		
						
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
					<td class="ta_01" align="center"  width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编号</td>
					<td class="ta_01" align="center"  width="60%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">名称</td>
					<td class="ta_01" align="center"  width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>					
				</tr>
			    
			   
			     <tr>
				   <td class="ta_01" align="center"  width="20%">1</td>
				   <td class="ta_01" align="center"  width="60%">
				   <input name="itemname" type="text"  size="45" maxlength="25"></td>
				   <td class="ta_01" align="center"  width="20%"></td>
				</tr>
	          
	            
			
	     </table>
	   </td>
	 </tr>
  <tr>
     <td >   
	 </td>
 </tr>
 <TR height=10><td colspan=3></td></TR>
  <tr>
     <td align="center" colspan=3>
       <input type="button" name="saveitem" value="保存" style="font-size:12px; color:black; height=20;width=50" onClick="return saveDict();">
	 </td>
 </tr>
 
       <input type="hidden" name="keywordname" >
       <input type="hidden" name="typeflag" >
	 
  </table>
   
    
   
  </s:form>
  </body>
</HTML>


