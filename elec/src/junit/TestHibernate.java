package junit;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.jiangxi.elec.domain.ElecText;

public class TestHibernate {
	@Test
	public void testElecText(){
		Configuration config = new Configuration();
		config.configure();//默认加载src下的hibernate.cfg.xml
		//创建sessionFactory
		SessionFactory sf = config.buildSessionFactory();
		//打开session,操作数据库
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		//创建对象,执行保存操作
		ElecText et = new ElecText();
		et.setTextName("测试hibernate名称");
		et.setTextDate(new Date());
		et.setTextRemark("测试hibernate备注");
		//保存
		session.save(et);
		//提交事务
		tx.commit();
		session.close();
	}
}
