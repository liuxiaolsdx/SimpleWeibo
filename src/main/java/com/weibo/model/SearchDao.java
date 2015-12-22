package com.weibo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.weibo.DB.DB;
import com.weibo.util.WeiboLogger;

public class SearchDao {
	
	public ArrayList<UserInfo> getSearchUser(final String search,int showPageNum,int currPage) {
		DB db = new DB();
		ArrayList<UserInfo> userList = new ArrayList<>();
		String sql = "select * from user where u_account like ? or u_nickname like ? limit ?,?";
		//beside current user and who has followed
		ResultSet rs = db.executeQuery(sql,new Object[]{"%"+search+"%","%"+search+"%",showPageNum*(currPage-1),showPageNum});
		try {
			while (rs.next()){
				UserInfo user = new UserInfo();
				user.setU_id(rs.getInt("u_id"));
				user.setU_account(rs.getString("u_account"));
				user.setU_nickname(rs.getString("u_nickname"));
				user.setU_sex(rs.getString("u_sex"));
				user.setU_sign(rs.getString("u_sign"));
				user.setU_name(rs.getString("u_name"));
				user.setU_date(rs.getString("u_date"));
				user.setU_img(rs.getString("u_img"));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}

	public long countSearchUser(final String search){
		DB Conn=new DB();
		String sql="select count(*) from user where u_account like ? or u_nickname like ?";
		ResultSet rs=Conn.executeQuery(sql, new Object[]{"%"+search+"%", "%"+search+"%"});
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
