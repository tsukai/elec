package cn.jiangxi.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jiangxi.elec.dao.IElecSystemDDLDao;
import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecSystemDDL;
import cn.jiangxi.elec.domain.ElecText;

@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL> implements IElecSystemDDLDao {


	/**
	 * 查询关键字，去重复
	 */
	public List<Object> findKeywork() {
		String hql = "select distinct o.keyword from ElecSystemDDL o";
		List<Object> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 使用数据类型和数据项的编号获取数据项的名称
	 */
	public String findDDLName(String keyword, String ddlCode) {
		String hql = "from ElecSystemDDL o where o.keyword = '" + keyword +
				"' and o.ddlCode = '" + ddlCode +"'";
		List<ElecSystemDDL> list = this.getHibernateTemplate().find(hql);
		String ddlName = "";
		if(list != null && list.size() > 0){
			ElecSystemDDL esd = list.get(0);
			ddlName = esd.getDdlName();
		}
		return ddlName;
	}
	
}
