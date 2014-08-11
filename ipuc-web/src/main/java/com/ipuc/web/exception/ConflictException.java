package com.ipuc.web.exception;

/**
 *
 * @author Wilson Rivera
 */
public class ConflictException extends Exception{
 
    private static final long serialVersionUID = 1L;

    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }
    
}
