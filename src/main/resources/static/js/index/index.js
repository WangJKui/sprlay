
//监听选中页签添加样式
layui.config({
	base: '/sprlay/js/index/'   //navbar组件js所在目录
}).extend({
	"navbar" : "navbar"
});

layui.use(['navbar','form','element','layer','jquery'], function() {
	var form = layui.form,
		element = layui.element,
		$ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : top.layer;
	var navbar = layui.navbar({
			elem: '#sprlay-nav-id', //ul id
			url: "/sprlay/js/index/nav.json"   //数据源地址，我用了本地写的json数据
		});
	
	//获取菜单以及渲染菜单
	navbar.render();

	//点击菜单
	element.on('nav(sprlay-nav)', function(elem){
  	   
		addOrChangeTab(elem);
    });
	
	var isShow = true;  //定义一个标志位
	//隐藏左侧导航 
	$(".sprlay-flexible").click(function(){

		$("#sprlay-app-flexible").toggleClass("layui-icon-shrink-right").toggleClass("layui-icon-spread-left");

		//选择出所有的span，并判断是不是hidden
		$('.layui-layout-left-tree .layui-nav-item span').each(function(){
			if($(this).is(':hidden')){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
		//判断isshow的状态
		if(isShow){
			$('.layui-side.layui-bg-black').width(48); //设置宽度
			//将footer和body的宽度修改
			$('.layui-body').css('left', 48+'px');
			$('.layui-footer').css('left', 48+'px');
			//将二级导航栏隐藏
			$('dd span').each(function(){
				$(this).hide();
			});
			//修改标志位
			isShow = false;
		}else{
			$('.layui-side.layui-bg-black').width(200);
			$('.layui-body').css('left', 200+'px');
			$('.layui-footer').css('left', 200+'px');
			$('dd span').each(function(){
				$(this).show();
			});
			
			isShow = true;
		}

	})
	
	//点击水平菜单 sprlay-nav-right
	element.on('nav(sprlay-nav-right)', function(elem){
		var id = $(elem).attr("id");
		//帮助
  	    if("sprlay-help" == id){
  	    	layer.open({
  	    		title: '帮助',
  	    		content: '联系方式'
  	    	});     
  	  	} else if("sprlay-user-password" == id){
  	  		//修改密码弹框 ${request.contextPath}/user/password"
  	  		//sprlay-user-password
  	  		layer.open({
	  			id:"userPassword",
	              type: 2,
	              title: "修改密码",
	              area: ['500px', '320px'],
	              content: ctx+'/user/topassword',
	          });		
  	  	}
  	    else {
  	    	addOrChangeTab(elem);
  	    }
    });  
	
	//添加或者定位到tab
	function addOrChangeTab(elem) {
		var title = $(elem).text();
		var url = $(elem).attr("data-url");
		var id = $(elem).attr("id");
//		console.log($(elem));
//		console.log($(elem).siblings());

		 if($(elem).siblings().length == 0){
		    	//所有tab
			 for (var i = 0; i <$('.layui-tab-title li').length; i++) {
				 if($('.layui-tab-title li').eq(i).attr('lay-id')==id){
					 navbar.tabChange(id);
					 return;
				 }
			 };
		  		
			 navbar.tabAdd(title,id,url);
		 }
	}
	
	
});
