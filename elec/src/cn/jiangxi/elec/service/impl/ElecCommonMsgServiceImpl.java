package cn.jiangxi.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.ws.message.StringHeader;

import cn.jiangxi.elec.dao.IElecCommonMsgDao;
import cn.jiangxi.elec.domain.ElecCommonMsg;
import cn.jiangxi.elec.service.IElecCommonMsgService;
import cn.jiangxi.elec.util.StringHelper;
import cn.jiangxi.elec.web.form.ElecCommonMsgForm;

@Transactional(readOnly=true)
@Service(IElecCommonMsgService.SERVICE_NAME)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {

	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao ecmDao;

	/**
	 * 查询所有的待办事宜列表
	 */
	public List<ElecCommonMsgForm> findElecCommonMsgList(
			ElecCommonMsgForm elecCommonMsgForm) {
		String hqlWhere = "";
		Object[] params = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.createDate", "desc");
		List<ElecCommonMsg> list= ecmDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgPOListToVoList(list);
		return formList;
	}

	/**
	 * 将待办事宜的结果信息从PO对象转换成VO对象
	 * @param list PO对象结果集
	 * @return
	 */
	private List<ElecCommonMsgForm> elecCommonMsgPOListToVoList(
			List<ElecCommonMsg> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		for(int i = 0;list != null && i < list.size();i++){
			ElecCommonMsg ecm = list.get(i);
			ElecCommonMsgForm ecmf = new ElecCommonMsgForm();
			ecmf.setComID(ecm.getComID());
			ecmf.setStationRun(ecm.getStationRun());
			ecmf.setDevRun(ecm.getDevRun());
			ecmf.setCreateDate(String.valueOf(ecm.getCreateDate() != null ? ecm.getCreateDate() : ""));
			formList.add(ecmf);
		}
		return formList;
	}
	
	/**
	 * 保存待办事宜信息
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm) {
		//VO对象转换成PO对象
		ElecCommonMsg ecm = this.elecCommonMsgVOToPO(elecCommonMsgForm);
		ecmDao.save(ecm);
	}
	
	/**
	 * 页面传递的待办事宜信息从VO对象转换成PO对象
	 * @param elecCommonMsgForm
	 * @return
	 */
	private ElecCommonMsg elecCommonMsgVOToPO(
			ElecCommonMsgForm elecCommonMsgForm) {
		ElecCommonMsg ecm = new ElecCommonMsg();
		if(elecCommonMsgForm != null){
			ecm.setDevRun(elecCommonMsgForm.getDevRun());
			ecm.setStationRun(elecCommonMsgForm.getStationRun());
			ecm.setCreateDate(new Date());
		}
		return ecm;
	}

	/**
	 * 通过当前日期查询待办事宜列表
	 */
	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate() {
		//获取当前日期YYYY-MM-DD
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String currentDate = date.toString();
		List<Object[]> list = ecmDao.findElecCommonMsgListByCurrentDate(currentDate);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgObjectListToVO(list);
		return formList;
	}

	/**
	 * 将待办事宜查询的结果又object数组转换成VO对象
	 * @param list
	 * @return
	 */
	private List<ElecCommonMsgForm> elecCommonMsgObjectListToVO(
			List<Object[]> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm ecmf = null;
		for(int i = 0;list != null && i < list.size();i++){
			Object[] object = list.get(i);
			ecmf = new ElecCommonMsgForm();
			ecmf.setStationRun(object[0].toString());
			ecmf.setDevRun(object[1].toString());
			formList.add(ecmf);
		}
		return formList;
	}

}
