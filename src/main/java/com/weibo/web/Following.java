package com.weibo.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.weibo.model.*;

public class Following extends HttpServlet{
	
	private static final long serialVersionUID = 6390367354510928455L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		int fid = Integer.parseInt(request.getParameter("f"));
		HttpSession s = request.getSession();
		UserInfo currUser = (UserInfo) s.getAttribute("userinfo");
		int uid = currUser.getU_id();
		FriendsDao fDao = new FriendsDao();
		PrintWriter out = response.getWriter();
		if(fDao.addFollowing(uid, fid)){
			out.println("关注成功！");
		}else{
			out.print("关注失败！此用户你已经关注了！");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
