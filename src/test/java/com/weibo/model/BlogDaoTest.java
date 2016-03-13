package com.weibo.model;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.List;

import com.weibo.model.dao.BlogDao;
import org.junit.Ignore;
import org.junit.Test;

import com.weibo.DB.DB;
import com.weibo.model.entity.Blog;

public class BlogDaoTest {
	private DB db = null;
	private ResultSet rs = null;
//	
//	@Before
//	public void setUp() throws SQLException{
//		rs = createMock(ResultSet.class);
//		rs.next();
//		expectLastCall().andReturn(true).times(1);
//		expectLastCall().andReturn(false).times(1);
//		rs.getInt(isA(String.class));
//		expectLastCall().andReturn("int").times(5);
//		rs.getString(isA(String.class));
//		expectLastCall().andReturn("string").times(4);
//		rs.getTimestamp(isA(String.class));
//		expectLastCall().andReturn("timestamp").times(1);
//		
//		db = createMockBuilder(DB.class)
//				.withConstructor()
////				.addMockedMethod("someMethod");
//				.createMock();
//		expect(db.executeQuery(isA(String.class),anyObject())).andReturn(rs);
//		
//		replay(rs,db);
//	}
//
	
	@Test
	@Ignore
	public void test() {
		List<Blog> blogList = new BlogDao().getAllBlogByUid(1, 4, 1);
		System.out.println(blogList.toString());
		assertNotNull(blogList);
		assertNotNull(blogList.get(0).getBid());
		assertNotNull(blogList.get(0).getUid());
		assertNotNull(blogList.get(0).getAccount());
		assertNull(blogList.get(0).getNickname());
		assertNotNull(blogList.get(0).getTime());
		System.out.println(blogList.get(0).getTime());
		System.out.println(blogList.get(0).getAccount());
		assertNotNull(blogList.get(0).getContent());
	}
	@Test
	@Ignore
	public void testGetAllBlogCount(){
		assertEquals(0,new BlogDao().getAllBlogSum(12));
	}
	@Test
	@Ignore
	public void testGetAllBlogCount2(){
		assertNotNull(new BlogDao().getAllBlogSum(1));
	}

}
