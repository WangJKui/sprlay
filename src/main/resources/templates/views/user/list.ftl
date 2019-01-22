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
	<table class="layui-hide" id="sprlay-user-list" lay-filter="sprlay-user-list-filter"></table>

	<script type="text/html" id="toolbarUserList">
 		 <div class="layui-btn-container">
   			 <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
  			 <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
  			 <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
 			 <button class="layui-btn layui-btn-sm" lay-event="delete">删除</a>

  		 </div>
	</script>

	<script type="text/html" id="barUserList">
 		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>
 		 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 		 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>

	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#sprlay-user-list',
				url : '/sprlay/js/user/user.json',
				toolbar : '#toolbarUserList',
				title : '用户数据表',
				cols : [ [ {
					type : 'checkbox',
					fixed : 'left'
				}, {
					field : 'id',
					title : 'ID',
					width : 80,
					fixed : 'left',
					unresize : true,
					sort : true
				}, {
					field : 'username',
					title : '用户名',
					width : 120,
					//edit : 'text'
				}, {
					field : 'email',
					title : '邮箱',
					width : 150,
					//edit : 'text',
					templet : function(res) {
						return '<em>' + res.email + '</em>'
					}
				}, {
					field : 'sex',
					title : '性别',
					width : 80,
					//edit : 'text',
					sort : true
				}, {
					field : 'city',
					title : '城市',
					width : 100
				}, {
					field : 'sign',
					title : '签名'
				}, {
					field : 'experience',
					title : '积分',
					width : 80,
					sort : true
				}, {
					field : 'ip',
					title : 'IP',
					width : 120
				}, {
					field : 'logins',
					title : '登入次数',
					width : 100,
					sort : true
				}, {
					field : 'joinTime',
					title : '加入时间',
					width : 120
				}, {
					fixed : 'right',
					title : '操作',
					toolbar : '#barUserList',
					width : 150
				} ] ],
				page : true
			});

			//头工具栏事件
			table.on('toolbar(sprlay-user-list-filter)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				switch (obj.event) {
				case 'getCheckData':
					layer.alert(JSON.stringify(data));
					break;
				case 'getCheckLength':
					layer.msg('选中了：' + data.length + ' 个');
					break;
				case 'isAll':
					layer.msg(checkStatus.isAll ? '全选' : '未全选');
					break;
				case 'delete':
					layer.msg("删除："+data.length);
					break;
				}
				;
			});

			//监听行工具事件
			table.on('tool(sprlay-user-list-filter)', function(obj) {
				var data = obj.data;
				//console.log(obj)
				if (obj.event === 'detail') {
					 layer.msg('查看操作');
				}
				else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function(index) {
						obj.del();
						layer.close(index);
					});
				} else if (obj.event === 'edit') {
					/* layer.prompt({
						formType : 2,
						value : data.email
					}, function(value, index) {
						obj.update({
							email : value
						});
						layer.close(index);
					}); */
					layer.open({
						type: 2,
						title:"编辑用户信息",
						content: '/sprlay/user/form', //这里content是一个普通的String
						area: ['600px', '400px']
						});
				}
			});
			
			

		});
	</script>

</body>

</html>