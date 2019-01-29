<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>sprlay管理后台登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
   
    <link rel="icon" href="${request.contextPath}/images/favicon.ico" />
	<link rel="stylesheet" href="${request.contextPath}/css/login/login.css">
    
 
    <script  type="text/javascript">  
    
		var ctx = "${request.contextPath}";

		//session过期后登录页面嵌套在框架中问题的解决方案
		if (top != window)  
	      top.location.href = window.location.href;  
	</script>
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/login.js"></script>
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form">
            <input name="username" placeholder="用户名" value="admin" type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" value="123456" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
    
    <!-- 底部结束 -->
</body>
</html>