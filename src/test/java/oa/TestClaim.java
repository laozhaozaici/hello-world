package oa;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lanou.bean.Emp;
import com.lanou.dao.ClaimDao;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.entity.TRTCar;
import com.lanou.service.ClaimService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class TestClaim {
	@Autowired
	private ClaimService claimService;
	@Test
	public void showmanager() {
		Date d = new Date();
		//日历类
		Calendar c  = Calendar.getInstance();
		c.setTime(d);
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.get(Calendar.MONTH)+1);

		
//		BizClaimVoucher b = claimDao.findClaimById(2);
	}
	
	@Test
	public void number() {
		System.out.println(10.1-9.9);
		BigDecimal b = new BigDecimal("10.1");
		BigDecimal a = new BigDecimal("9.9");
		BigDecimal d = b.subtract(a);
		System.out.println(d.doubleValue());
		
		
		try {
			  Class clazz = Class.forName("com.lanou.entity.SysEmployee");//这里的类名是全名。。有包的话要加上包名
			  Object obj = clazz.newInstance();
			  Field[] fields = clazz.getDeclaredFields();
			  //调用setter方法写数据
			   PropertyDescriptor pd = new PropertyDescriptor("sn", clazz);
			   Method wM = pd.getWriteMethod();//获得写方法
			   wM.invoke(obj, 2);//因为知道是int类型的属性，所以传个int过去就是了。。实际情况中需要判断下他的参数类型
				   
			   //调用getter方法读数据
			   Method rM = pd.getReadMethod();//获得读方法
			   Integer num = (Integer) rM.invoke(obj);//因为知道是int类型的属性,所以转换成integer就是了。。也可以不转换直接打印
			   System.out.println(num);
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		
		
	}
	@Test
	public void updateClaim() {
	 BizClaimVoucher b = claimService.findClaimById(1);
	 b.setSysEmployeeByNextDealSn(null);
	 claimService.update(b);
	}
	
	
	@Test
	public void toBean() {
		String json = "{\"Carid\":\"9255023\",\"Usetime\":0,\"Stoptime\":\"\",\"OpenId1\":\"\",\"Power\":\"165\",\"Alarm\":\"\",\"State\":\"Stop\",\"Starttime\":0}";
        TRTCar student = JSON.parseObject(json, new TypeReference<TRTCar>() {});
		System.out.println(student.getCarid());
	}
	
	
	
	
	
}
