<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台商品列表</title>
</head>
<body style="text-align: center">
	<br />
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="90%">
		<caption>
			<h2>商品信息</h2>
		</caption>
		<tr>
			<td>商品名</td>
			<td>描述</td>
			<td>图片</td>
			<td>操作</td>
		</tr>

		<c:forEach var="good" items="${list}">
			<tr>
				<td>${good.name }</td>
				<td>${good.description }</td>
				<td><a
					href="${pageContext.request.contextPath }/images/${good.image }">查看图片</a></td>
				<td><a href="${pageContext.request.contextPath }/manager/GoodServlet?method=del&id=${good.id}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
</body>
</html>
