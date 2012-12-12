<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>修改合同</title>

</head>
<body>
	<fieldset>
		<legend>修改合同</legend>
		<form:form action="jbpm.do" name="form" modelAttribute="contract" method="post">
			<input type="hidden" value="modify" name="method" id="method" />
			<input type="hidden" value="${contract.cntId }" name="cntId" />
			<input type="hidden" value="${taskId}" name="taskId" />
		公司名称：<form:input path="comName" />
			<br>
			合同名称：<form:input path="cntName" />
			<br>
			备注：<textarea name="remark">${contract.remark}</textarea>
			<br>
			<input type="submit" value=" 修 改 合 同  " />
		</form:form>
	</fieldset>
</body>
</html>