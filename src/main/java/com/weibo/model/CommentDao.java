package com.weibo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weibo.DB.DB;
import com.weibo.util.WeiboLogger;


public class CommentDao {
	/**
	 * insert comment into table comments 
	 * @param com: blog's comment
	 * @return true:insert success; false:some error
	 */
	public boolean CommentPublish(Comment com) {

		String c_content = com.getContent();
		int u_id = com.getUid();
		int b_id = com.getBid();

		String strSQL = "insert into comments (c_content,u_id,b_id) values(?,?,?)";

		DB dbConn = new DB();
		int affectedRows = dbConn.executeUpdate(strSQL, new Object[] { c_content, u_id, b_id });

		dbConn.closeConn();
		return affectedRows > 0;
	}
	/**
	 * Discard!!
	 * get comments ArrayList by blog's id
	 * @param bid: blog's id ,reference to table blog
	 * @param showPageNum: show the number of comments in each blog
	 * @param currPage: current page
	 * @return
	 */
	public List<Comment> getAllCommentsByBid(int bid, int showPageNum, int currPage) {

		DB db = new DB();
		List<Comment> commentList = new ArrayList<>();
		String sql = "SELECT * FROM comments LEFT JOIN user ON comments.u_id=user.u_id "
				+ "WHERE comments.b_id = ? ORDER BY c_time DESC LIMIT ?,?";
		
		ResultSet rs = db.executeQuery(sql, new Object[] { bid, showPageNum * (currPage - 1), showPageNum });
		try {
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setBid(rs.getInt("b_id"));
				comment.setUid(rs.getInt("u_id"));
				comment.setContent(rs.getString("c_content"));
				comment.setTime(rs.getTimestamp("c_time"));
				comment.setAccount(rs.getString("u_account"));
				comment.setNickname(rs.getString("u_nickname"));
				commentList.add(comment);
			}
			return commentList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * get comments ArrayList by blog's id
	 * @param bid: blog's id ,reference to table blog
	 * @return ArrayList<Comment>
	 */
	public List<Comment> getAllCommentsByBid(int bid) {

		DB db = new DB();
		List<Comment> commentList = new ArrayList<>();
		String sql = "SELECT * FROM comments LEFT JOIN user ON comments.u_id=user.u_id "
				+ "WHERE comments.b_id = ? ORDER BY c_time ASC ";
		
		ResultSet rs = db.executeQuery(sql, new Object[] { bid });
		try {
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setBid(rs.getInt("b_id"));
				comment.setUid(rs.getInt("u_id"));
				comment.setContent(rs.getString("c_content"));
				comment.setTime(rs.getTimestamp("c_time"));
				comment.setAccount(rs.getString("u_account"));
				comment.setNickname(rs.getString("u_nickname"));
				commentList.add(comment);
			}
			return commentList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * count all comments per blog
	 * @param bid:blog's id ,reference to table blog
	 * @return comments sum
	 */
	public long getAllCommentsSum(int bid) {

		DB db = new DB();
		String sql = "SELECT count(*) FROM comments WHERE b_id = ?";
		
		ResultSet rs = db.executeQuery(sql, new Object[] { bid});
		try {
			long num = 0;
			while (rs.next()) {
				num = rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return 0;
		} finally {
			db.closeConn();
		}
	}


}
