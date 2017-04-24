/**
 * 
 */
package com.unionpay.opdept.mchntaudittest.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.service.AuditTaskService;

//表示继承了SpringJUnit4ClassRunner类
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

/**
 * @author DATAN
 *
 */
public class AudtTaskServiceImplTest {
	private static Logger logger = Logger.getLogger(AudtTaskServiceImplTest.class);

	@Resource
	private AuditTaskService auditTaskService;
	
	@Test
	public void testFetchAuditTask() {
		int size = 20;
		List<AuditTask> taskList = this.auditTaskService.fetchBranchTask(size);
		for(AuditTask e : taskList) {
			logger.debug(e.getTaskId());
		}
	}

}
