<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
   
    <link rel="stylesheet" href="${request.contextPath}/css/user/list.css">
    
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/user/list.js"></script>
    
  </head>

<body>
	<!-- <div class="sprlay-nav">
		<span class="layui-breadcrumb" lay-separator="->">
			<a><cite>系统管理</cite></a> 
			<a><cite>用户管理</cite></a> 
			<a><cite>用户列表</cite></a>
		</span> 
		<a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> 
			<i class="layui-icon" style="line-height: 30px">&#xe669;</i></a>
	</div> -->
	<div class="sprlay-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 sprlay-so">
				<input class="layui-input" placeholder="开始日" name="start" id="start">
				<input class="layui-input" placeholder="截止日" name="end" id="end">
				<input type="text" name="username" placeholder="请输入用户名"
					autocomplete="off" class="layui-input">
				<button class="layui-btn" lay-submit="" lay-filter="sreach">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>
		<xblock>
			<button class="layui-btn layui-btn-danger" onclick="delAll()">
				<i class="layui-icon"></i>批量删除
			</button>
			<button class="layui-btn"
				onclick="sprlay_admin_show('添加用户','./admin-add.html')">
				<i class="layui-icon"></i>添加
			</button>
			<span class="sprlay-right" style="line-height: 40px">共有数据：88 条</span> 
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>ID</th>
					<th>登录名</th>
					<th>手机</th>
					<th>邮箱</th>
					<th>角色</th>
					<th>加入时间</th>
					<th>状态</th>
					<th>操作</th>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="layui-unselect layui-form-checkbox" lay-skin="primary"
							data-id='2'>
							<i class="layui-icon">&#xe605;</i>
						</div>
					</td>
					<td>1</td>
					<td>admin</td>
					<td>18925139194</td>
					<td>113664000@qq.com</td>
					<td>超级管理员</td>
					<td>2017-01-01 11:11:42</td>
					<td class="td-status">
						<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>
					</td>
					<td class="td-manage">
						<a onclick="member_stop(this,'10001')" href="javascript:;" title="启用"> 
							<i class="layui-icon">&#xe601;</i>
						</a> 
						<a title="编辑" onclick="x_admin_show('编辑','admin-edit.html')" href="javascript:;"> 
							<i class="layui-icon">&#xe642;</i>
						</a> 
						<a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;"> 
							<i class="layui-icon">&#xe640;</i>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- 分页 -->
		<div id="sprlay-page">
		</div>

	</div>
</body>

</html>