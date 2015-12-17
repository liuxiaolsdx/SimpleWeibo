package com.weibo.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.weibo.model.*;

public class DeleteFollowing extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6390367354510928455L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		int fid = Integer.parseInt(request.getParameter("f"));//get Following ID from Parameter
		//get current user's information from session
		HttpSession s = request.getSession();
		UserInfo currUser = (UserInfo) s.getAttribute("userinfo");
		int uid = currUser.getU_id();
		FriendsDao fDao = new FriendsDao();
		//simple response
		PrintWriter out = response.getWriter();
		if(fDao.deleFollowing(uid, fid)){
			out.println("取消关注成功！");
		}else{
			out.print("取消关注失败！");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
