<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提交合同</title>
<script type="text/javascript">
	function repeal(){
		document.getElementById("method").value="repeal";
		document.form.submit();
	}
</script>
</head>
<body>
	<form:form name="form" action="contract.do" modelAttribute="contract" method="post">
		<input type="hidden" value="submit" name="method" id="method"/>
		<input type="hidden" name="cntId" value="${contract.cntId}" />
		<font size="5"><b>提交合同</b></font>
		<br>
		<br>
		<table border="0" cellspacing="0">
			<tr>
				<th>公司名称：</th>
				<td>${contract.comName}</td>
			</tr>
			<tr>
				<th>合同名称：</th>
				<td>${contract.cntName}</td>
			</tr>
			<tr>
				<th>合同草拟人：</th>
				<td>${contract.drafter.roleName}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><textarea name="remark">${contract.remark}</textarea></td>
			</tr>
		</table>
		<input type="submit" value=" 提 交 审 核  " />
		<input type="button" value=" 废 弃 合 同  " onclick="repeal()"/>
	</form:form>
</body>
</html>