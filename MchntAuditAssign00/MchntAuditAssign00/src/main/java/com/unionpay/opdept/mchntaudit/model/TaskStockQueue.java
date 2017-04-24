/**
 * 
 */
package com.unionpay.opdept.mchntaudit.model;

import java.util.LinkedList;
import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;

/**
 * @author DATAN
 *
 */
public class TaskStockQueue extends LinkedList<AuditTask> {

	private static final long serialVersionUID = -8999172512116472624L;

	public TaskStockQueue(List<AuditTask> list) {
		super(list);
	}

	public TaskStockQueue() {
		super();
	}

	public AuditTask poll(AuditUser user) {
		String usrCd = user.getUsrCd();
		if (null == usrCd || usrCd.trim().isEmpty()) {
			return null;
		}

		AuditTask ret = null;
		for (AuditTask e : this) {
			if (!usrCd.equals(e.getTaskCommitUsr()) && !usrCd.equals(e.getAvoidUsr())) {
				ret = e;
				break;
			}
		}

		if (this.remove(ret)) {
			return ret;
		}
		return null;
	}
}
