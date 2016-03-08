package com.weibo.model;

import java.sql.Timestamp;

public class Comment {

	private int cid;
	private int uid;
	private int bid;
	private String content;
	private Timestamp time;
	private String account;
	private String nickname;

	public Comment() {
	}

	public Comment(int cid, int uid, int bid, String content, Timestamp time) {
		this.cid = cid;
		this.bid = bid;
		this.content = content;
		this.time = time;
		this.uid = uid;
	}

	public void setCid(int id) {
		cid = id;
	}

	public int getCid() {
		return cid;
	}

	public void setUid(int id) {
		uid = id;
	}

	public int getUid() {
		return uid;
	}

	public void setBid(int id) {
		bid = id;
	}

	public int getBid() {
		return bid;
	}

	public void setContent(String str) {
		content = str;
	}

	public String getContent() {
		return content;
	}

	public void setTime(Timestamp t) {
		time = t;
	}

	public Timestamp getTime() {
		return time;
	}

	// user info
	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setContentLink() {
		this.content = BlogTransfer.GenerateLinks(content);
	}

}
