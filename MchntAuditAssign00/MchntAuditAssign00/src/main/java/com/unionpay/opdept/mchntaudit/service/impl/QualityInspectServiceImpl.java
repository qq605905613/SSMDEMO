package com.unionpay.opdept.mchntaudit.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unionpay.opdept.mchntaudit.model.QualityInspectTaskReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingRsp;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.QualityInspect;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;
import com.unionpay.opdept.mchntaudit.orm.mapper.QualityInspectMapper;
import com.unionpay.opdept.mchntaudit.orm.mapper.TaskAssignMapper;
import com.unionpay.opdept.mchntaudit.service.QualityInspectService;

@Service("qualityInspectService")
@Transactional
public class QualityInspectServiceImpl implements QualityInspectService {

	private static final Logger logger = Logger.getLogger(QualityInspectServiceImpl.class);

	@Resource
	private TaskAssignMapper taskAssignMapper;

	@Resource
	private QualityInspectMapper qualityInspectMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public QualityInspect getInspectTask(QualityInspectTaskReq qualityInspectTaskReq, AuditUser user) {
		// 优先从QUALITY_INSPECT表中选取正在质检中的任务
		QualityInspect qualityInspect = qualityInspectMapper.selectByInspectUsrCd(user.getUsrCd());
		if (null != qualityInspect) {
			logger.info("query selected qualityInspect:[user=" + user.getUsrCd() + "],[qualityInspect="
					+ qualityInspect.toString() + "]");
			return qualityInspect;
		} else {
			Boolean flag = true;
			while (flag) {
				// 从TASK_ASSIGN表中随机抽取符合条件的任务
				TaskAssign taskAssign = taskAssignMapper.selectInspectTask(qualityInspectTaskReq);
				// 修改TASK_ASSIGN表中此条记录comments为1
				if (null != taskAssign) {
					taskAssign.setComments("1");
					int rsp = taskAssignMapper.updateByCommentsSelective(taskAssign);
					// 如若修改成功，则将此条记录插入QUALITY_INSPECT表中
					if (rsp == 1) {
						flag = false;
						qualityInspect = converInspect(taskAssign, user);
						int result = qualityInspectMapper.insertSelective(qualityInspect);
						logger.info("insert to qualityInspect:[userCd=" + user.getUsrCd() + "],[qualityInspect="
								+ qualityInspect.toString() + "],[result=" + result + "]");
						if (result == 1) {
							return qualityInspect;
						}
					} // 如若修改失败，则重新抽取任务，并重复前2步
				} else if (null == taskAssign) {
					return null;
				}
			}
			return null;
		}
	}

	private QualityInspect converInspect(TaskAssign taskAssign, AuditUser user) {
		QualityInspect qualityInspect = new QualityInspect();
		qualityInspect.setAcptInsIdCd(taskAssign.getAcptInsIdCd());
		qualityInspect.setAcqCommitAuditTs(taskAssign.getAcqCommitAuditTs());
		qualityInspect.setAcqInsIdCd(taskAssign.getAcptInsIdCd());
		qualityInspect.setActualDoneTs(taskAssign.getActualDoneTs());
		qualityInspect.setAssignGroupId(taskAssign.getAssignGroupId());
		qualityInspect.setAssignTs(taskAssign.getAssignTs());
		qualityInspect.setAssignUsrCd(taskAssign.getAssignUsrCd());
		qualityInspect.setAuditSt(taskAssign.getAuditSt());
		qualityInspect.setConnMd(taskAssign.getConnMd());
		qualityInspect.setCupBranchInsIdCd(taskAssign.getCupBranchInsIdCd());
		qualityInspect.setInspectUsrCd(user.getUsrCd());
		qualityInspect.setIsWhiteMchnt(taskAssign.getIsWhiteMchnt());
		qualityInspect.setMchntAuditId(taskAssign.getMchntAuditId());
		qualityInspect.setMchntCd(taskAssign.getMchntCd());
		qualityInspect.setMchntCnNm(taskAssign.getMchntCnNm());
		qualityInspect.setMchntRating(taskAssign.getMchntRating());
		qualityInspect.setMchntSrvTp(taskAssign.getMchntSrvTp());
		qualityInspect.setMchntTp(taskAssign.getMchntTp());
		qualityInspect.setPendingMemo(taskAssign.getPendingMemo());
		qualityInspect.setPlanDoneTs(taskAssign.getPlanDoneTs());
		qualityInspect.setRecCrtTs(new Date());
		qualityInspect.setRecSt(taskAssign.getRecSt());
		qualityInspect.setRecUpdTs(new Date());
		qualityInspect.setRejectMemo(taskAssign.getRejectMemo());
		qualityInspect.setSpecDiscLvl(taskAssign.getSpecDiscLvl());
		qualityInspect.setSpecDiscTp(taskAssign.getSpecDiscTp());
		qualityInspect.setTaskId(taskAssign.getTaskId());
		qualityInspect.setTaskResult(taskAssign.getTaskResult());
		qualityInspect.setIsRight("3");
		return qualityInspect;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int processInspectTask(String taskId, String isRight, String memo) {
		int result = qualityInspectMapper.updateByTaskId(taskId, isRight, memo, new Date());
		logger.info("update inspect task:[req]=taskId:" + taskId + ",isRight:" + isRight + ",memo:" + memo + ",[rsp]="
				+ result + "]");
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public QueryInspectByPagingRsp queryInspectByPaging(QueryInspectByPagingReq req) {
		int startNum = req.getPageSize() * (req.getStartPage() - 1) + 1;
		int endNum = req.getPageSize() * req.getStartPage();
		req.setStartNum(startNum);
		req.setEndNum(endNum);
		List<QualityInspect> inspectList = qualityInspectMapper.queryInspectByPaging(req);
		int totalNum = qualityInspectMapper.queryInspectCountByPaging(req);
		QueryInspectByPagingRsp rsp = new QueryInspectByPagingRsp();
		rsp.setInspectList(inspectList);
		rsp.setTotalNum(totalNum);
		return rsp;
	}
}
