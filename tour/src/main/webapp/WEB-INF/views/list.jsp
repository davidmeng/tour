<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">   
<title>Hello World</title>
<link  href='<c:url value="/resources/css/bootstrap.css" />' rel="stylesheet">
<style type="text/css">
body,button, input, select, textarea,h1 ,h2, h3, h4, h5, h6 { font-family: Microsoft YaHei,'宋体' , Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;}

</style>
<script src="<c:url value="/resources/js/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>

</head>
<body>
<div class="container">
<h2 class="jumbotron text-center"><a target="_blank" href="<c:url value='/' />">Hello World</a></h2>
<table class="table table-hover">
	<thead class="panel-heading">
	<tr>
		<th width="100px">line</th>
		<c:forEach items="${list}" var="item">
		<th>
			<a target="_blank" href="<c:out value="${item.address }" />"><c:out value="${item.title }" /></a>
		</th>
		</c:forEach>
	</tr>
	</thead>
	<tr>
		<td><span class="label label-default">price</span></td>
		<c:forEach items="${list}" var="item">
		<td>
			<c:out value="${item.price }" />
		</td>
		</c:forEach>
	</tr>
	<c:forEach items="${ resultList }" var="result">
	<tr
	
		<c:if test="${result.common }">
		class="alert-success"
		</c:if>
	>
		<td>
		<c:if test="${result.common }">
		<span class="label label-success">
		</c:if>
		<c:if test="${!result.common }">
		<span class="label label-default">
		</c:if>
		<c:out value="${result.key }" />
		</span></td>
		<c:forEach items="${result.contents}" var="content">
		<td>
			<c:out value="${content}" />
		</td>
		</c:forEach>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>