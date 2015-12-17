package com.weibo.model;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BlogTransferTest {
	private Blog blog;
	
	@Before
	public void setUp() throws Exception {
		blog = new Blog();
		blog.setContent("This is a Test!@first@second@third@four@five@somthingelse,so what output?");
	}

	@Test
	@Ignore
	public void test() {
		String str2 = "Test two:@  first@@first,  @second;,so what output?";
		
		String newContent = BlogTransfer.GenerateLinks(blog.getContent());
		String newstr2 = BlogTransfer.GenerateLinks(str2);
		
		System.out.println(newContent);
		System.out.println(newstr2);
	}

}
