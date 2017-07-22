// ************************
// 窗体 全局变量定义
// ************************
var modifytype = null;
var formdata = [];

/**
 * Grid控件
 */
var dgMenu = null;

/**
 * 窗体 启动 事件
 */
function pageLoad() {
	pageIni();
}

// 初始化 Grid
function pageIni() {

	// 取得FORM数据
	var url = "menuConfig.referAllForms";
	var inf = {};

	$.ajax({
		async : false,
		type : "POST",
		url : url,
		cache : false,
		data : ServerRequestPar(0, inf),
		dataType : "json",
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		},
		success : function(res) {
			if (res.code != 0) {
				$smsg(res.message, "E", res.code);
			} else {
				formdata = res.data;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});

	// 初始化treeMenu
	$("#treeMenu").tree({
		animate : true,
		checkbox : false,
		cascadeCheck : false,
		lines : true,
		dnd : false
	});

	var pg = null;

	/***************************************************************************************************************************************************************************************************
	 * 初始化Grid 菜单
	 **************************************************************************************************************************************************************************************************/
	dgMenu = new comDataGrid("dgMenu", 1, false, true);

	// 初始化Grid列
	// grid.setHeadCol(rowindex,colid, colname, colwidth, valign,irowspan,icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit,oformat,bprimery, bnecessary,binput, bhidden)

	dgMenu.setHeadCol(1, "menuId", "菜单", 60, "right", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgMenu.setHeadCol(1, "menuName", "菜单名称", 140, "left", 0, 0, false, false, true, null, null, {
		type : 'validatebox',
		options : {
			required : true
		}
	}, null, false, false, true, false);
	dgMenu.setHeadCol(1, "farMenuId", "父菜单", 60, "right", 0, 0, false, false, true, null, null, {
		type : 'validatebox',
		options : {
			required : true
		}
	}, null, false, false, true, false);
	dgMenu.setHeadCol(1, "menuSeq", "顺序", 60, "right", 0, 0, false, false, true, null, null, {
		type : 'numberbox'
	}, null, false, false, true, false);
	dgMenu.setHeadCol(1, "formId", "界面", 140, "left", 0, 0, false, false, true, null, null, {
		type : 'combobox',
		options : {
			required : false,
			editable : false,
			valueField : 'formId',
			textField : 'formName',
			data : formdata
		}
	}, fmtForm, false, false, true, false);
	dgMenu.setHeadCol(1, "property0", "图标", 200, "left", 0, 0, false, false, true, null, null, {
		type : 'text'
	}, null, false, false, true, false);
	dgMenu.setHeadCol(1, "prexp", "说明", 80, "left", 0, 0, false, false, true, null, null, {
		type : 'text'
	}, null, false, false, true, false);

	dgMenu.dbinf.query = {
		url : "menuConfig.referMenu",
		inpar : [
				{
					type : "jtext",
					name : "farMenuId",
					crtl : $('#far_menu_id')
				}, {
					type : "jtext",
					name : "menuName",
					crtl : $('#menu_name')
				},
		]
	};

	dgMenu.dbinf.modify = {
		url : "menuConfig.modifyMenu",
	};

	// 初始化Grid属性
	dgMenu.Grid.datagrid({
		nowrap : true,
		rownumbers : true,
		fit : true,
		fitColumns : false,
		singleSelect : true,
		autoSizeColumn : true,
		autoRowHeight : false,
		checkOnSelect : false,
		selectOnCheck : false,
		remoteSort : false,
		// idField : 'ROWINDEX',
		pagination : true,
		pageList : [
				50, 100, 500
		],
		pageSize : 100,
		loadMsg : "信息处理中，请等待 ...",

		frozenColumns : dgMenu.aryheadcols.bfrozen,
		columns : dgMenu.aryheadcols.ufrozen,

		loadFilter : function(res) {
			var data = {
				total : 0,
				rows : []
			};

			if (res == null) {
			} else {
				// 查询成功
				if (res.code != 0) {
					$smsg(res.message, "E", res.code);
				} else {
					data.total = res.rowcount;
					data.rows = res.data;
				}
			}

			return data;
		},

		onBeforeLoad : function() {
			if (dgMenu.canedit) {
				if (dgMenu.ineditrows != null || dgMenu.updaterows != null || dgMenu.insertrows != null || dgMenu.deleterows != null) {
					if ($cwmsg("数据未提交，忽略继续操作...??")) {
						dgMenu.ineditrows = null;
						dgMenu.insertrows = null;
						dgMenu.updaterows = null;
						dgMenu.deleterows = null;

						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
		},

		onClickRow : function(rowIndex, rowData) {
			dgMenu.crtrow = rowIndex;
		},

		onDblClickRow : function(rowIndex, rowData) {
			dgMenu.crtrow = rowIndex;
			if (menuEERow(true)) {
				dgMenu.crteditrowdata = JSON.stringify(rowData);
				dgMenu.BEditRow();
			}
		},

		toolbar : [
				{
					text : '查询',
					disabled : false,
					iconCls : 'icon-reload',
					handler : function() {
						dgMenu.doRefer();
					}
				}, '-', {
					text : '保存',
					disabled : false,
					iconCls : 'icon-save',
					handler : function() {
						if (menuEERow(true)) {
							dgMenu.doModify();
						}
					}
				}, "-", {
					text : '新增',
					disabled : false,
					iconCls : 'icon-add',
					handler : function() {
						if (menuEERow(true)) {
							dgMenu.doInsert(null, []);
						}
					}
				}, '-', {
					text : '删除',
					disabled : false,
					iconCls : 'icon-remove',
					handler : function() {
						if (menuEERow(true)) {
							dgMenu.doDelete(null);
						}
					}
				}, '-', {
					text : '撤销',
					disabled : false,
					iconCls : 'icon-undo',
					handler : function() {
						dgMenu.doCancel(null);
					}
				}
		],
	});

	pg = dgMenu.Grid.datagrid("getPager");
	if (pg) {
		$(pg).pagination({
			total : 0
		});
	}
}

/**
 * 结束行编辑
 */
function menuEERow(showmsg) {
	if (dgMenu.crteditrow != null) {
		if (dgMenu.Grid.datagrid("validateRow", dgMenu.crteditrow)) {
			if (instr(dgMenu.ineditrows, fmtindex(dgMenu.crteditrow)) >= 0) {
				dgMenu.Grid.datagrid("endEdit", dgMenu.crteditrow);
				// 判断数据是否更新
				var crtdata = dgMenu.Grid.datagrid("getRows");

				if (instr(dgMenu.insertrows, fmtindex(dgMenu.crteditrow)) >= 0 || instr(dgMenu.updaterows, fmtindex(dgMenu.crteditrow)) >= 0
						|| JSON.stringify(crtdata[dgMenu.crteditrow]) != dgMenu.crteditrowdata) {
					dgMenu.EEditRow();
				} else {
					dgMenu.delModifyRow("E");
				}
			}
			return true;
		} else {
			if (showmsg)
				$smsg("请先完成上次操作...!!", "I");
			return false;
		}
	} else {
		return true;
	}
}

/**
 * 查询菜单
 */
function menuRef() {
	var url = "menuConfig.referMenuTree";
	var inf = {};

	rootnodes = $('#treeMenu').tree('loadData', []);

	// 加载菜单
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : ServerRequestPar(0, inf),
		dataType : "json",
        headers : {
            'Content-Type' : 'application/json;charset=utf-8'
        },
		success : function(res) {
			if (res.code != 0) {
				$smsg(res.message, "E", res.code);
			} else {
				for (var icnt = 0; icnt < res.data.length; icnt++) {
					if (res.data[icnt].farMenuId == "0") {
						$('#treeMenu').tree('append', {
							parent : null,
							data : [
								{
									id : 'node' + res.data[icnt].menuId,
									text : res.data[icnt].menuName,
									attributes : res.data[icnt].menuSeq,
								}
							]
						});
					} else {
						$('#treeMenu').tree('append', {
							parent : $('#treeMenu').tree("find", "node" + res.data[icnt].farMenuId).target,
							data : [
								{
									id : 'node' + res.data[icnt].menuId,
									text : res.data[icnt].menuName,
									attributes : res.data[icnt].menuSeq
								}
							]
						});
					}
				}
				$("#treeMenu").tree('collapseAll');
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});
}
/**
 * 编辑菜单
 */
function menuEdit() {
	var selectnode = $('#treeMenu').tree('getSelected');

	if (selectnode == null) {
		$smsg("请选择一个菜单结点...!!", "E", -100);
	} else {
		modifytype = "U";
		$obj("far_menu_id").value = replace(selectnode.id, "node", "");
		$('#mdy_menu_name').textbox('setValue', selectnode.text);
		$('#mdy_menu_seq').textbox('setValue', selectnode.attributes);
		$("#modifyTitle").html("&nbsp;&nbsp;&nbsp;编辑菜单：<" + selectnode.text + ">");
		$('#menuModiy').window('open');
		$("#mdy_menu_name").textbox("textbox").focus();
		$("#mdy_menu_name").textbox("textbox").select();
	}
}
/**
 * 新增菜单
 */
function menuAdd() {
	var selectnode = $('#treeMenu').tree('getSelected');

	if (selectnode == null) {
		$smsg("请选择一个菜单结点...!!", "E", -100);
	} else {
		modifytype = "I";
		$obj("far_menu_id").value = replace(selectnode.id, "node", "");
		$('#mdy_menu_name').textbox('setValue', "");
		$('#mdy_menu_seq').textbox('setValue', "");
		$("#modifyTitle").html("&nbsp;&nbsp;&nbsp;新增菜单到：<" + selectnode.text + ">");
		$('#menuModiy').window('open');
		$("#mdy_menu_name").textbox("textbox").focus();
	}
}
/**
 * 删除菜单
 */
function menuDel() {
	var selectnode = $('#treeMenu').tree('getSelected');

	if (selectnode == null) {
		$smsg("请选择一个菜单结点...!!", "E", -100);
	} else {
		var menuid = replace(selectnode.id, "node", "");

		modifytype = "D";
		$cmsg("确定删除菜单：" + selectnode.text + " ..??", function(bok) {
			if (bok) {
				var url = "menuConfig.modifyMenu";
				var inf = {
					parCount : 1,
					inPar : [
						{
							_type : modifytype,
							menuId : menuid
						}
					]
				};
				$.ajax({
					type : "POST",
					url : url,
					cache : false,
					data : JSON.stringify(inf),
					dataType : "json",
                    headers : {
                        'Content-Type' : 'application/json;charset=utf-8'
                    },
					success : function(res) {
						if (res.code != 0) {
							$smsg(res.message, "E", res.code);
						} else {
							menuRef();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
					}
				});
			}
		});
	}
}

/**
 * 
 */
function selectFarMenuID() {
	$("#far_menu_id").textbox("textbox").focus();
	$("#far_menu_id").textbox("textbox").select();
}

/**
 * 
 */
function selectMenuName() {
	$("#menu_name").textbox("textbox").focus();
	$("#menu_name").textbox("textbox").select();
}

/**
 * 
 */
function selectMdyMenuName() {
	$("#mdy_menu_name").textbox("textbox").focus();
	$("#mdy_menu_name").textbox("textbox").select();
}

/**
 * 
 */
function selectMdyMenuSeq() {
	$("#mdy_menu_seq").textbox("textbox").focus();
	$("#mdy_menu_seq").textbox("textbox").select();
}
/**
 * 
 */
function modifyOK() {
	var selectnode = $('#treeMenu').tree('getSelected');
	var menunameo = selectnode.text, menuseqo = selectnode.menuSeq;
	var menunamen = $("#mdy_menu_name").textbox("getValue"), menuseqn = $("#mdy_menu_seq").textbox("getValue");
	var menuid = $obj("far_menu_id").value;

	if (menunameo != null || menunameo != menunamen || menuseqo != menuseqn) {
		var url = "menuConfig.modifyMenu";
		var inf = null;

		if (modifytype == "U" || modifytype == "D") {
			inf = {
				parCount : 1,
				inPar : [
					{
						_type : modifytype,
						menuId : menuid,
						menuName : menunamen,
						menuSeq : menuseqn
					}
				]
			};
		} else {
			inf = {
				parCount : 1,
				inPar : [
					{
						_type : modifytype,
						farMenuId : menuid,
						menuName : menunamen,
						menuSeq : menuseqn
					}
				]
			};
		}

		$.ajax({
			type : "POST",
			url : url,
			cache : false,
			data : JSON.stringify(inf),
			dataType : "json",
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			},
			success : function(res) {
				if (res.code != 0) {
					$smsg(res.message, "E", res.code);
				} else {
					$('#menuModiy').window('close');
					menuRef();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
			}
		});
	}
}

/**
 * 
 */
function modifyCancel() {
	$('#menuModiy').window('close');
}

/**
 * 格式化窗体
 * 
 * @param value
 */
function fmtForm(value) {
	for (var icnt = 0; icnt < formdata.length; icnt++) {
		if (formdata[icnt].formId == value)
			return formdata[icnt].formName;
	}
	return value;
}