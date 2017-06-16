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
<title>订单预览</title>
<link rel="stylesheet" type="text/css" href="css/css.css">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function choose_shjr(id, shjr, yh_dz) {

		$("#fid").val(id);
		$("#fshjr").val(shjr);
		$("#fyh_dz").val(yh_dz);
	}
</script>
</head>
<jsp:include page="sale_index_header.jsp"></jsp:include>
<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>
<body>
	<div class="message">
		<div class="msg_title">收货人信息</div>
		<c:forEach items="${list_user_address}" var="address">
			<div class="msg_addr">
				<span class="msg_left"> <input type="radio" name="yh_dz"
					value="${address.id}"
					onclick="choose_shjr(this.value,'${address.shjr}','${address.yh_dz}');">
					${address.shjr}
				</span> <span class="msg_right"> ${address.yh_dz} </span>
			</div>
		</c:forEach>

		<span class="addrs">查看更多地址信息</span>
		<div class="msg_line"></div>

		<div class="msg_title">送货清单</div>
		<div class="msg_list">


			<div class="msg_list_left">
				配送方式
				<div class="left_title">京东快递</div>
			</div>
			<c:forEach items="${list_order}" var="oderitem" varStatus="index">
				<div class="msg_line">订单${index.count}</div>
				<c:forEach items="${oderitem.list_order_info}" var="orderinfo">
					商品名称：${orderinfo.sku_mch }
					价格：${orderinfo.sku_jg }
					数量：${orderinfo.sku_shl}
					<br>
				</c:forEach>
				应付金额：<b>${oderitem.zje}</b>
			</c:forEach>

			<div class="msg_sub">
				<form action="submit_order.do" method="post">
					<div class="msg_sub_adds">
						寄送至 ： <input type="text" id="fid" name="id" value=""
							readonly="readonly"> <input type="text" id="fshjr"
							name="shjr" value="" readonly="readonly" /> <input type="text"
							id="fyh_dz" name="yh_dz" value="" readonly="readonly">
					</div>
					<input class="msg_btn" type="submit" value="提交订单">

				</form>
			</div>

		</div>
	</div>



</body>
</html>