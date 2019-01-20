<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>sprlay管理后台登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
   
    <link rel="shortcut icon" href="${request.contextPath}/images/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${request.contextPath}/css/login/login.css">
    
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/login.js"></script>
    <script  type="text/javascript">  
		//session过期后登录页面嵌套在框架中问题的解决方案
		if (top != window)  
	      top.location.href = window.location.href;  
	  </script>
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" action=" ${request.contextPath}/login/login">
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
    
    <!-- 底部结束 -->
</body>
</html>