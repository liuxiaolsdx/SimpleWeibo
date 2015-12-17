package com.weibo.web;

import java.io.File;  
import java.io.IOException;  
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
 
  
import com.weibo.util.FreeMarkerUtil;  
  
import freemarker.template.TemplateException;  
  
public class UserInfoFTLTest {  
	private Map<String, Object> root;
	
	@Before
	public void setUP(){
		root = new HashMap<>();
		Map<String, Object> user = new HashMap<>();
		root.put("user", user);
		user.put("u_account", "u_account");
		user.put("u_nickname", "u_nickname");
		user.put("u_name", "u_name");
		user.put("u_sex", "u_sex");
		user.put("u_sign", "abcd<a href='home'>主页</a>");
	}
    @Test  
    @Ignore
    public void print() throws TemplateException, IOException{ 
        new FreeMarkerUtil().print("userinfo.ftl", root);  
        new FreeMarkerUtil().printFile("userinfo.ftl", root, new File("D:\\ftl\\userinfo.html"));  
    }  
}  