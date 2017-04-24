package com.unionpay.opdept.mchntaudit.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionpay.opdept.mchntaudit.model.RejectReasonMappers;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;
import com.unionpay.opdept.mchntaudit.utils.SessionUtil;

@Controller
@RequestMapping("auditUtil")
public class AuditUtilController {

	@RequestMapping(value = "queryRejectReason.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryRejectReason(TaskAssign taskAssign, HttpServletRequest request) throws Exception {
		SessionUtil.checkSession(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != taskAssign && null != taskAssign.getMchntTp() && null != taskAssign.getSpecDiscLvl()
				&& null != taskAssign.getSpecDiscTp()) {
			// 公共拒绝原因
			Map<String, String> reason = RejectReasonMappers.getInstance().getReasonComType();
			// 获取MCC/特殊计费类型、档次对应的REASON_TP
			String type = RejectReasonMappers.getInstance().getReasonConfig()
					.get(taskAssign.getMchntTp() + taskAssign.getSpecDiscTp() + taskAssign.getSpecDiscLvl());
			// 非公共拒绝原因
			if (null != type) {
				for (int i = 0; i < type.length(); i++) {
					String tp = String.valueOf(type.charAt(i));
					reason.put(tp, RejectReasonMappers.getInstance().getReasonUnComType().get(tp));
				}
			}
			result.put("reason", reason);
		}
		return result;
	}
}
