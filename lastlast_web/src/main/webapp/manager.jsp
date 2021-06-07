<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线网上书店后台管理页面</title>
<style type="text/css">
#main {
		margin-top: 20px;
		background-image: url(${pageContext.request.contextPath }/images/background.jpg) no-repeat center center fixed;
				/*兼容浏览器版本*/
                -webkit-background-size: cover;
				-o-background-size: cover;                
				background-size: cover;
	}
</style>
</head>
<frameset rows="18%,*">
	<frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head" />
	<frameset cols="*,18%">
		<frame src="" id="main" name="right" />
		<frame src="${pageContext.request.contextPath }/manager/left.jsp" name="left" />
	</frameset>
</frameset>
</html>
