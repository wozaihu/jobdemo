package com.example.jobdemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class RegExUtil {
    private static final RegExUtil REG_EX_UTIL = new RegExUtil();
    private RegExUtil() {
    }

    public static RegExUtil getInstance() {
        return REG_EX_UTIL;
    }

    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@.+$");
        Matcher matcher = pattern.matcher(email);
        boolean isMatching = matcher.matches();
        return isMatching;
    }
}
