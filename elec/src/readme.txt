项目框架搭建总结
	1、建立Web工程
		*导入需要的jar包
		 db:连接数据库的驱动包
		 hibernate：使用hibernate的jar包
		 jstl:java的标准标签库
		 junit：测试用到的jar包
		 spring：使用spring的jar包
		 struts2：使用Struts2的jar包
		*项目体系分层
		 cn.jiangxi.elec.container:自定义的spring容器，用于在控制层调用操作业务层
		 cn.jiangxi.elec.dao:项目的dao层，负责连接数据库的操作
		 cn.jiangxi.elec.domain:封装实体对象（PO对象），对应连接数据库的映射文件
		 cn.jiangxi.elec.service:项目的service层，负责操作各个功能模块的业务逻辑
		 cn.jiangxi.elec.util:封装系统使用的工具类的方法和属性
		 cn.jiangxi.elec.web.action:系统的控制层，负责页面和项目后台的跳转
		 cn.jiangxi.elec.web.form：封装值对象（VO对象），对应页面传递的表单值得属性
		 junit：测试类
		
		*配置文件
		 放置到src目录下
		 beans.xml:spring的配置文件
		 hibernate.cfg.xml:hibernate的配置文件
		 struts.xml：struts2的配置文件
	2、建立持久层
		*在cn.jiangxi.elec.domain中创建持久层的对象ElecText.java
		*对应javabean的PO对象，创建与数据库表的映射文件ElecText.hbm.xml文件
		*创建连接数据库的hibernate配置文件，hibernate.cfg.xml
	3、DAO层的搭建
		*在cn.jiangxi.elec.dao的目录下创建ICommonDao接口
		*在cn.jiangxi.elec.dao.impl目录下创建ICommonDao的实现类CommonDaoImpl
		*在cn.jiangxi.elec.dao目录下创建IElecTextDao接口，继承ICommonDao
		*在cn.jiangxi.elec.dao.impl目录下创建ElecTextDaoImpl，实现IElecTextDao
		*创建spring的配置文件，beans.xml
	4、建立Service层
		*在cn.jiangxi.elec.service目录下创建IElecTextService接口
		*在cn.jiangxi.elec.service.impl目录下创建ElecTextServiceImpl，实现IElecTextService
		
	5、建立控制层（action）
		*导入系统需要的css/script/images
		 以及需要的jsp的包menu（系统登录、首页显示的jsp页面，system（系统管理中的功能页面）。
		*在cn.jiangxi.elec.web.action目录下创建ElecTextAction
		*在cn.jiangxi.elec.web.form目录下创建ElecTextForm对象
		*在cn.jiangxi.elec.web.action目录下创建BaseAction类
		*自定义Spring容器
		   在cn.jiangxi.elec.container目录下建立ServiceProviderCore类
		   在cn.jiangxi.elec.container目录下建立ServiceProvider类（服务提供者）
		*建立struts的配置文件，struts.xml
		 同时在web.xml文件中添加struts的过滤器
		 
		 
数据字典
主键ID		数据类型		数据项编号	数据项名称
1			所属单位		1			北京
2			所属单位		2			天津
3			性别			1			男
4			性别			2			内存
5			是否在职		1			是
6			是否在职		2			否

特点：
	*在数据类型相同的情况下，数据项编号不能重复，且从1开始，按照顺序进行排列
	*在数据类型形同的情况下，数据项的名称不能重复
	*数据字典中，数据类型，数据项编号，数据项名称不能为空。
	
操作：
	1、查询数据类型，使用distinct关键字，去掉护具类型的重复值
	2、PO转VO
	
用户管理：
操作
	1、查询用户列表，获取用户信息
		*从左侧列表进入页面时，查询所有用户信息
		*在userIndex.jsp点击查询的时候，需要将用户姓名组成查询条件，查询用户信息
	2、查询集合对象中的PO对象转换成VO对象
	3、将VO对象中的性别和是否在职，从数据字典中的数据项编号获取到数据项名称。
		*条件：数据项编号，数据类型
		*值：数据项名称
		
