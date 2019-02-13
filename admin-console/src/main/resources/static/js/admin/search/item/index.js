layui.define([ 'form', 'laydate', 'table' ], function(exports) {
	var form = layui.form;
	var laydate = layui.laydate;
	var table = layui.table;
	var siteTable = null;
	
	var view ={
		
		init:function(){
			this.initTable();
			this.initSearchForm();
			this.initToolBar();
			window.dataReload = function(){
				Lib.doSearchForm($("#searchForm"),siteTable)
			}
			
			
		},
		initTable:function(){
			siteTable = table.render({
				elem : '#siteTable',
				height : Lib.getTableHeight(2),
				method : 'post',
				url : Common.ctxPath + '/admin/search/item/list.json' //数据接口
				,page : Lib.tablePage //开启分页
				,limit : 10,
				cols : [ [ //表头
				{
					type : 'checkbox',
					fixed:'left',
				}, {
					field : 'id',
					title : 'id',
					width : 80,
					fixed:'left',
					sort : true
				}, {
					field : 'website',
					title : '网站',
					width : 80,
					sort : true
				}, {
					field : 'baseUri',
					title : '网站base路径',
					width : 140,
					sort : true
				}, {
					field : 'bookItem',
					title : '书籍单条结果',
					width : 140,
					sort : true
				}, {
					field : 'bookItemEx',
					title : '书籍单条结果过滤',
					width : 150,
					sort : true
				},
				 {
					field : 'bookPicture',
					title : '书籍封面图片地址',
					width : 140,
					sort : true
				},
				 {
					field : 'bookPictureEx',
					title : '书籍封面图片地址过滤',
					width : 150,
					sort : true
				},
				{
					field : 'bookName',
					title : '书籍名',
					width : 140,
					sort : true
				},
				{
					field : 'bookNameEx',
					title : '书籍名过滤',
					width : 150,
					sort : true
				},
				{
					field : 'bookAuthor',
					title : '作者',
					width : 150,
					sort : true
				},
				{
					field : 'bookAuthorEx',
					title : '作者过滤',
					width : 150,
					sort : true
				},
				{
					field : 'bookIntro',
					title : '简介',
					width : 150,
					sort : true
				},
				{
					field : 'bookIntroEx',
					title : '简介过滤',
					width : 150,
					sort : true
				},
				{
					field : 'updateTime',
					title : '修改时间',
					width : 120,
					templet:function(d){
						return Common.getDate(d.createTime);
					},
					sort : true
				}

				] ]

			});
		},
		
		initSearchForm:function(){
			Lib.initSearchForm( $("#searchForm"),siteTable,form);
		},
		initToolBar:function(){
			toolbar = {
					add : function() { //获取选中数据
						var url = "/admin/search/site/add.do";
						Common.openDlg(url,"网站配置>新增");
					},
					edit : function() { //获取选中数目
						var data = Common.getOneFromTable(table,"siteTable");
						if(data==null){
							return ;
						}
						var url = "/admin/search/site/edit.do?id="+data.id;
						Common.openDlg(url,"网站配置>编辑");
						
					},
					del : function() { 
						layui.use(['del'], function(){
							  var delView = layui.del
							  delView.delBatch();
						});
					},
					changePassword:function(){
						var data = Common.getOneFromTable(table,"siteTable");
						if(data==null){
							return ;
						}
						var url = "/admin/user/changePassword.do?id="+data.id;
						Common.openDlg(url,"用户管理>更改密码");

					}
				};
			$('.ext-toolbar').on('click', function() {
				var type = $(this).data('type');
				toolbar[type] ? toolbar[type].call(this) : '';
			});
		}
	}

	 exports('index',view);
	
});