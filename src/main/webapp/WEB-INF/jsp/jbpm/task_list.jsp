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

<title>个人待办任务</title>
</head>
<body>
	<table width="100%" id="mytable" id="mytable">
		<caption>个人待办任务</caption>
		<tr>
			<th class="spec">流程ID</th>
			<th>当前节点</th>
			<th>当前处理人</th>
			<th>查看详细信息</th>
			<th>查看流程图</th>
		</tr>
		<c:forEach items="${tasks}" var="task">
			<tr>
				<td class="alt">${task.getId()}</td>
				<td class="alt">${task.getName()}</td>
				<td class="alt">${task.getAssignee()}</td>
				<td class="alt"><a href="jbpm.do?method=formPage&taskId=${task.getId()}">任务处理</a></td>
				<td class="alt"><a
					href="jbpm.do?method=viewProcessImage&instanceId=${task.getExecutionId()}">查看流程图</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>