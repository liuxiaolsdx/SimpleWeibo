package com.weibo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.weibo.model.UserInfo;

public class SignOut extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5229172479901375942L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getSession().invalidate();
		setCookie(response);
		response.sendRedirect("index.html");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void setCookie(HttpServletResponse response) throws ServletException, IOException {
        Cookie c1 = new Cookie("userName","");
        Cookie c2 = new Cookie("password", "");
        c1.setMaxAge(0) ;
        c2.setMaxAge(0);
        response.addCookie(c1);
        response.addCookie(c2);
	}

}
