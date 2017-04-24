/**
 * 
 */
package com.unionpay.opdept.mchntaudittest.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unionpay.opdept.mchntaudit.model.QualityInspectTaskReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingRsp;
import com.unionpay.opdept.mchntaudit.model.RejectReasonMappers;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.QualityInspect;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;
import com.unionpay.opdept.mchntaudit.orm.mapper.QualityInspectMapper;
import com.unionpay.opdept.mchntaudit.orm.mapper.TaskAssignMapper;
import com.unionpay.opdept.mchntaudit.service.AssignTaskService;
import com.unionpay.opdept.mchntaudit.service.QualityInspectService;

//表示继承了SpringJUnit4ClassRunner类
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })

/**
 * @author DATAN
 *
 */
public class AssignTaskServiceImplTest {
	private static final Logger logger = Logger.getLogger(AssignTaskServiceImplTest.class);

	@Resource
	private AssignTaskService assignTaskService;

	@Resource
	private TaskAssignMapper taskAssignMapper;

	@Resource
	private QualityInspectService qualityInspectService;

	// private ProjectConfig projectConfig;
	// @Test
	// public void testAssignTask() {
	// int pavg = 10;
	// int bNum = 1;
	// int hNum = 9;
	// this.assignTaskService.taskAssign(pavg, bNum, hNum);
	// }

	// @Test
	// public void testAssign() {
	// AuditUser user = new AuditUser();
	// user.setFlg1st("0");
	// user.setFlgBa("1");
	// user.setUsrCd("a");
	// user.setGroupId("1");
	// user.setUsrId(0);
	// TaskAssign taskAssign = this.assignTaskService.getTaskByUser(user);
	// System.out.println(taskAssign.toString());
	// }

	// @Test
	// public void TestPreviousTask() {
	// String userCd = "123456";
	// TaskAssign ta = this.assignTaskService.getPreviousTask(userCd);
	// System.out.println(ta);
	// }
	//
	// @Test
	// public void TestQueryTaskByPaging() {
	// QueryTaskPagingServiceReq req = new QueryTaskPagingServiceReq();
	// req.setUsrCd("a");
	// req.setStartNum(1);
	// req.setEndNum(10);
	// req.setAuditSt("F");
	// Calendar todayStart = Calendar.getInstance();
	// todayStart.set(Calendar.HOUR_OF_DAY, 0);
	// todayStart.set(Calendar.MINUTE, 0);
	// todayStart.set(Calendar.SECOND, 0);
	// todayStart.set(Calendar.MILLISECOND, 0);
	// Calendar todayEnd = Calendar.getInstance();
	// todayEnd.set(Calendar.HOUR_OF_DAY, 23);
	// todayEnd.set(Calendar.MINUTE, 59);
	// todayEnd.set(Calendar.SECOND, 59);
	// todayEnd.set(Calendar.MILLISECOND, 999);
	// req.setStartTime(todayStart.getTime());
	// req.setEndTime(todayEnd.getTime());
	// req.setTaskSt("0");
	// List<TaskAssign> taskList =
	// this.assignTaskService.queryTaskByPaging(req);
	// System.out.println(taskList);
	// }
	//
	// @Test
	// public void TestQueryTotalTask() {
	// int total = this.assignTaskService.queryTotalTaskCount("111", null);
	// System.out.println(total);
	// }

	// @Test
	// public void TestQueryByTime() {
	// Calendar todayStart = Calendar.getInstance();
	// todayStart.set(Calendar.HOUR, 0);
	// todayStart.set(Calendar.MINUTE, 0);
	// todayStart.set(Calendar.SECOND, 0);
	// todayStart.set(Calendar.MILLISECOND, 0);
	// Calendar todayEnd = Calendar.getInstance();
	// todayEnd.set(Calendar.HOUR, 23);
	// todayEnd.set(Calendar.MINUTE, 59);
	// todayEnd.set(Calendar.SECOND, 59);
	// todayEnd.set(Calendar.MILLISECOND, 999);
	// int total = this.assignTaskService.queryTotalByTime("111", "4", new
	// Date(todayStart.getTime().getTime()-1*24*3600*1000), new
	// Date(todayEnd.getTime().getTime()-1*24*3600*1000));
	// logger.info(total);
	// }

	// @Test
	// public void testRejectReasonMappers() {
	// TaskAssign taskAssign = new TaskAssign();
	// taskAssign.setMchntTp("8651");
	// taskAssign.setSpecDiscTp("01");
	// taskAssign.setSpecDiscLvl("1");
	// Map<String, Object> result = new HashMap<String, Object>();
	// if (null != taskAssign && null != taskAssign.getMchntTp() && null !=
	// taskAssign.getSpecDiscLvl()
	// && null != taskAssign.getSpecDiscTp()) {
	// // 公共拒绝原因
	// result.put("common",
	// RejectReasonMappers.getInstance().getReasonComType());
	// // 获取MCC/特殊计费类型、档次对应的REASON_TP
	// String type = RejectReasonMappers.getInstance().getReasonConfig()
	// .get(taskAssign.getMchntTp() + taskAssign.getSpecDiscTp() +
	// taskAssign.getSpecDiscLvl());
	// // 非公共拒绝原因
	// Map<String, String> unCommon = new HashMap<String, String>();
	// for (int i = 0; i < type.length(); i++) {
	// String tp = String.valueOf(type.charAt(i));
	// unCommon.put(tp,
	// RejectReasonMappers.getInstance().getReasonUnComType().get(tp));
	// }
	// result.put("unCommon", unCommon);
	// }
	// }

	@Test
	public void testQualityInspect() {
		QualityInspectTaskReq req = new QualityInspectTaskReq();
		req.setEndTime(new Date());
		req.setGroupId("银联一组");
		req.setTaskResult("2");
		req.setMccTp("A");
		req.setAssignUsrNm("测试用户");
		AuditUser user = new AuditUser();
		user.setUsrCd("b");
		QualityInspect rsp = qualityInspectService.getInspectTask(req, user);
		System.out.println(rsp);
	}
	
	@Test
	public void testQueryInspectByPaging(){
		QueryInspectByPagingReq req=new QueryInspectByPagingReq();
		req.setUsrCd("b");
		req.setStartPage(1);
		req.setPageSize(10);
		req.setIsRight("1");
		req.setMccTp("A");
		req.setEndTime(new Date());
		QueryInspectByPagingRsp rsp=qualityInspectService.queryInspectByPaging(req);
		System.out.println(rsp);
	}
}
