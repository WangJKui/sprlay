layui.use(['form', 'jquery'], function(){
	var form = layui.form,
	$ = layui.jquery;
	// layer.msg('玩命卖萌中', function(){
	//   //关闭后的操作 ['table', 'layer','jquery']
	//   });
	//监听提交  action=" ${request.contextPath}/login/login"
	form.on('submit(login)', function(data){
//		layer.msg(JSON.stringify(data.field));
		$.ajax({
			url:ctx+"/login/login",
			type:'post',//method请求方式，get或者post
			dataType:'json',//预期服务器返回的数据类型
			data:data.field,//如果表格数据序列化，则是字符串
			//contentType: "application/json; charset=utf-8",
			success:function(res){//res为相应体,function为回调函数
				if(res.code == 0){
//					layer.msg("保存信息成功",{icon: 6});
					window.location.href=ctx+"/index";
				}else{
					layer.msg(res.msg,{icon:5});
				}
			},
			error:function(){
				layer.msg("登录失败，请重新登录！",{icon:5});
			}
		});
		return false;
	});
});

