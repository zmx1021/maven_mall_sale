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
<title>Pay for</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/checkedstand.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function pay_for_submit() {
		if ($("#pay_for_fid").val() != "") {
			$("#pay_for_form").submit();
		}
	}
</script>
</head>
<body>
<body>
	<div class="warp">
		<div class="money">${sum}</div>
		<input type="text" id="pay_for_fid" class="password"
			placeholder="请输入京东支付密码" />

		<form id="pay_for_form" action="submit_pay_for.do" method="post">
			<!-- 			订单总金额： -->
			<%-- 			<c:forEach items="${list_order}" var="order"> --%>
			<%-- 				<c:forEach items="${order.list_order_info }" var="order_info"> --%>
			<%--                  ${order_info.sku_mch}<br> --%>
			<%-- 				</c:forEach> --%>
			<%-- 			</c:forEach> --%>

			<div class="pay">
				<img src="images/pay.gif" onclick="pay_for_submit()" />
			</div>
		</form>

	</div>
</body>



</body>
</html>