新增用户
	1、将性别、所属单位、是否在职从数据库字典中获取相应结果列表
	   使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称
	2、将PO对象转换成VO对象

	1、获取页面中传递的需要进行保存的用户值
	2、VO对象转换成PO对象
	3、执行保存。
	
编辑用户：
	1、点击编辑的时候从页面中获取userID，使用userID进行查询，获取用户详细信息
	2、PO对象转换成VO对象
	3、使用值栈的形式存放VO对象
	4、修改用户页面的功能，需将性别、所属单位、是否在职从数据字典中获取相应结果集列表
	    使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称
	5、PO转VO
	
	Update:
		1、获取页面中传递的需要进行保存的用户值
		2、VO对象转换成PO对象
		3、从VO对象中获取userID的值，如果userID有值，执行update操作，否则执行save操作
	
删除用户：
	1、从VO对象中获取userID
	2、根据userID执行删除操作	

Ajax校验
	校验输入的登录名在数据库中是否存在
		如果存在，不能执行保存，重新输入
		如果不存在可以执行保存
	1、获取登录名的值
	2、根据获取的登录名，组织查询条件，查询用户信息
		*如果值存在，不能执行保存。
		*如果值不存在，可以执行保存。
		
角色：
	1、角色和用户--多对多关系
	2、角色与权限--多对多关系
	3、用户、角色、权限三者之间通过角色做连接的桥梁
  数据库设计：
  	角色表：角色id、角色名称（使用数据字典表进行维护）
  	权限表：权限code、权限名称、父级菜单code、父级菜单名称
  	用户与角色关联表：主键id、用户id、角色id、备注
  	角色和权限关联表：角色id、权限code集合、备注
  	
 操作：
 	1、查询数据字典获得所有的的角色类型列表
 	2、从PO对象转换成VO对象
 	
 	1、查询Function.xml文件，获取权限的集合
 	2、放到XmlJavaBean中

页面权限显示：
	String parentCode = "";
	List<XmlIbject> list = (List<XmlIbject>)request.getAttribute("xmlList");
	for(int i = 0;list != null && i < list.size();i++){
		XmlObject xmlObj = list.get(i);
		if(parentCode.equals(xmlObj.getParentCode())){
			System.out.println(XmlObj.getName());
		}else{
			parentCode = xmlObj.getParentCode();
			System.out.println(xmlObj.getParentName());
			System.out.println(xmlObj.getName());
		}
	}
角色具有的权限
	1、获取页面中角色类型的ID（roleID）	
	2、使用roleID查询该角色所具有的权限
	3、从Function.xml文件中获取所有的权限列表
	4、匹配：选中的roleID获取的权限和系统中所有的权限进行匹配，
		*如果匹配成功，将页面中的复选框选中。
		*如果匹配不成功，则页面中的权限复选框不被选中。
角色分配的用户
	1、获取页面中传递的角色类型ID
	2、使用roleID查询该角色所拥有的用户
	3、从用户表中获取所有的用于列表，且用户需在职
	4、匹配：选中roleID拥有的用户和系统中所有的用户进行匹配
		*如果匹配成功，将页面中的用户复选框选中。
		*如果匹配不成功，则页面中的用户付选中不被选中。
		
	select distinct case eur.roleid
	when '1' then '1' else '0' end as flag,
	eu.username,eu.userid,eu.logonname
	from elec_user eu
	left outer join elec_user_role eur
	on eu.userid = eur.userid
	and eur.roleid='1'
	where eu.isDuty = '1
	
角色权限的保存
	1、roleid：存放当前需要保存的角色id
	2、selectoper[]:存放当前角色所选中的权限
	3、selectuser[]:存放当前角色下所拥有的用户
	*保存角色与权限的关联表
		1、从页面中获取角色ID，权限的集合
		2、使用角色ID查询关联表，如果存在则update，否则save
	*保存用户与角色的关联表
		1、从页面中获取角色id，用户集合
		2、使用角色id删除所有该角色下的用户
		3、插入新选择的用户
		
