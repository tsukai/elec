package cn.jiangxi.elec.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jiangxi.elec.dao.IElecSystemDDLDao;
import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecSystemDDL;
import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.service.IElecSystemDDLService;
import cn.jiangxi.elec.service.IElecTextService;
import cn.jiangxi.elec.web.form.ElecSystemDDLForm;
import cn.jiangxi.elec.web.form.ElecTextForm;

@Transactional(readOnly=true)
@Service(IElecSystemDDLService.SERVICE_NAME)
public class ElecSystemDDLServiceImpl implements IElecSystemDDLService {

	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao esdDao;

	/**
	 * 查询数据类型关键字，去重复值
	 * 返回数据类型列表
	 */
	public List<ElecSystemDDLForm> findKeyword() {
		List<Object> list = esdDao.findKeywork();
		List<ElecSystemDDLForm> formList = this.elecSystemDDLObjectToVO(list);;
		return formList;
	}

	/**
	 * 将查询的数据类型列表由object对象转换成vo对象 
	 */
	private List<ElecSystemDDLForm> elecSystemDDLObjectToVO(List<Object> list) {
		List<ElecSystemDDLForm> formList = new ArrayList<ElecSystemDDLForm>();
		ElecSystemDDLForm esdf = null;
		for(int i = 0;list != null && i < list.size();i++){
			esdf = new ElecSystemDDLForm();
			esdf.setKeyword(list.get(i).toString());
			formList.add(esdf);
		}
		return formList;
	}

	/**
	 * 根据选中的数据类型，查询对应的数据项
	 * 返回对应数据项的VO集合
	 */
	public List<ElecSystemDDLForm> findElecSystemDDLListByKeyword(String keyword) {
		List<ElecSystemDDL> list = findSystemDDLListByCondition(keyword);
		List<ElecSystemDDLForm> formList = this.elecSystemDDLPOListToVOList(list);
		return formList;
	}

	/**
	 * 根据选中的数据类型，查询对应的数据项
	 */
	private List<ElecSystemDDL> findSystemDDLListByCondition(String keyword) {
		String hqlWhere = " and o.keyword = ?";
		Object[] params = {keyword};
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");
		List<ElecSystemDDL> list = esdDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		return list;
	}

	/**
	 * 数据项的集合列表从PO对象转换成VO对象 
	 * @param list
	 * @return
	 */
	private List<ElecSystemDDLForm> elecSystemDDLPOListToVOList(
			List<ElecSystemDDL> list) {
		List<ElecSystemDDLForm> formList = new ArrayList<ElecSystemDDLForm>();
		ElecSystemDDLForm esdf = null;
		for (int i = 0;list != null && i < list.size(); i++) {
			esdf = new ElecSystemDDLForm();
			ElecSystemDDL esd = list.get(i);
			esdf.setSeqID(String.valueOf(esd.getSeqID()));
			esdf.setKeyword(esd.getKeyword());
			esdf.setDdlCode(String.valueOf(esd.getDdlCode()));
			esdf.setDdlName(esd.getDdlName());
			formList.add(esdf);
		}
		return formList;
	}

	/**
	 * 保存数据字典，保存时包括数据类型，数据项编号，数据项名称
	 * elecSystemDDLForm存放页面传递的表单值
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm) {
		//获取页面传递的表单值
		//获取数据类型
		String keyword = elecSystemDDLForm.getKeywordname();
		//获取判断标识
		String typeflag = elecSystemDDLForm.getTypeflag();
		//获取需要保存的数据项的名称
		String[] itemname = elecSystemDDLForm.getItemname();
		//如果是新增数据类型的操作，此时typeflag=new
		if(typeflag != null && typeflag.equals("new")){
			//保存数据字典
			saveSystemDDLWithParams(keyword, itemname);
		}
		//否则表示在原有的数据类型中进行修改和编辑，此时typeflag=add
		else{
			//删除原有的数据项
			List<ElecSystemDDL> list = findSystemDDLListByCondition(keyword);
			esdDao.deleteObjectByCollection(list);
			//保存新的数据项
			saveSystemDDLWithParams(keyword, itemname);
		}
	}

	/**
	 * 保存数据字典
	 * @param keyword 数据类型
	 * @param itemname 数据项名称数组
	 */
	
	private void saveSystemDDLWithParams(String keyword, String[] itemname) {
		List<ElecSystemDDL> list = new ArrayList<ElecSystemDDL>();
		ElecSystemDDL esd = null;
		for (int i = 0;itemname != null && i < itemname.length; i++) {
			esd = new ElecSystemDDL();
			esd.setKeyword(keyword);
			esd.setDdlName(itemname[i]);
			esd.setDdlCode(i + 1);
			list.add(esd);
//			esdDao.save(esd);
		}
		esdDao.saveObjectByCollection(list);
	}

}
