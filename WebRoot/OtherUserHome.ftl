<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>${user.u_account}的主页</title>
</head>
<a href="home">回到自己主页</a>
<p>
<strong>${user.u_account}的主页</strong>
<p>
<table border=1>
	<tr><td>
    	<#if user.u_img?has_content>
		头像：<img src=${user.u_img} width="50" height="50"></td>
		<#else>
		头像：无
		</#if>
	
   	<tr><td>账号：${user.u_account}<td>昵称：${(user.u_nickname)!}<td>性别：${(user.u_sex)!"未知"}
	<tr><td>个性签名：${user.u_sign!}
	<#if friendFlag>
	<tr><td><a href="deleteFollowing.do?f=${user.u_id}">取消关注</a>
	<#else>
	<tr><td><a href="following.do?f=${user.u_id}">关注</a>
	</#if>
 </table>
<p>
<strong>${user.u_account}的微博：</strong>
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
<@pages url="otheruser?u=${user.u_account}&" totalPages=totalPages p=p />
</#if>

<#function max x y>
    <#if (x<y)><#return y><#else><#return x></#if>
</#function>
<#function min x y>
    <#if (x<y)><#return x><#else><#return y></#if>
</#function>
<#function add x y>
    <#return x+y>
</#function>

<#macro pages totalPages p url>
    <#assign size = totalPages>
    <#if (p<=5)> <#-- p among first 5 pages -->
        <#assign interval = 1..(min(5,size))>
    <#elseif ((size-p)<5)> <#-- p among last 5 pages -->
        <#assign interval = (max(1,(size-4)))..size >
    <#else>
        <#assign interval = (p-2)..(p+2)>
    </#if>
    <#if !(interval?seq_contains(1))>
     <a href="${url}p=1">1</a> ... <#rt>
    </#if>
    <#list interval as page>
        <#if page=p>
         <${page}> <#t>
        <#else>
         <a href="${url}p=${page}">${page} <#t></a>
        </#if>
    </#list>
    <#if !(interval?seq_contains(size))>
     ... ${size}<#lt>
    </#if>

	<#assign next = (add(p,1))>
	<#if p=size>
	下一页
	<#else>
	<a href="${url}p=${next}">下一页</a>
	</#if>
</#macro>