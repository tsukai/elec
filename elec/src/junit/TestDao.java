package junit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.service.IElecTextService;

public class TestDao {
	//保存
	@Test
	public void saveElecText(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao dao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		ElecText et = new ElecText();
		et.setTextName("测试DAO名称");
		et.setTextDate(new Date());
		et.setTextRemark("测试DAO备注");
		dao.save(et);
	}
	
	//修改
	@Test
	public void updateElecText(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao dao = (IElecTextDao)ServiceProvider
				.getService(IElecTextDao.SERVICE_NAME);
		ElecText et = new ElecText();
		et.setTextID("4028814d4355d092014355d109ef0002");
		et.setTextName("测试DAO名称");
		et.setTextDate(new Date());
		et.setTextRemark("测试DAO备注");
		dao.update(et);
	}
	
	//通过id查询对象
	@Test
	public void findObjectById(){
		IElecTextDao dao = (IElecTextDao)ServiceProvider
				.getService(IElecTextDao.SERVICE_NAME);
		Serializable id = "4028814d4355d092014355d109ef0002";
		ElecText et = dao.findObjectById(id);
		System.out.println(et.getTextName());
	}
	
	//删除
	@Test
	public void deleteObjectByIds(){
		IElecTextDao dao = (IElecTextDao)ServiceProvider
				.getService(IElecTextDao.SERVICE_NAME);
		Serializable[] ids = {"4028814d4355b28c014355b297520001","4028814d4355d092014355d098170001"};
		dao.deleteObjectByIds(ids);
	}
	
	//通过集合对象删除
	@Test
	public void deleteObjectByCollection(){
		IElecTextDao dao = (IElecTextDao)ServiceProvider
				.getService(IElecTextDao.SERVICE_NAME);
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText et1 = new ElecText();
		et1.setTextID("4028814b4351f5dd014351f5eaaf0001");
		list.add(et1);
		ElecText et2 = new ElecText();
		et2.setTextID("4028814d4355d092014355d109ef0002");
		list.add(et2);
		dao.deleteObjectByCollection(list);
	}
}
