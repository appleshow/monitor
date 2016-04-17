var tableType = {
	editor : function() {
	},
	table : function() {
	},
	data : {},
	info : {}
};

jQuery(document).ready(function() {
	iniPage();
});

function iniPage() {
	setTableHtmlColumn();
	iniEditorType();
	iniTableType();
}

function iniEditorType() {
	// Initialized Editor
	tableType.editor = new $.fn.dataTable.Editor({
		table : "#table-type",
		fields : getEditorFields(tableType.info),
		i18n : {
			create : {
				button : '<i class="glyphicon glyphicon-plus green"></i> 新增',
				title : "<h4>新增一条记录</h4>",
				submit : '<i class="glyphicon glyphicon-floppy-disk blue"></i> 新增'
			},
			edit : {
				button : '<i class="glyphicon glyphicon-pencil blue"></i> 编辑',
				title : "<h4>编辑当前记录</h4>",
				submit : '<i class="glyphicon glyphicon-floppy-disk blue"></i> 保存'
			},
			remove : {
				button : '<i class="glyphicon glyphicon-remove red"></i> 删除',
				title : "<h4>删除当前记录</h4>",
				submit : '<i class="glyphicon glyphicon-floppy-disk blue"></i> 删除',
				confirm : {
					_ : "确定要删除所选择中的 %d 条记录？",
					1 : "确定要删除所选择中的 1  条记录？"
				}
			},
			error : {
				system : "操作未完成，向服务器请求失败..."
			},
			datetime : {
				previous : '',
				next : '',
				months : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
				weekdays : [ '六', '日', '一', '二', '三', '四', '五' ]
			}
		},

		ajax : function(method, url, rows, callSuccess, callError) {
			var type = "";
			var inf = {
				parCount : 0,
				inPar : []
			};

			if (rows.action === "create") {
				type = "I";
			} else if (rows.action === "edit") {
				type = "U";
			} else if (rows.action === "remove") {
				type = "D";
			} else {
				tableType.editor.i18n.error.system = "操作失败，未知的处理类型！";
				callError();
				return;
			}

			for ( var primaryValue in rows.data) {
				rows.data[primaryValue]["_type"] = type;
				inf.inPar.push(rows.data[primaryValue]);
				inf.parCount++;
			}

			$.ajax({
				async : false,
				type : "POST",
				url : "hbTypeConfig.modifyHbType",
				cache : false,
				data : "inf=" + JSON.stringify(inf),
				dataType : "json",
				success : function(res) {
					if (res.code != 0) {
						tableType.editor.i18n.error.system = res.message;
						callError();
					} else {
						callSuccess(tableType.data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					callError(XMLHttpRequest, textStatus, errorThrown);
				}
			});
		}
	});

	tableType.editor.on('initCreate', function() {
		// Enable order for create
		$.each(tableType.editor.fields(), function(index, value) {
			var field = tableType.editor.field(value);

			if (tableType.info[value].edit == 1) {
				field.enable();
			} else {
				field.disable();
			}
		});
	}).on('initEdit', function() {
		// Disable for edit
		$.each(tableType.editor.fields(), function(index, value) {
			var field = tableType.editor.field(value);

			if (tableType.info[value].edit == 1 && tableType.info[value].primary == 0) {
				field.enable();
			} else {
				field.disable();
			}
		});
	}).on('preSubmit', function() {
		for ( var columId in tableType.info) {
			if (tableType.info[columId].hide === 0 && this.val(columId) === "") {
				this.error("操作失败，请录入项目：<strong>" + tableType.info[columId].name + "</strong>");
				return false;
			}
		}

		return true;
	});
}

function iniTableType() {
	// Initialized DataTable
	tableType.table = $('#table-type').DataTable({
		dom : "<'row'<'col-sm-10'B><'col-sm-2'l>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-3'i><'col-sm-9'p>>",
		language : {
			loadingRecords : "加载中...",
			processing : "请求数据中...",
			search : '<i class="glyphicon glyphicon-search blue"></i>',
			lengthMenu : "显示 _MENU_ /页",
			zeroRecords : "无法找到对应的资料...",
			info : "当前第 _START_ 至 _END_ 条，共 _TOTAL_ 条记录",
			infoEmpty : "没有可用的记录...",
			infoFiltered : "( _MAX_ 条记录)",
			paginate : {
				previous : "上一页",
				next : "下一页"
			}
		},
		lengthMenu : [ [ 25, 100, 300, -1 ], [ "25条", "100条", "300条", "全部" ] ],
		pageLength : 25,
		select : true,
		processing : true,
		serverSide : true,
		scrollY : '80vh',
		scrollCollapse : true,
		columns : getTableColumns(tableType.info),
		buttons : [ {
			text : '<i class="glyphicon glyphicon-search blueberry"></i> 查询',
			action : function(e, dt, node, config) {
				tableType.table.ajax.reload(null, false);
			}
		}, {
			extend : "create",
			editor : tableType.editor
		}, {
			extend : "edit",
			editor : tableType.editor
		}, {
			extend : "remove",
			editor : tableType.editor
		}, {
			extend : 'collection',
			text : '<i class="glyphicon glyphicon-download-alt"></i> 导出',
			buttons : [ {
				extend : "copy",
				text : '<a class="btn btn-default purple" href="javascript:void(0);" style="width:100%;text-align:left;"><i class="fa fa-clipboard"></i> 复制</a>'
			}, {
				extend : "print",
				text : '<a class="btn btn-default purple" href="javascript:void(0);" style="width:100%;text-align:left;"><i class="fa fa-print"></i> 打印</a>'
			}, {
				extend : "excel",
				text : '<a class="btn btn-default purple" href="javascript:void(0);" style="width:100%;text-align:left;"><i class="fa fa-table"></i> 另存为Excel</a>'
			}, {
				extend : "csv",
				text : '<a class="btn btn-default purple" href="javascript:void(0);" style="width:100%;text-align:left;"><i class="fa fa-table"></i> 另存为CSV</a>'
			}, {
				extend : "pdf",
				text : '<a class="btn btn-default purple" href="javascript:void(0);" style="width:100%;text-align:left;"><i class="fa fa-file-text"></i> 另存为PDF</a>'
			} ]
		} ],
		ajax : function(data, callback, settings) {
			var displayStart = data.start, displayLength = data.length;
			var inf = {
				parCount : 2,
				inPar : {
					pageNumber : displayLength == -1 ? 0 : parseInt(displayStart / displayLength) + 1,
					pageSize : displayLength == -1 ? 0 : displayLength
				}
			};

			$.ajax({
				async : false,
				type : "POST",
				url : "hbTypeConfig.referHbType",
				cache : false,
				data : "inf=" + JSON.stringify(inf),
				dataType : "json",
				success : function(res) {

					var tableData = {
						draw : settings.iDraw,
						recordsTotal : 0,
						recordsFiltered : 0,
						data : []
					};

					if (res.code != 0) {
						$("#mwTitle").html("警告");
						$("#mwMessage").html(res.message);
						$("#modal-warning").modal("show");
					} else {
						tableData.recordsTotal = res.totalCount;
						tableData.recordsFiltered = res.totalCount;

						$.each(res.data, function(index, valueRow) {
							var DT_RowId = "";

							for ( var columnId in tableType.info) {
								if (tableType.info[columnId].primary === 1) {
									DT_RowId += "_" + valueRow[columnId];
								}
							}

							valueRow.DT_RowId = DT_RowId;
							tableData.data.push(valueRow);
						});
					}

					tableType.data = tableData;
					callback(tableData);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					var tableData = {
						draw : settings.iDraw,
						recordsTotal : 0,
						recordsFiltered : 0,
						data : []
					};

					callback(tableData);

					$("#mwTitle").html("警告");
					$("#mwMessage").html(res.message);
					$("#modal-warning").modal("show");
				}
			});
		},
	});
}

function setTableHtmlColumn() {
	var url = "comm.referPageShow";
	var inf = {
		parCount : 1,
		inPar : {
			pageId : 9
		}
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
				$("#mwTitle").html("警告");
				$("#mwMessage").html(res.message);
				$("#modal-warning").modal("show");
			} else {
				var innerHtml = "";

				$.each(res.data, function(index, value) {
					var addWidth = "";
					var columnInfo = {};

					columnInfo.name = value.columnName;
					columnInfo.primary = value.columnPrimary === undefined ? 0 : value.columnPrimary;
					columnInfo.update = value.columnUpdate === undefined ? 0 : value.columnUpdate;
					columnInfo.edit = value.columnEdit === undefined ? 0 : value.columnEdit;
					columnInfo.type = value.columnType === undefined ? "text" : value.columnType;
					columnInfo.lock = value.columnLock === undefined ? 0 : value.columnLock;
					columnInfo.sort = value.columnSort === undefined ? 0 : value.columnSort;
					columnInfo.hide = value.columnHide === undefined ? 0 : value.columnHide;
					columnInfo.width = value.columnWidth === undefined ? 0 : value.columnWidth;

					tableType.info[value.columnId] = columnInfo;

					for (var len = 1; len <= value.columnWidth; len++) {
						addWidth += "&nbsp;"
					}
					innerHtml += "<th>" + value.columnName + addWidth + "</th>";
				});

				$("#table-type-columns").html(innerHtml);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#mwTitle").html("警告");
			$("#mwMessage").html("操作未完成，向服务器请求失败...");
			$("#modal-warning").modal("show");
		}
	});
}

function getTableColumns(columnInfo) {
	var tableColumns = [];

	for ( var columnId in columnInfo) {
		var column = {};

		column.data = columnId;
		if (columnInfo[columnId].hide == 1) {
			column.visible = false;
		} else {
			column.visible = true;
		}
		column.sortable = false;
		column.defaultContent = "";

		tableColumns.push(column);
	}

	return tableColumns;
}

function getEditorFields(columnInfo) {
	var editorFiels = [];

	for ( var columnId in columnInfo) {
		var field = {};

		if (columnInfo[columnId].hide === 0) {
			if (columnInfo[columnId].update === 1) {
				field.label = '<font color="red">*</font> ' + columnInfo[columnId].name + ":";
			} else {
				field.label = columnInfo[columnId].name + ":";
			}
			field.name = columnId;
			field.type = columnInfo[columnId].type;

			editorFiels.push(field);
		}
	}

	return editorFiels;
}
