package com.weibo.web;

import javax.servlet.http.*;

import com.weibo.model.*;
import com.weibo.util.WeiboLogger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

import javax.servlet.*;

public class ShowUserInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8090767439467839828L;
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
		request.setCharacterEncoding("UTF-8");
		
		String account=null;
		HttpSession session = request.getSession();
		if( request.getParameter("account") != null){
			account = request.getParameter("account");
		}else{
			account = (String) session.getAttribute("s_account");
		}
		
		UserInfo users = new UserInfoDao().getUserInfoByAccount(account);

		Map<String, Object> UserInfo = new HashMap<>();
		Map<String, String> usersMap = new HashMap<>();
		UserInfo.put("user",usersMap);
		usersMap.put("u_account", account);
		usersMap.put("u_nickname", users.getU_nickname());
		usersMap.put("u_name", users.getU_name());
		usersMap.put("u_sex", users.getU_sex());
		usersMap.put("u_sign", users.getU_sign());
		usersMap.put("u_img", users.getU_img());
		
		Writer out = response.getWriter();
		Template template = cfg.getTemplate("userinfo.ftl");
		try {
			template.process(UserInfo,out);
		} catch (TemplateException e) {
			WeiboLogger.exception(e);
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}

