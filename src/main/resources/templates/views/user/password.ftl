<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<link rel="stylesheet" href="${request.contextPath}/css/public.css">
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>

</head>

<body>
	<blockquote class="layui-elem-quote">修改密码</blockquote>

	<form class="layui-form" action="" >
		<div class="layui-form-item">
			<label class="layui-form-label">原密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" required
					lay-verify="required" placeholder="请输入原密码" autocomplete="off"
					class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">辅助文字</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">输入新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" required
					lay-verify="required" placeholder="请输入新密码" autocomplete="off"
					class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">辅助文字</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">再次输入新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" required
					lay-verify="required" placeholder="请输入新密码" autocomplete="off"
					class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">辅助文字</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

	<script>
		//Demo
		layui.use('form', function(){
		  var form = layui.form;
		  
		  //监听提交
		  form.on('submit(formDemo)', function(data){
		    layer.msg(JSON.stringify(data.field));
		    return false;
		  });
		});
</script>
</body>

</html>