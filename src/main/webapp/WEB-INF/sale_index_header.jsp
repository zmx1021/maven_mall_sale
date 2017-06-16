<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/finishCart.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		var user = cookie_utile("atguiguUser");
		var user_url = decodeURIComponent(user);
		$("#index_user_nch").html(user_url);
	});

	function cookie_utile(key) {
		var cookie = document.cookie;
		var cookies = cookie.split("; ");
		var re = "";
		for (i = 0; i < cookies.length; i++) {
			var values = cookies[i].split("=");
			if (values[0] == key) {
				re = values[1];
			}
		}
		return re;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${user==null}">
				<a href="javascript:;"><span id="index_user_nch"></span></a>
				<a href="goto_login1228.do" target="_blank">请登录</a>
				<a href="goto_regist.do" target="_blank">请注册</a>
	        </c:if>
	    <c:if test="${user!=null}">
		<a href="javascript:;">欢迎 ${user.yh_nch}</a>
		<a href="to_order_list.do">我的订单</a>
		<a href="user_logout.do">退出</a>
	    </c:if>
		</div>
	</div>
	<div class="top_img">
		<img src="images/top_img.jpg" alt="">
	</div>
</body>
</html>