<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>流程定义</title>
</head>
<body>
	<table width="100%" id="mytable" id="mytable">
		<caption>流程定义</caption>
		<tr>
			<th class="spec">流程id</th>
			<th>流程名称</th>
			<th>版本号</th>
			<th>流程删除</th>
			<th>启动流程</th>
		</tr>
		<c:forEach items="${processDefinitions}" var="processDefinition">
			<tr>
				<td class="spec">${processDefinition.getId()}</td>
				<td class="alt">${processDefinition.getName()}</td>
				<td class="alt">${processDefinition.getVersion()}</td>
				<td class="alt"><a
					href="jbpm.do?method=deleteProcess&processId=${processDefinition.getId()}">删除流程</a></td>
				<td class="alt"><a
					href="jbpm.do?method=startProcess&processId=${processDefinition.getId()}">启动流程</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>