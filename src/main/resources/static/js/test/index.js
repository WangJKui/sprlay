
//监听选中页签添加样式
layui.config({
	base: '/sprlay/js/'   //navbar组件js所在目录
}).extend({
	"navbar" : "navbar"
})

layui.use(['navbar','form','element','layer','jquery'], function() {
	var form = layui.form,
		element = layui.element,
		$ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : top.layer;
	var navbar = layui.navbar({
			elem: '#sprlay-nav-id', //ul id
			url: "/sprlay/js/nav.json"   //数据源地址，我用了本地写的json数据
		});
	
	//获取菜单以及渲染菜单
	navbar.render();

	//点击菜单
	element.on('nav(sprlay-nav)', function(elem){
  	   
  	    var title = $(elem).text();
  	    var url = $(elem).attr("data-url");
  	    var id = $(elem).attr("id");
  	  
  	  	//如果不存在子级
  	    if($(this).siblings().length == 0){
  	    	//所有tab
  	    	for (var i = 0; i <$('.layui-tab-title li').length; i++) {
  	    		if($('.layui-tab-title li').eq(i).attr('lay-id')==id){
  	    			navbar.tabChange(id);
  	    			return;
  	    		}
  	    	};
  	  		
  	  		navbar.tabAdd(title,id,url);
  	  	}
    });
	
});
