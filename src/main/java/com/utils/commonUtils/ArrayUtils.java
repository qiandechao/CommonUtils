package com.utils.commonUtils;

/**
 * @author lihuan
 */
public class ArrayUtils {

	/**
	 * String数组转int数组
	 */
	public static Integer[] stringToInteger(String[] array) {
		if (array == null) {
			return null;
		}
		Integer[] n = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			n[i] = Integer.parseInt(array[i]);
		}
		return n;
	}

}
