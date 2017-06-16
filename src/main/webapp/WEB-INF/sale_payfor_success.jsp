<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>支付成功</title>
<link rel="stylesheet" type="text/css" href="css/css.css">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function a() {
	}
</script>
</head>
<body>


	<c:forEach items="${list_order}" var="order_list" varStatus="index">

订单${index.count}
<c:forEach items="${order_list.list_order_info}" var="order_info">
					商品名称：${order_info.sku_mch }
					价格：${order_info.sku_jg }
					数量：${order_info.sku_shl}
					<br>
		</c:forEach>

	</c:forEach>

	success,支付成功
</body>
</html>