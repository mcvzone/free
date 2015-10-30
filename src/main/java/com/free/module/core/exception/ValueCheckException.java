package com.free.module.core.exception;

public class ValueCheckException extends Exception{

	private static final long serialVersionUID = 1L;

	public ValueCheckException(String sFieldName){
		super(sFieldName, new Throwable("is null."));
	}
}
