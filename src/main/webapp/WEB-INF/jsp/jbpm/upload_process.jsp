<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/style.css" type="text/css"></link>

<title>上传流程定义</title>
</head>
<body>
	<form:form action="jbpm.do" method="post" enctype="multipart/form-data">
		<input name="method" value="uploadOne" type="hidden" />
		<table>
			<caption>上传流程定义</caption>
			<tr>
				<td><input type="file" name="file" value="" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="上传" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>