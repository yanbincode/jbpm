<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>草拟合同</title>

</head>
<body>
	<form:form action="contract.do" name="form" modelAttribute="contract" method="post">
		<input type="hidden" value="draft" name="method" id="method"/>
		<font size="5"><b>草拟合同</b></font>
		<br>
		<br>
		<table border="0" cellspacing="0">
			<tr>
				<th>公司名称：</th>
				<td><form:input path="comName" /></td>
			</tr>
			<tr>
				<th>合同名称：</th>
				<td><form:input path="cntName" /></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><textarea name="remark"></textarea></td>
			</tr>
		</table>
		<input type="submit" value=" 草 拟 合 同  " />
	</form:form>
</body>
</html>