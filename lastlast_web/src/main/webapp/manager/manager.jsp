<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
</head>
<body style="text-align: center">
	<form action="${pageContext.request.contextPath }/manager/ManagerServlet?method=add" method="post">
		<table width="300px" align="center">
			<caption>
				<h2>用户添加</h2>
			</caption>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" style="width: 200px"></td>
			<tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" style="width: 200px"></td>
			</tr>
			<tr>
				<td>手机</td>
				<td><input type="text" name="phone" style="width: 200px"></td>
			</tr>
			<tr>
				<td>住址</td>
				<td><input type="text" name="address" style="width: 200px"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email" style="width: 200px">
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
	<form action="${pageContext.request.contextPath }/manager/ManagerServlet?method=set" method="post">
		<table width="300px" align="center">
			<caption>
				<h2>用户改密码</h2>
			</caption>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" style="width: 200px"></td>
			<tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" style="width: 200px"></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td><input type="password" name="newPwd" style="width: 200px"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="确认"></td>
			</tr>
		</table>
	</form>
	<form action="${pageContext.request.contextPath }/manager/ManagerServlet?method=del" method="post">
		<table width="300px" align="center">
			<caption>
				<h2>删除用户</h2>
			</caption>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" style="width: 200px"></td>
			<tr>
			<tr>
				<td>root密码</td>
				<td><input type="password" name="password" style="width: 200px"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="确认"></td>
			</tr>
		</table>
	</form>
</body>
</html>
