package com.weibo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weibo.model.entity.Blog;
import com.weibo.model.dao.BlogDao;
import com.weibo.model.entity.UserInfo;

public class ForwardServlet extends HttpServlet {

	private static final long serialVersionUID = -6872380779507560827L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int bid =Integer.parseInt(request.getParameter("b"));
		
		BlogDao bDao = new BlogDao();
		
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("userinfo");
		
		int uid =user.getU_id();
		Blog orgBlog = bDao.getBlogByBid(bid);

		Blog blog=new Blog();
		
		if(request.getParameter("f")!=null){
			int firstBid=Integer.parseInt(request.getParameter("f"));
			Blog firstblog = bDao.getBlogByBid(firstBid);
			int firstFnum = firstblog.getFnum();
			bDao.addForwardNum(firstBid, firstFnum);//原创微博转发数增加1
			blog.setFid(firstBid);//设置微博的转发ID为原创微博的ID
		}else{
			blog.setFid(bid);
		}
		blog.setContent("转发微博//@"+orgBlog.getAccount()+" :");
		blog.setUid(uid);
		bDao.blogpublish(blog);
		int oldFNum = orgBlog.getFnum();
		bDao.addForwardNum(bid, oldFNum);
		response.sendRedirect("home");
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
