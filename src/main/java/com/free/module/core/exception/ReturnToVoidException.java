package com.free.module.core.exception;

public class ReturnToVoidException extends Exception{

    private static final long serialVersionUID = 1L;

    public ReturnToVoidException(String sFieldName){
        super(sFieldName, new Throwable("is null."));
    }
}
