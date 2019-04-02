<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>分配角色</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="sprlay-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   
    <link rel="stylesheet" href="${request.contextPath}/css/public.css">
    <script type="text/javascript" src="${request.contextPath}/layui/layui.js"></script>
  </head>

<body style="margin-top:-10px;">
	<table class="layui-hide" id="sprlay-role-list" lay-filter="sprlay-role-list-filter"></table>
	<!-- 工具栏 -->
	<script type="text/html" id="toolbarAR">
 		 <div class="layui-btn-container">
 			 <button class="layui-btn layui-btn-sm" lay-event="save">保存</a>
 			 <button class="layui-btn layui-btn-sm" lay-event="close">关闭</a>
  		 </div>
	</script>
	<script>
	
		var ctx = "${request.contextPath}";
		var userid = "${userid}";
		
		layui.use(['table','layer','jquery','form'], function() {
			
			var table = layui.table,
				$ = layui.jquery,
				layer = layui.layer,
				form = layui.form;
			table.render({
				id: 'roleTableID',
				defaultToolbar:[],
				toolbar : '#toolbarAR',
				elem : '#sprlay-role-list',
				url : ctx+'/role/loadall',
				method : "post",
				where: {status: 1,userid:userid},//正常数据
				parseData: function(res){ //res 即为原始返回的数据
				    return {
				      "code": res.code, //解析接口状态
				      "msg": res.msg, //解析提示文本
				    //  "count": res.data.total, //解析数据长度
				      "data": res.data //解析数据列表
				    };
				},
				title : '角色数据表',
				height: 330,//滚动条
				cols : [ [ 
					{type : 'checkbox',fixed : 'left'},
					{field : 'id',title : 'ID',width : 120,fixed : 'left'},
					{field : 'name',title : '名称',fixed : 'left',unresize : true,sort : true},
					{field : 'code',title : '编码',sort : true},
					{field : 'remark',title : '描述',sort : true}, 
				 ] ],
				page : false,
				size: 'sm'
			});
			
			//头工具栏事件
			table.on('toolbar(sprlay-role-list-filter)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var data = checkStatus.data;
				var index = parent.layer.getFrameIndex(window.name);  
				switch (obj.event) {
				case 'save':
					
					var param = {
			    		userid : userid,
			   	   	 	json : JSON.stringify(data)
			   	 	};
					$.ajax({
                        url: ctx+'/user/role/',
                        type: "POST",
                        async:false,
                        //contentType: "application/json; charset=utf-8",
                        data:param,//表格数据序列化
                        success: function (res) {
                            if (res.code == 0) {
                            	 layer.msg("保存成功", {icon: 6});
                            } else {
                            	 layer.msg(res.msg, {icon: 5});
                            }
                        },
                        error:function(){
	  				      	layer.msg("操作失败", {icon: 5});
	  			        }
                    });
					break;
				case 'close':
					parent.layer.close(index);//关闭弹出层
					break;
				}
			});
		});
	</script>
</body>

</html>