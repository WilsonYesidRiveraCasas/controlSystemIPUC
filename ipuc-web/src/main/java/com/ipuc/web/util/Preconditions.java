package com.ipuc.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wilson-rivera
 */
public final class Preconditions {
    
    private Preconditions() {
        
    }

    public static void notNull(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String value, String message) throws IllegalArgumentException {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static String getNotEmpty(String value, String message) throws IllegalArgumentException {
        notEmpty(value, message);
        return value;
    }
    
    public static Date notNullDate(String value, String message) throws IllegalArgumentException {
        notEmpty(value, message);
        return getDate(value);       
    }
    
    public static Date getDate(String value) {
        if(value == null || value.isEmpty()) {
            return null;
        }
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/dd/yyyy");
        Date nuevaFecha = null;
        try {
            nuevaFecha = formatoFecha.parse(value);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Formato de fecha inválida");
        }
        
        return nuevaFecha;
        
    }
    
    public static void notLength(String value, int length, String messageNull, String messageLength) throws IllegalArgumentException {
        notEmpty(value, messageNull);
        if(value.length() > length) {
            throw new IllegalArgumentException(messageLength);
        }
    }

}
