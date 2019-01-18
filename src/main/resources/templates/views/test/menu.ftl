<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>test左侧菜单</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

	<link rel="stylesheet" href="${request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/test/login.js"></script>

</head>
<body>
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
   <div class="layui-side layui-bg-black" id="admin-side">
		        <div class="layui-side-scroll">
		            <ul class="layui-nav layui-nav-tree" id="nav"  lay-filter="demo"></ul>
		        </div>
 	</div>

</body>
</html>