<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   
    <link rel="stylesheet" href="${request.contextPath}/css/public.css">
    
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    
  </head>

<body>
	<!-- 查询栏 -->
	<div class="roleSearchTable">
		名称：
		<div class="layui-inline">
			<input class="layui-input" name="s_name" id="s_name_id" autocomplete="off">
		</div>
		编码：
		<div class="layui-inline">
			<input class="layui-input" name="s_code" id="s_code_id" autocomplete="off">
		</div>
		<button class="layui-btn search-reload" data-type="reload">查询</button>
		<button class="layui-btn layui-btn-primary search-reset" data-type="reset">重置</button>
	</div>
	<table class="layui-hide" id="sprlay-role-list" lay-filter="sprlay-role-list-filter"></table>

	<!-- 工具栏 -->
	<script type="text/html" id="toolbarRoleList">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="add">新增</a>
  		 </div>
	</script>

	<!-- 操作列 -->
	<script type="text/html" id="barRoleList">
 		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>
 		 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 		 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	<!-- 禁用 -->
	<script type="text/html" id="checkboxTpl">
 		 <input type="checkbox" name="status" value="{{d.status}}"  id="{{d.id}}" title="禁用" lay-filter="roleStatus" {{ d.status == 0 ? 'checked' : '' }}>
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
				id: 'roleTableID',
				elem : '#sprlay-role-list',
				url : ctx+'/role/load',
				method : "post",
				parseData: function(res){ //res 即为原始返回的数据
				    return {
				      "code": res.code, //解析接口状态
				      "msg": res.msg, //解析提示文本
				      "count": res.data.total, //解析数据长度
				      "data": res.data.list //解析数据列表
				    };
				},
				toolbar : '#toolbarRoleList',
				title : '角色数据表',
				height: 410,//滚动条
				cols : [ [ 
					{type : 'checkbox',fixed : 'left'},
					{field : 'id',title : 'ID',width : 120,fixed : 'left'},
					{field : 'name',title : '名称',fixed : 'left',unresize : true,sort : true},
					{field : 'code',title : '编码',sort : true},
					{field : 'remark',title : '描述',sort : true}, 
					{field : 'ctime',title : '创建时间',sort : true}, 
	                {field : 'status', title:'是否禁用',  templet: '#checkboxTpl', unresize: true,align: 'center'},
					{fixed : 'right', title : '操作',toolbar : '#barRoleList',width : 160,align: 'center'}
				 ] ],
				page : true
			});
			//头工具栏事件
			table.on('toolbar(sprlay-role-list-filter)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				switch (obj.event) {
				case 'add':
						layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							id:"roleAdd", 
							type: 2,
		                    title: "新增角色",
		                    area: ['700px', '240px'],
		                    content: ctx+'/role/form/add/0'//id=0
		                });
					break;
				}
			});
			//监听行工具事件
			table.on('tool(sprlay-role-list-filter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"roleDetail",
	                    type: 2,
	                    title: "角色详情",
	                    area: ['700px', '240px'],
	                    content: ctx+'/role/form/detail/'+data.id
	                });
				}
				else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function (index) {
	                    $.ajax({
	                        url: ctx+'/role/delete/'+data.id,
	                        type: "POST",
	                        async :false,
	                        success: function (res) {
	                            if (res.code == 0) {
	                            	 layer.msg("删除成功", {icon: 6});
	                                //刷新list,重载
	                               	 table.reload("roleTableID");
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
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"roleEdit",
	                    type: 2,
	                    title: "编辑角色",
	                    area: ['700px', '240px'],
	                    content: ctx+'/role/form/update/'+data.id
	                });	
				}
			});
		   
		   var active = {
				   reload: function(){
				       var name = $('#s_name_id');
				       var code = $('#s_code_id');
				       
				      //执行重载
				       table.reload('roleTableID', {
				            page: {
				           		curr: 1 //重新从第 1 页开始
				            },
				      		where: {name: name.val(),code: code.val()}
				      });
				    }
				  };
		   
		    //列表查询按钮   
			$('.roleSearchTable .search-reload').on('click', function(){
				  var type = $(this).data('type');
				  active[type] ? active[type].call(this) : '';
			});
			
			//列表重置按钮 
			$('.roleSearchTable .search-reset').on('click', function(){
				$("#s_name_id").val("");
				$("#s_code_id").val("");
			});
			//监听禁用操作
			 form.on('checkbox(roleStatus)', function(data){//1 status：true
				 var checked = data.elem.checked;
				 var id = $(data.elem).attr("id");
				 var status = 1;
				 if(checked){
				 	status = 0;
				 }else{
				 	status = 1;
				 }
				 $.ajax({
				     url:ctx+"/role/update",
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