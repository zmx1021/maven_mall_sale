<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

	$(function (){
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#index_class_1_ul").append("<li onmouseover='index_get_class_2_by_class_1_id(this.value)' value="+json.id+"><a href='javascript:;'>"+json.flmch1+"</a></li>");
			});
		});
	});
	
	function index_get_class_2_by_class_1_id(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_class_2_ul").empty();
			$("#index_class_2_ul").show();
			$(data).each(function(i,json){
				$("#index_class_2_ul").append("<li  value="+json.id+"><a href='get_sku_by_class_2/"+json.id+"/"+json.flmch2+".do' target='_blank'>"+json.flmch2+"</a></li>");
			});
		});
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城首页</title>
</head>
<body>
<!-- 头部引入 -->
<jsp:include page="sale_index_header.jsp"></jsp:include>
<!-- 搜索 和迷你购物车引入 -->
<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul id="index_class_1_ul">
							<li>
								<div class="two_nav" id="index_class_2_ul">
								</div>
							</li>
							
						</ul>
					</div>
				</div>
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
   <div class="banner">
		<div class="ban">
			<img src="images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div> 
</body>
</html>