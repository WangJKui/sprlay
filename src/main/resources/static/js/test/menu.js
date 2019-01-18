//监听选中页签添加样式
layui.config({
	base: '/sprlay/js/test/'   //navbar组件js所在目录
}).use('navbar', function() {
	var navbar = layui.navbar();
	navbar.set({
		elem: '#nav',
		url: "/sprlay/js/test/nav.json"   //数据源地址，我用了本地写的json数据
	});
	navbar.render();
	//下面的部分不是必须的
	navbar.on('click(demo)', function(data) {
		/*console.log(data.elem);
		console.log(data.field.title);//标题
		console.log(data.field.icon);//图标
		console.log(data.field.href);//调转地址
		layer.msg(data.field.href);*/
	});

	//给选中的页签添加选中样式（解决刷新失效问题）
	var url = window.location.href.replace("//", "");
	var relUrl = url.substring(url.lastIndexOf("/") + 1);
	//去掉参数部分
	if (relUrl.indexOf("?") != -1) {
		relUrl = relUrl.split("?")[0];
	}
	$("#leftNavbar a").each(function () {
		var that = this;
		if ($(that).attr("href") == relUrl) {
			$(that).parent().addClass("layui-this");
			$(that).parents("li:eq(0)").addClass("layui-nav-itemed");
			var nodes = $(that).parents("li:eq(0)").find("a .layui-nav-more");
			if (nodes.length > 0) {
				nodes.each(function () {
					if ($(this).parents("dd:eq(0)").find("[href='" + relUrl + 
					"']").length > 0) {
						$(this).parent().parent().addClass("layui-nav-itemed");
					}
				});
			}
		}
	});

});
