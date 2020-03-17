package com.csx.basic.exception;

/**
 * 
 * 自定义常见类型错误
 * 
 * @author Cuisx
 *
 */
public class MyException extends Exception {

	public MyException() {
		
	}

	public MyException(String message) {
		super(message);
	}

	public String NotFoundErr(int id) {
		return id+"不存在!!!";
	}
	
	
	
	
}
