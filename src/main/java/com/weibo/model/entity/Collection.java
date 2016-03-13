package com.weibo.model.entity;


public class Collection {
	private int sid;
	private int uid;
	private int bid;
	public Collection() {
	}

	public Collection(int sid, int uid,int bid) {
		this.sid = sid;
		this.uid = uid;
		this.bid = bid;
	}

	public void setSid(int sid){
		this.sid = sid;
	}
	
	public int getSid(){
		return sid;
	}
	public void setUid(int uid){
		this.uid = uid;
	}
	
	public int getUid(){
		return uid;
	}
	public void setBid(int bid){
		this.bid = bid;
	}
	
	public int getBid(){
		return bid;
	}



}
