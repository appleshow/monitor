/**
 * @param parCount
 * @param inPars
 * @returns {String}
 */
function ServerRequestPar(parCount, inPars) {
	var par = {
		parCount : parCount,
		inPar : inPars
	};

	return JSON.stringify(par).replace(/%/g, "%25").replace(/\&/g, "%26").replace(/\+/g, "%2B");
}

/**
 * @param data
 * @param keyName
 * @param valueName
 * @returns {Array}
 */
function TransToOptions(data, keyName, valueName) {
	var options = [];

	$.each(data, function(index, value) {
		var option = {};

		option.label = value[valueName];
		option.value = value[keyName];

		options.push(option);
	});

	return options;
}
/**
 * @param type
 * @param name
 * @param value
 * @param control
 * @returns {___anonymous296_371}
 */
function ControlPar(type, name, value, control) {
	return {
		type : type,
		name : name,
		value : value,
		control : control
	};
}
function DataTablesButtons(table, button) {
	var buttons = [];

	if (!(typeof (button) === "undefined" || button === null)) {
		if (button.indexOf("R") >= 0) {
			buttons.push({
				text : '<i class="glyphicon glyphicon-search blueberry"></i> 查询',
				action : function(e, dt, node, config) {
					table.table.ajax.reload(null, false);
				}
			});
		}
		if (button.indexOf("N") >= 0) {
			buttons.push({
				extend : "create",
				editor : table.editor
			});
		}
		if (button.indexOf("E") >= 0) {
			buttons.push({
				extend : "edit",
				editor : table.editor
			});
		}
		if (button.indexOf("D") >= 0) {
			buttons.push({
				extend : "remove",
				editor : table.editor
			});
		}
		if (button.indexOf("P") >= 0) {
			buttons.push({
				extend : 'collection',
				text : '<i class="glyphicon glyphicon-download-alt"></i> 导出',
				buttons : [
						{
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
						}
				]
			});
		}
	}

	return buttons;
}
/**
 * @param pageId
 * @param columnHeadName
 * @param callError
 * @returns {___anonymous587_588}
 */
function DataTablesColumnInfo(pageId, columnHeadName, callError) {
	var tableColumnInfo = {};

	$.ajax({
		async : false,
		type : "POST",
		url : "comm.referPageShow",
		cache : false,
		data : ServerRequestPar(1, {
			pageId : pageId
		}),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if (res.code != 0) {
				if (typeof (callError) != "undefined" && callError != null) {
					callError(res.code, res.message);
				}
			} else {
				var innerHtml = "";

				$.each(res.data, function(index, value) {
					var columnInfo = {};

					columnInfo.name = value.columnName;
					columnInfo.primary = typeof (value.columnPrimary) === "undefined" ? 0 : value.columnPrimary;
					columnInfo.update = typeof (value.columnUpdate) === "undefined" ? 0 : value.columnUpdate;
					columnInfo.edit = typeof (value.columnEdit) === "undefined" ? 0 : value.columnEdit;
					columnInfo.type = typeof (value.columnType) === "undefined" ? "text" : value.columnType;
					columnInfo.lock = typeof (value.columnLock) === "undefined" ? 0 : value.columnLock;
					columnInfo.sort = typeof (value.columnSort) === "undefined" ? 0 : value.columnSort;
					columnInfo.hide = typeof (value.columnHide) === "undefined" ? 0 : value.columnHide;
					columnInfo.align = typeof (value.columnAlign) === "undefined" ? 0 : value.columnAlign;
					columnInfo.prtype = typeof (value.prtype) === "undefined" ? "T" : value.prtype;

					tableColumnInfo[value.columnId] = columnInfo;

					innerHtml += "<th>" + value.columnName + "</th>";
				});

				$(columnHeadName).html(innerHtml);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (typeof (callError) != "undefined" && callError != null) {
				callError(-900, "操作未完成，向服务器请求失败...");
			}

		}
	});

	return tableColumnInfo;
};
/**
 * @param columnInfo
 * @returns {___anonymous2304_2305}
 */
function DataTablesColumns(columnInfo) {
	var tableColumns = {};

	for ( var columnId in columnInfo) {
		var column = {};

		column.data = columnId;
		if (columnInfo[columnId].hide == 1) {
			column.visible = false;
		} else {
			column.visible = true;
		}
		/*
		 * if ( columnInfo[columnId].sort == 0 ) { column.sortable = false; }
		 * else { column.sortable = true; }
		 */
		column.sortable = false;
		column.defaultContent = "";
		if (columnInfo[columnId].type === "checkbox") {
			column.render = function(data, type, row) {
				if (type === 'display') {
					if (data === 1) {
						return '√';
					} else {
						return '✖';
					}
				}
				return data;
			};

		}
		if (columnInfo[columnId].align == 2) {
			column.className = "text-right";
		} else if (columnInfo[columnId].align == 1) {
			column.className = "text-center";
		} else {
			column.className = "text-left"
		}
		tableColumns[columnId] = column;
	}

	return tableColumns;
}
/**
 * @param columnInfo
 * @returns {___anonymous3244_3245}
 */
