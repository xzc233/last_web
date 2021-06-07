<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上商城后台管理页面的页头部分</title>
<style type="text/css">
	body{
		background-image:url(${pageContext.request.contextPath }/images/head.jpg);
		background-size:cover;
	}
</style>
</head>
<body style="text-align: center;">
	<h1>网上商城后台管理</h1>
	<a href="${pageContext.request.contextPath}/index.jsp" style="color:red" target = "_top">返回首页</a>
	<div style="float: right;">
		<a>欢迎您，${user.username }</a>	
	</div>
	<div style="clear: both"></div>
</body>
</html>
