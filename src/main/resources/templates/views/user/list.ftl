<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   
    <link rel="stylesheet" href="${request.contextPath}/css/public.css">
    
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/sprlay.js"></script>
    
  </head>

<body>
	<!-- 查询栏 -->
	<div class="userSearchTable">
		账号：
		<div class="layui-inline">
			<input class="layui-input" name="s_username" id="s_username_id" autocomplete="off">
		</div>
		姓名：
		<div class="layui-inline">
			<input class="layui-input" name="s_nickname" id="s_nickname_id" autocomplete="off">
		</div>
		<button class="layui-btn search-reload" data-type="reload">查询</button>
		<button class="layui-btn layui-btn-primary search-reset" data-type="reset">重置</button>
	</div>
	<table class="layui-hide" id="sprlay-user-list" lay-filter="sprlay-user-list-filter"></table>
	<!-- 工具栏 -->
	<script type="text/html" id="toolbarUserList">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="add">新增</a>
  		 </div>
	</script>
	<!-- 操作列 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>-->
	<script type="text/html" id="barUserList">
		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="assignrole">角色</a>
 		 
 		 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 		 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	<!-- 禁用 -->
	<script type="text/html" id="checkboxTpl">
 		 <input type="checkbox" name="status" id="{{d.id}}" value="{{d.status}}" title="禁用" lay-filter="userStatus" {{ d.status == 0 ? 'checked' : '' }}>
	</script>
	<script>
	
		var ctx = "${request.contextPath}";
		//add or update
		var type = "";
		layui.use(['table', 'layer','jquery','form'], function() {
			
			var table = layui.table,
				$ = layui.jquery,
				layer = layui.layer,
				form = layui.form;
			
			table.render({
				id: 'userTableID',
				elem : '#sprlay-user-list',
				url : ctx+'/user/load',
				method : "post",
				request: {
				    pageName: 'pageNum' ,//页码的参数名称，默认：page pageSize
				    limitName: 'pageSize' //每页数据量的参数名，默认：limit 
				},
				parseData: function(res){ //res 即为原始返回的数据
				    return {
				      "code": res.messageCode, //解析接口状态
				      "msg": res.message, //解析提示文本
				      "count": res.data.total, //解析数据长度
				      "data": res.data.list //解析数据列表
				    };
				},
				toolbar : '#toolbarUserList',
				title : '用户数据表',
				height: 410,//滚动条
				cols : [ [ 
					{type : 'checkbox',fixed : 'left'},
					{field : 'id',title : 'ID',fixed : 'left'},
					{field : 'username',title : '账号',fixed : 'left',unresize : true,sort : true},
					{field : 'nickname',title : '姓名',sort : true},
					{field : 'sex',title : '性别',width : 80,sort : true}, 
				 	{field : 'phone',title : '电话',sort : true},
				 	{field : 'email',title : '邮箱',sort : true},  
					{field : 'ctime',title : '创建时间',sort : true}, 
	                {field : 'status', title:'是否禁用',  templet: '#checkboxTpl', unresize: true,align: 'center'},
					{fixed : 'right', title : '操作',toolbar : '#barUserList',width : 160,align: 'center'}
				 ] ],
				page : true
			});
			//头工具栏事件
			table.on('toolbar(sprlay-user-list-filter)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				switch (obj.event) {
				case 'add':
					
					var param = spr.buildStr({
						type : "add",
						userid : 0
			   	 	});
					
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"userAdd", 
						type: 2,
		                title: "新增用户",
		                area: ['700px', '300px'],
		                content: ctx+'/user/form?'+param//id=0
		             });
					break;
				}
			});
			//监听行工具事件
			table.on('tool(sprlay-user-list-filter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					
					var param = spr.buildStr({
						type : "detail",
						userid : data.id
			   	 	});
					
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"userDetail",
	                    type: 2,
	                    title: "用户信息",
	                    area: ['700px', '250px'],
	                    content: ctx+'/user/form/?'+param
	                });
				}
				else if (obj.event === 'del') {
					
					var param = spr.buildJson({
						userid : data.id
			   	 	});
					
					layer.confirm('真的删除行么', function (index) {
	                    $.ajax({
	                        url: ctx+'/user/delete',
	                        type: "POST",
	                        async: false,
	                        data: param,
	                        success: function (res) {
	                            if (res.code == 0) {
	                            	 layer.msg("删除成功", {icon: 6});
	                                //刷新list,重载
	                               	 table.reload("userTableID");
	                            } else {
	                            	 layer.msg(res.msg, {icon: 5});
	                            }
	                        },
	                        error:function(){
		  				      	layer.msg("操作失败", {icon: 5});
		  			        }
	                    });
	                });
				} else if (obj.event === 'edit') {
					
					var param = spr.buildStr({
						type : "update",
						userid : data.id
			   	 	});

					layer.open({
						id:"userEdit",
	                    type: 2,
	                    title: "编辑用户",
	                    area: ['700px', '300px'],
	                    content: ctx+'/user/form/?'+param
	                });	
				}else if (obj.event === 'assignrole') {
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"assignRole",
	                    type: 2,
	                    title: data.username+":分配角色",
	                    area: ['700px', '380px'],
	                    content: ctx+'/user/assign/'+data.id
	                });	
				}
				
			});
		   
		   var active = {
				   reload: function(){
				       var username = $('#s_username_id');
				       var nickname = $('#s_nickname_id');
				       
				       var param = spr.buildJson({
				    	   username : username.val(),
				    	   nickname : nickname.val()
				   	 	});
				       
				      //执行重载
				       table.reload('userTableID', {
				            page: {
				           		curr: 1 //重新从第 1 页开始
				            },
				      		where: param
				      });
				    }
				  };
		   
		    //列表查询按钮   
			$('.userSearchTable .search-reload').on('click', function(){
				  var type = $(this).data('type');
				  active[type] ? active[type].call(this) : '';
			});
			
			//列表重置按钮 
			$('.userSearchTable .search-reset').on('click', function(){
				$("#s_username_id").val("");
				$("#s_nickname_id").val("");
			});
			 
			//监听禁用操作
			 form.on('checkbox(userStatus)', function(data){
			 	var checked = data.elem.checked;
			 	var id = $(data.elem).attr("id");
			 	var status = 1;
			 	if(checked){
			 		status = 0;
			 	}else{
			 		status = 1;
			 	}
			 	$.ajax({
			         url:ctx+"/user/update",
			         type:'post',//method请求方式，get或者post
			         dataType:'json',//预期服务器返回的数据类型
			         data:JSON.stringify({"id":id,"status":status}),//表格数据序列化
			         contentType: "application/json; charset=utf-8",
			         success:function(res){//res为相应体,function为回调函数
			            if(res.code==0){
			            	layer.msg("修改成功",{icon: 6});
			            }else{
			            	layer.msg(res.msg, {icon: 5});
			            }
			          },
			          error:function(){
			        	  layer.msg("操作失败", {icon: 5});
			          }
			      });
			});
		});
	</script>
</body>

</html>