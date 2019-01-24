<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>用户表单</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<link rel="stylesheet" href="${request.contextPath}/css/user/list.css">
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>

</head>

<body>
	<blockquote class="layui-elem-quote">基本资料</blockquote>

	<form class="layui-form" style="width: 350px">
		<div class="layui-form-item">
			<label class="layui-form-label">账号</label>
			<div class="layui-input-block">
				<input type="text" name="username" lay-verify="username"
					placeholder="请输入账号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" name="nickname" lay-verify="nickname"
					placeholder="请输入姓名" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-block">
				<input type="phone" name="phone" lay-verify="required|phone"
					placeholder="请输入电话" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="email" name="email" lay-verify="required|email"
					placeholder="请输入邮箱" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
				<input type="radio" name="sex" value="男" title="男" checked> 
				<input type="radio" name="sex" value="女" title="女" >
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="userform">保存</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

	<script>
		var ctx = "${request.contextPath}";

		//Demo
		layui.use(['form','jquery'], function(){
		  var form = layui.form;
		  var $ = layui.jquery;

		  form.verify({
			  username: function(value, item){ //value：表单的值、item：表单的DOM对象
				if(!value){
				  return '必填项不能为空';
				}
				if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '账号不能有特殊字符';
			    }
			    if(/(^\_)|(\__)|(\_+$)/.test(value)){
			      return '账号首尾不能出现下划线\'_\'';
			    }
			    if(/^[0-9]*$/.test(value)){
			      return '账号不能全为数字';
			    }
			    if(value.length<3 || value.length>26){
				   return '账号位数范围{3,26}';
				}  
			  },
			  
			  nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
				if(!value){
				   return '必填项不能为空';
				}
				if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '姓名不能有特殊字符';
			    }
			    if(/(^\_)|(\__)|(\_+$)/.test(value)){
			      return '姓名首尾不能出现下划线\'_\'';
			    }
			    if(/^[0-9]*$/.test(value)){
			      return '姓名不能全为数字';
			    }
			    if(value.length<2 || value.length>26){
					return '姓名位数范围{2,26}';
				}  
			  }
			  
			}); 
		  
		  //监听提交 ${request.contextPath}/user/add
		  form.on('submit(userform)', function(data){
		   // layer.msg(JSON.stringify(data.field));
		    
		    $.ajax({
		          url:ctx+"/user/add",
		          type:'post',//method请求方式，get或者post
		          dataType:'json',//预期服务器返回的数据类型
		          data:JSON.stringify(data.field),//表格数据序列化
		          contentType: "application/json; charset=utf-8",
		          success:function(res){//res为相应体,function为回调函数
		            if(res.code==0){
		            	layer.msg("保存信息成功");
		              //	layer.alert('保存信息成功',{icon:1});
		             	var index = parent.layer.getFrameIndex(window.name);  
					 	setTimeout(function(){
						  parent.layer.close(index);//关闭弹出层
						  parent.location.reload();//更新父级页面（提示：如果需要跳转到其它页面见下文）
			    	    }, 1000);

		            }else{
		            	layer.msg(res.msg);
		             // layer.alert(data.msg,{icon: 5});
		            }
		          },
		          error:function(){
		        	  layer.msg("操作失败");
		            //layer.alert('操作失败！！！',{icon:5});
		          }
		        });
		    return false;
		  });
		});
</script>
</body>

</html>