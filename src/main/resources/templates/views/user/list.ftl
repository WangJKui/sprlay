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
 			 <button class="layui-btn layui-btn-sm" lay-event="add">新增</a>

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
	
		var ctx = "${request.contextPath}";
	
		layui.use('table', function() {
			var table = layui.table;
			table.render({
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
				}, {
					field : 'sex',
					title : '性别',
					width : 80,
					//edit : 'text',
					sort : true
				}, {
					field : 'email',
					title : '邮箱',
					width : 150,
					//edit : 'text',
					templet : function(res) {
						return '<em>' + res.email + '</em>'
					}
				}, {
					field : 'phone',
					title : '电话',
					width : 120,
					sort : true
				},   {
					field : 'createTime',
					title : '加入时间',
					width : 120
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
				case 'add':
					layer.open({
						type: 2,
						title:"新增用户信息",
						content: ctx+'/user/form/add', //这里content是一个普通的String
						area: ['500px', '430px']
						});
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
					layer.open({
						type: 2,
						title:"编辑用户信息",
						content: ctx+'/user/form/edit', //这里content是一个普通的String
						area: ['500px', '430px']
						});
				}
			});
			
			
		});
	</script>

</body>

</html>