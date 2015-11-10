package com.weibo.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.weibo.model.Blog;
import com.weibo.model.BlogDao;

public class BlogDaoTest {

	@Test
	@Ignore
	public void test() {
		ArrayList <Blog> blogList = new BlogDao().getAllBlogByUid(1, 4, 1);
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
	public void testGetAllBlogCount(){
		assertEquals(0,new BlogDao().getAllBlogSum(12));
	}
	@Test
	public void testGetAllBlogCount2(){
		assertNotNull(new BlogDao().getAllBlogSum(1));
	}

}
