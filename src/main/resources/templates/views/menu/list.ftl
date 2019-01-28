<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>菜单list</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<link rel="stylesheet" href="${request.contextPath}/css/menu/list.css">
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>

</head>
<body>
	<table id="auth-table" class="layui-table" lay-filter="auth-table" style="margin-top: -10px"></table>
	
	<script type="text/html" id="toolbarMenuList">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="tadd">同级新增</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="xadd">下级新增</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="expand">全部展开</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="fold">全部折叠</a>
  		 </div>
	</script>
	
	
	<!-- 操作列 -->
	<script type="text/html" id="auth-state">
 		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>
    	 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 		 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>


	<script>
	    layui.config({
	        base: '/sprlay/module/treetable/'
	    }).extend({
	        treetable: 'treetable'
	    }).use(['table', 'treetable'], function () {
	        var $ = layui.jquery;
	        var table = layui.table;
	        var treetable = layui.treetable;
	
	        // 渲染表格
	        layer.load(2);
	        treetable.render({
	        	toolbar : '#toolbarMenuList',
	        	
	            treeColIndex: 1,
	            treeSpid: -1,
	            treeIdName: 'authorityId',
	            treePidName: 'parentId',
	            elem: '#auth-table',
	            url: '/sprlay/module/treetable/menus.json',
	            page: false,
	            cols: [[
	               // {type: 'numbers'},
	                {type: 'checkbox'},
	                {field: 'authorityName', minWidth: 200, title: '菜单名称'},
	                {field: 'authority', title: '菜单标识'},
	                {field: 'menuUrl', title: '菜单url'},
	                {field: 'icon', width: 160, align: 'center', title: '图标'},
	                {
	                    field: 'isMenu', width: 80, align: 'center', templet: function (d) {
	                        if (d.isMenu == 1) {
	                            return '<span class="layui-badge layui-bg-green">按钮</span>';
	                        }
	                        if (d.parentId == -1) {
	                            return '<span class="layui-badge layui-bg-blue">目录</span>';
	                        } else {
	                            return '<span class="layui-badge layui-bg-red">菜单</span>';
	                        }
	                    }, title: '类型'
	                },
	                {templet: '#auth-state', width: 180, align: 'center', title: '操作'}
	            ]],
	            done: function () {
	                layer.closeAll('loading');
	            }
	        });
	
	        
	    	//头工具栏事件
			table.on('toolbar(auth-table)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				switch (obj.event) {
				case 'expand':
					treetable.expandAll('#auth-table');
					break;
				case 'fold':
					treetable.foldAll('#auth-table');
					break;
				case 'tadd':
					if(data.length != 1){
						layer.msg("只能选择一条数据！");
						return;
					}
					layer.msg(JSON.stringify(data));
					break;
				case 'xadd':
					if(data.length != 1){
						layer.msg("只能选择一条数据！");
						return;
					}
					layer.msg(JSON.stringify(data));
					break;
				break;
				}
			});
	    });

	</script>

</body>
</html>