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
	<div style="margin: 0 auto;width:400px;">
		<form class="layui-form user-password-100" style="padding-top:20px;">
			<div class="layui-form-item" >
				<label class="layui-form-label">原密码</label>
				<div class="layui-input-inline">
					<input type="password" name="oldPassword" required
						lay-verify="required" placeholder="请输入原密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">输入新密码</label>
				<div class="layui-input-inline">
					<input type="password" name="password" required
						lay-verify="password" placeholder="请输入新密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">再次输入新密码</label>
				<div class="layui-input-inline">
					<input type="password" name="rePassword" required
						lay-verify="rePassword" placeholder="请输入新密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="userPassword">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		//Demo
		var ctx = "${request.contextPath}";

		layui.use(['form','jquery','layer'], function(){
		  var form = layui.form;
		  var $ = layui.jquery;

		  form.verify({
			  password: function(value, item){ //value：表单的值、item：表单的DOM对象
				  if (value === "") 
				      return "请输入新密码！";
				  if (!(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/.test(value))) {
				      return '只能数字和字母范围{6,12}！';
				  }
			  },
			  
			  rePassword: function(value) {
				    if(value === "") 
				      return "请再次输入新密码！";
				    if($('input[name=password').val() !== value) 
				      return "二次输入的密码不一致！";
				},
		   
			}); 
		  //监听提交
		  form.on('submit(userPassword)', function(data){
			  $.ajax({
		          url:ctx+"/user/uppassword",
		          type:'post',//method请求方式，get或者post
		          dataType:'json',//预期服务器返回的数据类型
		          data:JSON.stringify(data.field),//表格数据序列化
		          contentType: "application/json; charset=utf-8",
		          success:function(res){//res为相应体,function为回调函数
		            if(res.code==0){
		            	layer.msg("修改密码成功",{icon: 6});
		            }else{
		            	layer.msg(res.msg,{icon: 5});
		            }
		          },
		          error:function(){
		        	  layer.msg("修改密码成功",{icon: 5});
		          }
		        });
		    return false;
		  });
		});
</script>
</body>

</html>