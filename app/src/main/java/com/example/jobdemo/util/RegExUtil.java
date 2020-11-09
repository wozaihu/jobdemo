package com.example.jobdemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {
    private static RegExUtil regExUtil = new RegExUtil();

    private RegExUtil() {
    }

    public static RegExUtil getInstance() {
        return regExUtil;
    }

    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@.+$");
        Matcher matcher = pattern.matcher(email);
        boolean isMatching = matcher.matches();
        return isMatching;
    }
}
