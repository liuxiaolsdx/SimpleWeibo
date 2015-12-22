package com.weibo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.weibo.DB.DB;
import com.weibo.util.WeiboLogger;

public class FriendsDao {
	/**
	 * new following
	 * @param uid : current user
	 * @param fid : whom current user will follow
	 * @return true: success; false: error
	 */
	public boolean addFollowing (int uid,int fid){
		DB db = new DB();
		String sql = "insert into relationship (r_uid, r_fid) values (?, ?)";
		int rs = 0;
		if(!isFollowing(uid,fid))
			rs = db.executeUpdate(sql, new Object[] { uid, fid });
		db.closeConn();
		return rs>0;
	}
	/**
	 * uid is (or not) following fid
	 * @param uid
	 * @param fid
	 * @return ture:uid has followed fid; false: not
	 */
	public boolean isFollowing(int uid,int fid){
		DB db=new DB();
		String sql = "select * from relationship where r_uid=? and r_fid=?";
		ResultSet rs = db.executeQuery(sql, new Object[] { uid, fid });
		try {
			return rs.next();
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return false;
		}finally{
			db.closeConn();
		}

	}
	/**
	 * delete following relationship
	 * @param uid
	 * @param fid
	 * @return true : success
	 */
	public boolean deleFollowing(int uid,int fid){
		DB db=new DB();
		String sql = "delete from relationship where r_uid=? and r_fid=?";
		int rs = db.executeUpdate(sql, new Object[] { uid, fid });
		db.closeConn();
		return rs>0;
	}
	/**
	 * count user's fans/followed
	 * @param id
	 * @return fans sum
	 */
	public long countFollowed(final int id){
		DB Conn=new DB();
		String sql="select count(*) from relationship where r_fid=?";
		ResultSet rs=Conn.executeQuery(sql, new Object[]{id});
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
	/**
	 * count user's following
	 * @param id
	 * @return following sum
	 */
	public long countFollowing(final int id){
		DB Conn=new DB();
		String sql="select count(*) from relationship where r_uid=?";
		ResultSet rs=Conn.executeQuery(sql, new Object[]{id});
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
	/**
	 * get your current following's infomation
	 * @param uid :current user
	 * @param showPageNum : number of per page
	 * @param currPage : current page
	 * @return ArrayList<UserInfo> following
	 */
	public ArrayList<UserInfo> getFollowing(int uid,int showPageNum,int currPage) {
		DB db = new DB();
		ArrayList<UserInfo> userList = new ArrayList<>();
		String sql = "select * from user where u_id=any(select r_fid from relationship where r_uid=?)"
				+ " limit ?,?";
		//beside current user and who has followed
		ResultSet rs = db.executeQuery(sql,new Object[]{uid,showPageNum*(currPage-1),showPageNum});
		try {
			while (rs.next()){
				UserInfo user = new UserInfo();
				user.setU_id(rs.getInt(1));
				user.setU_account(rs.getString(2));
				user.setU_nickname(rs.getString(4));
				user.setU_img(rs.getString(5));
				user.setU_sex(rs.getString(6));
				user.setU_sign(rs.getString(7));
				user.setU_name(rs.getString(8));
				user.setU_date(rs.getString(9));
				userList.add(user);
//				counts++;
			}
			return userList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * get your current followed
	 * @param uid :current user
	 * @param showPageNum : number of per page
	 * @param currPage : current page
	 * @return ArrayList<UserInfo> followed
	 */
	public ArrayList<UserInfo> getFollowed(int uid,int showPageNum,int currPage) {
		DB db = new DB();
		ArrayList<UserInfo> userList = new ArrayList<>();
		String sql = "select * from user where u_id=any(select r_uid from relationship where r_fid=?)"
				+ " limit ?,?";
		//beside current user and who has followed
		ResultSet rs = db.executeQuery(sql,new Object[]{uid,showPageNum*(currPage-1),showPageNum});
		try {
			while (rs.next()){
				UserInfo user = new UserInfo();
				user.setU_id(rs.getInt(1));
				user.setU_account(rs.getString(2));
				user.setU_nickname(rs.getString(4));
				user.setU_img(rs.getString(5));
				user.setU_sex(rs.getString(6));
				user.setU_sign(rs.getString(7));
				user.setU_name(rs.getString(8));
				user.setU_date(rs.getString(9));
				userList.add(user);
//				counts++;
			}
			return userList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}
}
