/*访问后台的代码*/
layui.define([], function(exports) {
	var api={
			updateMenu:function(callback){
				Lib.submitForm("/admin/search/item/update.json",$('#updateForm'),{},callback)
			},
			addMenu:function(callback){
				Lib.submitForm("/admin/search/item/save.json",$('#addForm'),{},callback)
			},
			del:function(ids,callback){
				Common.post("/admin/search/item/batchDel.json",{"ids":ids},callback)
			}
			
		
	};
	
	 exports('menuApi',api);
	
});