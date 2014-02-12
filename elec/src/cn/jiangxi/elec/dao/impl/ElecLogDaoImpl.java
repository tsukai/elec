package cn.jiangxi.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jiangxi.elec.dao.IElecLogDao;
import cn.jiangxi.elec.domain.ElecLog;

@Repository(IElecLogDao.SERVICE_NAME)
public class ElecLogDaoImpl extends CommonDaoImpl<ElecLog> implements IElecLogDao {
	
}
