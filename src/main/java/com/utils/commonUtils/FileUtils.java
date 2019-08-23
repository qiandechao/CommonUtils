package com.utils.commonUtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.utils.constants.Separator;

/**
 * @author Administrator 文件管理操作实用类
 * @author www.inxedu.com
 */
public class FileUtils {
	
	 private static String DEFAULT_PREVFIX = "thumb_";
	 private static Boolean DEFAULT_FORCE = true;//建议该值为false
	 private static int DEFAULT_WIDTH=80;
	 private static int DEFAULT_HEIGHT=80;
	 
	/**
	 * 新建目录
	 * 
	 * @param filePath
	 */
	public static void createPath(String filePath) {
		filePath = filePath.toString();// 中文转换
		File myFilePath = new File(filePath);
		if (!myFilePath.exists())
			myFilePath.mkdirs();
	}

	/**
	 * 新建文件
	 *
	 * @param filePath
	 */
	public static void createFile(String filePath) throws FileUtilException {
		try {
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists())
				myFilePath.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUtilException("创建文件错误!");
		}
	}

	/**
	 * 删除文件
	 *
	 * @param filePath
	 */
	public static void deleteFile(String filePath) throws FileUtilException {
		try {
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			if (myDelFile.exists())
				myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("删除文件错误!");
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param sourceFilePath
	 * @param distFilePath
	 */
	public static void copyFile(String sourceFilePath, String distFilePath) throws FileUtilException {
		try {
			int bytesum = 0;
			int byteread = 0;
			InputStream inStream = new FileInputStream(sourceFilePath);
			FileOutputStream fs = new FileOutputStream(distFilePath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件拷贝错误!");
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param sourceFilePath
	 * @param distFilePath
	 */
	public static void copyFile(File file, String distFilePath, String fileName) throws FileUtilException {
		try {
			File f = new File(distFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			int bytesum = 0;
			int byteread = 0;
			InputStream inStream = new FileInputStream(file);
			FileOutputStream fs = new FileOutputStream(distFilePath + fileName);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件拷贝错误!");
		}
	}

	/**
	 * 文件夹拷贝
	 * 
	 * @param sourceFilePath
	 * @param distFilePath
	 */
	public static void copyFilePath(String sourceFilePath, String distFilePath) throws FileUtilException {
		try {
			(new File(distFilePath)).mkdirs();
			File[] file = (new File(sourceFilePath)).listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					file[i].toString();
					FileInputStream input = new FileInputStream(file[i]);
					FileOutputStream output = new FileOutputStream(distFilePath + "/" + (file[i].getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件夹拷贝错误!");
		}
	}

	/**
	 * 读到流中
	 * 
	 * @param filePath
	 * @return
	 * @throws FileUtilException
	 */
	public static InputStream fileToStream(String filePath) throws FileUtilException {
		try {
			File file = new File(filePath);
			if (file.exists())
				return new FileInputStream(file);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件转化输出流错误!");
		}

	}

	/**
	 * 读文件到字节数组中
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static byte[] fileToByte(File file) throws FileUtilException {
		FileInputStream is = null;
		try {
			byte[] dist = null;
			if (file.exists()) {
				is = new FileInputStream(file);
				dist = new byte[is.available()];
				is.read(dist);
			}
			return dist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件转化字节数组错误!");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 读文件到字节数组中
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static byte[] fileToByte(String filePath) throws FileUtilException {
		FileInputStream is = null;
		try {
			File file = new File(filePath);
			byte[] dist = null;
			if (file.exists()) {
				is = new FileInputStream(file);
				dist = new byte[is.available()];
				is.read(dist);
			}
			return dist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件转化字节数组错误!");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径)
	 * @param fileName(文件名)
	 * @param args[]
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String[] args) throws IOException {
		FileWriter fw = new FileWriter(filePath + fileName);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < args.length; i++) {
			out.write(args[i]);
			out.println();
			out.flush();
		}
		fw.close();
		out.close();
	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径)
	 * @param fileName(文件名)
	 * @param args
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String args) throws IOException {
		FileWriter fw = new FileWriter(filePath + fileName);
		fw.write(args);
		fw.close();
	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径+文件名)
	 * @param args
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String args) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(args);
		fw.close();
	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径+文件名)
	 * @param args
	 *            要写入的内容
	 * @param isUTF8
	 *            是否以UTF-8的文件编码写入文件
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String args, boolean isUTF8) throws IOException {
		if (isUTF8) {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
			out.write(args);
			out.flush();
			out.close();
		} else {
			FileWriter fw = new FileWriter(filePath);
			fw.write(args);
			fw.close();
		}
	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath文件路径
	 * @param fileName
	 *            文件名
	 * @param args
	 *            要写入的内容
	 * @param isUTF8
	 *            是否以UTF-8的文件编码写入文件
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String args, boolean isUTF8) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		if (isUTF8) {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath + fileName), "UTF-8");
			out.write(args);
			out.flush();
			out.close();
		} else {
			FileWriter fw = new FileWriter(filePath + fileName);
			fw.write(args);
			fw.close();
		}
	}

	/**
	 * 创建与删除文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @return 创建成功返回true
	 * @throws IOException
	 */
	public static boolean createAndDeleteFile(String filePath, String fileName) throws IOException {
		boolean result = false;
		File file = new File(filePath, fileName);
		if (file.exists()) {
			file.delete();
			result = true;
		} else {
			file.createNewFile();
			result = true;
		}
		return result;
	}

	/**
	 * 创建和删除目录
	 * 
	 * @param folderName
	 * @param filePath
	 * @return 删除成功返回true
	 */
	public static boolean createAndDeleteFolder(String folderName, String filePath) {
		boolean result = false;
		try {
			File file = new File(filePath + folderName);
			if (file.exists()) {
				file.delete();
				System.out.println("目录已经存在，已删除!");
				result = true;
			} else {
				file.mkdir();
				System.out.println("目录不存在，已经建立!");
				result = true;
			}
		} catch (Exception ex) {
			result = false;
			System.out.println("CreateAndDeleteFolder is error:" + ex);
		}
		return result;
	}

	/**
	 * 输出目录中的所有文件及目录名字
	 * 
	 * @param filePath
	 */
	public static void readFolderByFile(String filePath) {
		File file = new File(filePath);
		File[] tempFile = file.listFiles();
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].isFile()) {
				System.out.println("File : " + tempFile[i].getName());
			}
			if (tempFile[i].isDirectory()) {
				System.out.println("Directory : " + tempFile[i].getName());
			}
		}
	}

	/**
	 * 检查文件中是否为一个空
	 * 
	 * @param filePath
	 * @param fileName
	 * @return 为空返回true
	 * @throws IOException
	 */
	public static boolean fileIsNull(String filePath, String fileName) throws IOException {
		boolean result = false;
		FileReader fr = new FileReader(filePath + fileName);
		if (fr.read() == -1) {
			result = true;
			System.out.println(fileName + " 文件中没有数据!");
		} else {
			System.out.println(fileName + " 文件中有数据!");
		}
		fr.close();
		return result;
	}

	/**
	 * 读取文件中的所有内容
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void readAllFile(String filePath, String fileName) throws IOException {
		FileReader fr = new FileReader(filePath + fileName);
		int count = fr.read();
		while (count != -1) {
			count = fr.read();
			if (count == 13) {
				fr.skip(1);
			}
		}
		fr.close();
	}

	/**
	 * 一行一行的读取文件中的数据
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void readLineFile(String filePath, String fileName) throws IOException {
		FileReader fr = new FileReader(filePath + fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			line = br.readLine();
		}
		br.close();
		fr.close();
	}

	/**
	 * 一行一行的读取文件中的数据,转换成字符串
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static String readLineFile(String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
		return sb.toString();
	}

	/**********************************/

	/**
	 * 读取文件已byte流的形式返回,并删除临时文件;
	 * 
	 * @param path
	 *            :文件在服务器上的绝对路径;
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream file2ByteArrayInputStream(String fileName) throws Exception {
		try {
			File file = new File(fileName);
			return file2ByteArrayInputStream(file);
		} catch (Exception e) {
			throw new Exception("将文件转换为流的过程中出现错误!");
		}
	}

	/**
	 * 读取文件已byte流的形式返回,并删除临时文件;
	 * 
	 * @param path
	 *            :文件在服务器上的绝对路径;
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream file2ByteArrayInputStream(File file) throws Exception {
		try {
			FileInputStream is = new FileInputStream(file);
			byte[] b = new byte[is.available()];
			is.read(b);
			is.close();
			// file.delete();
			return new ByteArrayInputStream(b);
		} catch (Exception e) {
			throw new Exception("将文件转换为流的过程中出现错误!");
		}
	}

	/**
	 * 从url读取文本
	 * 
	 * @param strURL
	 *            String url地址
	 * @return String
	 */
	public String readFromURL(String strURL) {
		try {
			URL url = new URL(strURL);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			String rtnStr = "";
			while ((str = in.readLine()) != null) {
				rtnStr = rtnStr + new String(str.getBytes(), "GB2312");
			}
			in.close();
			return rtnStr;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 从输入流中读取内容
	 * 
	 * @param is
	 *            InputStream 输入流对象
	 * @throws Exception
	 * @return String
	 */
	public String readFromIS(InputStream is) throws Exception {
		try {
			String strRtn = "";
			int length = is.available();
			byte[] buf = new byte[length];
			while ((is.read(buf, 0, length)) != -1) {
				strRtn = strRtn + new String(buf, 0, length, "GB2312");
			}
			return strRtn;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			is.close();
		}
	}

	/**
	 * 将文件夹清空
	 * 
	 * @param staticPath
	 */
	public static void clearFile(String staticPath) {
		File file = new File(staticPath);
		deleteFile(file);
		file.mkdirs();
	}

	/**
	 * 递归删除文件夹下内容
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}

	/**
	 * 
	 * 将文件转成base64字符串
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 * @throws FileNotFoundException
	 * @throws FileUtilException
	 */
	public static String encodeBase64File(File file) throws FileNotFoundException, FileUtilException {
		if (!file.isFile()) {
			throw new FileNotFoundException("没有找到文件" + file);
		}
		try {
			FileInputStream inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			String filename = file.getName();
			String extname = filename.substring(filename.lastIndexOf("."), filename.length());
			StringBuilder base64Code = new StringBuilder("data:image/");
			base64Code.append(extname);
			base64Code.append(";base64--");
			base64Code.append(Base64.getEncoder().encodeToString(buffer));
			return base64Code.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUtilException("将文件[" + file + "]转成base64字符串时发生I/O异常,详情!");
		}
	}

	/**
	 * 将base64字符解码保存文件
	 * 
	 * @param base64Code
	 *            文件的base64加密流
	 * @param targetPath
	 *            文件写入目标地址
	 * @return
	 * @throws FileUtilException 
	 */
	public static boolean decoderBase64File(String base64Code, String targetPath, String filename) throws FileUtilException {
		System.out.println(targetPath.concat(filename));
		FileOutputStream out = null;
		try {
			System.out.println(base64Code);
			byte[] buffer = Base64.getDecoder().decode(base64Code);
			String filepath = targetPath.concat(filename);
			File routeChecker = new File(filepath.substring(0, filepath.lastIndexOf(Separator.FS)));
			if (!routeChecker.isDirectory()) {
				routeChecker.mkdirs();
			}
			out = new FileOutputStream(filepath);
			out.write(buffer);
			out.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUtilException("将base64字符解码并保存到目标地址[" + targetPath + "]时发生I/O异常");
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 根据传递的文件路径删除文件
	 * 
	 * @param arg0
	 *            要删除的文件路径
	 * @return
	 */
	public static boolean deleteByTargetPath(String arg0) {
		File file = new File(arg0);
		return file.delete();
	}
	
	/**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 依据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制依照宽高生成缩略图(假设为false，则生成最佳比例缩略图)
     */
	public static void thumbnailImage(String sourceImagePath, int w, int h, String prevfix, boolean force) throws FileNotFoundException, FileUtilException{
        File imgFile = new File(sourceImagePath);
        if(imgFile.exists()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀所有小写，然后推断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
                	throw new FileUtilException("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                }
                Image img = ImageIO.read(imgFile);
                if(!force){
                    // 依据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
            	ImageIO.write(bi, suffix, new File(p.substring(0,p.lastIndexOf(File.separator)) + File.separator + prevfix +imgFile.getName()));
                System.out.println("缩略图在原路径下生成成功");
            } catch (IOException e) {
            	e.printStackTrace();
            	throw new FileUtilException("generate thumbnail image failed");
            }
        }else{
        	throw new FileNotFoundException("该路径【"+sourceImagePath+"】对应图片不存在");
        }
    }
	public static void thumbnailImage(String imagePath) throws FileNotFoundException, FileUtilException{
		thumbnailImage(imagePath,DEFAULT_WIDTH,DEFAULT_HEIGHT,DEFAULT_PREVFIX,DEFAULT_FORCE);
    }
	public static void thumbnailImage(String imagePath,String prevfix) throws FileNotFoundException, FileUtilException{
		thumbnailImage(imagePath,DEFAULT_WIDTH,DEFAULT_HEIGHT,prevfix,DEFAULT_FORCE);
    }
	public static void thumbnailImage(String imagePath, int w, int h) throws FileNotFoundException, FileUtilException{
		thumbnailImage(imagePath,w,h,DEFAULT_PREVFIX,DEFAULT_FORCE);
    }
	
	public static void main(String[] args) throws FileNotFoundException, FileUtilException, UnsupportedEncodingException {
		//System.out.println(URLEncoder.encode(encodeBase64File(new File("/Users/lihuan/Downloads/1.jpg")), "utf-8"));
		new FileUtils().thumbnailImage("E:/images/2.jpg");
	}
	
}
