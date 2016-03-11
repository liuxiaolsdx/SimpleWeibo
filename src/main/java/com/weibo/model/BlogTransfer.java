package com.weibo.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogTransfer {

	static Pattern r_pattern = Pattern.compile("@([^@^\\s:;,]+)");//@([^@^\s:]+)([\s:,;]?)

	public static String GenerateLinks(String blog) {
		StringBuilder newBlog = new StringBuilder();
		int lastIdx = 0;
		Matcher matchr = r_pattern.matcher(blog);
		while (matchr.find()) {
			String orgStr = matchr.group();
			String str = orgStr.substring(1, orgStr.length()).trim();//去掉"@"

			newBlog.append(blog.substring(lastIdx, matchr.start()+1));//获得"@"之前的文本,包括@

			UserInfoDao uDao = new UserInfoDao();
			UserInfo user = uDao.getUserInfoByAccount(str);

			if (user != null && user.getU_account() != null) {
				newBlog.append("<a href='otheruser?u=" + user.getU_account() + "'>@");
				newBlog.append(str.trim());
				newBlog.append("</a>");
			} else {
				newBlog.append(str);
			}
			lastIdx = matchr.end();

		}
		newBlog.append(blog.substring(lastIdx));
		return newBlog.toString();
	}
}
