package cn.jiangxi.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.service.IElecTextService;
import cn.jiangxi.elec.web.form.ElecTextForm;

@Transactional(readOnly=true)
@Service(IElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements IElecTextService {

	@Resource(name=IElecTextDao.SERVICE_NAME)
	private IElecTextDao etDao;

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText et) {
		etDao.save(et);
	}

	/**
	 * 使用查询条件，查询列表集合（不分页）
	 */
	public List<ElecText> findCollectionByConditionNoPage(ElecTextForm etf) {
		/**
		 * 组织hql语句的where条件
		 * select * from elec_text o where 1 = 1		放置DAO层
		 * and o.textName like '%张%' 		放置Service层
		 * and o.textRemark like '%李%'
		 * order by o.textDate desc,o.textName as c
		 */
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(etf != null && StringUtils.isNotBlank(etf.getTextName())){
			hqlWhere += " and o.textName like ?";
			paramsList.add("%" + etf.getTextName() + "%");
		}
		
		if(etf != null && StringUtils.isNotBlank(etf.getTextRemark())){
			hqlWhere += " and o.textRemark like ?";
			paramsList.add("%" + etf.getTextRemark() + "%");
		}
		Object[] params = paramsList.toArray();
		//组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.textDate", "desc");
		orderby.put("o.textName", "asc");
		 //查询列表
		List<ElecText> list = etDao.findCollectionByConditionNoPage(hqlWhere
				,params,orderby);
		for(int i = 0;list != null && i < list.size();i++){
			ElecText et = list.get(i);
			System.out.println(et.getTextName()+"   "+et.getTextRemark());
		}
		return null;
	}


}
