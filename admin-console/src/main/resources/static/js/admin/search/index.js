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
				url : Common.ctxPath + '/admin/user/list.json' //数据接口
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
					field : 'code',
					title : '用户名',
					width : 150
				}, {
					field : 'name',
					title : '姓名',
					width : 120,
					sort : true
				}, {
					field : 'orgName',
					title : '机构',
					width : 120,
					sort : true
				}, {
					field : 'stateText',
					title : '状态',
					width : 120,
					sort : true
				},
				 {
					field : 'jobType0Text',
					title : '职位',
					width : 120,
					sort : true
				},
				 {
					field : 'jobType1Text',
					title : '职位明细',
					width : 80,
					sort : true
				},
				{
					field : 'createTime',
					title : '创建时间',
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
						var url = "/admin/user/add.do";
						Common.openDlg(url,"用户管理>新增");
					},
					edit : function() { //获取选中数目
						var data = Common.getOneFromTable(table,"siteTable");
						if(data==null){
							return ;
						}
						var url = "/admin/user/edit.do?id="+data.id;
						Common.openDlg(url,"用户管理>编辑");
						
					},
					del : function() { 
						layui.use(['del'], function(){
							  var delView = layui.del
							  delView.delBatch();
						});
					},
					userRole : function() { //获取选中数目
						var data = Common.getOneFromTable(table,"siteTable");
						if(data==null){
							return ;
						}
						var url = "/admin/user/role/list.do?id="+data.id;
						Common.openDlg(url,"用户管理>"+data.name+">角色管理");
						
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