package com.welyab.margelet;

public class MargeletException extends RuntimeException {

    public MargeletException() {
    }

    public MargeletException(String message) {
	super(message);
    }

    public MargeletException(Throwable cause) {
	super(cause);
    }

    public MargeletException(String message, Throwable cause) {
	super(message, cause);
    }
}
