package com.qtc.hospitalcore.domain.exception;

public abstract class PPException extends RuntimeException {
    public PPException(String msg) {
        super(msg);
    }
}
