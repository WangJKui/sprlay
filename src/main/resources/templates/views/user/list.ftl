<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   
    <link rel="stylesheet" href="${request.contextPath}/css/user/list.css">
    
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/user/list.js"></script>
    
  </head>

<body>
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

	<script type="text/html" id="toolbarUserList">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="add">新增</a>
  		 </div>
	</script>

	<script type="text/html" id="barUserList">
 		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>
 		 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 		 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>

	<script>
	
		var ctx = "${request.contextPath}";
		//add or update
		var type = "";
		layui.use(['table', 'layer','jquery'], function() {
			
			var table = layui.table,
			$ = layui.jquery,
			layer = layui.layer;
			table.render({
				id: 'userTableID',
				elem : '#sprlay-user-list',
				url : ctx+'/user/all',
				method : "post",
				parseData: function(res){ //res 即为原始返回的数据
				    return {
				      "code": res.code, //解析接口状态
				      "msg": res.msg, //解析提示文本
				      "count": res.data.total, //解析数据长度
				      "data": res.data.list //解析数据列表
				    };
				},
				toolbar : '#toolbarUserList',
				title : '用户数据表',
				cols : [ [ {
					type : 'checkbox',
					fixed : 'left'
				}, {
					field : 'id',
					title : 'ID',
					fixed : 'left',
					//hide:true
				}, {
					field : 'username',
					title : '账号',
					width : 120,
					fixed : 'left',
					unresize : true,
					sort : true
				}, {
					field : 'nickname',
					title : '姓名',
					width : 120,
					//edit : 'text'
					sort : true
				}, {
					field : 'sex',
					title : '性别',
					width : 80,
					//edit : 'text',
					sort : true
				},  {
					field : 'phone',
					title : '电话',
					width : 120,
					sort : true
				}, {
					field : 'email',
					title : '邮箱',
					width : 180,
					//edit : 'text',
					/* templet : function(res) {
						return '<em>' + res.email + '</em>'
					} */
					sort : true
				},  {
					field : 'createtime',
					title : '加入时间',
					width : 180,
					sort : true
				}, {
					fixed : 'right',
					title : '操作',
					toolbar : '#barUserList',
					width : 180
				} ] ],
				page : true
			});
			//头工具栏事件
			table.on('toolbar(sprlay-user-list-filter)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				switch (obj.event) {
				case 'add':
						layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							id:"userAdd", 
							type: 2,
		                    title: "新增用户",
		                    area: ['700px', '300px'],
		                    content: ctx+'/user/form/add/0'//id=0
		                });
					break;
				}
			});
			//监听行工具事件
			table.on('tool(sprlay-user-list-filter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"userDetail",
	                    type: 2,
	                    title: "用户信息",
	                    area: ['700px', '250px'],
	                    content: ctx+'/user/form/detail/'+data.id
	                });
				}
				else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function (index) {
	                    $.ajax({
	                        url: ctx+'/user/delete/'+data.id,
	                        type: "POST",
	                        async :false,
	                        success: function (res) {
	                            if (res.code == 0) {
	                            	 layer.msg("删除成功", {icon: 6});
	                                //刷新list,重载
	                               	 table.reload("userTableID");
	                            } else {
	                                layer.msg("删除失败", {icon: 5});
	                            }
	                        }
	                    });
	                });
				} else if (obj.event === 'edit') {
						layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							id:"userEdit",
		                    type: 2,
		                    title: "编辑用户",
		                    area: ['700px', '300px'],
		                    content: ctx+'/user/form/update/'+data.id
		                });
				}
			});
		   
		   var active = {
				   reload: function(){
				       var username = $('#s_username_id');
				       var nickname = $('#s_nickname_id');
				       
				      //执行重载
				       table.reload('userTableID', {
				            page: {
				           		curr: 1 //重新从第 1 页开始
				            },
				      		where: {username: username.val(),nickname: nickname.val()}
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
			  
		});
	</script>
</body>

</html>