package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;

public interface AuditUserMapper {
	int deleteByPrimaryKey(Integer usrId);

	int insert(AuditUser record);

	int insertSelective(AuditUser record);

	AuditUser selectByPrimaryKey(Integer usrId);

	int updateByPrimaryKeySelective(AuditUser record);

	int updateByPrimaryKey(AuditUser record);

	AuditUser loginAuth(String UsrCd, String pwd);
	//查询所有的用户
	 List <AuditUser> selectAlluser ();
	 //更改技能
	 int updateFla(Integer usrId,String usrFla);
	 
	 List <AuditUser> selectAllusera ();
	 
}