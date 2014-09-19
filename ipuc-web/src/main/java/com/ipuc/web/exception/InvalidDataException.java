
package com.ipuc.web.exception;

/**
 *
 * @author WRIVER1
 */
public class InvalidDataException extends Exception {
    
    public InvalidDataException() {
        super();
    }
    
    public InvalidDataException(String msg) {
        super(msg);
    }
    
    public InvalidDataException(String msg, Exception e) {
        super(msg, e);
    }
    
}
