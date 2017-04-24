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
public class TaskQueue extends LinkedList<AuditTask> {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6600277406613490831L;

	public TaskQueue(List<AuditTask> list) {
		super(list);
	}

	public TaskQueue() {
		super();
	}

	public AuditTask poll(AuditUser user) {
		String usrCd = user.getUsrCd();
		if (null == usrCd || usrCd.trim().isEmpty()) {
			return null;
		}

		AuditTask ret = null;
		for (AuditTask e : this) {
			if (!usrCd.equals(e.getTaskCommitUsr()) && !usrCd.equals(e.getAvoidUsr()) ) {
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
