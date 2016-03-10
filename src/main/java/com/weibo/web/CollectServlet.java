package com.weibo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weibo.model.Blog;
import com.weibo.model.BlogDao;
import com.weibo.model.UserInfo;

public class CollectServlet extends HttpServlet{

	private static final long serialVersionUID = -2237692493657555760L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int bid =Integer.parseInt(request.getParameter("b"));
		
		BlogDao bDao = new BlogDao();
		
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("userinfo");
		
		int uid =user.getU_id();
		Blog blog=bDao.getBlogByBid(bid);
		
		if(bDao.CollectBlog(blog.getCnum(), bid, uid))
		{
			response.sendRedirect("home");			
		}else{
			response.sendRedirect("collectRs.html");
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
