package com.weibo.model;

public class Friends {
	private int id;
	private int uid;
	private int fid;
	private int state;
	
	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Friends(int fId, int fUid, int fGid, int fState) {
		super();
		id = fId;
		uid = fUid;
		fid = fGid;
		state = fState;
	}
	
	public int getId(){
		return id;
	}
	public int getUid(){
		return uid;
	}
	public int getFid(){
		return fid;
	}
	public int getState(){
		return state;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public void setUid(int uid){
		this.uid=uid;
	}
	public void setFid(int fid){
		this.fid=fid;
	}
	public void setState(int state){
		this.state=state;
	}

}
