package com.weibo.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.weibo.model.FriendsDao;

public class FriendsDaoTest {

	@Test
	@Ignore
	public void testAddFollowing() {
		assertTrue(new FriendsDao().addFollowing(1,2));
	}

	@Test
	@Ignore
	public void testIsFollowing() {
		assertTrue(new FriendsDao().isFollowing(1,2));
	}

}
