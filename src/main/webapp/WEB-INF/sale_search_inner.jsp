<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<c:forEach items="${list_object_sku}" var="sku">
		<div class="list">
		<div class="img"><img src="upload/${sku.spu.shp_tp}" width="150px" height="150px" /></div>
		<div class="price">${sku.jg}</div>
		<div class="title"><a
				href="get_sku_detail.do?sku_id=${sku.id}&spu_id=${sku.spu.id}">${sku.sku_mch}</a></div>
		</div>
	</c:forEach>
