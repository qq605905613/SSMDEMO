package com.unionpay.opdept.mchntaudit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author liming
 *
 */
public final class ProjectConfig extends ConfigFile {
	// 日志
	private static final Logger logger = Logger.getLogger(ProjectConfig.class);
	
	// 项目配置文件名
	private static final String PROJECT_CONFIG_FILENAME;
	// 保存项目配置惟一对象
	private static class ConfigHolder {
		private static ProjectConfig instance = new ProjectConfig();
	}
	// 初始化静态常量
	static {
		PROJECT_CONFIG_FILENAME = ProjectConst.PRJ_CONF_FILE_NM;
	}
	
	// 项目配置内容
	private Properties properties;
	
	/**
	 * 私有构造函数
	 */
	private ProjectConfig(){
		this.initial();
		logger.info("本应用名称：" + properties.getProperty(ProjectConst.APP_NM_KEY));
	};
	
	/**
	 * 读取项目配置文件
	 * @return
	 */
	private Properties loadConfig() {
		InputStream inputStream = super.openConfigFile(PROJECT_CONFIG_FILENAME);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		}
		catch (IOException e) {
			throw new IllegalStateException("项目配置文件加载失败：" + PROJECT_CONFIG_FILENAME, e);
		}
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("InputStream对象关闭失败：" + inputStream);
			}
		}
		
		return p;
	}
	
	/**
	 * 获取项目配置对象
	 * @return
	 */
	public static ProjectConfig getInstance() {
		return ConfigHolder.instance;
	}
	
	/**
	 * 根据key获取对应配置项
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return (String) getInstance().properties.get(key);
	}
	
	/* (non-Javadoc)
	 * @see com.unionpay.utils.common.Cacheable#initial()
	 */
	public void initial() {
		this.properties = this.loadConfig();
	}

	/* (non-Javadoc)
	 * @see com.unionpay.utils.common.Cacheable#reflash()
	 */
	public boolean reflash() {
		Properties p;
		try {
			p = this.loadConfig();
			this.properties = p;
			return true;
		}
		catch (Exception e) {
			logger.error("项目配置文件刷新失败：" + PROJECT_CONFIG_FILENAME);
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see com.unionpay.utils.common.Cacheable#destroy()
	 */
	public void destroy() {
		logger.info("项目配置销毁不进行任何操作");
	}

}
