package com.weibo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Blog {
	
	private int bid;
	private int uid;
	private int fid;
	private int fnum;
	private int cnum;
	private String content;
	private String account;
	private String nickname;
	private String u_img;
	private List<Comment> commentList;
	private Blog blog;
	
	private Timestamp time;

	public Blog() {
	}

	public Blog(int bid,int uid,int fnum,int fid,int cnum, String content, Timestamp time, String account,String nickname,String u_img
			,List<Comment> commentList, Blog blog) {
		this.bid = bid;
		this.uid = uid;
		this.fnum = fnum;
		this.content = content;
		this.time = time;
		this.account = account;
		this.nickname = nickname;
		this.u_img = u_img;
		this.commentList=commentList;
		this.blog = blog;
		this.fid = fid;
		this.cnum=cnum;
		
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBid() {
		return this.bid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getFid() {
		return fid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getTime() {
		return time;
	}
	//user info
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
	public void setFnum(int funm){
		this.fnum=funm;
	}
	public String getU_img() {
		return u_img;
	}
	public void setU_img(String img) {
		this.u_img = img;
	}

	public int getFnum(){
		return fnum;
	}
	
	public void setCommentList(List<Comment> comments){
		this.commentList = comments;
	}
	public List<Comment> getCommentList(){
		return commentList; 
	}
	public void setContentLink(){
		this.content=BlogTransfer.GenerateLinks(content);
	}

	public void setBlog(Blog blog){
		this.blog = blog;
	}
	
	public Blog getBlog(){
		return blog;
	}
	public void setCnum(int cnum){
		this.cnum=cnum;
	}
	public int getCnum(){
		return cnum;
	}
}
