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

import com.weibo.model.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class ShowCollectBlog extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1761524385203771146L;
	private Configuration cfg;

	public void init() throws ServletException {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading(getServletContext(), "./");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		UserInfo user = new UserInfo();
		user = (UserInfo) session.getAttribute("userinfo");
		
		int uid = user.getU_id();
		BlogDao bDao = new BlogDao();
		//set number of blogs per page
		int showPageNum = 4;//每页个数
		int currPage = 1;
		if(request.getParameter("p") != null)
			currPage = Integer.parseInt(request.getParameter("p"));

		
		ArrayList <Blog> CollectBlogList = bDao.getCollectBlog(uid, showPageNum, currPage);
		long counts = bDao.getAllCollectBlogSum(uid);
		int totalPages = (int)counts/showPageNum + ((counts%showPageNum)>0?1:0);
		
		//get comments
		CommentDao cDao = new CommentDao();
		for(Blog b:CollectBlogList){
			ArrayList<Comment> CommentList = cDao.getAllCommentsByBid(b.getBid());
			b.setCommentList(CommentList);
			b.setContentLink();
			for(Comment c:CommentList){
				c.setContentLink();
			}
		}

		//put into DataMap
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("account", user.getU_account());
		root.put("p", currPage);
		root.put("totalPages", totalPages);
		root.put("CollectBlogList", CollectBlogList);
		
		Template template = cfg.getTemplate("CollectBlog.ftl");
		Writer out = response.getWriter();
		try {
			template.process(root, out );
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
