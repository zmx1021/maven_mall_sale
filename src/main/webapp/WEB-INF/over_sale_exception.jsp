<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<%
	String APP_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	request.setAttribute("APP_PATH", APP_PATH);
%>
<base href="${APP_PATH}">
<title>超卖异常</title>
<link rel="stylesheet" type="text/css" href="css/css.css">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function a(){
             }
</script>
</head>
<body>

over sale exception
</body>
</html>