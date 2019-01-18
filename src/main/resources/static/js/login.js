layui.use('form', function(){
	var form = layui.form;
	// layer.msg('玩命卖萌中', function(){
	//   //关闭后的操作
	//   });
	//监听提交
	form.on('submit(login)', function(data){
		layer.msg(JSON.stringify(data.field));
//		return false;
	});
});

