package cn.jiangxi.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.jiangxi.elec.domain.ElecSystemDDL;
import cn.jiangxi.elec.domain.ElecUser;
import cn.jiangxi.elec.util.PageInfo;

public interface ICommonDao<T> {
	public void save(T t);
	public void update(T t);
	public T findObjectById(Serializable id);
	public void deleteObjectByIds(Serializable... ids);
	public void deleteObjectByCollection(Collection<T> list);
	public List<T> findCollectionByConditionNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderby);
	public List<T> findCollectionByConditionWithPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderby,
			PageInfo pageInfo);
	public void saveObjectByCollection(Collection<T> list);
}
