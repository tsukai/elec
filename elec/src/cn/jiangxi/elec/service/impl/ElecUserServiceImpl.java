package cn.jiangxi.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jiangxi.elec.dao.IElecSystemDDLDao;
import cn.jiangxi.elec.dao.IElecUserDao;
import cn.jiangxi.elec.domain.ElecUser;
import cn.jiangxi.elec.service.IElecUserService;
import cn.jiangxi.elec.util.GenerateSqlFromExcel;
import cn.jiangxi.elec.util.MD5keyBean;
import cn.jiangxi.elec.util.PageInfo;
import cn.jiangxi.elec.util.StringHelper;
import cn.jiangxi.elec.web.form.ElecUserForm;

@Transactional(readOnly=true)
@Service(IElecUserService.SERVICE_NAME)
public class ElecUserServiceImpl implements IElecUserService {

	@Resource(name=IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;
	
	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	/**
	 * 查询用户列表信息，判断用户姓名是否为空，不为空按照用户姓名组织查询条件
	 * 返回用户结果集对象
	 */
	public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm,HttpServletRequest request) {
		//组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm != null && elecUserForm.getUserName() != null &&
				!elecUserForm.getUserName().equals("")){
			hqlWhere += " and o.userName like ?";
			paramsList.add("%"+elecUserForm.getUserName()+"%");
		}
		Object[] params = paramsList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		//添加分页功能
		PageInfo pageInfo = new PageInfo(request);
		List<ElecUser> list = elecUserDao.findCollectionByConditionWithPage(hqlWhere, params, orderby,pageInfo);
		request.setAttribute("page", pageInfo.getPageBean());
		//List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);
		return formList;
	}

	/**
	 * 用户列表PO转VO
	 * @param list
	 * @return
	 */
	private List<ElecUserForm> elecUserPOListToVOList(List<ElecUser> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm euf = null;
		for (int i = 0;list != null && i < list.size(); i++) {
			ElecUser eu = list.get(i);
			euf = new ElecUserForm();
			euf.setUserID(eu.getUserID());
			euf.setLogonName(eu.getLogonName());
			euf.setUserName(eu.getUserName());
			if(eu.getSexID()!=null && !eu.getSexID().equals("")){
				euf.setSexID(elecSystemDDLDao.findDDLName("性别",eu.getSexID()));
			}else{
				euf.setSexID("");
			}
			euf.setContactTel(eu.getContactTel());
			if(eu.getIsDuty()!=null && !eu.getIsDuty().equals("")){
				euf.setIsDuty(elecSystemDDLDao.findDDLName("是否在职",eu.getIsDuty()));
			}else{
				euf.setIsDuty("");
			}
			formList.add(euf);
		}
		return formList;
	}

	/**
	 * 保存用户信息
	 * 参数VO对象
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUser(ElecUserForm elecUserForm) {
		//Vo对象转换成PO对象
		ElecUser eu = this.elecUserVOToPO(elecUserForm);
		if(elecUserForm != null && (elecUserForm.getUserID() == null || elecUserForm.getUserID().equals(""))){
			elecUserDao.save(eu);
		}else{
			elecUserDao.update(eu);
		}
	}

	/**
	 * 将用户信息从VO对象转换成PO对象
	 * @param elecUserForm
	 * @return
	 */
	private ElecUser elecUserVOToPO(ElecUserForm elecUserForm) {
		ElecUser eu = new ElecUser();
		if(elecUserForm != null){
			if(elecUserForm != null && elecUserForm.getUserID() != null && !elecUserForm.getUserID().equals("")){
				eu.setUserID(elecUserForm.getUserID());
				if(elecUserForm != null && elecUserForm.getOffDutyDate() != null && !elecUserForm.getOffDutyDate().equals("")){
					eu.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
				}
			}
			eu.setJctID(elecUserForm.getJctID());
			eu.setLogonName(elecUserForm.getLogonName());
			eu.setUserName(elecUserForm.getUserName());
			//密码加密处理
			MD5keyBean md5 = new MD5keyBean();
			String pwd = elecUserForm.getLogonPwd();
			//初始化密码
			if(pwd==null || "".equals(pwd)){
				pwd = "000000";
			}
			//两次密码不一致
			if(!"1".equals(elecUserForm.getMd5flag())){
				pwd = md5.getkeyBeanofStr(pwd);
			}
			eu.setLogonPwd(pwd);
			eu.setSexID(elecUserForm.getSexID());
			if(elecUserForm.getBirthday() != null && !elecUserForm.getBirthday().equals("")){
				eu.setBirthday(StringHelper.stringConvertDate(elecUserForm.getBirthday()));
			}
			eu.setContactTel(elecUserForm.getContactTel());
			eu.setAddress(elecUserForm.getAddress());
			eu.setEmail(elecUserForm.getEmail());
			eu.setMobile(elecUserForm.getMobile());
			eu.setIsDuty(elecUserForm.getIsDuty());
			if(elecUserForm.getOnDutyDate() != null && !elecUserForm.getOnDutyDate().equals("")){
				eu.setOnDutyDate(StringHelper.stringConvertDate(elecUserForm.getOnDutyDate()));
			}
			eu.setRemark(elecUserForm.getRemark());
		}
		return eu;
	}

	/**
	 * 使用userID进行查询，获取用户的详细信息。
	 */
	public ElecUserForm findElecUser(ElecUserForm elecUserForm) {
		ElecUser eu = elecUserDao.findObjectById(elecUserForm.getUserID());
		//PO转VO
		ElecUserForm euf = this.elecUserPOToVO(eu);
		return euf;
	}

	/**
	 * 获取用户详细信息，从PO对象转换成VO对象
	 * @param eu
	 * @return
	 */
	private ElecUserForm elecUserPOToVO(ElecUser eu) {
		ElecUserForm euf = new ElecUserForm();
		if(eu != null){
			euf.setUserID(eu.getUserID());
			euf.setAddress(eu.getAddress());
			euf.setBirthday(String.valueOf(eu.getBirthday() != null && !eu.getBirthday().equals("") ? eu.getBirthday() : ""));
			euf.setContactTel(eu.getContactTel());
			euf.setEmail(eu.getEmail());
			euf.setIsDuty(eu.getIsDuty());
			euf.setJctID(eu.getJctID());
			euf.setLogonName(eu.getLogonName());
			euf.setMobile(eu.getMobile());
			euf.setOffDutyDate(String.valueOf(eu.getOffDutyDate() != null && !eu.getOffDutyDate().equals("") ? eu.getOffDutyDate() : ""));
			euf.setOnDutyDate(String.valueOf(eu.getOnDutyDate() != null && !eu.getOnDutyDate().equals("") ? eu.getOnDutyDate() : ""));
			euf.setRemark(eu.getRemark());
			euf.setSexID(eu.getSexID());
			euf.setUserName(eu.getUserName());
			euf.setLogonPwd(eu.getLogonPwd());
			
		}
		return euf;
	}

	/**
	 * 从页面中获取userID的值，根据userID删除用户信息
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecUser(ElecUserForm elecUserForm) {
		String userId = elecUserForm.getUserID();
		elecUserDao.deleteObjectByIds(userId);
	}

	/**
	 * 使用登录名查询数据库库，判断当前登录名在数据库中是否存在
	 * 返回String类型的标识，1为有，2为没有
	 */
	public String checkLogonName(String logonName) {
		String hqlWhere = " and o.logonName = ?";
		Object[] params = {logonName};
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		String checkflag = "";
		if(list != null && list.size() > 0){
			checkflag = "1";
		}else{
			checkflag = "2";
		}
		return checkflag;
	}

	/**
	 * 使用登录名获取用户的详细信息，用于首页登录的校验
	 */
	public ElecUser findElecUserByLogonName(String name) {
		String hqlWhere = " and o.logonName = ?";
		Object[] params = {name};
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		ElecUser user = null;
		if(list != null && list.size() > 0){
			user = list.get(0);
		}
		return user;
	}

	/**
	 * 使用登录名获取当前登录名所具有的权限
	 */
	public String findElecPopedomByLogonName(String name) {
		List<Object> list = elecUserDao.findElecPopedomByLOgonName(name);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0;list != null && i < list.size(); i++) {
			Object obj = list.get(i);
			buffer.append(obj.toString());
		}
		return buffer.toString();
	}

	/**
	 * 使用当前登录名获取所具有的的角色
	 */
	public Hashtable<String, String> findElecRoleByLogonName(String name) {
		List<Object[]> list = elecUserDao.findElecRoleByLogonName(name);
		Hashtable<String, String> ht = null;
		if(list!= null && list.size() > 0){
			ht = new Hashtable<String, String>();
			for (int i = 0; i < list.size(); i++) {
				Object[] objs = list.get(i);
				ht.put(objs[0].toString(), objs[1].toString());
			}
		}
		return ht;
	}

	/**
	 * 获取Excel标题数据形式
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getExcelFieldName() {
		String[] titles = {"登录名","用户姓名","性别","联系电话","是否在职"};
		ArrayList fieldName = new ArrayList();
		for (int i = 0; i < titles.length; i++) {
			fieldName.add(titles[i]);
		}
		return fieldName;
	}

	/**
	 * 获取Excel的数据内容
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getExcelFieldDataList(ElecUserForm elecUserForm) {
		//组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm != null && elecUserForm.getUserName() != null &&
				!elecUserForm.getUserName().equals("")){
			hqlWhere += " and o.userName like ?";
			paramsList.add("%"+elecUserForm.getUserName()+"%");
		}
		Object[] params = paramsList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);		
		//构造报表的数据
		ArrayList fieldData = new ArrayList();
		for (int i = 0;formList != null && i < formList.size(); i++) {
			ArrayList dataList = new ArrayList();
			ElecUserForm euf = formList.get(i);
			dataList.add(euf.getLogonName());
			dataList.add(euf.getUserName());
			dataList.add(euf.getSexID());
			dataList.add(euf.getContactTel());
			dataList.add(euf.getIsDuty());
			fieldData.add(dataList);
		}
		return fieldData;
	}

	/**
	 * 从Excel中读取用户信息数据，保存到数据库表中
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUserWithExcel(ElecUserForm elecUserForm) {
		File flie = elecUserForm.getFile();
		try {
			ArrayList<String[]> list = GenerateSqlFromExcel.generateStationBugSql(flie);
			List<ElecUser> userList = new ArrayList<ElecUser>();
			MD5keyBean md5 = new MD5keyBean();
			for (int i = 0;list != null && i < list.size(); i++) {
				String[] data = list.get(i);
				ElecUser user = new ElecUser();
				user.setLogonName(data[0]);
				user.setLogonPwd(md5.getkeyBeanofStr(data[1]));
				user.setUserName(data[2]);
				user.setSexID(data[3]);
				user.setContactTel(data[4]);
				user.setIsDuty(data[5]);
				userList.add(user);
			}
			elecUserDao.saveObjectByCollection(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
