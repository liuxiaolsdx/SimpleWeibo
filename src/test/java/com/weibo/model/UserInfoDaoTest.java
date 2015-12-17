package com.weibo.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.weibo.model.UserInfo;
import com.weibo.model.UserInfoDao;

public class UserInfoDaoTest {
	

	@Test
	@Ignore
	public void testCheckAccount() {
		assertTrue (new UserInfoDao().checkUser("first", "1"));
		assertFalse(new UserInfoDao().checkUser("first", "2"));
	}
//	@Test
//	public void testgetUserInfoByAccount() {
//		UserInfo user=new UserInfo();
//		user.setU_account("first");
//		user.setU_id(1);
//		user.setU_nick(null);
//		user.setU_img(null);
//		user.setU_sex(null);
//		user.setU_sign(null);
//		user.setU_name(null);
//		user.setU_date(null);
//		assertEquals(user,new UserInfoDao().getUserInfoByAccount("first"));
//		
//	}
	@Test
	@Ignore
	public void testIsEmpty(){
		assertNotNull(new UserInfoDao().getUserInfoByAccount("first"));
	}
	
	@Test
	@Ignore
	public void testGetID(){
		int id=new UserInfoDao().getUserInfoByAccount("first").getU_id();
		assertEquals(1,id);
	}
//	@Test
//	public void testGetAccount(){
//		String nickname=new UserInfoDao().getUserInfoByAccount("first").getU_nickname();
//		assertNull(nickname);
//	}
	@Test
	@Ignore
	public void testUpdateUserInfo(){
		UserInfo u = new UserInfo();
		u.setU_name("aa");
		assertTrue(new UserInfoDao().updateUserInfo("first", u));
	}
	@Test
	@Ignore
	public void testGetAllUser(){
//		ArrayList<UserInfo> userList = new ArrayList<UserInfo>();
//		int counts = 0;
		assertNotNull(new UserInfoDao().getSomeUser(1,10,1));
//		assertEquals(1,counts);
	}

}
