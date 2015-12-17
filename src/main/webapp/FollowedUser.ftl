<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<a href="home">主页</a>
<p>关注你的用户:
<table border=1>
  <#list userList as u>
    <tr><td>账号：${u.u_account}<td>昵称：${(u.u_nickname)!"无"}<td>性别：${(u.u_sex)!"未知"}
	<tr><td><a href="following.do?f=${u.u_id}">关注</a>
  </#list>
</table>

<#if totalPages!=0>
<#import "/pagin.ftl" as my/>
<@my.pages url="isFollowing.do" totalPages=totalPages p=p />
</#if>