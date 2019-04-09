/**
  项目JS主入口
  以依赖layui的layer和form模块为例
 **/    
layui.define(['layer'], function(exports){
	var $ = layui.jquery;
	var layer = layui.layer;

	var spr = {
			// 包装json
			buildJson : function (json) {
				
				var time = new Date().getTime();
				
				var param = {
						json : JSON.stringify(json),//转为字符串
						_ts	 : time
				};

				return param;
			},
			
			buildStr : function (json) {
				
				var time = new Date().getTime();
				
				var param = 'json='+encodeURIComponent(JSON.stringify(json), 'utf-8')+'&_ts='+time;

				return param;
			},

	}; 

	exports('spr', spr); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
}); 