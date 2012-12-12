<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>主页导航</title>
</head>
<body>
	<table>
		<caption>主页导航</caption>
		<tr>
			<th><a href="jbpm.do?method=uploadOne">上传流程定义</a></th>
		</tr>
		<tr>
			<th><a href="jbpm.do?method=showProcess">流程定义列表</a></th>
		</tr>
		<tr>
			<th><a href="jbpm.do?method=processList">所有流程</a></th>
		</tr>
		<tr>
			<th><a href="jbpm.do?method=taskList">任务列表(待办任务)</a></th>
		</tr>
	</table>
</body>
</html>