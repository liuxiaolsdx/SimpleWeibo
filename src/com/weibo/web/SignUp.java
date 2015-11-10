package com.weibo.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.weibo.model.UserInfoDao;

public class SignUp extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -968364510897096777L;
	private String account;
	private String password;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		account = request.getParameter("account");
		password = request.getParameter("password");
		
		
		UserInfoDao UserInfoDao = new UserInfoDao();
		PrintWriter out = response.getWriter();
		if(!UserInfoDao.checkAccount(account)){
			if(UserInfoDao.insertAccount(account, password)){
				out.println("你好 " + account + " !" + "注册成功！");}
		}else{
			out.println("你好 " + ",此用户名已被注册！");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		doGet(request, response);
	}

}
