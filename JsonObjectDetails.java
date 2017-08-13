package com.data.pojo;

import java.util.ArrayList;

public class JsonObjectDetails {

	private String action;
	private Long changeSize;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Long getChangeSize() {
		return changeSize;
	}
	public void setChangeSize(Long changeSize) {
		this.changeSize = changeSize;
	}
	public boolean isAnon() {
		return isAnon;
	}
	public void setAnon(boolean isAnon) {
		this.isAnon = isAnon;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<JsonObjectDetails> getJsonObjectDetailsList() {
		return jsonObjectDetailsList;
	}
	public void setJsonObjectDetailsList(ArrayList<JsonObjectDetails> jsonObjectDetailsList) {
		this.jsonObjectDetailsList = jsonObjectDetailsList;
	}
	private boolean isAnon;
	private String pageTitle;
	private String url;
	private String user;
	private ArrayList<JsonObjectDetails> jsonObjectDetailsList; 
}
