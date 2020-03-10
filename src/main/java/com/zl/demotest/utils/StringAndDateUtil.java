/**
 * User: zlin
 * Date: 2019/8/5
 * Time: 12:33
 **/

package com.zl.demotest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringAndDateUtil {

    /**
     * String转换为Date类型
     * @param s
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String s) throws ParseException {
        java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (s == null) {
            return null;
        }else {
            return format.parse(s);
        }
    }

    /**
     * String转换为DateTime类型
     * @param s
     * @return
     * @throws ParseException
     */
    public static Date StringToDateTime(String s) throws ParseException {
        java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //return format.parse(s);
        if (s == null) {
            return null;
        }else {
            return format.parse(s);
        }
    }

    /**
     * Date类型转换String
     * @param d
     * @return
     * @throws ParseException
     */
    public static String DateToString(Date d) throws ParseException {
        java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (d == null) {
            return null;
        }else {
            return format.format(d);
        }
    }

    /**
     * DateTime类型转换String
     * @param d
     * @return
     * @throws ParseException
     */
    public static String DateTimeToString(Date d) throws ParseException {
        java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (d == null) {
            return null;
        }else {
            return format.format(d);
        }
    }
}
