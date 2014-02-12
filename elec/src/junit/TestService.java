package junit;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jiangxi.elec.container.ServiceProvider;
import cn.jiangxi.elec.dao.IElecTextDao;
import cn.jiangxi.elec.domain.ElecText;
import cn.jiangxi.elec.service.IElecTextService;
import cn.jiangxi.elec.web.form.ElecTextForm;

public class TestService {
	/*@Test
	public void saveElecText(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService service = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		ElecText et = new ElecText();
		et.setTextName("测试Service名称");
		et.setTextDate(new Date());
		et.setTextRemark("测试Service备注");
		service.saveElecText(et);
	}*/
	
	@Test
	public void saveElecText(){
		IElecTextService service = (IElecTextService)ServiceProvider.getService(IElecTextService.SERVICE_NAME);
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		IElecTextService service = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		ElecText et = new ElecText();
		et.setTextName("张三");
		et.setTextDate(new Date());
		et.setTextRemark("李思思");
		service.saveElecText(et);
	}
	
	/**
	 * 通过查询条件，查询对象的列表集合
	 * 模仿Action层
	 */
	@Test
	public void findCollection(){
		IElecTextService service = (IElecTextService)ServiceProvider.getService(IElecTextService.SERVICE_NAME);
		ElecTextForm etf = new ElecTextForm();
		etf.setTextName("张");
		etf.setTextRemark("李");
		List<ElecText> list = service.findCollectionByConditionNoPage(etf);
//		service.saveElecText(etf);
	}
}
