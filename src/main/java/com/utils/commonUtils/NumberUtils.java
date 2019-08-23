package com.utils.commonUtils;

/**
 * 
 * 数字转换帮助类
 * 
 * @author 李欢
 * 
 */
public class NumberUtils {

	/**
	 * 
	 * 将字符串转为Integer类型，并判断传入字符串是否为null或空，如果为null或空则返回null
	 * 
	 * @param arg0
	 *            带转换字符串
	 * @return
	 */
	public static Integer parseInteger(String arg0) {
		if (arg0 != null && !arg0.equals("")) {
			return Integer.valueOf(arg0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 将字符串转为Integer类型，并判断传入字符串是否为null或空，如果为null或空则返回null
	 * 
	 * @param arg0
	 *            带转换字符串
	 * @return
	 */
	public static Integer[] parseInteger(String[] arg0) {
		if (arg0 != null && arg0.length > 0) {
			Integer[] numbers = new Integer[arg0.length];
			for (int i = 0; i < arg0.length; i++) {
				numbers[i] = parseInteger(arg0[i]);
			}
			return numbers;
		} else {
			return new Integer[0];
		}
	}

	/**
	 * 
	 * 将字符串转为Long类型，并判断传入字符串是否为null或空，如果为null或空则返回null
	 * 
	 * @param arg0
	 *            带转换字符串
	 * @return
	 */
	public static Long parseLong(String arg0) {
		if (arg0 != null && !arg0.equals("")) {
			return Long.valueOf(arg0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 将字符串转为Float类型，并判断传入字符串是否为null或空，如果为null或空则返回null
	 * 
	 * @param arg0
	 *            带转换字符串
	 * @return
	 */
	public static Float parseFloat(String arg0) {
		if (arg0 != null && !arg0.equals("")) {
			return Float.valueOf(arg0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 将字符串转为Double类型，并判断传入字符串是否为null或空，如果为null或空则返回null
	 * 
	 * @param arg0
	 *            带转换字符串
	 * @return
	 */
	public static Double parseDouble(String arg0) {
		if (arg0 != null && !arg0.equals("")) {
			return Double.valueOf(arg0);
		} else {
			return null;
		}
	}

}
