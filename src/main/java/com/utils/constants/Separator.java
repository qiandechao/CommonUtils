package com.utils.constants;

public interface Separator {
	/**
	 * 换行符
	 */
	public static final String LS = System.getProperty("line.separator");
	/**
	 * 路径分割符,windows下是";",linux下是":"
	 */
	public static final String PS = System.getProperty("path.separator");
	/**
	 * 目录分割符,windows下是"\",linux下是"/"
	 */
	public static final String FS = System.getProperty("file.separator");
}