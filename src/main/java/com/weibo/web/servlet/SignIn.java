package com.weibo.web.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.weibo.model.dao.UserInfoDao;
import com.weibo.model.entity.UserInfo;


/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4382557448405001203L;
	private String account;
	private String password;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
//		UserInfo UserInfo = (UserInfo) session.getAttribute("userinfo");
		
		if (request.getParameter("account") != null&&request.getParameter("password")!=null) {
			account = request.getParameter("account");
			password = request.getParameter("password");
		} 
//		else {
//			account = UserInfo.getU_account();
//			password = UserInfo.getU_password();
//		}

		UserInfoDao UserInfoDao = new UserInfoDao();
		PrintWriter out = response.getWriter();
		
		if (UserInfoDao.checkAccount(account)) {
			if (UserInfoDao.checkUser(account, password)) {
				// get user
				UserInfo UserInfo = UserInfoDao.getUserInfoByAccount(account);
				session.setAttribute("userinfo", UserInfo);// put into session attribute
				session.setAttribute("s_account", account);
				session.setAttribute("s_password", password);
				response.sendRedirect("home");
			} else {
				out.print("密码不正确！请重新登录！");
			}
		} else {
			out.print("您还未注册！");
			out.print("<br><a href='SignUp.html'>注册</a>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
