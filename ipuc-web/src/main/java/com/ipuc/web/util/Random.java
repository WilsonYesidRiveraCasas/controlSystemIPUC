
package com.ipuc.web.util;

/**
 *
 * @author wilson-rivera
 */
public class Random {
    
    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";

    private static final String chars_numbers = "0123456789";

    public static String generateName() {
        return System.currentTimeMillis() + Random.generateString(10);
    }

    public static String generateString(int length) {
        String str = "";
        for(int i = 0; i < length; i++) {
            int numRandom = (int) (Math.random() * chars.length());
            str += chars.charAt(numRandom);
        }

        return str;
    }

    public static String generateStringNumber(int length) {
        String str = "";
        for(int i = 0; i < length; i++) {
            int numRandom = (int) (Math.random() * chars_numbers.length());
            str += chars_numbers.charAt(numRandom);
        }

        return str;
    }
    
}
