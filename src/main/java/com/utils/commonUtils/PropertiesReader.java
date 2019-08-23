package com.utils.commonUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 属性文件读取工具类
 */
public class PropertiesReader {
	/**
	 * 获取属性文件的值
	 * @param file_name 文件名
	 * @param key 键
	 * @return 返回键的值
	 * @throws MissingResourceException
	 */
	public static String getValue(String file_name, String key)throws MissingResourceException {
		ResourceBundle res = ResourceBundle.getBundle(file_name);
		String value = res.getString(key);
		return value.trim();
	}

	/**
	 * @param file_name 文件名
	 * @return 返回文件的所有键=值
	 * @throws MissingResourceException
	 */
	public static Properties fillProperties(String file_name) throws MissingResourceException {
		Properties properties = new Properties();
		ResourceBundle res = ResourceBundle.getBundle(file_name);
		Enumeration<String> en = res.getKeys();
		String key = null;
		String value = null;
		while (en.hasMoreElements()) {
			key = en.nextElement().trim();
			value = res.getString(key);
			properties.setProperty(key, value.trim());
		}
		return properties;
	}

	/**
	 * 设置属性文件的K的V
	 * @param file_name 文件名
	 * @param key 
	 * @param value
	 * @throws MissingResourceException
	 */
	public static void setValue(String file_name, String key, String value) {
		try {
			Properties properties = new Properties();
			PropertiesReader propertiesReader = new PropertiesReader();
			String staticPath = propertiesReader.getClass().getClassLoader().getResource("").getPath();
			if (file_name.indexOf(".properties") < 0) {
				file_name = file_name + ".properties";
			}
			String file_name_path = staticPath + file_name;
			FileInputStream in = new FileInputStream(file_name_path);
			properties.load(in);
			FileOutputStream fis = new FileOutputStream(file_name_path);
			properties.setProperty(key, value);
			properties.store(fis, file_name);
			in.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}