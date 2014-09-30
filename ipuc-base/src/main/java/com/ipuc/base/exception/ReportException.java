
package com.ipuc.base.exception;

/**
 *
 * @author wilson-rivera
 */
public class ReportException extends Exception {
    
    public ReportException() {
        super();
    }
    
    public ReportException(String message) {
        super(message);
    }
    
    public ReportException(String message, Exception e) {
        super(message, e);
    }
}
