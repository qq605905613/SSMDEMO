/**
 * 
 */
package com.unionpay.opdept.mchntaudittest.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.service.AuditUserService;





//表示继承了SpringJUnit4ClassRunner类
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })

/**
 * @author DATAN
 *
 */
public class UserServiceImplTest {

	private static Logger logger = Logger.getLogger(UserServiceImplTest.class);

	@Resource
	private AuditUserService userService;

////
	@Test
	public void testLogin() {
	AuditUser user = userService.loginAuth("123456", "abc1234");
		if (null != user) {
			System.out.println(user.toString());
		} else {
			System.out.println("null");
	}
	}

//	@Test
//	public void testModPwd() {
//		int result = userService.modifyPwd("123456", "abc123", "abc1234");
//		System.out.println(result);
//	}
	@Test
	public void testSelectAll(){
		List<AuditUser> auditUser = userService.selectAll();
		for (AuditUser auditUser2 : auditUser) {
			
			logger.debug(auditUser2.getFlgBa());
			
		}
		
	}
	@Test
	public void testUpdateFla(){
	
	int result= userService.updateFla(11, "Z");
		System.out.println(result);
	
	
	}
	
 @Test 
 	public void testInsertUser(){
	 AuditUser user = new AuditUser();
	 user.setUsrId(123);
	 user.setUsrCd("100");
	 user.setUsrNm("b");
	 user.setLoginPwd("100");
	 user.setGroupId("测试");
	 user.setFlgBa("A");
	 user.setComments("1");
	 user.setFlg1st("1");
	 user.setRecSt("1");
	 user.setRecCrtTs(new Date());
	 user.setRecUpdTs(new Date());
	 int result =userService.insertUser(user);
	 System.out.println(result);
 }
	
	
}
