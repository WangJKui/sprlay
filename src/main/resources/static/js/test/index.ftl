<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>sprlay</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
		content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	
	<link rel="shortcut icon" href="${request.contextPath}/images/favicon.ico" type="image/x-icon" />
	
	<link rel="stylesheet" href="${request.contextPath}/css/index.css">
	<link rel="stylesheet" href="${request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/index.js"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">sprlay管理后台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> 阁主
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="${request.contextPath}/login/logout">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				 <ul class="layui-nav layui-nav-tree" lay-filter="sprlay-nav" id="sprlay-nav-id"></ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="layui-tab layui-tab-card" lay-filter="sprlay-tab" lay-allowClose="true" >
				<ul class="layui-tab-title">
					<li class="layui-this sprlay-home" lay-id="sprlay-home-id">我的桌面</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<#include "/views/main.ftl">
					</div>
				</div>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>
</body>
</html>