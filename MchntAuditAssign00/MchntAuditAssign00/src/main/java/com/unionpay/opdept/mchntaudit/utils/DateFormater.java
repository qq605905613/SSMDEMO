/**
 * 版权所有(C)，中国银联股份有限公司，2013，所有权利保留。
 * 
 * 项目名：	LaborReport
 * 包名：	com.unionpay.utils.date
 * 文件名：	DateFormatUtil.java
 * 模块说明：	
 * 修改历史：	
 *     2013年7月5日	Bright创建。
 */
package com.unionpay.opdept.mchntaudit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author liming
 *
 */
public final class DateFormater {
	private static final Logger logger = Logger.getLogger(DateFormater.class);
	
	/**
	 * 格式化日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		// 参数校验
		if(null == date) {
			throw new IllegalArgumentException("Date对象为null");
		}
		// 设置默认值，format空时，函数返回空
		if(format == null) {
			format = "";
		}
		// 日期格式化
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}
	
	/**
	 * 日期完整格式：yyyy-MM-dd HH:mm:ss.SSS
	 * @param date
	 * @return
	 */
	public static String completeFormat(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	/**
	 * 8位字符串日期
	 * @param date
	 * @return
	 */
	public static String strDate(Date date) {
		return formatDate(date, "yyyyMMdd");
	}
	
	/**
	 * 8位整型日期
	 * @param date
	 * @return
	 */
	public static int intDate(Date date) {
		return Integer.parseInt(strDate(date));
	}
	
	/**
	 * 时间戳格式：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String timestamp(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 月中周号
	 * @param date
	 * @return
	 */
	public static String monthWeekNo(Date date) {
		return formatDate(date, "W");
	}
	
	/**
	 * 年中的周号
	 * @param date
	 * @return
	 */
	public static String yearWeekNo(Date date) {
		return formatDate(date, "w");
	}
	
	/**
	 * 8位日期字符串转换成日期对象，若转换异常，则返回null
	 * @param strDate
	 * @return
	 */
	public static Date parseStrDate(String strDate) {
		// 参数检查
		if(StringUtils.isBlank(strDate)) {
			return null;
		}
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date dt = null;
		try {
			dt = fmt.parse(strDate);
			if(strDate.equals(strDate(dt))) {
				return dt;
			} else {
				logger.error("日期格式错误：" + strDate);
				return null;
			}
		} catch (ParseException e) {
			logger.error("日期字符串转成日期对象异常：" + strDate, e);
		}
		return null;
	}
}