function DataTablesFields(columnInfo) {
	var editorFiels = {};

	for ( var columnId in columnInfo) {
		var field = {};

		if (columnInfo[columnId].update === 1) {
			field.label = '<font color="red">*</font> ' + columnInfo[columnId].name + ":";
		} else {
			field.label = columnInfo[columnId].name + ":";
		}
		field.name = columnId;
		field.type = columnInfo[columnId].type;
		if (field.type === "checkbox") {
			field.def = "0";
			field.separator = " ";
			field.options = [
				{
					label : '',
					value : 1
				}
			]
		}
		editorFiels[columnId] = field;

	}

	return editorFiels;
}
/**
 * @param tableName
 * @param columnHeadName
 * @param column
 * @param callError
 * @returns {___anonymous3244_3245}
 */
function CommDataTables(tableName, columnHeadName, column, callError) {
	this.table = function() {
	};
	this.editor = function() {
	};
	this.serverInfo = {
		referUrl : "",
		referControls : [],
		modifyUrl : ""
	};
	this.lengthInfo = {
		lengthMenu : [
				[
						50, 100, 300, -1
				], [
						"50条", "100条", "300条", "全部"
				]
		],
		pageLength : 50
	};
	this.scrollY = 80;
	this.scrollX = false;
	this.buttons = "RNEDP";
	if (!isNaN(column)) {
		this.columnsInfo = DataTablesColumnInfo(column, columnHeadName, callError);
	} else {
		this.columnsInfo = column;
	}
	this.columns = DataTablesColumns(this.columnsInfo);
	this.fields = DataTablesFields(this.columnsInfo);
	this.create = function(editorAjax, dataTableAjax) {
		var table = this, tableServerInfo = this.serverInfo;
		var tableColumns = [], tableFields = [], order = [];
		var indexColumn = 0;

		for ( var columnId in column) {
			if (column[columnId].sort == 1) {
				order.push([
						indexColumn, 'asc'
				])
			} else if (column[columnId].sort == 2) {
				order.push([
						indexColumn, 'desc'
				])
			}
			indexColumn++;
		}
		for ( var columnId in this.columns) {
			tableColumns.push(this.columns[columnId]);
		}
		for ( var columnId in this.fields) {
			tableFields.push(this.fields[columnId]);
		}

		table.editor = new $.fn.dataTable.Editor({
			table : tableName,
			fields : tableFields,
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
					months : [
							'一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
					],
					weekdays : [
							'六', '日', '一', '二', '三', '四', '五'
					]
				}
			},
			ajax : function(method, url, rows, callSuccess, callError) {
				if (!(typeof (editorAjax) === "undefined" || editorAjax === null)) {
					editorAjax(method, url, rows, callSuccess, callError);
				} else {
					var type = "", parCount = 0, inPars = [];

					if (rows.action === "create") {
						type = "I";
					} else if (rows.action === "edit") {
						type = "U";
					} else if (rows.action === "remove") {
						type = "D";
					} else {
						table.editor.i18n.error.system = "操作失败，未知的处理类型！";
						callError();
						return;
					}

					for ( var primaryValue in rows.data) {
						for ( var item in table.columnsInfo) {
							if (table.columnsInfo[item].type === "checkbox" && !(rows.data[primaryValue][item] === 1)) {
								rows.data[primaryValue][item] = 0;
							}
						}
						rows.data[primaryValue]["_type"] = type;
						inPars.push(rows.data[primaryValue]);
						parCount++;
					}

					$.ajax({
						async : false,
						type : "POST",
						url : tableServerInfo.modifyUrl,
						cache : false,
						data : ServerRequestPar(parCount, inPars),
						dataType : "json",
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8'
                        },
						success : function(res) {
							if (res.code != 0) {
								table.editor.i18n.error.system = res.message;
								callError();
							} else {
								callSuccess({
									data : inPars
								});
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							table.editor.i18n.error.system = "操作未完成，向服务器请求失败...";
							callError();
						}
					});
				}
			}
		});

		table.editor.on('initCreate', function() {
			// Enable order for create
			$.each(table.editor.fields(), function(index, value) {
				var field = table.editor.field(value);
				if (table.columnsInfo[value].hide == 1) {
					field.hide();
				} else {
					if (table.columnsInfo[value].edit == 1) {
						field.enable();
					} else {
						field.disable();
					}
				}
			});
		}).on('initEdit', function() {
			// Disable for edit
			$.each(table.editor.fields(), function(index, value) {
				var field = table.editor.field(value);
				if (table.columnsInfo[value].hide == 1) {
					field.hide();
				} else {
					if (table.columnsInfo[value].edit == 1 && table.columnsInfo[value].primary == 0) {
						field.enable();
					} else {
						field.disable();
					}
				}
			});
		}).on('preSubmit', function(e, data, action) {
			if (action === "remove") {
				return true;
			}
			for ( var columnId in table.columnsInfo) {
				if (!(table.columnsInfo[columnId].hide === 1)) {
					if (table.columnsInfo[columnId].update === 1 && this.val(columnId) === "") {
						table.editor.field(columnId).focus();
						table.editor.field(columnId).message("<font color='red'>此项不能为空</font>");
						this.error("操作失败，请录入项目：<strong>" + table.columnsInfo[columnId].name + "</strong>!");
						return false;
					} else {
						table.editor.field(columnId).message("");
					}
				}
				if (table.columnsInfo[columnId].prtype === "N" && table.columnsInfo[columnId].type === "text" && !(this.val(columnId) === "")) {
					if (isNaN(this.val(columnId).replace(" ", "a"))) {
						table.editor.field(columnId).focus();
						table.editor.field(columnId).message("<font color='red'>此项必须为数字</font>");
						this.error("操作失败，录入项目：<strong>" + table.columnsInfo[columnId].name + "</strong> 有误!");
						return false;
					} else {
						table.editor.field(columnId).message("");
					}
				}
			}

			return true;
		});

		table.table = $(tableName).DataTable({
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
			lengthMenu : table.lengthInfo.lengthMenu,
			pageLength : table.lengthInfo.pageLength,
			scrollY : table.scrollY + "vh",
			scrollX : table.scrollX,
			scrollCollapse : true,
			select : true,
			processing : true,
			serverSide : true,
			columns : tableColumns,
			order : order,
			buttons : DataTablesButtons(table, table.buttons),
			ajax : function(data, callback, settings) {
				if (!(typeof (dataTableAjax) === "undefined" || dataTableAjax === null)) {
					dataTableAjax(data, callback, settings);
				} else {
					var parCount = 2;
					var displayStart = data.start, displayLength = data.length;
					var inPar = {
						pageNumber : displayLength == -1 ? 0 : parseInt(displayStart / displayLength) + 1,
						pageSize : displayLength == -1 ? 0 : displayLength
					};
					var tableData = {
						draw : settings.iDraw,
						recordsTotal : 0,
						recordsFiltered : 0,
						data : []
					};

					for (var icnt = 0; icnt < tableServerInfo.referControls.length; icnt++) {
						if (tableServerInfo.referControls[icnt].type == "text") {
							if (tableServerInfo.referControls[icnt].control.val() != "") {
								parCount++;
								inPar[tableServerInfo.referControls[icnt].name] = tableServerInfo.referControls[icnt].control.val();
							}
						} else if (tableServerInfo.referControls[icnt].type == "real") {
							if (tableServerInfo.referControls[icnt].value != "") {
								parCount++;
								inPar[tableServerInfo.referControls[icnt].name] = tableServerInfo.referControls[icnt].value;
							}
						} else {
						}
					}

					$.ajax({
						async : false,
						type : "POST",
						url : tableServerInfo.referUrl,
						cache : false,
						data : ServerRequestPar(parCount, inPar),
						dataType : "json",
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8'
                        },
						success : function(res) {
							if (res.code != 0) {
								if (typeof (callError) != "undefined" && callError != null) {
									callError(res.code, res.message);
								}
							} else {
								tableData.recordsTotal = res.totalCount;
								tableData.recordsFiltered = res.totalCount;

								$.each(res.data, function(index, valueRow) {
									var DT_RowId = "";

									for ( var columnId in table.columnsInfo) {
										if (table.columnsInfo[columnId].primary === 1) {
											DT_RowId += "_" + valueRow[columnId];
										}
									}

									valueRow.DT_RowId = DT_RowId;
									tableData.data.push(valueRow);
								});
							}
							callback(tableData);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							if (typeof (callError) != "undefined" && callError != null) {
								callError(-900, "操作未完成，向服务器请求失败...");
							}

							callback(tableData);
						}
					});
				}
			}
		});
	}
}
