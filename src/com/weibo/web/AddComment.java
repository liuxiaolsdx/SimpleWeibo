package com.weibo.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.weibo.model.*;

public class AddComment extends HttpServlet{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -9177281840218145911L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession s = request.getSession();
		UserInfo currUser = (UserInfo) s.getAttribute("userinfo");//get current user from session
		int uid = currUser.getU_id();
		
		String content = request.getParameter("comment");
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		Comment comm = new Comment();
		comm.setUid(uid);
		comm.setContent(content);
		comm.setBid(bid);

		CommentDao cDao = new CommentDao();

		PrintWriter out = response.getWriter();
		if(cDao.CommentPublish(comm)){
			response.sendRedirect("home");
		}else{
			out.println("评论失败，请重试");
		}

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}

}
