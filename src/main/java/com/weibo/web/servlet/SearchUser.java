package com.weibo.web.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibo.model.dao.SearchDao;
import com.weibo.model.entity.UserInfo;
import com.weibo.util.WeiboLogger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class SearchUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7208127167933807019L;
	private Configuration cfg;

	public void init() throws ServletException {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading(getServletContext(), "./");  
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String search = request.getParameter("q");
		long counts = 0;
		int showPageNum = 4;//每页个数
		int currPage = 1;//默认第一页
		SearchDao sDao = new SearchDao();
		
		if(request.getParameter("p")!=null)
			currPage =Integer.parseInt(request.getParameter("p"));
		List<UserInfo> UserList = sDao.getSearchUser(search,showPageNum,currPage);
		counts=sDao.countSearchUser(search);
		int totalPages = (int)counts/showPageNum + ((counts%showPageNum)>0?1:0);
		
		Map<String, Object> root = new HashMap<>();
		root.put("userList", UserList);
		root.put("totalPages", totalPages);
		root.put("p", currPage);
		Writer out = response.getWriter();
		Template template = cfg.getTemplate("SearchUserRes.ftl");
		try {
			template.process(root,out);
		} catch (TemplateException e) {
			WeiboLogger.exception(e);
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


}
