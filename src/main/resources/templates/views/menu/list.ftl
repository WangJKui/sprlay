<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>菜单list</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<link rel="stylesheet" href="${request.contextPath}/css/public.css">
	
	<script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>

</head>
<body>
	<table id="menu-table" class="layui-table" lay-filter="menu-table-filter" style="margin-top: -11px"></table>
	
	<script type="text/html" id="toolbarMenuList">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="tadd">同级新增</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="xadd">下级新增</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="expand">全部展开</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="fold">全部折叠</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="reload">刷新</a>
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
 		 <input type="checkbox" name="status" id="{{d.id}}" value="{{d.status}}" title="禁用" lay-filter="menuStatusDemo" {{ d.status == 0 ? 'checked' : '' }}>
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
	        	id: 'menuTableID',
	        	toolbar : '#toolbarMenuList',
	        	height: 460,//滚动条
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
	                {field: 'orderno', title:'序号', width:80, align: 'center'},
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
	                {templet: '#menu-state', width: 160, align: 'center', title: '操作'}
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
				case 'reload':
					location.reload();
					break;
				case 'expand':
					treetable.expandAll('#menu-table');
					break;
				case 'fold':
					treetable.foldAll('#menu-table');
					break;
				case 'tadd'://同级新增 判断是否为按钮d.type == 3
					if(data.length < 1){
						layer.msg("请选择一条数据！");
						return;
					}
					if(data.length > 1){
						layer.msg("只能选择一条数据！");
						return;
					}
					layer.open({
						id:"menuAddTJ",
	                    type: 2,
	                    title: "新增资源",
	                    area: ['700px', '350px'],
	                    content: ctx+'/menu/form/add/'+data[0].pid
	                });
					break;
				case 'xadd'://下级新增 判断是否为按钮d.type == 3
					if(data.length < 1){
						layer.msg("请选择一条数据！");
						return;
					}
					if(data.length > 1){
						layer.msg("只能选择一条数据！");
						return;
					}
					layer.open({
						id:"menuAddXJ",
	                    type: 2,
	                    title: "新增资源",
	                    area: ['700px', '350px'],
	                    content: ctx+'/menu/form/add/'+data[0].id
	                });
					break;
				}
			});
	    	
			//监听行工具事件
			table.on('tool(menu-table-filter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					layer.open({
						id:"menuDetail",
	                    type: 2,
	                    title: "资源信息",
	                    area: ['700px', '300px'],
	                    content: ctx+'/menu/form/detail/'+data.id
	                });
				}
				else if (obj.event === 'del') {
					layer.confirm('真的删除该资源以及其子资源吗？', function (index) {
	                   $.ajax({
	                        url: ctx+'/menu/delete/'+data.id,
	                        type: "POST",
	                        async :false,
	                        success: function (res) {
	                            if (res.code == 0) {
	                            	 layer.msg("成功删除"+res.data+"条数据", {icon: 6});
	                                //刷新list,重载
	                            	 location.reload();
	                            } else {
	                                layer.msg(res.msg, {icon: 5});
	                            }
	                        },
	  				     	 error:function(){
					      	   layer.msg("删除失败", {icon: 5});
				         	 }
	                    });
	                });
				} else if (obj.event === 'edit') {
					layer.open({
						id:"menuEdit",
	                    type: 2,
	                    title: "编辑资源",
	                    area: ['700px', '350px'],
	                    content: ctx+'/menu/form/update/'+data.id
	                });
				}
			});
		   
	    	
			//监听禁用操作
			 form.on('checkbox(menuStatusDemo)', function(data){
				 var checked = data.elem.checked;
				 var id = $(data.elem).attr("id");
				 var status = 1;
				 if(checked){
				 	status = 0;
				 }else{
				 	status = 1;
				 }
				 $.ajax({
				     url:ctx+"/menu/update",
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