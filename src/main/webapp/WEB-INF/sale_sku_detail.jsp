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
<style type="text/css">
	.sku_detail{
		font-size: 12px;line-height: 26px!important;
	}
</style>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function shopping_cart_show() {
		$.ajax({
			type : 'post',
			url : 'get_mini_cart.do',
			data : '',
			datatype : 'html',
			success : function(data) {
				$("#sale_mini_cart_inner").html(data);
			}
		});
		$(".cart_pro").toggle();
	}
	function add_tjshl() {
		$("#tjshl").val($("#tjshl").val() - (-1));
	}
	function delete_tjshl() {
		if ($("#tjshl").val() == 0) {
			return false;
		}
		$("#tjshl").val($("#tjshl").val() - 1);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${user==null}">
				<span id="index_user_nch"></span>
				<a href="goto_login1228.do" target="_blank">请登录</a>
				<a href="goto_regist.do" target="_blank">请注册</a>
			</c:if>
			<c:if test="${user!=null}">
				<a href="javascript:;">欢迎 ${user.yh_nch}</a>
				<a href="javascript:;">我的订单</a>
				<a href="javascript:;">退出</a>
			</c:if>
		</div>
	</div>
	<div class="search searchBac">
		<div class="logo">
			<img src="images/logo.png" alt="">
		</div>
		<jsp:include page="sale_mini_cart.jsp"></jsp:include>
	</div>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">全部商品分类</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="Dbox">
		<div class="box">
			<div class="left">
				<div class="timg">
					<img src="upload/${object_sku.shp_tp}"
						style="height: 350px; width: 353px">
				</div>
				<div class="timg2">
					<div class="lf">
						<img src="images/lf.jpg" alt="">
					</div>
					<div class="center">
						<c:forEach items="${object_sku.list_image}" var="img">
							<span><img src="upload/${img.url}"
								style="height: 50px; width: 50px" /></span>
						</c:forEach>
					</div>
					<div class="rg">
						<img src="images/rg.jpg" alt="">
					</div>
				</div>
				<div class="goods">
					<img src="images/img_6.jpg" alt=""> 产品规格：
					<c:forEach items="${object_sku.list_attr_value_name}" var="a_v_n">
						<br>${a_v_n.attr_name}:${a_v_n.value_name}
					</c:forEach>
				</div>

			</div>
			<div class="cent">
				<div class="title">${object_sku.sku_mch}</div>
				<div class="bg">
					<p>
						市场价：<strong>${object_sku.jg}</strong>
					</p>
					<p>
						促销价：<strong>${object_sku.jg}</strong>
					</p>
				</div>
				<div class="clear">
					<span class="sku_detail">选择版本：</span> <br>
					<c:forEach items="${list_object_sku_other}" var="other">
						<div class="min_mx" style="width: 300px" align="left">
							<img style="width: 50px; height: 50px"
								src="upload/${other.shp_tp}"> <a
								href="get_sku_detail.do?sku_id=${other.id}&spu_id=${other.shp_id}">${other.sku_mch}</a><br>
						</div>
					</c:forEach>
				</div>
				<div class="clear">
					<div class="min_t">服务：</div>
					<div class="min_mx">服务1号1</div>
					<div class="min_mx">服务二号1112</div>
					<div class="min_mx">55英服务二号1111寸活动中3</div>
					<div class="min_mx">4</div>
					<div class="min_mx">呃呃呃5</div>
					<div class="min_mx">55英寸活动中6</div>
				</div>
				<div class="clear" style="margin-top: 20px;">
					<div class="min_t" style="line-height: 36px">数量：</div>
					<div class="num_box">
						<form action="add_cart.do" method="post" id="form_cart_info">
							<input type="hidden" name="sku_mch" value="${object_sku.sku_mch}" />
							<input type="hidden" name="sku_jg" value="${object_sku.jg}" /> <input
								type="text" id="tjshl" name="tjshl" value="1"
								style="width: 40px; height: 32px; text-align: center; float: left" />
							<div class="rg">
								<img src="images/jia.jpg" id='jia'
									onclick="javascript:add_tjshl();"> <img
									src="images/jian.jpg" id='jian'
									onclick="javascript:delete_tjshl();">
							</div>
							<input type="hidden" name="hj" value="${object_sku.jg}" /> <input
								type="hidden" name="shp_id" value="${object_sku.shp_id}" /> <input
								type="hidden" name="sku_id" value="${object_sku.id}" /> <input
								type="hidden" name="shp_tp" value="${object_sku.spu.shp_tp}" />
							<input type="hidden" name="kcdz" value="${object_sku.kcdz}" />
						</form>
					</div>
				</div>
				<div class="clear" style="margin-top: 20px;">
					<img src="images/mai.jpg"> <img src="images/shop.jpg"
						onclick="javascript:$('#form_cart_info').submit();">
				</div>
			</div>
		</div>
	</div>
	<div class="Dbox1">
		<div class="boxbottom">
			<div class="top">
				<span>商品详情</span> <span>评价</span>
			</div>
			<div class="btm">${object_sku.spu.shp_msh}</div>
		</div>
	</div>



	<div class="footer">
		<div class="top"></div>
		<div class="bottom">
			<img src="images/foot.jpg" alt="">
		</div>
	</div>
</body>
</html>