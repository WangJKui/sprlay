
//config的设置是全局的
layui.config({
  base: '/sprlay/module/util/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
	"spr" : "spr"
});

var spr = null;

//使用拓展模块
layui.use(['spr'], function(){
  spr = layui.spr;
  
});