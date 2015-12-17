package com.weibo.web;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weibo.model.Blog;
import com.weibo.model.BlogDao;
import com.weibo.model.Comment;
import com.weibo.model.CommentDao;
import com.weibo.model.FriendsDao;
import com.weibo.model.UserInfo;
import com.weibo.model.UserInfoDao;
import com.weibo.util.WeiboLogger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class OtherUserHome extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7126647486691802430L;
	private Configuration cfg;

	public void init() throws ServletException {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading(getServletContext(), "./");  
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		
		String account = request.getParameter("u");
		UserInfoDao uDao = new UserInfoDao();
		BlogDao bDao = new BlogDao();
		UserInfo user = uDao.getUserInfoByAccount(account);
		//set number of blogs per page
		int showPageNum = 4;//每页个数
		int currPage = 1;
		if(request.getParameter("p") != null)
			currPage = Integer.parseInt(request.getParameter("p"));
		//get blog
		ArrayList<Blog> blogList = bDao.getAllMyBlogByUid(user.getU_id(), showPageNum, currPage);
		long counts = bDao.getAllMyBlogSum(user.getU_id());
		int totalPages = (int)counts/showPageNum + ((counts%showPageNum)>0?1:0);
		
		//get comments
		CommentDao cDao = new CommentDao();
		for(Blog b:blogList){
			Blog orgblog = b.getBlog();
			if(orgblog!=null){
				ArrayList<Comment> orgCommentList = cDao.getAllCommentsByBid(orgblog.getBid());
				orgblog.setCommentList(orgCommentList);
				orgblog.setContentLink();
				for(Comment c:orgCommentList){
					c.setContentLink();
				}
			}
			ArrayList<Comment> CommentList = cDao.getAllCommentsByBid(b.getBid());
			b.setCommentList(CommentList);
			b.setContentLink();
			for(Comment c:CommentList){
				c.setContentLink();
			}
		}
		
		//whether is following
		HttpSession session = request.getSession();
		UserInfo currtUser = new UserInfo();
		currtUser = (UserInfo) session.getAttribute("userinfo");

		boolean isFollowing = false;
		FriendsDao fDao = new FriendsDao();
		if(fDao.isFollowing(currtUser.getU_id(), user.getU_id()))
			isFollowing = true;
		
		Map<String,Object> root = new HashMap<>();
		root.put("user", user);
		root.put("blogList", blogList);
		root.put("friendFlag", isFollowing);
		root.put("p", currPage);
		root.put("totalPages", totalPages);

		
		Template template = cfg.getTemplate("OtherUserHome.ftl");
		Writer out = response.getWriter();
		try {
			template.process(root, out);
		} catch (TemplateException e) {
			WeiboLogger.exception(e);
		}
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
