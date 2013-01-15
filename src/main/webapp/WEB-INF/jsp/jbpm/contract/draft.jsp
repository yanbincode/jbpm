<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jbpm" uri="http://www.jbpm.com/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>草拟合同</title>

</head>
<body>
	<fieldset>
		<legend>草拟合同</legend>
		<form:form action="jbpm.do" modelAttribute="contract" method="post">
			<input type="hidden" value="draft" name="method" />
			<input type="hidden" value="${taskId}" name="taskId" />
			<input type="hidden" value="${contract.cntId }" name="cntId" />
		公司名称：<form:input path="comName" />
			<br>
		合同名称：<form:input path="cntName" />
			<br>
		备注：<textarea name="remark"></textarea>
			<br>
			<input type="submit" value=" 草 拟 合 同  " />
			${taskId}
			<jbpm:button taskId="${taskId}" />
		</form:form>
	</fieldset>
</body>
</html>