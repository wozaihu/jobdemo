package lyx.mvp.javatest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author LYX
 * @Date 2022/8/27 13:49
 */
public class JavaMain {

    private static Student student2;

    public static void main(String[] args) {
        String str1 = "你好　[ http://baidu.com ]";
        String str2 = "这个他好　[ http://baidu.com]";
        String str3 = "ssw[ ] [ [　[ http://baidu.com ]";
        String str4 = "你好　[ http://baidu.com ]后面再来一个　[ http://sina.com ]0";
        String str5 = " 　[ http://baidu.com ]";
        System.out.println("str1 ="+checkPattern(str1)); // 输出 true
        System.out.println("str2 ="+checkPattern(str2)); // 输出 false
        System.out.println("str3 ="+checkPattern(str3));
        System.out.println("str4 ="+checkPattern(str4));
        System.out.println("str5 ="+checkPattern(str5));


        System.out.println("str1 ="+replaceUrls(str1));
        System.out.println("str2 ="+replaceUrls(str2));
        System.out.println("str3 ="+replaceUrls(str3));
        System.out.println("str4 ="+replaceUrls(str4));
    }

//    public static boolean checkPattern(String str) {
//        String pattern = "　\\[\\s.*?\\s\\]";
//        return str.matches(".*" + pattern + ".*");
//    }

    public static boolean checkPattern(String str) {
        String pattern = ".*　\\[\\s.*?\\s\\]$";
        return str.matches(pattern);
    }

    public static String replaceUrls(String str) {
        String pattern = "　\\[\\s.*?\\s\\]";
        return str.replaceAll(pattern, "📎网页链接");
    }
}
