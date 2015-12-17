<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${account}的主页</title>
	</head>
<body>
	你好！${account}<br><a href="SignOut.do">登出</a><br>
	<a href="showUserInfo.do">修改用户信息</a><br>
	<a href="showcollectblog">查看收藏的微博</a>
	<p>

	<table>
		<tr><td>用户关注：${fincount}<td>用户粉丝：${fedcount}
		<tr><td><a href="isFollowed.do?p=1">关注你的人</a>
		<td><a href="showOtherUser.do?p=1">推荐的人</a>
		<td><a href="isFollowing.do?p=1">已关注的人</a>
	</table>
	<p>

<form method="get" action="searchuser">
搜索用户：<input name="q" type="text" placeholder="账号或昵称。。。">
<input type="submit" value="确定">
</form>
<p>

<form method="post" action="WriteWeibo.do">
有什么新鲜事想告诉大家？<p>
<textarea name="content" rows="5" cols="30"></textarea>
<br>
<input type="submit" value="发布">
</form>

<p>自己发布的、转发的和已关注者的微博:
<#if blogList?has_content>

	<#list blogList as b>
		<table border=2>
			<#if b.blog?has_content>
			<#--current user info-->
				<tr>
					<td>
					<#if b.u_img?has_content>
					头像：<img src=${b.u_img} width="50" height="50"></td>
					<#else>
					头像：无
					</#if>
					<td>账号：${b.account}
					<td>昵称：${(b.nickname)!"无"}
				<tr><td>${b.content}
  			<table border=2><#--forward blog start-->
    			<tr><td>
    				<#if b.blog.u_img?has_content>
					头像：<img src=${b.blog.u_img} width="50" height="50"></td>
					<#else>
					头像：无
					</#if>
    				<td>账号：${b.blog.account}
					<td>昵称：${(b.blog.nickname)!"无"}
				<tr><td colspan="2">内容：${b.blog.content}
				<tr><td colspan="2">时间：${b.blog.time}
				<tr><td><a href="forward?b=${b.blog.bid}">转发${b.blog.fnum!}</a>
					<#--<td><a href="collect?b=${b.blog.bid}">收藏${b.blog.fcum!}</a>-->
				<tr><td colspan="2">
					<form method="post" action="AddComment.do?bid=${b.blog.bid}">
					添加评论：<br>
					<textarea name="comment"></textarea>
					<input type="submit" value="提交">
					</form><br>
			<#list b.blog.commentList as c><#--forward blog's comments-->
	
				<table border=1>
					<tr><td>账号：${c.account}<td>昵称：${(c.nickname)!"无"}
					<tr><td colspan="2">内容：${c.content}
					<tr><td colspan="2">时间：${c.time}
				</table>
			</#list>
					
  			</table><#--forward blog end-->
  			<tr><td>时间:${b.time}
			<tr><td><a href="forward?b=${b.bid}&f=${b.blog.bid}">转发${b.fnum!}</a>
				<#--<td><a href="collect?b=${b.bid}">转发${b.cnum!}</a>-->
			<tr><td colspan="2">
				<form method="post" action="AddComment.do?bid=${b.bid}">
				添加评论：<br>
				<textarea name="comment"></textarea>
				<input type="submit" value="提交">
				</form><br>

			<#list b.commentList as c><#--current user blog's comments-->
	
			<table border=1>
				<tr><td>账号：${c.account}<td>昵称：${(c.nickname)!"无"}
				<tr><td colspan="2">内容：${c.content}
				<tr><td colspan="2">时间：${c.time}
			</table>
			</#list>
<#--else start: original blog-->
  		<#else>
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
			<tr><td><a href="forward?b=${b.bid}">转发${b.fnum!}</a>
				<td><a href="collect?b=${b.bid}">收藏${b.cnum!}</a>
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
	</#if>
	</table>
	<br>
</#list>

<#else>
<p>(暂无内容)
</#if>

<#if totalPages!=0>
<#import "/pagin.ftl" as my/>
<@my.pages url="home" totalPages=totalPages p=p />
</#if>

</body>
</html>