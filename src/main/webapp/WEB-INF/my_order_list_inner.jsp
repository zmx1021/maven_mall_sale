<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<%
	String APP_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	request.setAttribute("APP_PATH", APP_PATH);
%>
<base href="${APP_PATH}">
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function a(){
             }
</script>
</head>
<body>
	<c:forEach items="${list_order}" var="orderlist">
		<div class="product">
			<div class="pro_name">${orderlist.id}</div>
			<div class="pro_name">${orderlist.shhr}</div>
			<div class="pro_deit">${orderlist.jdmsh}</div>
			<div class="pro_deit">未知</div>
			<div class="pro_fin"><a href="check_order_detail.do?id=${orderlist.id}">查看详情</a><c:if test="${orderlist.jdh==1}"><a href="javascript:;">/完成支付</a></c:if> </div>
		</div>
	</c:forEach>

</body>
</html>