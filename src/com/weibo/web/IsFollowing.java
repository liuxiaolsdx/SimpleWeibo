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

import com.weibo.model.FriendsDao;
import com.weibo.model.UserInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class IsFollowing extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4659144993277055611L;
	private Configuration cfg;

	public void init() throws ServletException {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading(getServletContext(), "./");  
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		ArrayList<UserInfo> UserList = new ArrayList<UserInfo>();
		UserInfo userinfo = new UserInfo();
		HttpSession session = request.getSession();
		userinfo = (UserInfo) session.getAttribute("userinfo");//get current userinfo
		FriendsDao fDao = new FriendsDao();
		int currPage = Integer.parseInt(request.getParameter("p"));
		long counts = 0;
		int showPageNum = 4;//每页个数
		UserList = fDao.getFollowing(userinfo.getU_id(),showPageNum,currPage);
		counts=fDao.countFollowing(userinfo.getU_id());
		
		int totalPages = (int)counts/showPageNum + ((counts%showPageNum)>0?1:0);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("userList", UserList);
		root.put("totalPages", totalPages);
		root.put("p", currPage);
		Writer out = response.getWriter();
		Template template = cfg.getTemplate("FollowingUser.ftl");
		try {
			template.process(root,out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}

