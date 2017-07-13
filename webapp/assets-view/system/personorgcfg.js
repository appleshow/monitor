// ************************
// 窗体 全局变量定义
// ************************
var orgcombdata = [];
var personcombdata = [];
var orgtreedata = null;

/**
 * Grid控件
 */
var dgUser = null;

/**
 * 窗体 启动 事件
 */
function pageLoad() {
	pageIni();
}

// 初始化 Grid
function pageIni() {

	// 取得COM_ORG数据
	var url = "personOrgConfig.referOrg";
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
				orgcombdata = res.data;

				orgtreedata = Json2Tree(orgcombdata, "orgId", "farOrgId", "orgName", 0, 0);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});

	// 取得COM_PERSON数据
	url = "personOrgConfig.referPerson";
	inf = {};
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
				personcombdata = res.data;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});

	var pg = null;

	/***************************************************************************************************************************************************************************************************
	 * 初始化Grid 用戶
	 **************************************************************************************************************************************************************************************************/
	dgUser = new comDataGrid("dgUser", 1, false, true);

	// 初始化Grid列
	// grid.setHeadCol(rowindex,colid, colname, colwidth, valign,irowspan,icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit,oformat,bprimery, bnecessary,binput, bhidden)
	dgUser.setHeadCol(1, "personId", "人员名称", 120, "left", 0, 0, false, false, true, null, null, {
		type : 'combobox',
		options : {
			required : true,
			editable : false,
			panelHeight : 'auto',
			valueField : 'personId',
			textField : 'userName',
			data : personcombdata
		}
	}, fmtUser, true, false, true, false);
	dgUser.setHeadCol(1, "orgId", "组织名称", 160, "left", 0, 0, false, false, true, null, null, {
		type : 'combotree',
		options : {
			animate : false,
			checkbox : false,
			cascadeCheck : false,
			lines : true,
			dnd : false,
			required : true,
			data : orgtreedata
		}
	}, fmtUserOrg, true, false, true, false);
	dgUser.setHeadCol(1, "prflag", "是否有效", 60, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgUser.setHeadCol(1, "uperson", "更新人", 60, "center", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgUser.setHeadCol(1, "utime", "更新时间", 140, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);

	dgUser.dbinf.query = {
		url : "personOrgConfig.referPersonOrg",
		inpar : [
				{
					type : "jtext",
					name : "userId",
					crtl : $('#user_id')
				}, {
					type : "jtext",
					name : "userName",
					crtl : $('#user_name')
				},
		]
	};

	dgUser.dbinf.modify = {
		url : "personOrgConfig.modifyPersonOrg",
	};

	// 初始化Grid属性
	dgUser.Grid.datagrid({
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

		frozenColumns : dgUser.aryheadcols.bfrozen,
		columns : dgUser.aryheadcols.ufrozen,

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

			for (var index = 0; index < data.rows.length; index++) {
				data.rows[index].uperson = fmtUser(data.rows[index].uperson);
			}

			return data;
		},

		onBeforeLoad : function() {
			if (dgUser.canedit) {
				if (dgUser.ineditrows != null || dgUser.updaterows != null || dgUser.insertrows != null || dgUser.deleterows != null) {
					if ($cwmsg("数据未提交，忽略继续操作...??")) {
						dgUser.ineditrows = null;
						dgUser.insertrows = null;
						dgUser.updaterows = null;
						dgUser.deleterows = null;

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
			dgUser.crtrow = rowIndex;
		},

		onDblClickRow : function(rowIndex, rowData) {
			dgUser.crtrow = rowIndex;
			if (personOrgEERow(true)) {
				dgUser.crteditrowdata = JSON.stringify(rowData);
				dgUser.BEditRow();
			}
		},

		toolbar : [
				{
					text : '查询',
					disabled : false,
					iconCls : 'icon-reload',
					handler : function() {
						dgUser.doRefer();
					}
				}, '-', {
					text : '保存',
					disabled : false,
					iconCls : 'icon-save',
					handler : function() {
						if (personOrgEERow(true)) {
							dgUser.doModify();
						}
					}
				}, "-", {
					text : '新增',
					disabled : false,
					iconCls : 'icon-add',
					handler : function() {
						if (personOrgEERow(true)) {
							dgUser.doInsert(null, []);
						}
					}
				}, '-', {
					text : '删除',
					disabled : false,
					iconCls : 'icon-remove',
					handler : function() {
						if (personOrgEERow(true)) {
							dgUser.doDelete(null);
						}
					}
				}, '-', {
					text : '撤销',
					disabled : false,
					iconCls : 'icon-undo',
					handler : function() {
						dgUser.doCancel(null);
					}
				}
		],
	});

	pg = dgUser.Grid.datagrid("getPager");
	if (pg) {
		$(pg).pagination({
			total : 0
		});
	}

}

/**
 * 结束行编辑
 */
function personOrgEERow(showmsg) {
	if (dgUser.crteditrow != null) {
		if (dgUser.Grid.datagrid("validateRow", dgUser.crteditrow)) {
			if (instr(dgUser.ineditrows, fmtindex(dgUser.crteditrow)) >= 0) {
				dgUser.Grid.datagrid("endEdit", dgUser.crteditrow);
				// 判断数据是否更新
				var crtdata = dgUser.Grid.datagrid("getRows");

				if (instr(dgUser.insertrows, fmtindex(dgUser.crteditrow)) >= 0 || instr(dgUser.updaterows, fmtindex(dgUser.crteditrow)) >= 0
						|| JSON.stringify(crtdata[dgUser.crteditrow]) != dgUser.crteditrowdata) {
					dgUser.EEditRow();
				} else {
					dgUser.delModifyRow("E");
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
 * 查询条件 代码标识
 */
function selectUserID() {
	$("#user_id").textbox("textbox").focus();
	$("#user_id").textbox("textbox").select();
}

/**
 * 查询条件 代码名称
 */
function selectUserName() {
	$("#user_name").textbox("textbox").focus();
	$("#user_name").textbox("textbox").select();
}

/**
 * 格式用户
 * 
 * @param value
 */
function fmtUser(value) {
	for (var icnt = 0; icnt < personcombdata.length; icnt++) {
		if (personcombdata[icnt].personId == value)
			return personcombdata[icnt].userName;
	}
	return value;
}
/**
 * 格式化组织
 * 
 * @param value
 */
function fmtUserOrg(value) {
	for (var icnt = 0; icnt < orgcombdata.length; icnt++) {
		if (orgcombdata[icnt].id == value)
			return orgcombdata[icnt].text;
	}
	return value;
}
