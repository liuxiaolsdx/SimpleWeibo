package com.weibo.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.weibo.model.FriendsDao;

public class FriendsDaoTest {

	@Test
	public void testAddFollowing() {
		assertTrue(new FriendsDao().addFollowing(1,2));
	}

	@Test
	public void testIsFollowing() {
		assertTrue(new FriendsDao().isFollowing(1,2));
	}

}
