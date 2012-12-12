<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>审核合同</title>

<script type="text/javascript">
	function reject() {
		document.getElementById("method").value = "reject";
		document.form.submit();
	}
	function repeal() {
		document.getElementById("method").value = "repeal";
		document.form.submit();
	}
</script>
</head>
<body>
	<fieldset>
		<legend>审核合同</legend>
		<form:form name="form" action="jbpm.do" modelAttribute="contract" method="post">
			<input type="hidden" value="approve" name="method" id="method" />
			<input type="hidden" name="cntId" value="${contract.cntId}" />
			<input type="hidden" name="taskId" value="${taskId}" />
		公司名称：${contract.comName} <br>
		合同名称：${contract.cntName} <br>
		合同草拟人：${contract.drafter.roleName}<br>
		合同提交人：${contract.submiter.roleName }<br>
		备注：<textarea name="remark">${contract.remark}</textarea>
			<br>
			<input type="submit" value=" 审 核  通 过 " />
			<input type="button" value=" 拒 绝 " onclick="reject()" />
			<input type="button" value=" 废 弃 合 同  " onclick="repeal()" />
		</form:form>
	</fieldset>
</body>
</html>