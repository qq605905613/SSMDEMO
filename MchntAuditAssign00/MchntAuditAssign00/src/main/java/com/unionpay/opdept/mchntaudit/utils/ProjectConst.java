/**
 * 
 */
package com.unionpay.opdept.mchntaudit.utils;

/**
 * @author DATAN
 *
 */
public class ProjectConst {
	/**
	 * 命令行参数：外部配置文件目录参数
	 */
	public static final String PRJ_CONF_PATH_KEY = "mchntaudit.config.path";
	
	/**
	 * 项目常量：项目配置文件名称
	 */
	public static final String PRJ_CONF_FILE_NM = "project.properties";
	
	/**
	 * 项目配置文件中的项索引
	 */
	public static final String APP_NM_KEY = "app.name";
	
	
	// 邮件发送相关常量
	public static final String EMAIL_SMTP_HOST_KEY = "email.smtp.host";
	public static final String EMAIL_SMTP_PORT_KEY = "email.smtp.port";
	public static final String EMAIL_SMTP_USER_KEY = "email.smtp.user";
	public static final String EMAIL_SMTP_AUTH_KEY = "email.smtp.auth";
	public static final String EMAIL_FROM_ADDR_KEY = "email.from.addr";
	public static final String EMAIL_FORM_NAME_KEY = "email.from.name";
	public static final String EMAIL_TO = "TO";
	public static final String EMAIL_CC = "CC";
	
	// 报表相关常量
	public static final String EXCEL_REPORT_DIR_KEY = "excel.report.dir";
}
