<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>角色表单</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<link rel="stylesheet" href="${request.contextPath}/css/public.css">
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>

</head>

<body>
	<form class="layui-form" style="width: 700px;padding-top:10px" lay-filter="user-form-filter">
		<div class="layui-form-item" style="display:none;">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" name="id" autocomplete="off" class="layui-input" value="${(role.id)!''}">
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-inline">
					<input type="text" name="name" required lay-verify="name" value="${(role.name)!''}"
						placeholder="请输入名称" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">编码</label>
				<div class="layui-input-inline">
					<input type="text" name="code" required lay-verify="rolecode" value="${(role.code)!''}"
						placeholder="请输入编码" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-inline">
					<input type="text" name="remark" lay-verify="remark" value="${(role.remark)!''}"
						placeholder="请输入描述" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
			  <div class="layui-inline">
				<label class="layui-form-label">创建时间</label>
				<div class="layui-input-inline">
					<input type="datetime" name="ctime" value="${(role.ctime)!''}" readonly="readonly" 
					autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
		</div>

		<div class="layui-form-item" 
			<#if (((type)!'') == 'detail')> 
				style="display:none;" 
			<#else>
			style="float: right;margin-right: 30px;" 
			</#if>
		 >
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm" lay-submit lay-filter="roleform">保存</button>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" id="roleClose">关闭</button>
			</div>
		</div>
	</form>

	<script>
		var ctx = "${request.contextPath}";
		var type = "${type}";
		//Demo
		layui.use(['form','jquery','layer'], function(){
		  var form = layui.form;
		  var $ = layui.jquery;
		  var layer = layui.layer;
		  
		  form.verify({
			  rolecode: function(value, item){ //value：表单的值、item：表单的DOM对象
				if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '编码不能有特殊字符';
			    }
			    if(/(^\_)|(\__)|(\_+$)/.test(value)){
			      return '编码首尾不能出现下划线\'_\'';
			    }
			    if(/^[0-9]*$/.test(value)){
			      return '编码不能全为数字';
			    }
			    if(value.length<3 || value.length>26){
				   return '编码位数范围{3,26}';
				}  
			  },
			  
			}); 
		  
		  //监听提交 ${request.contextPath}/user/add
		  form.on('submit(roleform)', function(data){
		    $.ajax({
		          url:ctx+"/role/"+type,
		          type:'post',//method请求方式，get或者post
		          dataType:'json',//预期服务器返回的数据类型
		          data:JSON.stringify(data.field),//表格数据序列化
		          contentType: "application/json; charset=utf-8",
		          success:function(res){//res为相应体,function为回调函数
		            if(res.code==0){
		            	layer.msg("保存信息成功",{icon: 6});
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
		//列表重置按钮 
		
			$('#roleClose').on('click', function() {
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			});
		});
	</script>
</body>

</html>