package com.weibo.model;

public class UserInfo {
	private Integer u_id;
	private String u_account;
	private String u_password;
	private String u_nickname;
	private String u_img;
	private String u_sex;
	private String u_name;
	private String u_date;
	private String u_sign;
	private String u_url;
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(Integer uId, String uAccount, String uPassword,
			String uNick, String uImg, String uSex, String uName, 
			String uDate, String uSign) {
		super();
		u_id = uId;
		u_account = uAccount;
		u_password = uPassword;
		u_nickname = uNick;
		u_img = uImg;
		u_sex = uSex;
		u_name = uName;
		u_date = uDate;
		u_sign = uSign;
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer uId) {
		u_id = uId;
	}
	public String getU_account() {
		return u_account;
	}
	public void setU_account(String uAccount) {
		u_account = uAccount;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String uPassword) {
		u_password = uPassword;
	}
	public String getU_nickname() {
		return u_nickname;
	}
	public void setU_nickname(String uNick) {
		u_nickname = uNick;
	}
	public String getU_img() {
		return u_img;
	}
	public void setU_img(String uImg) {
		u_img = uImg;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String uSex) {
		u_sex = uSex;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String uName) {
		u_name = uName;
	}
	public String getU_date() {
		return u_date;
	}
	public void setU_date(String uDate) {
		u_date = uDate;
	}
	public String getU_sign() {
		return u_sign;
	}
	public void setU_sign(String uSign) {
		u_sign = uSign;
	}
	public String getU_url() {
		return u_url;
	}
	public void setU_url(String uUrl) {
		u_url = uUrl;
	}

}
