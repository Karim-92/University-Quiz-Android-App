package com.smartUniversity.listactivity.model;


public class Courses {
	
	private String course_name;
	private boolean subscribeState=false;
	private String course_id;
	
	public String getName() {
		return course_name;
	}
	public void setName(String name) {
		this.course_name = name;
	}
	public boolean isSubscribeState() {
		return subscribeState;
	}
	public void setSubscribeState(boolean subscribeState) {
		this.subscribeState = subscribeState;
	}
	public String getId() {
		return course_id;
	}
	public void setId(String id) {
		this.course_id = id;
	}
}
