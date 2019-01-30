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
	<table id="menu-table" class="layui-table" lay-filter="menu-table-filter" style="margin-top: -10px"></table>
	
	<script type="text/html" id="toolbarMenuList">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="tadd">同级新增</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="xadd">下级新增</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="expand">全部展开</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="fold">全部折叠</a>
  		 </div>
	</script>
	
	
	<!-- 操作列 -->
	<script type="text/html" id="menu-state">
 		 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">查看</a>
    	 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 		 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>

 	<!-- 禁用 -->
	<script type="text/html" id="checkboxTpl">
 		 <input type="checkbox" name="status" value="{{d.status}}" title="禁用" lay-filter="menuStatusDemo" {{ d.status == 0 ? 'checked' : '' }}>
	</script>

	<script>
		
		var ctx = "${request.contextPath}";
		
	    layui.config({
	        base: ctx+'/module/treetable/'
	    }).extend({
	        treetable: 'treetable'
	    }).use(['table', 'treetable','form'], function () {
	        var $ = layui.jquery;
	        var table = layui.table;
	        var treetable = layui.treetable;
	        var form = layui.form;

	        // 渲染表格
	        layer.load(2);
	        treetable.render({
	        	toolbar : '#toolbarMenuList',
	        	height: 430,//滚动条
	            treeColIndex: 1,
	            treeSpid: 0,
	            treeIdName: 'id',
	            treePidName: 'pid',
	            elem: '#menu-table',
	            url: ctx+'/menu/load',
	            //method : "post",
	            page: false,
	            cols: [[
	               // {type: 'numbers'},
	                {type: 'checkbox'},
	                {field: 'name', minWidth: 200, title: '菜单名称'},
	                {field: 'permission', title: '权限值',align: 'center'},
	                {field: 'uri', title: '路径',align: 'center'},
	                {field: 'icon', align: 'center', title: '图标'},
	                {field: 'order', title:'序号', width:80, align: 'center'},
	                {field:'status', title:'是否禁用', width:110, templet: '#checkboxTpl', unresize: true,align: 'center'},
	                {
	                    field: 'type', width: 80, align: 'center', templet: function (d) {
	                    	//1:目录，2：菜单，3：按钮
	                        if (d.type == 3) {
	                            return '<span class="layui-badge layui-bg-green">按钮</span>';
	                        }else if (d.type == 2) {
	                        	return '<span class="layui-badge layui-bg-red">菜单</span>';
	                        }else {
	                            return '<span class="layui-badge layui-bg-blue">目录</span>';
	                        }
	                        
	                    }, title: '类型'
	                },
	                //{field: 'ctime', title:'创建时间', width:110, align: 'center'},
	                {templet: '#menu-state', width: 180, align: 'center', title: '操作'}
	            ]],
	            done: function () {
	                layer.closeAll('loading');
	            }
	        });
	
	        
	    	//头工具栏事件
			table.on('toolbar(menu-table-filter)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				switch (obj.event) {
				case 'expand':
					treetable.expandAll('#menu-table');
					break;
				case 'fold':
					treetable.foldAll('#menu-table');
					break;
				case 'tadd':
					if(data.length != 1){
						layer.msg("只能选择一条数据！");
						return;
					}
					layer.msg(JSON.stringify(data));
					break;
				case 'xadd'://下级新增 判断是否为按钮d.type == 3
					if(data.length != 1){
						layer.msg("只能选择一条数据！");
						return;
					}
					if(data[0].type == 3){
						layer.msg("按钮不能新增下级！");
						return;
					}
					layer.msg(JSON.stringify(data));
					break;
				}
			});
	    	
			//监听行工具事件
			table.on('tool(menu-table-filter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					/* layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"userDetail",
	                    type: 2,
	                    title: "用户信息",
	                    area: ['700px', '250px'],
	                    content: ctx+'/user/form/detail/'+data.id
	                }); */
					layer.msg("详情", {icon: 5});
				}
				else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function (index) {
	                   /*  $.ajax({
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
	                    }); */
						layer.msg("删除", {icon: 5});
	                });
				} else if (obj.event === 'edit') {
					/* layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"userEdit",
	                    type: 2,
	                    title: "编辑用户",
	                    area: ['700px', '300px'],
	                    content: ctx+'/user/form/update/'+data.id
	                });	 */	
					layer.msg("编辑"); 
				}
			});
		   
	    	
			//监听禁用操作
			 form.on('checkbox(menuStatusDemo)', function(obj){
			    layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
			});
	    });

	</script>

</body>
</html>