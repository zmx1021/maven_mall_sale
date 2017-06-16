<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="">
	<h6>最新加入的商品</h6>
	<c:forEach items="${list_cart}" var="shoppingcart">
		<div class="one">
			<img src="upload/${shoppingcart.shp_tp}"
				style="width: 60px; height: 60px" /> <span class="one_name">
				${shoppingcart.sku_mch} </span> <span class="one_prece"> <b>${shoppingcart.sku_jg}元</b><br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
			</span>
		</div>
	</c:forEach>
	<div class="gobottom">
		共<span>${count}</span>件商品&nbsp;&nbsp;&nbsp;&nbsp; 共计￥<span>${sum_price}</span>
		<button class="goprice"
			onclick="window.location.href='goto_shopping_cart.do'">去购物车</button>
	</div>
</div>

</html>