
package com.ipuc.base.exception;

/**
 *
 * @author wilson-rivera
 */
public class NotSendMailException extends Exception {
    
    public NotSendMailException() {
        super();        
    }
    
    public NotSendMailException(String message) {
        super(message);
    }
    
    public NotSendMailException(String message, Exception e) {
        super(message, e);
    }
    
}
