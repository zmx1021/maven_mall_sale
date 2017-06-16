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

<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function search_attr_value_up(attr_id,value_id,shx_mch){
		$("#search_param").before('<span class="gt">&gt</span><div class="filter_div">'+shx_mch+'</div>');
		$("#search_param").append("<input type='hidden' value='"+attr_id+"_"+value_id+"'>");
		// 调用异步查询sku集合的函数  传参ppid
		search_get_sku_by_class_2_attr_value(0);
	}
	
	function search_get_sku_by_class_2_attr_value(ppid){
		var inputs = $("input:hidden");
		var class_2_id = ${class_2_id};
		var paramObj = {"class_2_id":class_2_id,"pp_id":ppid};
		
		$(inputs).each(function(i,json){
			var json_input = json.value;
			paramObj["list_attr_value["+i+"].shxm_id"]= json_input.split("_")[0];
			paramObj["list_attr_value["+i+"].shxzh_id"]= json_input.split("_")[1];
		});
		$.post("get_sku_by_class_2_attr_value.do",
				paramObj,
				function(data){
			$("#search_inner").html(data);
		});
	}
	
	function search_by_pp(ppid,ppmch){
		search_get_sku_by_class_2_attr_value(ppid);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品检索页面</title>
</head>
<body>
	<jsp:include page="sale_index_header.jsp"></jsp:include>
	<jsp:include page="sale_header_serach_cart.jsp"></jsp:include>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
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
	<!--筛选阶段-->
	<form id="search_form">
	<div class="filter" >
		<h2>${class_2_name}</h2>
		<div class="filter_clear" id="search_param">清空筛选</div>
	</div>
	<div class="Sscreen">
		<div class="title">
			${class_2_name} 商品筛选 共1205个商品
		</div>
		<div class="list">
			<span>品牌：</span>
			<c:forEach items="${list_trade_mark}" var="trademark">
			<a href="javascript:search_by_pp(${trademark.id},'${trademark.ppmch}');">${trademark.ppmch}</a>&nbsp;&nbsp;
       </c:forEach>
			
		</div>
		<c:forEach items="${list_attr}" var="attr">
		<div class="list">
			<span>${attr.shxm_mch}:</span>
			<c:forEach items="${attr.list_value}" var="val">
				<a
					href="javascript:search_attr_value_up(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}');">${val.shxzh}${val.shxzh_mch}</a>
			</c:forEach>
		</div>
		
		
			
		</c:forEach>
		<div class="list">
			<span class="list_span" id="list_beas">销量</span>
			<span class="list_span">价格</span>
			<span class="list_span">评论数</span>
			<span class="list_span">上架时间</span>
		</div>
	</div>
     </form> 
	<div class="Sbox" id="search_inner">
		<jsp:include page="sale_search_inner.jsp"></jsp:include>
	</div>
		<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>