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
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="css/css.css">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function a(){
             }
</script>
</head>
<body>
<jsp:include page="sale_index_header.jsp"></jsp:include>
<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>

${order_detail.jdh}
${order_detail.dzh_mch}
${order_detail.shhr}
${order_detail.yjsdshj}<br>
<c:forEach items="${order_detail.list_order_info}" var="orderinfo">

${orderinfo.sku_mch}
${orderinfo.sku_jg}
${orderinfo.sku_shl}
${orderinfo.sku_kcdz}
<img src="upload/${orderinfo.shp_tp}" style="height: 100px;width: 100px"/>

</c:forEach>

</body>
</html>