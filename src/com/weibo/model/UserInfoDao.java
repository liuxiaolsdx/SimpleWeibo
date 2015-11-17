package com.weibo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.weibo.DB.DB;

public class UserInfoDao {
	/**
	 * check the current user is or not saved in table user
	 * @param account : user's account
	 * @param password :user password
	 * @return true : YES
	 */
	public boolean checkUser(String account, String password) {
		DB db = new DB();
		db.getConnection();
		String sql = "select * from user where u_account=? and u_password=?";
		ResultSet rs = db.executeQuery(sql, new Object[] { account, password });
		try {
			if (rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * if add user success
	 * @param account
	 * @param password
	 * @return true : success
	 */
	public boolean insertAccount(String account, String password) {
		DB db = new DB();
		db.getConnection();
		String sql = "insert into user(u_account,u_password) values(?,?)";
		int rs = 0;
		rs = db.executeUpdate(sql, new Object[] { account, password });
		db.closeConn();
		return rs > 0 ? true : false;
	}
	/**
	 * whether or not update user information success
	 * @param account
	 * @param userinfo : object user
	 * @return true : success
	 */
	public boolean updateUserInfo(String account, UserInfo userinfo) {
		String u_nickname = userinfo.getU_nickname();
		String u_name = userinfo.getU_name();
		String u_sex = userinfo.getU_sex();
		String u_sign = userinfo.getU_sign();
		String u_img = userinfo.getU_img();

		DB db = new DB();
		db.getConnection();
		String sql = "update user set u_nickname=?,u_name=?,u_sex=?,u_sign=?,u_img=? where u_account = ?";
		int rs = 0;
		rs = db.executeUpdate(sql, new Object[] { u_nickname, u_name, u_sex, u_sign, u_img, account });
		db.closeConn();
		return rs > 0 ? true : false;
	}
	/**
	 * get user information by account
	 * @param account
	 * @return object user
	 */
	public UserInfo getUserInfoByAccount(final String account) {
		String strSQL = "select * from user where u_account = ?";
		DB dbConn = new DB();
		ResultSet rs = dbConn.executeQuery(strSQL, new Object[] { account });
		UserInfo userInfo = new UserInfo();
		try {
			while (rs.next()) {
				userInfo.setU_id(rs.getInt("u_id"));
				userInfo.setU_account(account);
				userInfo.setU_password(rs.getString("u_password"));
				userInfo.setU_nickname(rs.getString("u_nickname"));
				userInfo.setU_img(rs.getString("u_img"));
				userInfo.setU_sex(rs.getString("u_sex"));
				userInfo.setU_sign(rs.getString("u_sign"));
				userInfo.setU_name(rs.getString("u_name"));
				userInfo.setU_date(rs.getString("u_date"));
			}
			return userInfo;
		} catch (Exception e) {
			return null;
		} finally {
			dbConn.closeConn();
		}
	}
	/**
	 * whether the account is in table user
	 * @param account
	 * @return true:YES
	 */
	public boolean checkAccount(String account) {
		DB db = new DB();
		db.getConnection();
		String sql = "select * from user where u_account=?";
		ResultSet rs = db.executeQuery(sql, new Object[] { account });
		try {
			if (rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * whether the password is in table user
	 * @param password
	 * @return true:YES
	 */
	public boolean checkPassword(String password) {
		DB db = new DB();
		db.getConnection();
		String sql = "select * from user where u_password=?";
		ResultSet rs = db.executeQuery(sql, new Object[] { password });
		try {
			if (rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * get some unfollowing user 
	 * @param uid
	 * @param showPageNum
	 * @param currPage
	 * @return ArrayList<UserInfo>
	 */
	public ArrayList<UserInfo> getSomeUser(int uid,int showPageNum,int currPage) {
		DB db = new DB();
		ArrayList<UserInfo> userList = new ArrayList<UserInfo>();
		String sql = "select * from user where u_id<>? and u_id not in(select r_fid from relationship where r_uid=?)"
				+ "order by rand() limit ?,?";
		//beside current user and who has followed
		ResultSet rs = db.executeQuery(sql,new Object[]{uid, uid,showPageNum*(currPage-1),showPageNum});
		try {
			while (rs.next()){
				UserInfo user = new UserInfo();
				user.setU_id(rs.getInt("u_id"));
				user.setU_account(rs.getString("u_account"));
				user.setU_nickname(rs.getString("u_nickname"));
				user.setU_img(rs.getString("u_img"));
				user.setU_sex(rs.getString("u_sex"));
				user.setU_sign(rs.getString("u_sign"));
				user.setU_name(rs.getString("u_name"));
				user.setU_date(rs.getString("u_date"));
				userList.add(user);
//				counts++;
			}
			return userList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * cout users unfollowing
	 * @param id : current user id
	 * @return unfollowing sum
	 */
	public long countUnfollowing(final int id){
		DB Conn=new DB();
		String sql="select count(*) from user where u_id<>? and u_id not in(select r_fid from relationship where r_uid=?)";
		ResultSet rs=Conn.executeQuery(sql, new Object[]{id,id});
		try {
			long num=0;
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			Conn.closeConn();
		}
	}

}
