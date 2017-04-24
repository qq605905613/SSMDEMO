package com.unionpay.opdept.mchntaudit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * @author liming
 *
 */
public class ConfigFile {
	private static final Logger logger = Logger.getLogger(ConfigFile.class);
	
	// 外部配置文件目录
	private static final String CONFIG_DIR_PATH;
	static {
		CONFIG_DIR_PATH = System.getProperty(ProjectConst.PRJ_CONF_PATH_KEY);
	}
	
	private boolean external;
	
	/**
	 * 相对路径拼接
	 * @param seporator 分隔符
	 * @param filename 文件名
	 * @param dirs 目录列表
	 * @return
	 */
	private String buildFilePath(String seporator, String filename, String... dirs) {
		if(null == dirs || 0 == dirs.length) {
			return filename;
		}
		
		StringBuilder buf = new StringBuilder();
		for(String dir : dirs) {
			if(null == dir || dir.trim().isEmpty()) {
				throw new IllegalArgumentException("目录名称为空");
			}
			buf.append(dir).append(seporator);
		}
		buf.append(filename);
		
		return buf.toString();
	}
	
	/**
	 * 加载外部配置文件
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
	private InputStream openExternalConfig(String filename, String... dirs) throws FileNotFoundException {
		// 外部配置路径为null
		if(null == CONFIG_DIR_PATH) {
			logger.info("外部配置文件路径为null");
			return null;
		}
		
		File configDir = new File(CONFIG_DIR_PATH);
		// 外部配置路径不存在或不是目录
		if(!configDir.exists() || !configDir.isDirectory()) {
			logger.info("外部配置路径不存在或不是目录");
			return null;
		}
		
		String fileRelativePath = this.buildFilePath(File.separator, filename, dirs);
		File configFile = new File(configDir, fileRelativePath);
		// 配置文件不存在或不是文件
		if(!configFile.exists() || !configFile.isFile()) {
			logger.info("配置文件不存在或不是文件");
			return null;
		}
		
		return new FileInputStream(configFile);
	}
	
	/**
	 * 加载内部配置文件
	 * @param filename
	 * @return
	 */
	private InputStream openInternalConfig(String filename, String... dirs) {
		String absolutePath = "/" + this.buildFilePath("/", filename, dirs);
		return this.getClass().getResourceAsStream(absolutePath);
	}
	
	/**
	 * 加载配置文件，先加载外部配置文件，再加载内部配置文件，若都失败，则抛出异常
	 * @param filename
	 * @return
	 */
	protected InputStream openConfigFile(String filename, String... dirs) {
		InputStream inputStream;
		try {
			external = true;
			inputStream = openExternalConfig(filename, dirs);
			// 外部配置文件加载失败
			if(inputStream == null) {
				logger.info("外部配置文件加载失败：" + this.buildFilePath(File.separator, filename, dirs));
				external =false;
				inputStream = openInternalConfig(filename, dirs);
			}
			
			// 内部配置文件加载失败
			if(inputStream == null) {
				throw new IllegalArgumentException("配置文件不存在：" + 
						this.buildFilePath(File.separator, filename, dirs));
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("配置文件读取失败：" + 
					this.buildFilePath(File.separator, filename, dirs), e);
		}
		return inputStream;
	}
	
	/**
	 * 是否为外部配置文件
	 * @return
	 */
	protected boolean isExteranl() {
		return external;
	}
}
