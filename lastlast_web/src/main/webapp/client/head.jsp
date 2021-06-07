<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>xzc的网上商城2.0</h1>
<br/>
<br/>
<div>
	<div style="margin-left":40%; float: left">
		<a href="${pageContext.request.contextPath}/index.jsp" style="color:red">首页</a>
		<a href="${pageContext.request.contextPath}/client/listcart.jsp" style="color:red">查看购物车</a>
		<a href="${pageContext.request.contextPath}/client/OrderListServlet?method=getAll" style="color:red">查看自己订单</a>
	</div>
	<div style="float: right;">
		<c:if test="${user == null }">
			<form action="${pageContext.request.contextPath}/client/LoginServlet" method="post">
				用户名：<input type="text" name="username" style="width: 50px">
				密码：<input type="password" name="password" style="width: 50px">
				<input type="submit" value="登录"> 
				<input type="button" value="注册" onclick="javascript:window.location.href='${pageContext.request.contextPath}/client/register.jsp'">
			</form>
		</c:if>
		<c:if test="${user != null }">
			<form action="${pageContext.request.contextPath}/client/LogoutServlet" method="post">
			欢迎您，${user.username }
			<input type="submit" value="注销" >
			</form>
		</c:if>
		<c:if test="${user != null&&user.permission!='normal'}">
			<a href="${pageContext.request.contextPath}/manager.jsp" style="color:red">后台管理</a>
		</c:if>
	</div>
	<div style="clear: both"></div>

</div>
<hr>

