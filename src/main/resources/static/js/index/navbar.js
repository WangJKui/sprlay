/**
 * 首页左侧菜单
 * @param exports
 * @returns
 */
layui.define(['element','jquery','layer'], function (exports) {
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element;
 
    var Navbar =  function (){
    		 // 默认配置
            this.config = {
            	elem: undefined, //容器
                url: undefined, //数据源地址
                type: 'GET' //读取方式
            };
    };   
 
    /**
     * 动态获取菜单数据
     * 渲染 element.render(type, filter);
     * 'nav' 导航，也就是左侧菜单
     */
    Navbar.prototype.render = function () {
    	var _that = this;
        var _config = _that.config;
        var $container = $('' + _config.elem + '');
    	$.ajax({
            type: _config.type,
            url: _config.url,
            async: false, //_config.async,
            dataType: 'json',
            success: function (result, status, xhr) {
            	var html = getHtml(result);
            	
            	 $container.html(html);
            	 // 渲染
                 //element.render('nav',_config.elem);没有点击效果
            	 element.init();
            },
            error: function (xhr, status, error) {
            	//错误信息
            	layer.msg('Navbar error:' + error, {
                    icon: 5
                });
            }
        });
    };
    
    
    
    /**
     * 配置Navbar
     */
    Navbar.prototype.set = function (options) {
        var that = this;
        $.extend(true, that.config, options);
        return that;
    };
    
    /**
     * 获取html字符串
     */
    function getHtml(data) {
        var ulHtml = '';
        for (var i = 0; i < data.length; i++) {
            ulHtml += '<li class="layui-nav-item">';
            if (data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0) {
                ulHtml += '<a href="javascript:;" id = "'+data[i].id+'">'+ data[i].title;
                ulHtml += '<span class="layui-nav-more"></span>';
                ulHtml += '</a>';
                ulHtml += '<dl class="layui-nav-child">';
                //二级菜单
                for (var j = 0; j < data[i].children.length; j++) {
                    //是否有孙子节点
                    if (data[i].children[j].children !== undefined && data[i].children[j].children !== null && data[i].children[j].children.length > 0) {
                        ulHtml += '<dd>';
                        ulHtml += '<a href="javascript:;" id = "'+data[i].children[j].id+'">&nbsp;&nbsp;&nbsp;&nbsp;' + data[i].children[j].title;
                        ulHtml += '<span class="layui-nav-more"></span>';
                        ulHtml += '</a>';
                        //三级菜单
	                    ulHtml += '<dl class="layui-nav-child">';
	                        var grandsonNodes = data[i].children[j].children;
	                        for (var k = 0; k < grandsonNodes.length; k++) {
	                            ulHtml += '<dd>';
	                            ulHtml += '<a href="javascript:;" data-url="'+ grandsonNodes[k].href +'" id = "'+grandsonNodes[k].id+'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + grandsonNodes[k].title + '</a>';
	                            ulHtml += '</dd>';
	                        }
	                    ulHtml += '</dl>';
	                    ulHtml += '</dd>';
                    }else{
                    	ulHtml += '<dd>';
                        ulHtml += '<a href="javascript:;" data-url="'+data[i].children[j].href+'" id = "'+data[i].children[j].id+'">' + data[i].children[j].title;
                        ulHtml += '</a>';
                        ulHtml += '</dd>';
                    }
                }
                ulHtml += '</dl>';
            } else {
                ulHtml += '<a href="javascript:;" data-url="'+data[i].href+'" id = "'+data[i].id+'">'+ data[i].title+'</a>';
            }
            ulHtml += '</li>';
        }
 
        return ulHtml;
    }
 
    //=============tab====================
    //新增一个Tab项 sprlay-tab:tab元素的 lay-filter="value" 过滤器的值（value）
    Navbar.prototype.tabAdd = function(title,id,url){
		 element.tabAdd('sprlay-tab', {
	            title: title ,
	            content: '<iframe data-id="'+id+'" frameborder="0" src="'+url+'"  class="sprlay-iframe"></iframe>',
	            id: id
	     });
		 element.tabChange('sprlay-tab', id);//切换
	}
	/*//删除指定Tab项
    Navbar.prototype.tabDelete = function(_this){
		//删除指定Tab项  tab元素的 lay-filter="value" 过滤器的值（value） 选项卡标题列表的 属性 lay-id 的值
        element.tabDelete('sprlay-tab', '44'); //删除：“商品管理”
	}*/
	//切换到指定Tab项
    Navbar.prototype.tabChange = function(id){
		 element.tabChange('sprlay-tab', id); //切换到：用户管理
	}
	//===================================================
	
    var navbar = new Navbar();
    
    exports('navbar', function (options) {
        return navbar.set(options);
    });
});
