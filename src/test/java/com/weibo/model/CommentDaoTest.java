package com.weibo.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.weibo.model.CommentDao;

public class CommentDaoTest {

	@Test
	@Ignore
	public void testGetAllComments() {
		
		assertNotNull(new CommentDao().getAllCommentsSum(1));
	}

}
