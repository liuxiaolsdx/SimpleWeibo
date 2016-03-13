package com.weibo.web;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.*;

import com.weibo.web.servlet.SignIn;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.weibo.model.entity.UserInfo;
//import com.weibo.model.dao.UserInfoDao;

public class SignInServletTest {

	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;
	private HttpSession mockSession;
//	private UserInfoDao mockUserDao;
	private IMocksControl control = createControl();
	private UserInfo UserInfo;
	private SignIn servlet;
	private PrintWriter mockPrintWriter;


	@Before
	public void init() throws Exception {
		servlet = new SignIn();
		mockRequest = control.createMock(HttpServletRequest.class);
		mockResponse = control.createMock(HttpServletResponse.class);
		mockSession = control.createMock(HttpSession.class);
//		mockUserDao = control.createMock(UserInfoDao.class);
		mockPrintWriter = control.createMock(PrintWriter.class);
		UserInfo = control.createMock(UserInfo.class);
	}

	@Test
	@Ignore
	public void testSignInFailNoSessionNoFillNoUser() throws IOException, ServletException {
		mockResponse.setContentType("text/html;charset=UTF-8");
		expectLastCall();
		mockRequest.setCharacterEncoding("UTF-8");
		expectLastCall();
		expect(mockRequest.getSession()).andReturn(mockSession);
		mockSession.getAttribute("userinfo");
		expectLastCall().andReturn(null);
		mockRequest.getParameter(anyObject());
		expectLastCall().andReturn(null).times(1);
		mockResponse.getWriter();
		expectLastCall().andReturn(mockPrintWriter);
//		mockUserDao.checkAccount(anyObject());
//		expectLastCall().andReturn(false);
		mockPrintWriter.print("您还未注册！");
		expectLastCall();
		mockPrintWriter.print("<br><a href='SignUp.html'>注册</a>");
		expectLastCall();
		control.replay();
		
		servlet.doGet(mockRequest, mockResponse);
		control.verify();
	}


	@Test
	@Ignore
	public void testSignInByFilled() throws IOException, ServletException {
		mockResponse.setContentType("text/html;charset=UTF-8");
		expectLastCall();
		mockRequest.setCharacterEncoding("UTF-8");
		expectLastCall();
		expect(mockRequest.getSession()).andReturn(mockSession);
		mockSession.getAttribute("userinfo");
		expectLastCall().andReturn(UserInfo);
//		UserInfo.getU_account();
//		expectLastCall().andReturn("account").times(2);
//		UserInfo.getU_password();
//		expectLastCall().andReturn("password");
		mockRequest.getParameter("account");
		expectLastCall().andReturn("first").times(2);
		mockRequest.getParameter("password");
		expectLastCall().andReturn("1").times(2);
		mockResponse.getWriter();
		expectLastCall().andReturn(mockPrintWriter);
//		mockUserDao.checkAccount(anyObject());
//		expectLastCall().andReturn(true);
//		mockUserDao.checkUser(anyObject(),anyObject());
//		expectLastCall().andReturn(true);
//		mockUserDao.getUserInfoByAccount(anyObject());
//		expectLastCall().andReturn(UserInfo);
		mockSession.setAttribute(anyObject(), anyObject());
		expectLastCall().times(3);
		mockResponse.sendRedirect("home");
		expectLastCall();
		
		control.replay();
		
		servlet.doGet(mockRequest, mockResponse);
		control.verify();



	}

}
