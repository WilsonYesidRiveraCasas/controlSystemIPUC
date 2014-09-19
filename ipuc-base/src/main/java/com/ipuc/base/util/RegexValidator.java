package com.ipuc.base.util;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos Sepulveda
 */
public class RegexValidator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidateEmail(final String txt) {
        return RegexValidator.isValidate(txt, EMAIL_PATTERN);
    }
    
    private static boolean isValidate(final String txt, final String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txt);
        return matcher.matches();
        
    }
}