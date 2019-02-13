layui.define(['table', 'menuApi'], function(exports) {
	var menuApi = layui.menuApi;
	var table=layui.table;
	var view = {
			init:function(){
				
			},
			delBatch:function(){
				var data = Common.getMoreDataFromTable(table,"siteTable");
				if(data==null){
					return ;
				}
				Common.openConfirm("确认要删除这些网站?",function(){
					var ids =Common.concatBatchId(data);
					menuApi.del(ids,function(){
						Common.info("删除成功");
						dataReload();
					})
				})
				
			}
				
	}
	 exports('del',view);
	
});