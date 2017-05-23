package com.moe.wadmin.pojo;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final T data;
	private final Integer code;
	private final String message;
	
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
	
	public T getData() {
		return data;
	}
	
	public RestResponse(T data,Integer status, String message) {
		this.data = data;
		this.code = status;
		this.message = message;
	}
}