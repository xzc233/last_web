<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车列表页面</title>
</head>
<body style="text-align: center;">
	<%@include file="/client/head.jsp"%>
	<br />
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="90%" align="center">
		<caption>
			<h2>购物车页面</h2>
		</caption>
		<tr>
			<td>书名</td>
			<td>售价</td>
			<td>数量</td>
			<td>小计</td>
			<td>操作</td>
		</tr>
		<c:forEach var="entry" items="${cart.map}">
			<tr>
				<td>${entry.value.good.name }</td>
				<td>${entry.value.good.price }</td>
				<td>${entry.value.quantity }</td>
				<td>${entry.value.price }元</td>
				<td>
				<a href="${pageContext.request.contextPath}/client/DeleteItemServlet?id=${entry.key }">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">合计</td>
			<td colspan="2">${cart.price }元</td>
			<td></td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath }/client/OrderServlet">生成订单</a>
</body>
</html>
