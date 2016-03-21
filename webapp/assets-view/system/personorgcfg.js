// ************************
// 窗体 全局变量定义
// ************************
var personid = null;
var username = null;
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
	personid = window.parent.document.getElementById("personid").value;
	username = window.parent.document.getElementById("username").value;

	pageIni();
}

// 初始化 Grid
function pageIni() {

	// 取得COM_ORG数据
	var url = "../servlet/DBHelper";
	var inf = {
		type : 'QJ',
		prc : 'aps.com.dao.COM0006.refComCode'
	};
	$.ajax({
		async : false,
		type : "POST",
		url : url,
		cache : false,
		data : "inf=" + JSON.stringify(inf),
		dataType : "json",
		success : function(res) {
			if (res[0].p_e_code != 0) {
				$smsg(res[0].p_e_msg, "E", res[0].p_e_code);
			} else {
				for (var row = 1; row < res.length; row++) {
					if (res[row].type == "P") {
						personcombdata.push(res[row]);
					} else if (res[row].type == "O") {
						orgcombdata.push(res[row]);
					}
				}
				orgcombdata = JSON.stringify(orgcombdata);
				orgtreedata = Json2Tree( JSON.parse(orgcombdata), -1, 0);
				orgcombdata = JSON.parse(orgcombdata);
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
	dgUser.setHeadCol(1, "PERSON_ID", "人员名称", 120, "left", 0, 0, false, false, true, null, null, {
		type : 'combobox',
		options : {
			required : true,
			editable : false,
			panelHeight : 'auto',
			valueField : 'id',
			textField : 'text',
			data : personcombdata
		}
	}, fmtUser, false, false, true, false);
	dgUser.setHeadCol(1, "ORG_ID", "组织名称", 160, "left", 0, 0, false, false, true, null, null, {
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
	}, fmtUserOrg, false, false, true, false);
	dgUser.setHeadCol(1, "PRFLAG", "是否有效", 60, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgUser.setHeadCol(1, "UPERSON", "更新人", 60, "center", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgUser.setHeadCol(1, "UTIME", "更新时间", 60, "center", 0, 0, false, false, true, null, null, null, null, false, false, false, false);

	dgUser.dbinf.query = {
		url : "../servlet/DBHelper",
		type : "QJ",
		prc : "aps.com.dao.COM0006.refPersonOrg",
		inpar : [ {
			type : "jtext",
			name : "user_id",
			crtl : $('#user_id')
		}, {
			type : "jtext",
			name : "user_name",
			crtl : $('#user_name')
		}, ]
	};

	dgUser.dbinf.modify = {
		url : "../servlet/DBHelper",
		type : "PJ",
		prc : "aps.com.dao.COM0006.mdyPersonOrg",
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
		pageList : [ 50, 100, 500 ],
		pageSize : 100,
		loadMsg : "信息处理中，请等待 ...",

		frozenColumns : dgUser.aryheadcols.bfrozen,
		columns : dgUser.aryheadcols.ufrozen,

		loadFilter : function(res) {
			var data = null;

			if (res == null || res.length <= 0) {
				data = '{"total":' + 0;
				data += ',"rows":[]}';
			} else {
				// 查询成功
				if (res[0].p_e_code != 0) {
					data = '{"total":' + 0;
					data += ',"rows":[]}';
					$smsg(res[0].p_e_msg, "E", res[0].p_e_code);
				} else {
					data = '{"total":' + res[0].p_m_rows;
					data += ',"rows":[';
					for (var row = 1; row < res.length; row++) {
						if (row == 1) {
							data += JSON.stringify(res[row]);
						} else {
							data += "," + JSON.stringify(res[row]);
						}
					}
					data += "]}";
				}
			}
			return JSON.parse(data);
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

		toolbar : [ {
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
		} ],
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
		if (personcombdata[icnt].id == value)
			return personcombdata[icnt].text;
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
