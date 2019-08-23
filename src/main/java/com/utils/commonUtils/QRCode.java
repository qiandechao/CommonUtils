package com.utils.commonUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
/**
 * 二维码工具类
 * @author sly
 * 
 */
public class QRCode {
	/**
	 * 验证图片中是否包含二维码
	 * @param fileUrl 图片路径
	 * @throws Exception
	 */
	public static String read (String fileUrl){
		String qrUrl = "";//二维码解析出的路径
		try{
	        QRCodeReader qrCodeReader = new QRCodeReader();
	        File f = new File(fileUrl);
	        BufferedImage bufferedImage = ImageIO.read(f);
	        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
	        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
	        Map<DecodeHintType, String> hints2 = new HashMap<>();
	        hints2.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        	Result result = qrCodeReader.decode(binaryBitmap, hints2);
        	qrUrl = result.getText();
        }catch (Exception e) {
			return qrUrl;//如果解析失败说明图片中没有包含二维码，直接返回；
		}
		return qrUrl;
    }
	/**
	 * 获取字符串中的图片路径
	 * @param htmlStr 图片路径列表
	 * @return
	 */
	public static List<String> getImgSrc(String htmlStr) {
		if( htmlStr == null ){
			return null;
		}
		String img = "";  
        Pattern p_image;  
        Matcher m_image;  
        List<String> pics = new ArrayList<String>();
        
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";  
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);  
        m_image = p_image.matcher(htmlStr);  
        while (m_image.find()) {  
            img = img + "," + m_image.group();  
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {  
                pics.add(m.group(1));  
            }  
        }  
        return pics;
	}
}
