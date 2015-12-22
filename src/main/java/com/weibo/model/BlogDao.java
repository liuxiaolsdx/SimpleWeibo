package com.weibo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import java.sql.Timestamp;

import com.weibo.DB.DB;
import com.weibo.util.WeiboLogger;

public class BlogDao {
	/**
	 * add blog into table blog
	 * @param blog: blog object
	 * @return true: success; false: DB error
	 */
	public boolean blogpublish(Blog blog) {

		String b_content = blog.getContent();
		// Timestamp b_time = blog.getTime();
		int u_id = blog.getUid();
		int b_fid = blog.getFid();

		String strSQL = "insert into blog (b_content, u_id,b_fid) values(?,?,?)";//auto generate blog's current time by MySql Timestamp

		DB dbConn = new DB();
		int affectedRows = dbConn.executeUpdate(strSQL, new Object[] { b_content, u_id, b_fid });

		dbConn.closeConn();
		return affectedRows > 0;
	}
	/**
	 * get blog by it's bid
	 * @param bid
	 * @return blog
	 */
	public Blog getBlogByBid(int bid){
		DB db = new DB();
		String sql = "SELECT * FROM blog LEFT JOIN user ON blog.u_id=user.u_id WHERE blog.b_id=?";
		// current user and who is following
		ResultSet rs = db.executeQuery(sql, new Object[] { bid });
		Blog blog = new Blog();
		try {
			while (rs.next()) {
				blog.setBid(rs.getInt("b_id"));
				blog.setUid(rs.getInt("u_id"));
				blog.setContent(rs.getString("b_content"));
				blog.setTime(rs.getTimestamp("b_time"));
				blog.setAccount(rs.getString("u_account"));
				blog.setNickname(rs.getString("u_nickname"));
				blog.setFnum(rs.getInt("b_fnum"));
				blog.setU_img(rs.getString("u_img"));
				blog.setCnum(rs.getInt("b_cnum"));
			}
			return blog;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
		
	}

	/**
	 * get my blog and all following's blog
	 * @param uid: current usr's id
	 * @param showPageNum: the mumber of blogs per page
	 * @param currPage: current page
	 * @return ArrayList<Blog>
	 */
	public ArrayList<Blog> getAllBlogByUid(int uid, int showPageNum, int currPage) {

		DB db = new DB();
		ArrayList<Blog> blogList = new ArrayList<>();
		String sql = "SELECT * FROM blog LEFT JOIN user ON blog.u_id=user.u_id "
				+ "WHERE blog.u_id=? OR blog.u_id=ANY(SELECT r_fid FROM relationship WHERE r_uid=?) "
				+ "ORDER BY b_time DESC LIMIT ?,?";
		// current user and who is following
		ResultSet rs = db.executeQuery(sql, new Object[] { uid, uid, showPageNum * (currPage - 1), showPageNum });
		try {
			while (rs.next()) {
				Blog blog = new Blog();
				//judge the blog whether forward
				int fid=rs.getInt("b_fid");
				if(fid!=0){
					Blog OrgBlog = getBlogByBid(fid);//if it is a 'forwardblog' then get original blog
					blog.setBlog(OrgBlog);
				}
				blog.setBid(rs.getInt("b_id"));
				blog.setUid(rs.getInt("u_id"));
				blog.setContent(rs.getString("b_content"));
				blog.setTime(rs.getTimestamp("b_time"));
				blog.setAccount(rs.getString("u_account"));
				blog.setNickname(rs.getString("u_nickname"));
				blog.setFnum(rs.getInt("b_fnum"));
				blog.setU_img(rs.getString("u_img"));
				blog.setCnum(rs.getInt("b_cnum"));
				blogList.add(blog);
			}
			return blogList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}
	/**
	 * get all my blog 
	 * @param uid: current usr's id
	 * @param showPageNum: the mumber of blogs per page
	 * @param currPage: current page
	 * @return ArrayList<Blog>
	 */
	public ArrayList<Blog> getAllMyBlogByUid(int uid, int showPageNum, int currPage) {

		DB db = new DB();
		ArrayList<Blog> blogList = new ArrayList<>();
		String sql = "SELECT * FROM blog LEFT JOIN user ON blog.u_id=user.u_id "
				+ "WHERE blog.u_id=? ORDER BY b_time DESC LIMIT ?,?";
		// current user and who is following
		ResultSet rs = db.executeQuery(sql, new Object[] { uid, showPageNum * (currPage - 1), showPageNum });
		try {
			while (rs.next()) {
				Blog blog = new Blog();
				//judge the blog whether forward
				int fid=rs.getInt("b_fid");
				if(fid!=0){
					Blog OrgBlog = getBlogByBid(fid);//if it is a 'forwardblog' then get original blog
					blog.setBlog(OrgBlog);
				}
				blog.setBid(rs.getInt("b_id"));
				blog.setUid(rs.getInt("u_id"));
				blog.setContent(rs.getString("b_content"));
				blog.setTime(rs.getTimestamp("b_time"));
				blog.setAccount(rs.getString("u_account"));
				blog.setNickname(rs.getString("u_nickname"));
				blog.setFnum(rs.getInt("b_fnum"));
				blog.setU_img(rs.getString("u_img"));
				blog.setCnum(rs.getInt("b_cnum"));
				blogList.add(blog);
			}
			return blogList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
	}

	/**
	 * count my blog and all following blog
	 * @param uid: current usr's id
	 * @return blogs sum
	 */
	public long getAllBlogSum(int uid) {

		DB db = new DB();
		String sql = "SELECT count(*) FROM blog LEFT JOIN user ON blog.u_id=user.u_id "
				+ "WHERE blog.u_id=? OR blog.u_id=ANY(SELECT r_fid FROM relationship WHERE r_uid=?)";
		// current user and who is following
		ResultSet rs = db.executeQuery(sql, new Object[] {uid, uid});
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
	/**
	 * count all my blog 
	 * @param uid: current usr's id
	 * @return blogs sum
	 */
	public long getAllMyBlogSum(int uid) {

		DB db = new DB();
		String sql = "SELECT count(*) FROM blog LEFT JOIN user ON blog.u_id=user.u_id WHERE blog.u_id=?";
		// current user and who is following
		ResultSet rs = db.executeQuery(sql, new Object[] {uid});
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
	/**
	 * overload
	 * add blog into table blog
	 * @param blog: blog object
	 * @return true: success; false: DB error
	 */
	public boolean blogpublish(Blog blog,int uid) {

		String b_content = blog.getContent();
		// Timestamp b_time = blog.getTime();

		String strSQL = "insert into blog (b_content, u_id) values(?,?)";//auto generate blog's current time by MySql Timestamp

		DB dbConn = new DB();
		int affectedRows = dbConn.executeUpdate(strSQL, new Object[] { b_content, uid });

		dbConn.closeConn();
		return affectedRows > 0;
	}
	/**
	 * add blog's forward number
	 * @param bid
	 * @param oldFNum
	 * @return
	 */
	public boolean addForwardNum(int bid,int oldFNum){
		DB db = new DB();
		String addsql = "update blog set b_fnum = ? where b_id=?";
		int affectedRows = db.executeUpdate(addsql, new Object[] { oldFNum+1, bid});
		db.closeConn();
		return affectedRows > 0;
	}
	/**
	 * forward blog
	 * @param oldCollectNum
	 * @param bid
	 * @param uid
	 * @return true success
	 */
	public boolean CollectBlog(int oldCollectNum,int bid,int uid)
	{
//		if(!blogpublish(blog,uid)) return false;
		DB db = new DB();
		String addsql = "update blog set b_cnum = ? where b_id=?";
		String insertsql = "insert into collection (u_id,b_id) values(?,?)";
		int affectedRows = db.executeUpdate(addsql, new Object[] { oldCollectNum+1, bid});
		int affectedRows2 = db.executeUpdate(insertsql, new Object[] { uid, bid});
		db.closeConn();
		return (affectedRows > 0)&&(affectedRows2 > 0);
		
	}
	/**
	 * count the number of blog forward
	 * @param bid
	 * @return
	 */
	public int countForwardBlog(int bid)
	{
		DB db = new DB();
		String sql = "select b_fnum from blog where b_id=?";
		ResultSet rs = db.executeQuery(sql, new Object[]{bid});
		int res=0;
		try {
			while(rs.next()){
				res = rs.getInt("b_fnum");
			}
			return res;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return 0;
		}finally{
			db.closeConn();
		}
	}
	/**
	 * get current user's collect blogs
	 * @param uid
	 * @param showPageNum
	 * @param currPage
	 * @return true success
	 */
	public ArrayList<Blog> getCollectBlog(int uid,int showPageNum, int currPage)
	{
		ArrayList<Blog> blogList = new ArrayList<>();
		
		DB db = new DB();
		String sql = "SELECT * FROM collection LEFT JOIN blog ON collection.b_id=blog.b_id LEFT JOIN user ON blog.u_id=user.u_id "
				+ "WHERE collection.u_id=? ORDER BY b_time DESC LIMIT ?,?";
		ResultSet rs = db.executeQuery(sql, new Object[] { uid, showPageNum * (currPage - 1), showPageNum });
		try {
			while (rs.next()) {
				Blog blog = new Blog();
				//judge the blog whether forward
				int fid=rs.getInt("b_fid");
				if(fid!=0){
					Blog OrgBlog = getBlogByBid(fid);//if it is a 'forwardblog' then get original blog
					blog.setBlog(OrgBlog);
				}
				blog.setBid(rs.getInt("b_id"));
				blog.setUid(rs.getInt("user.u_id"));
				blog.setContent(rs.getString("b_content"));
				blog.setTime(rs.getTimestamp("b_time"));
				blog.setAccount(rs.getString("u_account"));
				blog.setNickname(rs.getString("u_nickname"));
				blog.setFnum(rs.getInt("b_fnum"));
				blog.setU_img(rs.getString("u_img"));
				blog.setCnum(rs.getInt("b_cnum"));
				blogList.add(blog);
			}
			return blogList;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		} finally {
			db.closeConn();
		}
		
	}
	/**
	 * count my forward blog
	 * @param uid: current usr's id
	 * @return  sum
	 */
	public long getAllCollectBlogSum(int uid) {

		DB db = new DB();
		String sql = "SELECT count(*) FROM collection WHERE collection.u_id=?";
		// current user and who is following
		ResultSet rs = db.executeQuery(sql, new Object[] {uid});
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
