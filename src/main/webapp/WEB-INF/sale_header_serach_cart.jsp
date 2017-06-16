<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<script type="text/javascript">
function shopping_cart_show(){
    $.ajax({
   	 type:'post',
   	 url:'get_mini_cart.do',
   	 data:'',
   	 datatype:'html',
   	 success:function(data){
   		 $("#sale_mini_cart_inner").html(data);
   	 }
    });
	$(".cart_pro").toggle();
}

</script>
<div class="search">
	<div class="logo">
		<img src="images/jd.png" alt="">
	</div>
	<div class="search_on">
		<div class="se">
			<input type="text" name="search" class="lf"> <input
				type="submit" class="clik" value="搜索">
		</div>
		<div class="se">
			<a href="">取暖神奇</a> <a href="">1元秒杀</a> <a href="">吹风机</a> <a href="">玉兰油</a>
		</div>
	</div>
	<jsp:include page="sale_mini_cart.jsp"></jsp:include>
</div>