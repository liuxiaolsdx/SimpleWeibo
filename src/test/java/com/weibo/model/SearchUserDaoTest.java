package com.weibo.model;

import static org.junit.Assert.*;

//import java.sql.SQLException;
import java.util.ArrayList;

//import static org.easymock.EasyMock.*;
//import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.weibo.model.SearchDao;
import com.weibo.model.UserInfo;

//import com.mysql.jdbc.ResultSet;
//import com.weibo.DB.DB;

public class SearchUserDaoTest {
	private SearchDao SDao;
//	private ResultSet mockResultSet;
//	private DB mockDB;
//	
	@Before
	public void init(){
		SDao=new SearchDao();
//		mockResultSet = createMock(ResultSet.class);
//		mockDB = createMock(DB.class);
	}

	@Test
	@Ignore
	public void test() {
//		mockDB.executeQuery(anyObject(), anyObject());
//		expectLastCall().andStubReturn(mockResultSet);
//		try {
//			mockResultSet.next();
//			expectLastCall().andReturn(true).times(1);
//			expectLastCall().andReturn(false).times(1);
//			mockResultSet.getInt(1);
//			expectLastCall().andReturn(1).times(1);
//			mockResultSet.getString(2);
//			expectLastCall().andStubReturn("aaa");
//			replay(mockResultSet,mockDB);
			
			ArrayList<UserInfo> userList = SDao.getSearchUser("",1,1);
			assertNotNull(userList);
			assertEquals(1,(int)userList.get(0).getU_id());
			assertEquals("first",userList.get(0).getU_account());
//			verify(mockResultSet,mockDB);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		assertNotNull(new SearchDao().getSearchUser("0", 3, 1));
	}

}
