--角色信息
insert into elec_systemddl(SeqID,Keyword,DdlCode,DdlName) values(23,'角色类型',1,'系统管理员');
insert into elec_systemddl(SeqID,Keyword,DdlCode,DdlName) values(24,'角色类型',2,'高级管理员');
insert into elec_systemddl(SeqID,Keyword,DdlCode,DdlName) values(25,'角色类型',3,'中级管理员');
insert into elec_systemddl(SeqID,Keyword,DdlCode,DdlName) values(26,'角色类型',4,'业务用户');
insert into elec_systemddl(SeqID,Keyword,DdlCode,DdlName) values(27,'角色类型',5,'一般用户');
insert into elec_systemddl(SeqID,Keyword,DdlCode,DdlName) values(28,'角色类型',6,'普通用户');
--数据字典
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (5, '性别', 1, '男');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (6, '性别', 2, '女');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (7, '是否在职', 1, '是');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (8, '是否在职', 2, '否');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (18, '所属单位', 1, '北京');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (19, '所属单位', 2, '上海');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (20, '所属单位', 3, '天津');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (21, '所属单位', 4, '深圳');
INSERT INTO elec_systemddl (SeqID, Keyword, DdlCode, DdlName) VALUES (22, '所属单位', 5, '青岛');

--内置系统管理员，密码admin
INSERT INTO elec_user (UserID, JctId, UserName, LogonName, LogonPwd, SexId, Birthday, Address, ContactTel, Email, Mobile, IsDuty, OnDutyDate, OffDutyDate, remark) VALUES ('ff8080814401d057014401e3d3280001', '1', '超级管理员', 'admin', '21232F297A57A5A743894A0E4A801FC3', '1', null, '', '', '', '', '1', null, null, '');

--角色权限信息
INSERT INTO elec_role_popedom (RoleID, Popedomcode, remark) VALUES ('1', 'abcdefghijklmn', null);
INSERT INTO elec_user_role (SeqID, UserID, RoleID, remark) VALUES (11, 'ff8080814401d057014401e3d3280001', '1', null);
