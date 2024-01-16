package com.example.jobdemo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DjtChangeUrl {
    public static boolean checkPattern(String str) {
        String pattern = ".*　\\[\\s.*?\\s\\]$";
        return str.matches(pattern);
    }

    public static String replaceUrls(String str) {
        String pattern = "　\\[\\s.*?\\s\\]";
        return str.replaceAll(pattern, "📎网页链接");
    }

    public static String getUrl(String str) {
        String pattern = "　\\[\\s.*?\\s\\]";
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String findLastBracketContent(String str) {
        // 匹配最后一个中括号内的内容
        Pattern pattern = Pattern.compile("\\[\\s*([^\\[\\]]+)\\s*\\][^\\[\\]]*$");
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null; // 如果没有找到匹配的结果，返回null
    }

    public static List<String> findUrls(String str) {
        List<String> urls = new ArrayList<>();
        String pattern = "　\\[\\s.*?\\s\\]";
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        while (matcher.find()) {
            String url = matcher.group().replaceAll("　\\[\\s|\\s\\]", "");
            urls.add(url);
        }
        return urls;
    }

    public static String findUrl(String str) {
        if (checkPattern(str)) {
            int startIndex = str.lastIndexOf("[ ");
            int endIndex = str.lastIndexOf(" ]");
            if (startIndex != -1 && endIndex != -1 && endIndex - startIndex > 0) {
                String content = str.substring(startIndex + 2, endIndex);
                return content.trim();
            }
        }
        return null;
    }
}
