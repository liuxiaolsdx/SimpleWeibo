package com.weibo.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.weibo.model.Blog;

public class BlogTest {
	private Blog blog = new Blog();
	@Test
	public void testGet() {
		blog.setBid(1);
		blog.setUid(1);
		blog.setContent("aa");
		assertEquals(1, blog.getUid());
		assertEquals(1, blog.getBid());
		assertEquals("aa", blog.getContent());
	}

}
