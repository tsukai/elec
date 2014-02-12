--测试表
create table Elec_Text(
	textID varchar(50) not null,
	textName varchar(50),
	textDate datetime,
	textRemark varchar(500)
);

--待办事宜
--主键ID，站点运行情况，设备运行情况，创建日期，创建人
create table Elec_CommonMsg(
	ComId varchar(50) not null,
	StationRun	varchar(1000) null,
	DevRun	varchar(1000) null,
	CreateDate datetime null,
	--CreateEmpCode	varchar(50) null
);

--数据字典（包含角色）
create table elec_systemDDL(
	SeqID	int not null,
	Keyword	varchar(20) null,  --查新关键字
	DdlCode	int null,			--数据字典的code
	DdlName	varchar(50) null	--数据字典的value
);

--用户表
create table elec_user(
	UserID	varchar(50) not null, --主键id
	JctId	varchar(50) null,	--所属单位code
	UserName	varchar(50) null, --用户姓名
	LogonName	varchar(50) null, --登录名
	LogonPwd	varchar(50) null, --密码
	SexId	varchar(10) null, --性别
	Birthday	DATETIME null, --出生日期
	Address	varchar(100) null, --联系地址
	ContactTel	varchar(50) null, --联系电话
	Email	varchar(50) null, --电子邮箱
	Mobile	varchar(50) null, --手机
	IsDuty	varchar(10) null, --是否在职
	OnDutyDate	DATETIME null, --入职时间
	OffDutyDate	DATETIME null, --离职时间
	remark	varchar(500) null, --备注
	--IsDelete	varchar(10) null, --是否删除
	--CreateEmpId	varchar(50) null, --创建人ID
	--CreateDate	DATETIME null, --创建时间
	--LastEmpId	varchar(50) null, --修改人ID
	--LastDate	DATETIME null, --修改时间
);

--权限表（使用xml配置）

--用户角色关联表
create table Elec_User_Role(
	SeqID int not null, --主键id
	UserID varchar(50) null, --用户id
	RoleID varchar(50) null, --角色id
	remark varchar(500) null, --备注
	--CreateEmpCode varchar(50) null, --创建人
	--CreateDate datetime null --创建时间
);

--角色权限关联表
create table Elec_Role_Popedom(
	RoleID varchar(50) not null, --主键ID
	Popedomcode varchar(50) null, --配置web文件中权限的编码code的字符串连接
	remark varchar(500) null, --备注
	--IsDelete varchar(50) null, --是否删除
	--CreateEmpCode varchar(50) null, --创建人
	--CreateDate datetime null --创建时间
);

--日志管理
create table elec_log(
	LogID varchar(50) not null, --主键ID
	IpAddress varchar(50), --ip地址
	OpeName varchar(50), --操作人
	OpeTime datetime, --操作时间
	Details varchar(500) --操作明细
);