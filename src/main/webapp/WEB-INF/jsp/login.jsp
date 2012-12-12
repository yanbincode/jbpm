<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>登陆系统</title>
</head>
<body>
	<div>
		<form:form action="employee.do" method="post">
			<input type="hidden" value="login" name="method" />
			<font size="5"><b>登陆系统</b></font>
			<br>
			<br>
			<table border="1">
				<tr>
					<th>账号：</th>
					<td><select name="level">
							<option value="1">销售代表</option>
							<option value="2">销售组长</option>
							<option value="3">销售经理</option>
							<option value="4">总经理</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value=" 登 陆 "></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>