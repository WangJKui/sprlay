<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>资源表单</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<link rel="stylesheet" href="${request.contextPath}/css/public.css">
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>

</head>

<body>
	<form class="layui-form" style="width: 700px;padding-top:10px" lay-filter="menu-form-filter">
		<div class="layui-form-item" style="display:none;">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" name="id" autocomplete="off" class="layui-input" value="${(menu.id)!''}">
				<input type="text" name="pid" autocomplete="off" class="layui-input" value="${(menu.pid)!''}">
				<input type="text" name="innercode" autocomplete="off" class="layui-input" value="${(menu.innercode)!''}">
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-inline">
					<input type="text" name="name" lay-verify="required" value="${(menu.name)!''}"
						placeholder="请输入菜单名称" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">权限值</label>
				<div class="layui-input-inline">
					<input type="text" name="permission" lay-verify="required" value="${(menu.permission)!''}"
						placeholder="请输入权限值" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">路径</label>
				<div class="layui-input-inline">
					<input type="text" name="uri" value="${(menu.uri)!''}"
						placeholder="请输入路径" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">图标</label>
				<div class="layui-input-inline">
					<input type="text" name="icon" value="${(menu.icon)!''}"
						placeholder="请输入图标" autocomplete="off" class="layui-input" <#if (((type)!"") == "detail")> disabled </#if>>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">类型</label>
				<div class="layui-input-inline">
					<input type="radio" name="type" value="1" title="目录" <#if (((type)!"") == "detail")> disabled </#if> <#if (((menu.type)!"") == 1)> checked </#if> > 
					<input type="radio" name="type" value="2" title="菜单" <#if (((type)!"") == "detail")> disabled </#if> <#if (((menu.type)!"") == 2)> checked </#if> >
					<input type="radio" name="type" value="3" title="按钮" <#if (((type)!"") == "detail")> disabled </#if> <#if (((menu.type)!"") == 3)> checked </#if> >
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">序号</label>
				<div class="layui-input-inline">
					<input type="text" name="orderno" value="${(menu.orderno)!'1'}" lay-verify="number"
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
				<button class="layui-btn layui-btn-sm" lay-submit lay-filter="menuform">保存</button>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" id="menuClose">关闭</button>
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
			  menuname: function(value, item){ //value：表单的值、item：表单的DOM对象
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
			  
			}); 
		  
		  form.on('submit(menuform)', function(data){
		    $.ajax({
		          url:ctx+"/menu/"+type,
		          type:'post',//method请求方式，get或者post
		          dataType:'json',//预期服务器返回的数据类型
		          data:JSON.stringify(data.field),//表格数据序列化
		          contentType: "application/json; charset=utf-8",
		          success:function(res){//res为相应体,function为回调函数
		            if(res.code==0){
		            	layer.msg("保存信息成功",{icon: 6});
		             	var index = parent.layer.getFrameIndex(window.name);  
					 	setTimeout(function(){
						  parent.layer.close(index);//关闭弹出层
						  parent.location.reload();//更新父级页面（提示：如果需要跳转到其它页面见下文）
			    	    }, 1000);

		            }else{
		            	layer.msg(res.msg);
		            }
		          },
		          error:function(){
		        	  layer.msg("操作失败");
		          }
		        });
		    return false;
		  });
		//列表重置按钮 
		
			$('#menuClose').on('click', function() {
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			});
		});
	</script>
</body>

</html>