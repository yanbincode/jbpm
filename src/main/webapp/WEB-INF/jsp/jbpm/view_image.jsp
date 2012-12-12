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

<title>查看流程图</title>
</head>
<body>
	<img src="jbpm.do?method=getImage&instanceId=${instanceId}"
		style="position: absolute; left: 0px; top: 0px;" />
	<div
		style="position:absolute; border:1px solid red; left:${point.x}px; top:${point.y}px;width:${point.w}px;height:${point.h}px;"></div>
</body>
</html>