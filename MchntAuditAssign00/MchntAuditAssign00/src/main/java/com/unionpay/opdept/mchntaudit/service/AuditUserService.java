/**
 * 
 */
package com.unionpay.opdept.mchntaudit.service;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;

/**
 * @author DATAN
 *
 */
public interface AuditUserService {

	/**
	 * @param loginId
	 * @param pwd
	 * @return 登录成功返回User，登录失败返回NULL
	 */
	public AuditUser loginAuth(String loginId, String pwd);

	/**
	 * 修改密码
	 * 
	 * @param loginId
	 * @param oldPwd
	 * @param newPwd
	 * @return result -1:旧密码错误 1:修改成功
	 */
	public int modifyPwd(AuditUser auditUser, String oldPwd, String newPwd);
	
	/** 查询所有的用户标识
	 * @return
	 */
	public List<AuditUser>  selectAll();
	
	public int updateFla(Integer usrId,String fla);
	
	/** 添加用户
	 * @param auditUser
	 * @return
	 */
	public int insertUser(AuditUser auditUser);
	
	/**查询所有用户（人员管理模块）
	 * @return
	 */
	public List<AuditUser>  selectAlla();

}
