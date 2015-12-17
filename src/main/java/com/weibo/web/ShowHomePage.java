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

public class ShowHomePage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6367414960108248161L;
	private Configuration cfg;

	public void init() throws ServletException {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading(getServletContext(), "./");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();//get session
		String account = (String) session.getAttribute("s_account");//get account
		
		//create some object 
		Writer out = response.getWriter();
		UserInfoDao UserInfoDao = new UserInfoDao();
		FriendsDao fDao = new FriendsDao();
		UserInfo UserInfo = new UserInfo();
		UserInfo = UserInfoDao.getUserInfoByAccount(account);//get user's information
		
		//set number of blogs per page
		int showPageNum = 4;//每页个数
		int currPage = 1;
		if(request.getParameter("p") != null)
			currPage = Integer.parseInt(request.getParameter("p"));

		//get information of blogs
		int uid=UserInfo.getU_id();
		BlogDao bDao = new BlogDao();
		ArrayList<Blog> blogList = bDao.getAllBlogByUid(uid, showPageNum, currPage);
		long counts = bDao.getAllBlogSum(uid);
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

		
		//put into DataMap
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("account", account);
		root.put("fedcount", fDao.countFollowed(uid));
		root.put("fincount", fDao.countFollowing(uid));
		root.put("p", currPage);
		root.put("totalPages", totalPages);
		root.put("blogList", blogList);
		
		Template template = cfg.getTemplate("home.ftl");
		
		try {
			template.process(root, out);
		} catch (TemplateException e) {
			WeiboLogger.exception(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
