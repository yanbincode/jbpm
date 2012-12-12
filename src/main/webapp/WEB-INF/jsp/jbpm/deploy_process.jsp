<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>部署流程</title>
</head>
<body>
	<table border="1" bordercolor="#dee4f4" cellspacing="0" cellpadding="3">
		<caption>部署流程</caption>
		<tr>
			<th>流程名</th>
			<th>部署</th>
		</tr>
		<c:forEach items="${processList}" var="process">
			<tr>
				<td>${process}</td>
				<td><a href="jbpm.do?method=deplay&processName=${process}">部署流程</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>