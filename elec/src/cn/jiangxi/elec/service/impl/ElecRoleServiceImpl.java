package cn.jiangxi.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jiangxi.elec.dao.IElecRolePopedomDao;
import cn.jiangxi.elec.dao.IElecSystemDDLDao;
import cn.jiangxi.elec.dao.IElecUserDao;
import cn.jiangxi.elec.dao.IElecUserRoleDao;
import cn.jiangxi.elec.domain.ElecRolePopedom;
import cn.jiangxi.elec.domain.ElecUserRole;
import cn.jiangxi.elec.service.IElecRoleService;
import cn.jiangxi.elec.util.StringHelper;
import cn.jiangxi.elec.util.XmlObject;
import cn.jiangxi.elec.web.form.ElecRoleForm;
import cn.jiangxi.elec.web.form.ElecUserForm;

@Transactional(readOnly=true)
@Service(IElecRoleService.SERVICE_NAME)
public class ElecRoleServiceImpl implements IElecRoleService {

	@Resource(name=IElecUserRoleDao.SERVICE_NAME)
	private IElecUserRoleDao elecUserRoleDao;
	
	@Resource(name=IElecRolePopedomDao.SERVICE_NAME)
	private IElecRolePopedomDao elecRolePopedomDao;

	/**
	 * 从Function.xml中查询系统所有的功能权限
	 * 存放到XmlObject对象中
	 */
	public List<XmlObject> readXml() {
		File file = getFunctionXmlFile();
		List<XmlObject> list = parseXmlFile(file);
		return list;
	}
	
	/**
	 * 获取权限配置文件Function.xml
	 * @return
	 */
	private File getFunctionXmlFile() {
		ServletContext context = ServletActionContext.getServletContext();
		String realPath = context.getRealPath("/WEB-INF/classes/Function.xml");
		File file = new File(realPath);
		return file;
	}

	private List<XmlObject> parseXmlFile(File file) {
		List<XmlObject> list = new ArrayList<XmlObject>();
		//使用dom4j读取配置文件
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			Element rootEle = document.getRootElement();
			XmlObject xmlObj = null;
			/**
			 * Function:对应配置文件中的元素节点
			 * FunctionCode：对应配置文件中Function节点下的FunctionCode元素
			 * FunctionName：对应配置文件中Function节点下的FunctionName元素
			 * ParentCode：对应配置文件中Function节点下的ParentCode元素
			 * ParentName：对应配置文件中Function节点下的ParentName元素
			 */
			for(Iterator<Element> it = rootEle.elementIterator("Function");it.hasNext();){
				Element ele = it.next();
				xmlObj = new XmlObject();
				xmlObj.setCode(ele.elementText("FunctionCode"));
				xmlObj.setName(ele.elementText("FunctionName"));
				xmlObj.setParentCode(ele.elementText("ParentCode"));
				xmlObj.setParentName(ele.elementText("ParentName"));
				list.add(xmlObj);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 使用角色ID查询该角色下具有的权限，并与系统中所哦哟偶的权限进行匹配
	 * 返回匹配完成的权限集合
	 */
	public List<XmlObject> readEditXml(String roleID) {
		//使用roleID查询该角色下具有的权限
		ElecRolePopedom erp = elecRolePopedomDao.findObjectById(roleID);
		String popedom = "";
		if(erp != null){
			popedom = erp.getPopedomcode();
		}
		///与系统中的所有权限进行匹配
		List<XmlObject> list = this.readXmlByPopedom(popedom);
		return list;
	}

	/**
	 * 读取配置文件获取系统中所有的权限，与当期角色具有的权限进行匹配
	 * @param popedom
	 * @return
	 */
	private List<XmlObject> readXmlByPopedom(String popedom) {
		//查询所有权限
		List<XmlObject> list = readXml();
		for (int i = 0; list != null && i < list.size(); i++) {
			XmlObject xmlObj = list.get(i);
			if(popedom.contains(xmlObj.getCode())){
				xmlObj.setFlag("1");
			}else{
				xmlObj.setFlag("0");
			}
		}
		return list;
	}

	/**
	 * 查询用户列表集合，获取系统比重所有的用户，并与该角色具有的用户进行匹配
	 * 匹配成功设置flag==1，页面复选框选中
	 * 匹配不成功设置flag==0，页面复选框不选中
	 */
	public List<ElecUserForm> fin1dElecUserListByRoleID(String roleID) {
		List<Object[]> list = elecUserRoleDao.fin1dElecUserListByRoleID(roleID);
		List<ElecUserForm> formList = this.elecUserRoleObjectListToVOList(list);
		return formList;
	}

	/**
	 * 将获取到的用户列表信息从Object对象转换成VO对象
	 * @param list
	 * @return
	 */
	private List<ElecUserForm> elecUserRoleObjectListToVOList(
			List<Object[]> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm euf = null;
		for (int i = 0;list != null &&  i < list.size(); i++) {
			Object[] objs = list.get(i);
			euf = new ElecUserForm();
			euf.setFlag(objs[0].toString());
			euf.setUserID(objs[1].toString());
			euf.setUserName(objs[2].toString());
			euf.setLogonName(objs[3].toString());
			formList.add(euf);
			
		}
		return formList;
	}

	/**
	 * 保存角色和权限的关联表
	 * 保存用户和角色的关联表
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveRole(ElecRoleForm elecRoleForm) {
		//保存角色和权限的关联表
		this.saveRolePopedom(elecRoleForm);
		//保存用户和角色的关联表
		this.saveUserRole(elecRoleForm);
	}

	/**
	 * 保存用户和角色的关联表
	 * @param elecRoleForm
	 */
	private void saveUserRole(ElecRoleForm elecRoleForm) {
		//角色ID
		String roleid = elecRoleForm.getRoleid();
		//用户集合
		String[] selectuser = elecRoleForm.getSelectuser();
		//以roleid查询关联表，获取集合对象
		String hqlWhere = " and o.roleID = ?";
		Object[] params = {roleid};
		List<ElecUserRole> list = elecUserRoleDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		//以roleid为条件删除用户
		elecUserRoleDao.deleteObjectByCollection(list);
		//新增
		List<ElecUserRole> list1 = new ArrayList<ElecUserRole>();
		for(int i = 0;selectuser != null && i < selectuser.length;i++){
			String userID = selectuser[i];
			ElecUserRole eur = new ElecUserRole();
			eur.setRoleID(roleid);
			eur.setUserID(userID);
			list1.add(eur);
		}
		elecUserRoleDao.saveObjectByCollection(list1);
	}

	/**
	 * 保存角色和权限的关联表
	 * @param elecRoleForm
	 */
	private void saveRolePopedom(ElecRoleForm elecRoleForm) {
		//角色ID
		String roleid = elecRoleForm.getRoleid();
		//权限code集合
		String[] selectoper = elecRoleForm.getSelectoper();
		String popedom = StringHelper.stringArrayToString(selectoper);
		//使用角色ID查询关联表
		ElecRolePopedom erp = elecRolePopedomDao.findObjectById(roleid);
		//  存在记录，执行update操作
		if(erp != null){
			erp.setPopedomcode(popedom);
			elecRolePopedomDao.update(erp);
		}else{
			erp = new ElecRolePopedom();
			erp.setRoleID(roleid);
			erp.setPopedomcode(popedom);
			elecRolePopedomDao.save(erp);
		}
	}

}
