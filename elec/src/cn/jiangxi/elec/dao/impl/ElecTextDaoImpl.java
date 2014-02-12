package cn.jiangxi.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecText;

@Repository(IElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements IElecTextDao {
	
}
