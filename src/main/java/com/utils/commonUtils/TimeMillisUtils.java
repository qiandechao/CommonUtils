package com.utils.commonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 获取不重复的时间戳
 * 
 * @author lihuan
 *
 */
public class TimeMillisUtils {

	private static final Logger logger = LoggerFactory.getLogger(TimeMillisUtils.class);

	/**
	 * 
	 * 获取系统时间戳
	 * 
	 * @return 返回内容如:1309768383501
	 */
	public static synchronized long getTimeMillis() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			logger.error("获系统时间波动出错");
		}
		return System.currentTimeMillis();
	}

}
