package cn.jiangxi.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jiangxi.elec.dao.IElecLogDao;
import cn.jiangxi.elec.domain.ElecLog;
import cn.jiangxi.elec.domain.ElecUser;
import cn.jiangxi.elec.service.IElecLogService;
import cn.jiangxi.elec.web.form.ElecLogForm;

@Transactional(readOnly=true)
@Service(IElecLogService.SERVICE_NAME)
public class ElecLogServiceImpl implements IElecLogService {

	@Resource(name=IElecLogDao.SERVICE_NAME)
	private IElecLogDao elDao;

	/**
	 * 保存日志信息
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecLog(HttpServletRequest request, String details) {
		//实例化ELecLog
		ElecLog log = new ElecLog();
		//往PO对象赋值
		request.getRemoteHost();
		log.setIpAddress(request.getRemoteAddr());
		log.setOpeName(((ElecUser)request.getSession().getAttribute("globle_user")).getUserName());
		log.setOpeTime(new java.sql.Timestamp(new Date().getTime()));
		log.setDetails(details);
		//执行保存操作
		elDao.save(log);
	}

	/**
	 * 使用查询条件查询日志信息列表
	 */
	public List<ElecLogForm> findElecLogListByCondition(ElecLogForm elecLogForm) {
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecLogForm != null && elecLogForm.getOpeName() != null && 
				!"".equals(elecLogForm.getOpeName())){
			hqlWhere += " and o.opeName like ?";
			paramsList.add("%"+elecLogForm.getOpeName()+"%");
		}
		Object[] params = paramsList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.opeTime", "desc");
		List<ElecLog> list = elDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecLogForm> formLIst = this.elecLogPOListToVOList(list);
		return formLIst;
	}

	/**
	 * 日志信息列表PO转VO
	 * @param list
	 * @return
	 */
	private List<ElecLogForm> elecLogPOListToVOList(List<ElecLog> list) {
		List<ElecLogForm> formList = new ArrayList<ElecLogForm>();
		ElecLogForm elf = null;
		for (int i = 0;list != null &&  i < list.size(); i++) {
			ElecLog el = list.get(i);
			elf = new ElecLogForm();
			elf.setLogID(el.getLogID());
			elf.setIpAddress(el.getIpAddress());
			elf.setDetails(el.getDetails());
			elf.setOpeName(el.getOpeName());
			elf.setOpeTime(String.valueOf(el.getOpeTime()!=null ? el.getOpeTime() : ""));
			formList.add(elf);
		}
		return formList;
	}

	/**
	 * 通过日志ID删除日志列表信息
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecLogByLogIDs(ElecLogForm elecLogForm) {
		//1
		//elDao.deleteObjectByIds(elecLogForm.getLogid());
		//2
		String[] lodIDs = elecLogForm.getLogID().replaceAll(" ", "").split(",");
		elDao.deleteObjectByIds(lodIDs);
	}

}
