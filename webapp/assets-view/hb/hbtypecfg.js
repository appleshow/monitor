var tableType = null, tableTypeEditor = null;
var tableTypeColumns = [];

function pageIni() {
	iniTableColumns();

	// Initialized Editor
	tableTypeEditor = new $.fn.dataTable.Editor({
		ajax : function(data, callback, settings) {
			alert(222)
		},
		table : "#table-type",
		i18n : {
			create : {
				button : '<i class="glyphicon glyphicon-plus green"></i> 新增',
				title : "新增一条记录",
				submit : '<i class="glyphicon glyphicon-ok blue"></i> 新增'
			},
			edit : {
				button : '<i class="glyphicon glyphicon-pencil blue"></i> 编辑',
				title : "编辑当前记录",
				submit : '<i class="glyphicon glyphicon-ok blue"></i> 保存'
			},
			remove : {
				button : '<i class="glyphicon glyphicon-remove red"></i> 删除',
				title : "<h4>删除当前记录</h4>",
				submit : '<i class="glyphicon glyphicon-ok blue"></i> 删除',
				confirm : {
					_ : "确定要删除所选择中的 %d 条记录？",
					1 : "确定要删除所选择中的 1  条记录？"
				}
			},
			error : {
				system : "Une erreur s’est produite, contacter l’administrateur système"
			},
			datetime : {
				previous : '',
				next : '',
				months : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
				weekdays : [ '六', '日', '一', '二', '三', '四', '五' ]
			}
		},
		fields : [ {
			label : "类别代码：",
			name : "typeId"
		}, {
			label : "类别名称：",
			name : "typeName"
		}, {
			label : "更新时间：",
			name : "utime"
		} ]
	});

	// Initialized DataTable
	tableType = $('#table-type').DataTable({
		dom : "<'row'<'col-sm-9'B><'col-sm-3'f>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-2'l><'col-sm-3'i><'col-sm-7'p>>",
		select : true,
		language : {
			loadingRecords : "加载中...",
			processing : "请求数据中...",
			search : '<i class="glyphicon glyphicon-search blue"></i>',
			lengthMenu : "显示 _MENU_ 条/页",
			zeroRecords : "无法找到对应的资料...",
			info : "当前第 _START_ 至 _END_ 条，共 _TOTAL_ 条记录",
			infoEmpty : "没有可用的记录...",
			infoFiltered : "(从 _MAX_ 条记录中检索)",
			paginate : {
				previous : "上一页",
				next : "下一页"
			}
		},
		processing : true,
		ajax : function(data, callback, settings) {
			var data = {
				"draw" : 1,
				"recordsTotal" : 2,
				"recordsFiltered" : 2,
				"data" : [ {
					typeId : 1,
					typeName : "aaa",
					utime : "999"
				}, {
					typeId : 2,
					typeName : "aaa",
					utime : "999"
				} ]
			};
			callback(data);
		},
		columns : tableTypeColumns,
		buttons : [ {
			extend : "create",
			editor : tableTypeEditor
		}, {
			extend : "edit",
			editor : tableTypeEditor
		}, {
			extend : "remove",
			editor : tableTypeEditor
		}, {
			extend : 'collection',
			text : 'Export',
			buttons : [ 'copy', 'excel', 'csv', 'pdf', 'print' ]
		} ]
	});

	$('body').on('click', '.dropdown-menu.hold-on-click', function(e) {
		e.stopPropagation();
	});

	$('#modal-success').on('hidden.bs.modal', function(e) {
		$('#table-type').dataTable().fnDraw();
	});
}

function iniTableColumns() {
	var url = "comm.referPPageShow";
	var inf = {
		pageId : 9
	};

	$.ajax({
		async : false,
		type : "POST",
		url : url,
		cache : false,
		data : "inf=" + JSON.stringify(inf),
		dataType : "json",
		success : function(res) {
			if (res.code != 0) {
				$smsg(res.message, "E", res.code);
			} else {
				var tableColumns = $("#table-type-columns")
				var innerHtml = "";
				var columnData = res.data;

				for (var index = 0; index < columnData.length; index++) {
					var addWith = "";
					var column = {};

					column["data"] = columnData[index].columnId;
					if (columnData[index].columnHide == 1) {
						column["visible"] = false;
					} else {
						column["visible"] = true;
					}

					tableTypeColumns.push(column);

					for (var len = 1; len <= columnData[index].columnWith; len++) {
						addWith += "&nbsp;"
					}
					innerHtml += "<th>" + columnData[index].columnName + addWith + "</th>";
				}
				tableColumns.html(innerHtml);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});
}

function refer() {
	$('#table-type').DataTable().fnDraw();
}
