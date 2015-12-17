<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<a href="home">主页</a>
<p>搜索的用户:
<table border=1>
  <#list userList as u>
     <tr><td>
    	<#if u.u_img?has_content>
		头像：<img src=${u.u_img} width="50" height="50"></td>
		<#else>
		头像：无
		</#if>
     <tr><td>账号：${u.u_account}<td>昵称：${(u.u_nickname)!"无"}<td>性别：${(u.u_sex)!"未知"}
	<tr><td>个性签名：${u.u_sign!}
	<tr><td><a href="following.do?f=${u.u_id}">关注</a>
  </#list>
</table>


<#if totalPages!=0>
<#import "/pagin.ftl" as my/>
<@my.pages url="searchuser" totalPages=totalPages p=p />
</#if>