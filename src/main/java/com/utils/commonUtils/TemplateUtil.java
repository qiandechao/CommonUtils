package com.utils.commonUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;


public class TemplateUtil {

	/**
	 * 生成静态的HTML文件
	 * @param engine 模板生成器
	 * @param tempFileName 模板文件名
	 * @param file HTML生成的全路径
	 * @param context 模板上下文
	 * @return true生成成功 false生成失败
	 * @throws Exception
	 */
	public static boolean createHtmlFile(VelocityEngine engine,String tempFileName,File file,VelocityContext context){
		try{
			if(file.getParentFile().exists()==false){
	        	file.getParentFile().mkdirs();
	        }
			Template temp = engine.getTemplate(tempFileName, "UTF-8");
			FileOutputStream tempFos = new FileOutputStream(file); 
	        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(tempFos,"UTF-8"));
	        temp.setEncoding("UTF-8");
	        temp.merge(context, writer);
	        writer.close();
	        tempFos.close();
	        return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * 初始化模板生成器
	 * @param genDir 模板目录
	 * @return VelocityEngine
	 * @throws Exception
	 */
	public static VelocityEngine initEngine(String genDir) throws Exception{
		VelocityEngine engine = new VelocityEngine();
		Properties properties = new Properties();  
        properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,genDir);  
        properties.setProperty(VelocityEngine.INPUT_ENCODING,"UTF-8"); 
		properties.setProperty(VelocityEngine.OUTPUT_ENCODING,"UTF-8"); 
        engine.init(properties);   
        return engine;
	}


	/***
	 * 创建模板文件
	 * @param fileContext 模板内容
	 */
	public static String createTemplateFile(String fileContext,String createDirUrl){
		BufferedWriter bw = null;
		try{
			String fileDirUrl=createDirUrl.endsWith(".vm")?createDirUrl:createDirUrl+".vm";
			File file = new File(fileDirUrl);
			if(file.getParentFile().exists()==false){
				file.getParentFile().mkdirs();
			}
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bw.write(fileContext);
			return fileDirUrl;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}



}
