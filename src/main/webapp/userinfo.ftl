<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息</title>
</head>
<body>
	<form action="setUserInfo.do?account=${user.u_account}" method="post" enctype="multipart/form-data">
		<table>
		      <tr>
             	 <td>用户:<span name="account">${user.u_account}</span></td>
              </tr>
		
			<tr>
				<td>昵称：<input name="nickname" type="text" value=${(user.u_nickname)!}></td>
			</tr>
			<tr>
				<td>真实姓名：<input name="name" type="text" value=${(user.u_name)!}></td>
			</tr>
			
			<tr>
				<td>性别：<input name="sex" type="text" value=${(user.u_sex)!}></td>
			</tr>
			<tr>
				<td>个人签名：<textarea name="content" rows="3" cols="20" >${(user.u_sign)!}</textarea>
				</td>
			</tr>
			<tr>
				<td>
				<#if user.u_img?has_content>
				头像：<img src=${user.u_img} width="50" height="50"></td>
				<#else>
				头像：无
				</#if>
				<td>修改头像：<input type="file" name="img" />
				</td>
			</tr>
			
			<tr>
				<td><input type="submit" value="确定" />
				<input type="reset" value="取消" /></td>
			</tr>
		</table>
	</form>
</body>
</html>