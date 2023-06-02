package com.project.ringo.model.dto;
public class PageInfo {
	private boolean isForward;
	private String path;
	
	public PageInfo(boolean isForward, String path) {
		super();
		this.isForward = isForward;
		this.path = path;
	}
	public PageInfo(String path) {
		this(true, path);
	}

	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
