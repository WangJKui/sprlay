<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>sprlay主页</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	
	<link rel="icon" href="${request.contextPath}/images/favicon.ico" />
	<link rel="stylesheet" href="${request.contextPath}/css/index/index.css">
	<script type="text/javascript">
		var ctx = "${request.contextPath}";
	</script>
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/index/index.js"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">sprlay管理后台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item sprlay-flexible" lay-unselect>
					<a href="javascript:;" title="侧边伸缩">
             			 <i class="layui-icon layui-icon-shrink-right" id="sprlay-app-flexible"></i>
            		</a>
				</li>
				<li class="layui-nav-item layui-layout-left-tree layui-this" ><a href="javascript:;">控制台</a></li>
				<li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
				<li class="layui-nav-item"><a href="javascript:;">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;">邮件管理</a>
						</dd>
						<dd>
							<a href="javascript:;">消息管理</a>
						</dd>
						<dd>
							<a href="javascript:;">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right" lay-filter="sprlay-nav-right">
				<li class="layui-nav-item" sprlay-target="help">
					<a href="javascript:;" id="sprlay-help">
						<i class="layui-icon">&#xe607;</i><span>帮助</span>
					</a>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;"> 
						<img src="${request.contextPath}/images/favicon.ico" class="layui-nav-img"> 阁主
					</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" id="sprlay-user-form" data-url="${request.contextPath}/user/form">
								<i class="layui-icon layui-icon-friends"></i>
								<cite>基本资料</cite>
							</a>
						</dd>
						<dd>
							<a href="javascript:;" id="sprlay-user-password">
								<i class="layui-icon layui-icon-set-fill"></i>
								<cite>修改密码</cite>
							</a>
						</dd>
						<dd>
							<a href="${request.contextPath}/login/logout" id="sprlay-user-logout">
								<i class="layui-icon layui-icon-close"></i>
								<cite>注销</cite>
							</a>
						</dd>
					</dl></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				 <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="sprlay-nav" id="sprlay-nav-id"></ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="layui-tab layui-tab-card layui-tab-brief" lay-filter="sprlay-tab" lay-allowClose="true" >
				<ul class="layui-tab-title">
					<li class="layui-this sprlay-home" lay-id="sprlay-home-id">我的桌面</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
					<iframe data-id="sprlay-iframe-main" frameborder="0" src="${request.contextPath}/main"  class="sprlay-iframe"></iframe>
						<!-- 滚动条<#include "/views/main.ftl"> -->
					</div>
				</div>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© wangjkui.com - 底部固定区域
		</div>
	</div>
</body>
</html>