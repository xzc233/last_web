<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台首页</title>

<style type="text/css">
	body {
		margin: 0px;
		padding: 0px;
		text-align: center;
	}
	
	#container {
		width: 100%;
		height: 300px;
		/* border: solid 1px red; */
		text-align: left;
	}
	
	#main {
		margin-top: 20px;
		background: url(${pageContext.request.contextPath }/images/head.jpg) no-repeat center center fixed;
				/*兼容浏览器版本*/
                -webkit-background-size: cover;
				-o-background-size: cover;                
				background-size: cover;
                
	}
	
	#head {
		text-align: center;
		background: url(${pageContext.request.contextPath }/images/head.jpg) no-repeat center center fixed;
				/*兼容浏览器版本*/
                -webkit-background-size: cover;
				-o-background-size: cover;                
				background-size: cover;
	}
	
	#categories {
		border: solid 1px red;
		width: 200px;
		height: 400px;
		padding-left: 20px;
		line-height: 40px;
		float: right;
		background: url(${pageContext.request.contextPath }/images/left.jpg) no-repeat center center fixed;
				/*兼容浏览器版本*/
                -webkit-background-size: cover;
				-o-background-size: cover;                
				background-size: cover;
	}
	
	#goods {
		float: left;
		margin-left: 20px;
	}
	
	#image {
		float: left;
	}
	
	#info {
		float: left;
	}
	
	#good {
		float: left;
		width: 350px;
	}
</style>
</head>
<body style="text-align: center;" >
	<div id="container">
		<div id="head">
			<!-- 静态引入 -->
			<%@include file="/client/head.jsp" %>
		</div>
		
		<div id="main" background=${pageContext.request.contextPath }/images/background.jpg >
			<div id="categories">
				商品分类列表：
				<c:forEach var="c" items="${categories}">
					<li><a href="${pageContext.request.contextPath }/client/IndexServlet?category_id=${c.id }">${c.name }</a></li>
				</c:forEach>
			</div>
			
			<div id="goods">
				<c:forEach var="good" items="${pageBean.list }" varStatus="status">
					<div id="good">
						<div id="image">
							<img alt="" height="200" width="200" src="${pageContext.request.contextPath }/images/${good.image}" />
						</div>
						<div id="info">
							<li>${good.name }</li>
							<li>${good.price }</li>
							<li>
								<a href="${pageContext.request.contextPath }/client/BuyServlet?id=${good.id }">购买</a>
							</li>
						</div>
						<div style="clear: both"></div>
					</div>
					<!-- 每一行显示三本书籍 -->
					<c:if test="${status.count % 3 == 0 }">
						<%-- 这里要清除浮动，十分重要！ --%>
						<div style="clear: both"></div>
						<br/>
					</c:if>
				</c:forEach>
				
				<%-- 这里要清除浮动，十分重要！ --%>
				<div style="clear: both"></div>
				<br/>
				<div id="pagebar"> 
					总共${pageBean.totalpage }页，
					当前第${pageBean.currentpage }页，
					<c:forEach var="pagenum" items="${pageBean.pagebar }">
						<a href="${pageContext.request.contextPath }/client/IndexServlet?currentpage=${pagenum }&category_id=${param.category_id }">${pagenum }</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

