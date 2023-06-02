package com.project.ringo.model.dto;

public class DataInfo {
	private String contentType;
	private Object data;
	
	public DataInfo(String contentType, Object data) {
		super();
		this.contentType = contentType;
		this.data = data;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