登录：
	1、使用页面输入的鞥路名和密码获取用户信息
		*获取页面登录的登录名和密码
		*使用登录名查询elec_user表，获取用户信息
			*如果获取的用户信息为空，输入的登录名不存在，返回到登录页面
			*如果获取的用户信息不为空，则继续验证
		*从查询得到的用户信息，获取用户的密码，与登录页面中的密码进行匹配
			*如果匹配成功，则继续执行
			*如果匹配不成功，返回登录页面
		*将或渠道的用户信息存放到session中。
	2、使用页面输入的登录名获取当前登录名具有的权限 --将权限存放到string类型的字符串，存放到session中
		*如果当前登录名没有权限，当前用户不能登录系统，则返回到登录页面
		*如果当前登录名有权限，则继续执行
		select erp.popedomcode 
		from elec_role_popedom erp 
		left join elec_user_role eur on erp.roleid = eur.roleid 
		inner join elec_user eu on eur.userid = eu.userid 
		and eu.logonname = 'weiyan'
		where eu.isduty = '1'
	3、使用页面输入的登录名获取当前登录名具有的角色
		使用登录名获取当前登录名具有的角色，获取值得形式
		key=2 value=高级管理员
		key=3 value=中级管理员
		。。。
		将角色存放到hashtable中，放到session中
		*如果当前登录名没有分配角色，则不能登录系统，返回登录页面
		*如果分配的角色，则继续执行
		select  es.ddlcode ,es.ddlname
		from elec_user_role eur 
		inner join elec_user eu on eur.userid = eu.userid 
		and eu.logonname = 'weiyan'
		left join elec_systemddl es on eur.roleid = es.ddlcode 
		and es.keyword = '角色类型'
		where eu.isduty = '1'
	1,2,3均验证通过则登录成功。
	
进度条：
	文件的上查询和下载
	excel，word等文件的导入和导出
	操作大批量数据
	远程操作数据
	
验证码：
	1、从session中获取验证码的数值，
		*如果取到的值为空或不存在，跳转到index.jsp
		*如果取到相应数值，则继续校验
	2、从登陆页面获取验证码的数值
		*如果获取到的数值为空或者不存在，则跳转到index.jsp
		*如果获取到相应的数值，则继续进行校验
	3、比较两个验证码
		*如果不匹配，则跳转到index.jsp
		*如果匹配则校验成功
		
记住我：
	1、获取登陆页面的登录名和密码
	2、创建2个cookie分别保存登录名和密码
	3、设置cookie的有效路径，项目跟路径
	4、设置cookie的有效时长
		*如果页面中记住我的功能被选中，设置cookie的有效时长
		*如果页面中的记住我的功能不被选中，则清空cookie的有效时长
	5、将两个cookie的对象存放到response中。

乱码问题：
	URLEncoder.encode(logonName, "UTF-8")
	URLDecoder.decode( cookie.getValue(), "UTF-8");
	
添加过滤器：
	过滤 *.do和*.jsp
	
日志管理：
	1、对系统的安全、性能进行统一的维护和管理，保证系统健壮
	2、对系统中的业务数据进行统一的维护和管理，保证录入数据的准确性和时效性
	3、系统中添加日志的模块
		*登录模块，使用日志管理记录系统中用户的使用情况
		*用户管理模块，新增，修改，删除用户时，可向日志管理中调价数据，管理数据的存储过程
	4、日志管理，由系统管理员进行统一的维护和管理	
管理日志的方式：
	1、使用log4j在系统的硬盘上生成*.txt文件维护系统的日志
	2、使用数据库表的形式，维护系统日志
	
分页：
	使用js封装的ajax框架（userIndex.jsp和userList.jsp）
	用户管理
		1、在userIndex.jsp中添加2个form（fomr1和form2）。
		2、新建一个userList.jsp，将userIndex.jsp中form2的内容添加到userList.jsp
		3、分页实现的原理，提交form1表单中的参数，后台查询数据库，返回相应的查询结果在userList.jsp中显示
			将userList.jsp的内容放到userIndex.jsp中的form2
	操作：PageInfo.java 、PageBean.java、page.js、userIndex.jsp、userList.jsp
Excel导出
			