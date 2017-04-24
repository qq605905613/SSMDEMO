package com.unionpay.opdept.mchntaudit.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unionpay.opdept.mchntaudit.orm.bean.ReasonConfig;
import com.unionpay.opdept.mchntaudit.orm.bean.ReasonType;
import com.unionpay.opdept.mchntaudit.orm.mapper.ReasonConfigMapper;
import com.unionpay.opdept.mchntaudit.orm.mapper.ReasonTypeMapper;
import com.unionpay.opdept.mchntaudit.utils.SpringContextUtil;

public class RejectReasonMappers {

	/**
	 * CONFIG缓存
	 */
	private Map<String, String> reasonConfig;

	/**
	 * 公共拒绝原因缓存
	 */
	private Map<String, String> reasonComType;

	/**
	 * 个性化拒绝原因缓存
	 */
	private Map<String, String> reasonUnComType;

	private ReasonConfigMapper reasonConfigMapper;

	private ReasonTypeMapper reasonTypeMapper;

	public Map<String, String> getReasonConfig() {
		return reasonConfig;
	}

	public Map<String, String> getReasonUnComType() {
		return reasonUnComType;
	}

	public Map<String, String> getReasonComType() {
		return reasonComType;
	}

	public static final RejectReasonMappers getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final RejectReasonMappers INSTANCE = new RejectReasonMappers();
	}

	private RejectReasonMappers() {
		this.reasonConfigMapper = (ReasonConfigMapper) SpringContextUtil.getBean("reasonConfigMapper");
		this.reasonTypeMapper = (ReasonTypeMapper) SpringContextUtil.getBean("reasonTypeMapper");
		initMap();
	}

	private void initMap() {
		reasonConfig = new HashMap<String, String>();
		reasonComType = new HashMap<String, String>();
		reasonUnComType = new HashMap<String, String>();
		List<ReasonConfig> configList = reasonConfigMapper.selectAllConfig();
		if (!configList.isEmpty()) {
			for (ReasonConfig conf : configList) {
				this.reasonConfig.put(conf.getMchntTp() + conf.getSpecDiscTp() + conf.getSpecDiscLvl(),
						conf.getReasonTp());
			}
		}
		List<ReasonType> typeComList = reasonTypeMapper.selectAllType("1");
		if (!typeComList.isEmpty()) {
			for (ReasonType comType : typeComList) {
				this.reasonComType.put(comType.getReasonTp(), comType.getReasonMemo());
			}
		}
		List<ReasonType> typeUnComList = reasonTypeMapper.selectAllType("0");
		if (!typeUnComList.isEmpty()) {
			for (ReasonType unType : typeUnComList) {
				this.reasonUnComType.put(unType.getReasonTp(), unType.getReasonMemo());
			}
		}
	}

	public void init() {
		initMap();
	}
}
