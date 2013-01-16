<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>报销流程</title>

</head>
<body>
	<fieldset>
		<legend>报销流程</legend>
		<form:form name="form" action="expense.do" modelAttribute="expense" method="post">
			<input type="hidden" name="expId" value="${expense.expId}" />
			<form:input path="money" />
			<textarea name="reason">${expense.reason}</textarea>
			<jbpm:button taskId="${taskId}" />
		</form:form>
	</fieldset>
</body>
</html>