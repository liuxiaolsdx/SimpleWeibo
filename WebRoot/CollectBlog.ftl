<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<a href="home">主页</a>
<p>收藏的微博:
<#if CollectBlogList?has_content>
<#list CollectBlogList as b>

<p>
用户：${account}，收藏了此微博：<br>

  <table border=2>
    <tr><td>
    	<#if b.u_img?has_content>
		头像：<img src=${b.u_img} width="50" height="50"></td>
		<#else>
		头像：无
		</#if>
    	<td>账号：${b.account}
		<td>昵称：${(b.nickname)!"无"}
	<tr><td colspan="2">内容：${b.content}
	<tr><td colspan="2">时间：${b.time}
	<tr><td colspan="2">
	<form method="post" action="AddComment.do?bid=${b.bid}">
	添加评论：<br>
	<textarea name="comment"></textarea>
	<input type="submit" value="提交">
	</form>
	<br>
	
	<#list b.commentList as c>
	
		<table border=1>
			<tr><td>账号：${c.account}<td>昵称：${(c.nickname)!"无"}
			<tr><td colspan="2">内容：${c.content}
			<tr><td colspan="2">时间：${c.time}
		</table>
	</#list>
	
	</table>

</#list>
<#else>
<p>(暂无内容)
</#if>

<p>
<#if totalPages!=0>
<#import "/pagin.ftl" as my/>
<@my.pages url="ShowCollectBlog" totalPages=totalPages p=p />
</#if>