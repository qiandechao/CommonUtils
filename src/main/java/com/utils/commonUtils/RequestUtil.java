package com.utils.commonUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @author www.inxedu.com
 */
public class RequestUtil {
    public RequestUtil() {
    }

    public static int getInt(HttpServletRequest request, String str) {
        String tempStr = request.getParameter(str);
        boolean tempInt = false;
        if(tempStr == null || "".equals(tempStr)) {
            tempStr = "0";
        }

        int tempInt1;
        try {
            tempInt1 = Integer.parseInt(tempStr);
        } catch (Exception var5) {
            var5.printStackTrace();
            tempInt1 = 0;
            var5.printStackTrace();
        }

        return tempInt1;
    }

    public static int getInt(HttpServletRequest request, String str, int defaultInt) {
        String tempStr = request.getParameter(str);
        boolean tempInt = false;
        if(tempStr != null && !"".equals(tempStr)) {
            int tempInt1;
            try {
                tempInt1 = Integer.parseInt(tempStr);
            } catch (Exception var6) {
                var6.printStackTrace();
                tempInt1 = 0;
                var6.printStackTrace();
            }

            return tempInt1;
        } else {
            return defaultInt;
        }
    }

    public static Long getLong(HttpServletRequest request, String str) {
        String tempStr = request.getParameter(str);
        Long tempInt = Long.valueOf(0L);
        if(tempStr == null || "".equals(tempStr)) {
            tempStr = "0";
        }

        try {
            tempInt = Long.valueOf(tempStr);
        } catch (Exception var5) {
            var5.printStackTrace();
            tempInt = Long.valueOf(0L);
            var5.printStackTrace();
        }

        return tempInt;
    }

    public static Long getLong(HttpServletRequest request, String str, Long defaultInt) {
        String tempStr = request.getParameter(str);
        Long tempInt = Long.valueOf(0L);
        if(tempStr != null && !"".equals(tempStr)) {
            try {
                tempInt = Long.valueOf(tempStr);
            } catch (Exception var6) {
                var6.printStackTrace();
                tempInt = Long.valueOf(0L);
                var6.printStackTrace();
            }

            return tempInt;
        } else {
            return defaultInt;
        }
    }
}
