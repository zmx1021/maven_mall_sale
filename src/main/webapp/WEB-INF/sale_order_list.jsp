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
<title>我的订单</title>
<link rel="stylesheet" type="text/css" href="css/list.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		get_letf_option();
		get_order_list_page(0);
	});
	function get_order_list_page(jdh) {
		$.ajax({
			url : 'get_myorder_page.do',
			type : 'post',
			data : {'jdh':jdh},
			dataType : 'html',
			success : function(data) {
				$("#my_order_list_inner").html(data);
			}
		});
	}
	function get_letf_option() {
		$.ajax({
			url : 'get_letf_option.do',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				$.each(data, function(i, json) {
					$("#fmyList").after('<li><a href="javascript:get_order_list_page('+json.jdh+');">'+json.jdmsh+'</a></li>');
				});
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="sale_index_header.jsp"></jsp:include>
	<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>
	<div class="list" id="my_order_list">
		<div class="list_left">
			<ul class="myList">
				<li id="fmyList"><a href="javascript:get_order_list_page(0);">全部订单</a></li>
				
			</ul>
		</div>
		<div class="list_right">
			<div class="product">
				<div class="pro_name">订单编号</div>
				<div class="pro_name">姓名</div>
				<div class="pro_deit">订单状态</div>
				<div class="pro_deit">支付方式</div>
				<div class="pro_fin">操作</div>
			</div>
			<div id="my_order_list_inner"></div>
			<div></div>
		</div>
	</div>
</body>
</html>