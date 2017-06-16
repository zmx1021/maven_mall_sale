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
<base href="APP_PATH">
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/finishCart.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

$(function(){
	get_cart_page();
});
	function shopping_cart_toggle(){
		$(".cart_pro").toggle();
	}
	function add_cart_tishl(sku_id,tjshl){
		$.ajax({
			type:'post',
			url:'add_cart_tjshl.do',
			data:{"sku_id":sku_id,"tjshl":tjshl+1},
			dataType:'html',
			success:function(data){
				$("#sale_shoping_cart_inner").html(data);
			}
		});
	}
	function remove_cart_tishl(sku_id,tjshl){
		if(tjshl>0){
		$.ajax({
			type:'post',
			url:'add_cart_tjshl.do',
			data:{"sku_id":sku_id,"tjshl":tjshl-1},
			dataType:'html',
			success:function(data){
				$("#sale_shoping_cart_inner").html(data);
			}
		});
		}
	}
	function delete_cart(sku_id){
		$.ajax({
			type:'post',
			url:'add_cart_tjshl.do',
			data:{"sku_id":sku_id,"tjshl":0},
			dataType:'html',
			success:function(html){
				$("#sale_shoping_cart_inner").html(html);
			}
		})
	}
	function get_cart_page(){
		$.ajax({
			type:'post',
			url:'get_cart_page.do',
			dataType:'html',
			success:function(data){
				$("#sale_shoping_cart_inner").html(data);
			}
		});
	}
</script>
</head>
<style type="text/css">
td {
	vertical-align: middle !important;
}

.form-group {
	padding: 5px 0;
}
</style>

<body>
	
	<jsp:include page="sale_index_header.jsp"></jsp:include>
	<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>

 <div id="sale_shoping_cart_inner">
 </div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom">
			<img src="images/foot.jpg" alt="">
		</div>
	</div>

</body>
</html>