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
		var type = ""
		layui.use(['table', 'form', 'layer','util'], function() {
			
			var table = layui.table,
			$ = layui.jquery,
			form = layui.form,
			layer = layui.layer,
			util = layui.util;;
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
					hide:true
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
					//fixed : 'right',
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
				/* case 'batchDelete':
					layer.alert(JSON.stringify(data));
					break; */
				case 'add':
						layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							id:"userAdd", 
							type: 1,
		                    title: "新增用户",
		                    area: ['auto', '310px'],
		                    content: $("#userAddOrUpdate")//引用的弹出层的页面层的方式加载修改界面表单
		                });
		                //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
		                type = "add";
		                //重置表单
		              //  $("#userFrom").reset();不起作用
		                document.getElementById("userFrom").reset();
		                //显示按钮
		                $("#user-from-submit-reset .layui-btn-submit").show();
		                //加入时间
		                var createtime = util.toDateString(new Date());
		 			    form.val("user-form-filter", {
		 				    "createtime":createtime
		 				});
						$("#userFrom input").attr("disabled",false);
					break;
				}
			});
			//监听行工具事件
			table.on('tool(sprlay-user-list-filter)', function(obj) {
				var data = obj.data;
				//console.log(obj)
				if (obj.event === 'detail') {
					layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						id:"userDetail",
	                    type: 1,
	                    title: "用户信息",
	                    area: ['auto', '310px'],
	                    content: $("#userAddOrUpdate")//引用的弹出层的页面层的方式加载修改界面表单
	                });
					setFormValue(data);
					 //隐藏按钮
					$("#user-from-submit-reset .layui-btn-submit").hide();
					 //不允许编辑　　$("#id").attr("disabled","disabled");
					$("#userFrom input").attr("disabled",true);
				}
				else if (obj.event === 'del') {
					layer.confirm('真的删除行么', function (index) {
	                    $.ajax({
	                        url: ctx+'/user/delete/'+data.id,
	                        type: "POST",
	                        async :false,
	                        success: function (res) {
	                            if (res.code == 0) {
	                                //删除这一行
	                                //obj.del();
	                                //关闭弹框
	                                //layer.close(index);
	                                //刷新list,重载
	                                table.reload("userTableID");
	                                layer.msg("删除成功", {icon: 6});
	                            } else {
	                                layer.msg("删除失败", {icon: 5});
	                            }
	                        }
	                    });
	                });
				} else if (obj.event === 'edit') {
						layer.open({//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							id:"userEdit",
		                    type: 1,
		                    title: "编辑用户",
		                    area: ['auto', '310px'],
		                    content: $("#userAddOrUpdate"),//引用的弹出层的页面层的方式加载修改界面表单
		                   // btn: ['确定','取消'],//按钮
		                });
		                //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
		                type = "update";
		                setFormValue(data);
		                //显示按钮
		                $("#user-from-submit-reset .layui-btn-submit").show();
						$("#userFrom input").attr("disabled",false);
				}
			});
			
			//监听弹出框表单提交，data:数据
			form.on("submit(userform)", function(data) {
			    $.ajax({
					url:ctx+"/user/"+type,
					type:"post",
					async :false,
					dataType:"json",//预期服务器返回的数据类型
				 	data:JSON.stringify(data.field),//表格数据序列化
				 	contentType: "application/json; charset=utf-8",
				 	success:function(res){//res为相应体,function为回调函数
				 		if(res.code == 0){
				  	  		layer.msg("操作成功", {icon: 6});
				    		//layer.load(2,{time: 10*1000});
				    		//layer.closeAll('loading');//关闭加载层
                  	   	     //layer.closeAll();//关闭所有的弹出层
				    	 }else{
				    		 layer.msg("操作失败", {icon: 5});
				    	 }
				 	},
				 	error:function(){
			        	layer.msg("操作失败", {icon: 5});
			            //layer.alert('操作失败！！！',{icon:5});
			        }
				 });
			});
			
		   //初始化表单值
		   function setFormValue(data){
			   form.val("user-form-filter", {
				   "id":data == null?null:data.id ,
				   "username":data == null?null:data.username ,
				   "nickname":data.nickname,	 
				   "email":data.email,	
				   "phone": data.phone,	
				   "sex":  data.sex,
				   "createtime":data.createtime
				});
			};
			
		   
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
			//表单保存
			/* $('#user-from-submit-reset .user-close').on('click', function(){
				var index = layer.open();
				layer.close(index); //此时你只需要把获得的index，轻轻地赋予layer.close即可
			}); */
			//表单验证
			 form.verify({
				  username: function(value, item){ //value：表单的值、item：表单的DOM对象
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
				  
				  nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
					if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
				      return '姓名不能有特殊字符';
				    }
				    if(/(^\_)|(\__)|(\_+$)/.test(value)){
				      return '姓名首尾不能出现下划线\'_\'';
				    }
				    if(/^[0-9]*$/.test(value)){
				      return '姓名不能全为数字';
				    }
				    if(value.length<2 || value.length>26){
						return '姓名位数范围{2,26}';
					}  
				  }
				  
				}); 
			  
		});
	</script>


<!-- 这里是弹出层的表单信息 -->
<!-- 表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="userAddOrUpdate" style="display:none;">
    <form class="layui-form" style="width: 680px;margin-top:10px" lay-filter="user-form-filter" id="userFrom">
		<!-- <div class="layui-form-item" id="user-from-submit-reset">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-submit" lay-submit lay-filter="userform">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
		</div> -->
		<div class="userFormBtn" style="margin-left:20px;margin-bottom:20px;" id="user-from-submit-reset">
			<button class="layui-btn layui-btn-sm layui-btn-submit" data-type="save" lay-submit lay-filter="userform">保存</button>
			<button class="layui-btn layui-btn-sm layui-btn-primary user-close" data-type="close">关闭</button>
		</div>
		<div class="layui-form-item" style="display:none;">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" name="id" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">账号</label>
				<div class="layui-input-inline">
					<input type="text" name="username" required lay-verify="username"
						placeholder="请输入账号" autocomplete="off" class="layui-input">
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-inline">
					<input type="text" name="nickname" required lay-verify="nickname"
						placeholder="请输入姓名" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-inline">
					<input type="phone" name="phone" lay-verify="required|phone"
						placeholder="请输入电话" autocomplete="off" class="layui-input">
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="email" name="email" lay-verify="required|email"
						placeholder="请输入邮箱" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-inline">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-inline">
					<input type="radio" name="sex" value="男" title="男" checked> 
					<input type="radio" name="sex" value="女" title="女" >
				</div>
			</div>
			 <div class="layui-inline">
				<label class="layui-form-label">加入时间</label>
				<div class="layui-input-inline">
					<input type="datetime" name="createtime" readonly="readonly" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		</form>
    </div>
</body>

</html>