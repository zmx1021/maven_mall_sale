<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="Cbox">
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品属性</th>
				<th>商品价格</th>
				<th>商品数量</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list_shopping_cart}" var="shoping_cart">
				<tr>
					<td><img src="upload/${shoping_cart.shp_tp}" alt=""
						style="width: 100px; height: 100px"></td>
					<td>${shoping_cart.sku_mch}</td>
					<td>颜色：<span style='color: #ccc'>白色</span><br> 尺码：<span
						style='color: #ccc'>L</span>
					</td>
					<td>${shoping_cart.sku_jg}</td>
					<td><img alt="" src="images/jian.jpg"
						onclick="remove_cart_tishl(${shoping_cart.sku_id},${shoping_cart.tjshl});">
						<input type="text" name="min" value="${shoping_cart.tjshl}"
						style="width: 50px; text-align: center"> <img alt=""
						src="images/jia.jpg"
						onclick="add_cart_tishl(${shoping_cart.sku_id},${shoping_cart.tjshl});">
					</td>
					<td><a href="javascript:delete_cart(${shoping_cart.sku_id});">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="Cprice">
	<div class="price">总价：${cart_hjjg}元<br>总共：${count}件</div>
	<a href="to_review_order.do"><span class="jiesuan">生成订单</span></a>
</div>



