package com.weibo.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.weibo.model.*;

public class WriteWeibo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6509141941469347992L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession s = request.getSession();
		UserInfo currUser = (UserInfo) s.getAttribute("userinfo");
		int uid = currUser.getU_id();

		// Timestamp currentTime = new Timestamp(new Date().getTime());

		String content = request.getParameter("content");

		Blog blog = new Blog();

		blog.setUid(uid);
		blog.setContent(content);
		// blog.setTime(currentTime);

		BlogDao bDao = new BlogDao();

		PrintWriter out = response.getWriter();
		if (bDao.blogpublish(blog)) {
			response.sendRedirect("home");
		} else {
			out.println("发布失败，请重试");
		}

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}

}