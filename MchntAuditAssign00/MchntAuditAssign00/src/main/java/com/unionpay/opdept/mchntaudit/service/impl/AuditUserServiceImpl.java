/**
 * 
 */
package com.unionpay.opdept.mchntaudit.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.mapper.AuditUserMapper;
import com.unionpay.opdept.mchntaudit.service.AuditUserService;

/**
 * @author DATAN
 *
 */
@Service("auditUserService")
public class AuditUserServiceImpl implements AuditUserService {

	private static final Logger logger = Logger.getLogger(AuditUserServiceImpl.class);
	@Resource
	private AuditUserMapper auditUserMapper;

	@Override
	public AuditUser loginAuth(String loginId, String pwd) {
		AuditUser user = new AuditUser();
		user.setUsrCd(loginId);
		user.setLoginPwd(pwd);
		AuditUser auditUser = this.auditUserMapper.loginAuth(loginId, pwd);
		if (null == auditUser) {
			logger.debug("loginId or pwd is wrong");
			return null;
		}
		logger.debug("login successful");
		return auditUser;
	}

	@Override
	public int modifyPwd(AuditUser auditUser, String oldPwd, String newPwd) {
		// 校验旧密码
		String loginId = auditUser.getUsrCd();
		AuditUser us = this.auditUserMapper.loginAuth(loginId, oldPwd);
		if (null == us) {
			logger.debug("oldPwd is wrong");
			return -1;
		}
		// 修改新密码
		AuditUser user = new AuditUser();
		user.setUsrId(us.getUsrId());
		user.setLoginPwd(newPwd);
		user.setRecUpdTs(new Date());
		int result = this.auditUserMapper.updateByPrimaryKeySelective(user);
		return result;
	}

	@Override
	public List<AuditUser> selectAll() {
		
		return  this.auditUserMapper.selectAlluser();
	}



	@Override
	public int updateFla(Integer usrId, String fla) {
	
		//AuditUser auditUser = new AuditUser();
		//auditUser.setFlgBa(fla);
		//this.auditUserMapper.updateFla(usrId, fla);
	
		return this.auditUserMapper.updateFla(usrId, fla);
		
	}

	@Override
	public int insertUser(AuditUser auditUser) {
				
		
				return this.auditUserMapper.insert(auditUser);
	}

	@Override
	public List<AuditUser> selectAlla() {
		
		return this.auditUserMapper.selectAllusera();
	}



}
