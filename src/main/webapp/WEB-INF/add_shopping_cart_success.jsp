<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/css.css"/>
<link rel="stylesheet" type="text/css" href="css/finishCart.css"/>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function shopping_cart_show(){
		$(".cart_pro").toggle();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加成功</title>
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
	<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>

	<div class="cartPro">
		<span class="dec">该商品已加入购物车</span>
		<div class="lec">
			<a href=""><img src="upload/${formcart.shp_tp}" style="width: 70px;height: 70px"/></a>
		</div>
		<span class="lec_name"> ${formcart.sku_mch} </span><br>
		<span class="lec_des">数量： ${formcart.tjshl} </span>
		
		<div class="shop_des">
			<a href="get_sku_detail.do?sku_id=${formcart.sku_id}&spu_id=${formcart.shp_id}"><img src="images/shop_des.png" /></a>
		</div>
		<div class="shop_cart">
			<a href="goto_shopping_cart.do"><img src="images/shop_cart.png" /></a>
		</div>
	</div>
</body>
</html>