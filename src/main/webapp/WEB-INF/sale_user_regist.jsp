<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<%
	String APP_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	request.setAttribute("APP_PATH", APP_PATH);
%>
<base href="${APP_PATH}">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="css/sign.css"/>
<link rel="stylesheet" type="text/css" href="css/css.css">
<style type="text/css">
   .regist_button{
    width: 100%;
	height: 40px;
	font:bold 18px/40px "宋体";
	color: white;
	background-color: red;
	outline-color: white;
    }
</style>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function doRegist(){
			var flag = $("#ck").prop("checked");
			if(flag){
			$.ajax({
				type:'post',
				url:'user_regist.do',
				data:$("#user_regist").serialize(),
				dataType:'json',
				success:function(data){
					if(data.success){
						//注册成功跳转到登录页面
						alert("注册成功！");
						window.location.href="goto_login1228.do";
					}else{
						//如果注册失败 留在当前页面
						alert("注册失败，请重试！");
					}
				}
			});
			}
			
		}
	function check_login_account(){
		var loginaccount = $("#userName").val();
		if(loginaccount!=""){
			$("#usernametips").html("");
			//发送ajiax 请求 用户名查重
			$.getJSON(
					'check_user_loginAccount.do',
					{"loginaccount":loginaccount},
					function(data){
						if(!data.success){
							$("#userName").val("");
							$("#userName").focus();
							$("#usernametips").html("用户名已存在，请重新输入！");
							
						}else{
							$("#usernametips").html("");
						}
				
			});
		}else{
			$("#usernametips").html("请输入用户名！");
			$("#userName").focus();
		}
	}
	function chcek_pass_word(){
		var password = $("#pwd").val();
		if(password==""){
			$("#passwordtips").html("请输入密码！")
			$("#pwd").focus();
		}else{
			$("#passwordtips").html("");
		}
	}
	function check_pass_word_again(){
		var pwd2 = $("#pwd2").val();
		var pwd = $("#pwd").val();
		
		if(pwd==""){
			$("#pwd").focus();
			$("#passwordtips").html("请输入密码！")
		}else{
			$("#passwordtips").html("")
		}
		if(pwd2==""){
			$("#pwd2tips").html("请再次输入密码！");
		}else{
			$("#pwd2tips").html("");
		}
			
	    if(pwd!=pwd2){
				$("#pwd2tips").html("输入密码不一致，请重新输入！");
				$("#pwd").val("");
				$("#pwd2").val("");
				$("#pwd").focus();
			}else{
				$("#pwd2tips").html("");
			}
		
	}
</script>
</head>
<body>
    <!--头部-->
    <div class="header">
        <a class="logo" href="##"></a>
        <div class="desc">欢迎注册</div>
    </div>
    <!--版心-->
    <div class="container">
    	<!--京东注册模块-->
    	<form id="user_regist">
    	<div class="register">
    		<!--用户名-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default">
    				<label for="userName">用&nbsp;户&nbsp;名</label>
    				<input type="text" id="userName" name="yh_mch" placeholder
    				="您的账户名和登录名" onblur="check_login_account();"/>
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span id="usernametips"></span>
    			</div>
    		</div>
    		<!--设置密码-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default">
    				<label for="pwd">设 置 密 码</label>
    				
<!--     				chcek_pass_word(); -->
    				<input type="password" id="pwd" name="yh_mm" placeholder
    				="建议至少两种字符组合" onblur="" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip" id="passwordtips">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		<!--确认密码-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default">
    				<label for="pwd2">确 认 密 码</label>
    				<input type="password" id="pwd2"  placeholder="请再次输入密码" onblur="check_pass_word_again();" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip" id="pwd2tips">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
			<!--设置密码-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default">
    				<label for="email">邮 箱 验 证</label>
    				<input type="text" id="email" name="yh_yx" placeholder="请输入邮箱" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		<!--手机验证-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default">
    				<label for="mobile">手 机 验 证</label>
    				<input type="text" id="mobile" name="yh_shjh" placeholder="请输入手机号" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		 <!--注册协议-->
    		<div class="register-box xieyi">
    			<!--表单项-->
    			<div class="box default">
    				<input type="checkbox" id="ck" />
    				<span>我已阅读并同意<a href="##">《京东用户注册协议》</a></span>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		<!--注册-->
<!--     		<button id="btn" onclick="doRegist();">注册</button> -->
    		<input type="button" class='regist_button'  id="btn"  value="注册" onclick="doRegist();"/>
    	</div>
    </form>
    </div>
</body>
</html>