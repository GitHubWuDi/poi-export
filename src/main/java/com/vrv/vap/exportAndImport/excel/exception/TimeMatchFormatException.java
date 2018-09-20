
package com.vrv.vap.exportAndImport.excel.exception;

/**
 * author : wudi
 * date : 2018/9/18  10:28
 */
public class TimeMatchFormatException extends RuntimeException {

    private static final long serialVersionUID = 206910143412957809L;

    public TimeMatchFormatException() {
    }

    public TimeMatchFormatException(String message) {
        super(message);
    }

    public TimeMatchFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
