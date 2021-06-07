<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单展示列表页面</title>
</head>
<body style="text-align: center">
	<%@include file="/client/head.jsp"%>
	<br />
	<table border="1px" cellpadding="0" cellspacing="0" width="90%">
		<caption>
			<h2>订单信息</h2>
		</caption>
		<tr>
			<td>订单人</td>
			<td>下单时间</td>
			<td>订单状态</td>
			<td>订单总价</td>
			<td>操作</td>
		</tr>
		<c:forEach var="order" items="${list }">
			<tr>
				<td>${order.user.username }</td>
				<td>${order.ordertime }</td>
				<td>${order.status == false ? '未发货' : '已发货' }</td>
				<td>${order.price }</td>
				<td>
					<a href="${pageContext.request.contextPath }/client/OrderListServlet?method=find&id=${order.id }">查看明细</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
