// ************************
// 窗体 全局变量定义
// ************************
var treeorgaslt = null, treepageslt = null;
var orgformedita = " ", orgformeditb = " ";
var refa = false, refb = false;
var dataformctl = [];
var dataorgformctla = [];
var dataorgformctlb = [];

/**
 * Grid控件
 */
var dgOrgPageA = null;
var dgPageCtlA = null;
var dgOrgPageB = null;
var dgPageCtlB = null;

/**
 * 窗体 启动 事件
 */
function pageLoad() {
	pageIni();
}

// 初始化 Grid
function pageIni() {

	/***************************************************************************************************************************************************************************************************
	 * 初始化treeOrgA
	 **************************************************************************************************************************************************************************************************/
	$("#treeOrgA").tree({
		animate : true,
		checkbox : true,
		cascadeCheck : true,
		onlyLeafCheck : true,
		lines : true,
		dnd : false,

		onCheck : function(node, checked) {
			if (checked) {
				dgOrgPageA.Grid.datagrid('loading');
				if (treeorgaslt != null) {
					$("#treeOrgA").tree("uncheck", treeorgaslt.target);
				}
				var nodeid = replace(node.id, "node", "");
				var treepagroot = $("#treePageA").tree("getChecked");

				for (var icnt = 0; icnt < treepagroot.length; icnt++) {
					refa = true;
					$("#treePageA").tree("uncheck", treepagroot[icnt].target);
				}

				treeorgaslt = node;
				dgOrgPageA.Grid.datagrid('loadData', []);
				dgPageCtlA.Grid.datagrid('loadData', []);

				var orgname = node.text;
				var url = "orgFormConfig.refOrgForm";
				var inf = {
					parcnt : 1,
					inpar : {
						orgId : nodeid
					}
				};

				// 查询组织架构窗体信息
				$.ajax({
					type : "POST",
					url : url,
					cache : false,
					data : "inf=" + JSON.stringify(inf),
					dataType : "json",
					success : function(res) {
						if (res.code != 0) {
							refa = false;
							dgOrgPageA.Grid.datagrid('loaded');
							$smsg(res.message, "E", res.code);
						} else {
							var formid = " ";
							var roworgform = null;

							dataorgformctla = res.data;
							for (var icnt = 0; icnt < res.data.length; icnt++) {
								if (formid == " " || formid != res.data[icnt].formId) {
									formid = res.data[icnt].formId;
									roworgform = {
										org_id : res.data[icnt].orgId,
										org_name : orgname,
										form_id : res.data[icnt].formId,
										form_name : res.data[icnt].formName,
										prgroup : res.data[icnt].prgroup
									};
									dgOrgPageA.Grid.datagrid('appendRow', roworgform);
									refa = true;
									$("#treePageA").tree("check", $('#treePageA').tree("find", "node" + res.data[icnt].form_id).target);
								}
							}
							refa = false;
							dgOrgPageA.Grid.datagrid('loaded');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						dgOrgPageA.Grid.datagrid('loaded');
						refa = false;
						$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
					}
				});
			} else {
				var treepagroot = $("#treePageA").tree("getChecked");

				treeorgaslt = null;
				dgOrgPageA.Grid.datagrid('loadData', []);
				dgPageCtlA.Grid.datagrid('loadData', []);

				for (var icnt = 0; icnt < treepagroot.length; icnt++) {
					refa = true;
					$("#treePageA").tree("uncheck", treepagroot[icnt].target);
				}
				refa = false;
			}
		},
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化treePageA
	 **************************************************************************************************************************************************************************************************/
	$("#treePageA").tree({
		animate : true,
		checkbox : true,
		cascadeCheck : false,
		onlyLeafCheck : true,
		lines : true,
		dnd : false,

		onBeforeCheck : function(node, checked) {
			if (checked) {
				var orgnames = $("#treeOrgA").tree("getChecked");

				if (orgnames.length == 0) {
					$smsg("请先选择一个组织架构...!!", "E", -666);
					return false;
				} else {
					return true;
				}
			}
		},

		onCheck : function(node, checked) {
			if (refa) {
				return;
			}
			if (checked) {
				var findformid = -1;
				var roworgform = null;
				var formid = replace(node.id, "node", "");
				var griddata = dgOrgPageA.Grid.datagrid("getRows");

				for (var row = 0; row < griddata.length; row++) {
					if (griddata[row].form_id == formid) {
						findformid = row;
						break;
					}
				}
				if (findformid != -1) {
					dgOrgPageA.setRowHeader(findformid + 1, false, findformid);
					orgformedita = replace(orgformedita, findformid + ",", "");
				} else {
					var first = true;
					var orgid = replace($("#treeOrgA").tree("getChecked")[0].id, "node", "");
					var orgname = $("#treeOrgA").tree("getChecked")[0].text;
					var griddatalen = dgOrgPageA.Grid.datagrid("getRows").length;

					for (var icnt = parseInt(orgcount) + 1; icnt < dataformctl.length; icnt++) {
						if (formid == dataformctl[icnt].form_id) {
							if (first) {
								first = false;

								roworgform = {
									org_id : orgid,
									org_name : orgname,
									form_id : dataformctl[icnt].form_id,
									form_name : dataformctl[icnt].form_name,
									prgroup : dataformctl[icnt].prgroup
								};
								dgOrgPageA.Grid.datagrid('appendRow', roworgform);
								dgOrgPageA.setRowHeader("Insert", false, griddatalen);
								dgOrgPageA.Grid.datagrid('checkRow', griddatalen);

								orgformedita += griddatalen + ",";
							}

							roworgform = {
								org_id : orgid,
								form_id : dataformctl[icnt].form_id,
								form_name : dataformctl[icnt].form_name,
								right_id : dataformctl[icnt].right_id,
								right_inf : dataformctl[icnt].right_inf,
								code_value : dataformctl[icnt].code_value,
								code_name : dataformctl[icnt].code_name,
								rea : dataformctl[icnt].rea,
								rel : 1,
								r1 : dataformctl[icnt].r1,
								r2 : dataformctl[icnt].r2,
								r3 : dataformctl[icnt].r3,
								personid : personid
							};
							dgPageCtlA.Grid.datagrid('appendRow', roworgform);
							dgPageCtlA.setRowHeader("Insert", false, dgPageCtlA.Grid.datagrid("getRows").length - 1);
							dgPageCtlA.addModifyRow("I", dgPageCtlA.Grid.datagrid("getRows").length - 1);
						}
					}
				}
			} else {
				var type = null;
				var formid = replace(node.id, "node", "");
				var orgformdata = dgOrgPageA.Grid.datagrid("getRows");
				var panel = dgOrgPageA.Grid.datagrid("getPanel");
				var tr = panel.find("div.datagrid-cell-rownumber");

				tr.each(function(icnt) {
					if (orgformdata[icnt].form_id == formid) {
						type = $(this).html();
						if (isNaN(type)) {
							switch (type) {
							case "Update":
								break;
							case "Insert":
								dgOrgPageA.Grid.datagrid('deleteRow', icnt);

								var formctldata = dgPageCtlA.Grid.datagrid("getRows");

								for (var row = 0; row < formctldata.length; row++) {
									if (formctldata[row].form_id == formid) {
										dgPageCtlA.Grid.datagrid('deleteRow', row);
										row--;
									}
								}
								orgformedita = replace(orgformedita, icnt + ",", "");
								break;
							case "Delete":
								break;
							default:
							}
						} else {
							dgOrgPageA.Grid.datagrid('uncheckRow', icnt);
							$(this).html("Delete");
							orgformedita += icnt + ",";
						}
					}
				});
			}
		}
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化treeOrgB
	 **************************************************************************************************************************************************************************************************/
	$("#treeOrgB").tree({
		animate : true,
		checkbox : true,
		cascadeCheck : false,
		onlyLeafCheck : true,
		lines : true,
		dnd : false,

		onBeforeCheck : function(node, checked) {
			if (checked) {
				var formslt = $("#treePageB").tree("getChecked");

				if (formslt.length == 0) {
					$smsg("请先选择一个界面...!!", "E", -666);
					return false;
				} else {
					return true;
				}
			}
		},

		onCheck : function(node, checked) {
			if (refb) {
				return;
			}
			if (checked) {
				var findorgid = -1;
				var roworgform = null;
				var orgid = replace(node.id, "node", "");
				var formid = replace($("#treePageB").tree("getChecked")[0].id, "node", "");
				var griddata = dgOrgPageB.Grid.datagrid("getRows");

				for (var row = 0; row < griddata.length; row++) {
					if (griddata[row].org_id == orgid) {
						findorgid = row;
						break;
					}
				}
				if (findorgid != -1) {
					dgOrgPageB.setRowHeader(findorgid + 1, false, findorgid);
					orgformeditb = replace(orgformeditb, findorgid + ",", "");
				} else {
					var first = true;
					var orgid = replace(node.id, "node", "");
					var orgname = node.text;
					var griddatalen = dgOrgPageB.Grid.datagrid("getRows").length;

					for (var icnt = parseInt(orgcount) + 1; icnt < dataformctl.length; icnt++) {
						if (formid == dataformctl[icnt].form_id) {
							if (first) {
								first = false;

								roworgform = {
									org_id : orgid,
									org_name : orgname,
									form_id : dataformctl[icnt].form_id,
									form_name : dataformctl[icnt].form_name,
									prgroup : dataformctl[icnt].prgroup
								};
								dgOrgPageB.Grid.datagrid('appendRow', roworgform);
								dgOrgPageB.setRowHeader("Insert", false, griddatalen);
								dgOrgPageB.Grid.datagrid('checkRow', griddatalen);

								orgformeditb += griddatalen + ",";
							}

							roworgform = {
								org_id : orgid,
								form_id : dataformctl[icnt].form_id,
								org_name : orgname,
								right_id : dataformctl[icnt].right_id,
								right_inf : dataformctl[icnt].right_inf,
								code_value : dataformctl[icnt].code_value,
								code_name : dataformctl[icnt].code_name,
								rea : dataformctl[icnt].rea,
								rel : 1,
								r1 : dataformctl[icnt].r1,
								r2 : dataformctl[icnt].r2,
								r3 : dataformctl[icnt].r3,
								personid : personid
							};
							dgPageCtlB.Grid.datagrid('appendRow', roworgform);
							dgPageCtlB.setRowHeader("Insert", false, dgPageCtlB.Grid.datagrid("getRows").length - 1);
							dgPageCtlB.addModifyRow("I", dgPageCtlB.Grid.datagrid("getRows").length - 1);
						}
					}
				}
			} else {
				var type = null;
				var orgid = replace(node.id, "node", "");
				var formid = replace($("#treePageB").tree("getChecked")[0].id, "node", "");
				var orgformdata = dgOrgPageB.Grid.datagrid("getRows");
				var panel = dgOrgPageB.Grid.datagrid("getPanel");
				var tr = panel.find("div.datagrid-cell-rownumber");

				tr.each(function(icnt) {
					if (orgformdata[icnt].org_id == orgid) {
						type = $(this).html();
						if (isNaN(type)) {
							switch (type) {
							case "Update":
								break;
							case "Insert":
								dgOrgPageB.Grid.datagrid('deleteRow', icnt);

								var formctldata = dgPageCtlB.Grid.datagrid("getRows");

								for (var row = 0; row < formctldata.length; row++) {
									if (formctldata[row].form_id == formid) {
										dgPageCtlB.Grid.datagrid('deleteRow', row);
										row--;
									}
								}
								orgformeditb = replace(orgformeditb, icnt + ",", "");
								break;
							case "Delete":
								break;
							default:
							}
						} else {
							dgOrgPageB.Grid.datagrid('uncheckRow', icnt);
							$(this).html("Delete");
							orgformeditb += icnt + ",";
						}
					}
				});
			}
		}
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化treePageB
	 **************************************************************************************************************************************************************************************************/
	$("#treePageB").tree({
		animate : true,
		checkbox : true,
		cascadeCheck : true,
		onlyLeafCheck : true,
		lines : true,
		dnd : false,

		onCheck : function(node, checked) {
			if (checked) {
				dgOrgPageB.Grid.datagrid('loading');
				if (treepageslt != null) {
					$("#treePageB").tree("uncheck", treepageslt.target);
				}
				var formid = replace(node.id, "node", "");
				var treeorgroot = $("#treeOrgB").tree("getChecked");

				for (var icnt = 0; icnt < treeorgroot.length; icnt++) {
					refb = true;
					$("#treeOrgB").tree("uncheck", treeorgroot[icnt].target);
				}

				treepageslt = node;
				dgOrgPageB.Grid.datagrid('loadData', []);
				dgPageCtlB.Grid.datagrid('loadData', []);

				var url = "../servlet/DBHelper";
				var inf = {
					type : 'QJ',
					prc : 'aps.com.dao.COM0005.refFrm',
					inpar : {
						form_id : formid
					}
				};

				// 查询组织架构窗体信息
				$.ajax({
					type : "POST",
					url : url,
					cache : false,
					data : "inf=" + JSON.stringify(inf),
					dataType : "json",
					success : function(res) {
						if (res[0].p_e_code != 0) {
							refa = false;
							dgOrgPageB.Grid.datagrid('loaded');
							$smsg(res[0].p_e_msg, "E", res[0].p_e_code);
						} else {
							var orgid = " ";
							var roworgform = null;

							dataorgformctlb = res;
							for (var icnt = 1; icnt < res.length; icnt++) {
								if (orgid == " " || orgid != res[icnt].org_id) {
									orgid = res[icnt].org_id;
									roworgform = {
										org_id : res[icnt].org_id,
										org_name : $("#treeOrgB").tree("find", "node" + res[icnt].org_id).text,
										form_id : res[icnt].form_id,
										form_name : res[icnt].form_name,
										prgroup : res[icnt].prgroup
									};
									dgOrgPageB.Grid.datagrid('appendRow', roworgform);
									refb = true;
									$("#treeOrgB").tree("check", $('#treeOrgB').tree("find", "node" + res[icnt].org_id).target);
								}
							}
							refb = false;
							dgOrgPageB.Grid.datagrid('loaded');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						dgOrgPageB.Grid.datagrid('loaded');
						refa = false;
						$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
					}
				});
			} else {
				var treeorgroot = $("#treeOrgB").tree("getChecked");

				treepageslt = null;
				dgOrgPageB.Grid.datagrid('loadData', []);
				dgPageCtlB.Grid.datagrid('loadData', []);

				for (var icnt = 0; icnt < treeorgroot.length; icnt++) {
					refb = true;
					$("#treeOrgB").tree("uncheck", treeorgroot[icnt].target);
				}
				refb = false;
			}
		},
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化Grid 组织架构窗体A
	 **************************************************************************************************************************************************************************************************/
	dgOrgPageA = new comDataGrid("dgOrgPageA", 1, true, true);

	// 初始化Grid列
	// grid.setHeadCol(rowindex,colid, colname, colwidth, valign,irowspan,icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit,oPageat,bprimery, bnecessary,binput, bhidden)

	dgOrgPageA.setHeadCol(1, "ORG_ID", "ORG_ID", 80, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, true);
	dgOrgPageA.setHeadCol(1, "ORG_NAME", "组织架构名称", 100, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgOrgPageA.setHeadCol(1, "FORM_ID", "界面ID", 80, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgOrgPageA.setHeadCol(1, "FORM_NAME", "界面名称", 160, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgOrgPageA.setHeadCol(1, "PRGROUP", "界面组别", 60, "center", 0, 0, false, false, true, null, null, null, null, false, false, false, false);

	dgOrgPageA.dbinf.modify = {
		url : "../servlet/DBHelper",
		type : "PJ",
		prc : "aps.com.dao.COM0005.mdyOrgFrm",
	};

	// 初始化Grid属性
	dgOrgPageA.Grid.datagrid({
		nowrap : true,
		rownumbers : true,
		fit : true,
		fitColumns : false,
		singleSelect : false,
		autoSizeColumn : true,
		autoRowHeight : false,
		checkOnSelect : true,
		selectOnCheck : true,
		remoteSort : false,
		// idField : 'ROWINDEX',
		pagination : false,
		pageList : [ 50, 100, 500 ],
		pageSize : 100,
		loadMsg : "信息处理中，请等待 ...",

		frozenColumns : dgOrgPageA.aryheadcols.bfrozen,
		columns : dgOrgPageA.aryheadcols.ufrozen,

		onBeforeCheck : function(index, row) {
			if (instr(orgformedita, index + ",") >= 0) {
				return false;
			} else {
				return true;
			}
		},

		onBeforeUncheck : function(index, row) {
			if (instr(orgformedita, index + ",") >= 0) {
				return false;
			} else {
				return true;
			}
		},

		onCheck : function(index, row, check) {
			var type = null, insert = false;
			var panel = dgOrgPageA.Grid.datagrid("getPanel");
			var tr = panel.find("div.datagrid-cell-rownumber");

			pageCtlAEERow(true);
			tr.each(function(icnt) {
				if (icnt == index) {
					type = $(this).html();
					if (isNaN(type)) {
						if (type == "Insert") {
							insert = true;
						}
					}
				}
			});

			if (!insert) {
				var roworgform = null;
				var formid = row.form_id;
				var orgid = replace($("#treeOrgA").tree("getChecked")[0].id, "node", "");

				for (var icnt = 1; icnt < dataorgformctla.length; icnt++) {
					if (formid == dataorgformctla[icnt].form_id) {

						roworgform = {
							org_id : orgid,
							form_id : dataorgformctla[icnt].form_id,
							form_name : dataorgformctla[icnt].form_name,
							right_id : dataorgformctla[icnt].right_id,
							right_inf : dataorgformctla[icnt].right_inf,
							code_value : dataorgformctla[icnt].code_value,
							code_name : dataorgformctla[icnt].code_name,
							rea : dataorgformctla[icnt].rea,
							rel : dataorgformctla[icnt].rel,
							r1 : dataorgformctla[icnt].r1,
							r2 : dataorgformctla[icnt].r1,
							r3 : dataorgformctla[icnt].r1
						};
						dgPageCtlA.Grid.datagrid('appendRow', roworgform);
					}
				}
			}
		},

		onUncheck : function(index, row) {
			var remove = 0;
			var formid = row.form_id;
			var formctldata = dgPageCtlA.Grid.datagrid("getRows");

			for (var row = 0; row < formctldata.length; row++) {
				if (formctldata[row].form_id == formid) {
					dgPageCtlA.Grid.datagrid('deleteRow', row);
					row--;

					dgPageCtlA.delModifyRow("U", remove);
					dgPageCtlA.delModifyRow("I", remove);
					remove++;
				}
			}

			pageCtlAEERow(true);
		},

		onClickRow : function(rowIndex, rowData) {
			dgOrgPageA.crtrow = rowIndex;
		}
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化Grid 窗体控件A
	 **************************************************************************************************************************************************************************************************/
	dgPageCtlA = new comDataGrid("dgPageCtlA", 1, false, true);

	// 初始化Grid列
	// grid.setHeadCol(rowindex,colid, colname, colwidth, valign,irowspan,icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit,oPageat,bprimery, bnecessary,binput, bhidden)

	dgPageCtlA.setHeadCol(1, "ORG_ID", "ORG_ID", 80, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, true);
	dgPageCtlA.setHeadCol(1, "FORM_ID", "界面标识", 80, "left", 0, 0, false, false, true, null, null, null, null, true, true, true, false);
	dgPageCtlA.setHeadCol(1, "FORM_NAME", "界面名称", 160, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgPageCtlA.setHeadCol(1, "RIGHT_ID", "功能标识", 60, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgPageCtlA.setHeadCol(1, "RIGHT_INF", "功能描述", 120, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgPageCtlA.setHeadCol(1, "CODE_VALUE", "功能类型ID", 80, "right", 0, 0, false, false, true, null, null, null, null, false, false, true, true);
	dgPageCtlA.setHeadCol(1, "CODE_NAME", "功能类型描述", 160, "left", 0, 0, false, false, true, null, null, null, null, true, false, false, false);
	dgPageCtlA.setHeadCol(1, "REA", "锁定", 40, "center", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgPageCtlA.setHeadCol(1, "REL", "可用", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgPageCtlA.setHeadCol(1, "R1", "备用1", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgPageCtlA.setHeadCol(1, "R2", "备用2", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgPageCtlA.setHeadCol(1, "R3", "备用3", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);

	dgPageCtlA.dbinf.modify = {
		url : "../servlet/DBHelper",
		type : "PJ",
		prc : "aps.com.dao.COM0005.mdyOrgFrmCtl",
	};

	// 初始化Grid属性
	dgPageCtlA.Grid.datagrid({
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
		pagination : false,
		pageList : [ 50, 100, 500 ],
		pageSize : 100,
		loadMsg : "信息处理中，请等待 ...",

		frozenColumns : dgPageCtlA.aryheadcols.bfrozen,
		columns : dgPageCtlA.aryheadcols.ufrozen,

		onClickRow : function(rowIndex, rowData) {
			dgPageCtlA.crtrow = rowIndex;
		},

		onDblClickRow : function(rowIndex, rowData) {
			dgPageCtlA.crtrow = rowIndex;
			if (pageCtlAEERow(true)) {
				dgPageCtlA.crteditrowdata = JSON.stringify(rowData);
				dgPageCtlA.BEditRow();
			}
		},
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化Grid 组织架构窗体B
	 **************************************************************************************************************************************************************************************************/
	dgOrgPageB = new comDataGrid("dgOrgPageB", 1, true, true);

	// 初始化Grid列
	// grid.setHeadCol(rowindex,colid, colname, colwidth, valign,irowspan,icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit,oPageat,bprimery, bnecessary,binput, bhidden)

	dgOrgPageB.setHeadCol(1, "ORG_ID", "ORG_ID", 80, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, true);
	dgOrgPageB.setHeadCol(1, "ORG_NAME", "组织架构名称", 100, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgOrgPageB.setHeadCol(1, "FORM_ID", "界面ID", 80, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgOrgPageB.setHeadCol(1, "FORM_NAME", "界面名称", 160, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgOrgPageB.setHeadCol(1, "PRGROUP", "界面组别", 60, "center", 0, 0, false, false, true, null, null, null, null, false, false, false, false);

	dgOrgPageB.dbinf.modify = {
		url : "../servlet/DBHelper",
		type : "PJ",
		prc : "aps.com.dao.COM0005.mdyOrgFrm",
	};

	// 初始化Grid属性
	dgOrgPageB.Grid.datagrid({
		nowrap : true,
		rownumbers : true,
		fit : true,
		fitColumns : false,
		singleSelect : false,
		autoSizeColumn : true,
		autoRowHeight : false,
		checkOnSelect : true,
		selectOnCheck : true,
		remoteSort : false,
		// idField : 'ROWINDEX',
		pagination : false,
		pageList : [ 50, 100, 500 ],
		pageSize : 100,
		loadMsg : "信息处理中，请等待 ...",

		frozenColumns : dgOrgPageB.aryheadcols.bfrozen,
		columns : dgOrgPageB.aryheadcols.ufrozen,

		onBeforeCheck : function(index, row) {
			if (instr(orgformeditb, index + ",") >= 0) {
				return false;
			} else {
				return true;
			}
		},

		onBeforeUncheck : function(index, row) {
			if (instr(orgformeditb, index + ",") >= 0) {
				return false;
			} else {
				return true;
			}
		},

		onCheck : function(index, row) {
			var type = null, insert = false;
			var panel = dgOrgPageB.Grid.datagrid("getPanel");
			var tr = panel.find("div.datagrid-cell-rownumber");

			pageCtlBEERow(true);
			tr.each(function(icnt) {
				if (icnt == index) {
					type = $(this).html();
					if (isNaN(type)) {
						if (type == "Insert") {
							insert = true;
						}
					}
				}
			});

			if (!insert) {
				var roworgform = null;
				var orgid = row.org_id;
				var orgname = row.org_name;

				for (var icnt = 1; icnt < dataorgformctlb.length; icnt++) {
					if (orgid == dataorgformctlb[icnt].org_id) {

						roworgform = {
							org_id : orgid,
							form_id : dataorgformctlb[icnt].form_id,
							org_name : orgname,
							right_id : dataorgformctlb[icnt].right_id,
							right_inf : dataorgformctlb[icnt].right_inf,
							code_value : dataorgformctlb[icnt].code_value,
							code_name : dataorgformctlb[icnt].code_name,
							rea : dataorgformctlb[icnt].rea,
							rel : dataorgformctlb[icnt].rel,
							r1 : dataorgformctlb[icnt].r1,
							r2 : dataorgformctlb[icnt].r2,
							r3 : dataorgformctlb[icnt].r3
						};
						dgPageCtlB.Grid.datagrid('appendRow', roworgform);
					}
				}
			}
		},

		onUncheck : function(index, row) {
			var remove = 0;
			var orgid = row.org_id;
			var formctldata = dgPageCtlB.Grid.datagrid("getRows");

			for (var row = 0; row < formctldata.length; row++) {
				if (formctldata[row].org_id == orgid) {
					dgPageCtlB.Grid.datagrid('deleteRow', row);
					row--;

					dgPageCtlB.delModifyRow("U", remove);
					dgPageCtlB.delModifyRow("I", remove);
					remove++;
				}
			}

			pageCtlBEERow(true);
		},

		onClickRow : function(rowIndex, rowData) {
			dgOrgPageB.crtrow = rowIndex;
		}
	});

	/***************************************************************************************************************************************************************************************************
	 * 初始化Grid 窗体控件B
	 **************************************************************************************************************************************************************************************************/
	dgPageCtlB = new comDataGrid("dgPageCtlB", 1, false, true);

	// 初始化Grid列
	// grid.setHeadCol(rowindex,colid, colname, colwidth, valign,irowspan,icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit,oPageat,bprimery, bnecessary,binput, bhidden)

	dgPageCtlB.setHeadCol(1, "ORG_ID", "组织架构ID", 80, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgPageCtlB.setHeadCol(1, "FORM_ID", "界面ID", 80, "left", 0, 0, false, false, true, null, null, null, null, true, true, true, true);
	dgPageCtlB.setHeadCol(1, "ORG_NAME", "组织架构名称", 120, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgPageCtlB.setHeadCol(1, "RIGHT_ID", "功能标识", 60, "left", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgPageCtlB.setHeadCol(1, "RIGHT_INF", "功能描述", 120, "left", 0, 0, false, false, true, null, null, null, null, false, false, false, false);
	dgPageCtlB.setHeadCol(1, "CODE_VALUE", "功能类型ID", 80, "right", 0, 0, false, false, true, null, null, null, null, false, false, true, true);
	dgPageCtlB.setHeadCol(1, "CODE_NAME", "功能类型描述", 160, "left", 0, 0, false, false, true, null, null, null, null, true, false, false, false);
	dgPageCtlB.setHeadCol(1, "REA", "锁定", 40, "center", 0, 0, false, false, true, null, null, null, null, false, false, true, false);
	dgPageCtlB.setHeadCol(1, "REL", "可用", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgPageCtlB.setHeadCol(1, "R1", "备用1", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgPageCtlB.setHeadCol(1, "R2", "备用2", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);
	dgPageCtlB.setHeadCol(1, "R3", "备用3", 40, "center", 0, 0, false, false, true, null, null, {
		type : 'checkbox',
		options : {
			on : '1',
			off : '0'
		}
	}, null, false, false, true, false);

	dgPageCtlB.dbinf.modify = {
		url : "../servlet/DBHelper",
		type : "PJ",
		prc : "aps.com.dao.COM0005.mdyOrgFrmCtl",
	};

	// 初始化Grid属性
	dgPageCtlB.Grid.datagrid({
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
		pagination : false,
		pageList : [ 50, 100, 500 ],
		pageSize : 100,
		loadMsg : "信息处理中，请等待 ...",

		frozenColumns : dgPageCtlB.aryheadcols.bfrozen,
		columns : dgPageCtlB.aryheadcols.ufrozen,

		onClickRow : function(rowIndex, rowData) {
			dgPageCtlB.crtrow = rowIndex;
		},

		onDblClickRow : function(rowIndex, rowData) {
			dgPageCtlB.crtrow = rowIndex;
			if (pageCtlBEERow(true)) {
				dgPageCtlB.crteditrowdata = JSON.stringify(rowData);
				dgPageCtlB.BEditRow();
			}
		},
	});
}

/**
 * 查询
 */
function toolRef() {
	dgOrgPageA.Grid.datagrid('loadData', []);
	dgPageCtlA.Grid.datagrid('loadData', []);
	dgOrgPageB.Grid.datagrid('loadData', []);
	dgPageCtlB.Grid.datagrid('loadData', []);

	orgformedita = " ";
	orgformeditb = " ";
	$('#treeOrgA').tree('loadData', []);
	$('#treePageA').tree('loadData', []);
	$('#treeOrgB').tree('loadData', []);
	$('#treePageB').tree('loadData', []);
	treeorgaslt = null;
	treepageslt = null;

	var url = "orgFormConfig.refOrg";
	var inf = {};
	// 加载组织Tree
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
				for (var icnt = 0; icnt < res.data.length; icnt++) {
					if (res.data[icnt].farOrgId == 0) {
						$('#treeOrgA').tree('append', {
							parent : null,
							data : [ {
								id : 'node' + res.data[icnt].orgId,
								text : res.data[icnt].orgName
							} ]
						});

						$('#treeOrgB').tree('append', {
							parent : null,
							data : [ {
								id : 'node' + res.data[icnt].orgId,
								text : res.data[icnt].orgName
							} ]
						});
					} else {
						$('#treeOrgA').tree('append', {
							parent : $('#treeOrgA').tree("find", "node" + res.data[icnt].farOrgId).target,
							data : [ {
								id : 'node' + res.data[icnt].orgId,
								text : res.data[icnt].orgName
							} ]
						});

						$('#treeOrgB').tree('append', {
							parent : $('#treeOrgB').tree("find", "node" + res.data[icnt].farOrgId).target,
							data : [ {
								id : 'node' + res.data[icnt].orgId,
								text : res.data[icnt].orgName
							} ]
						});
					}
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});

	url = "orgFormConfig.refForm";
	// 加载窗体Tree
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
				var prgroup = " ";

				dataformctl = res.data;
				for (var icnt = 0; icnt < res.data.length; icnt++) {
					if (prgroup == " " || prgroup != res.data[icnt].prgroup) {
						prgroup = res.data[icnt].prgroup;
						$('#treePageA').tree('append', {
							parent : null,
							data : [ {
								id : 'node' + prgroup,
								text : prgroup
							} ]
						});

						$('#treePageA').tree('append', {
							parent : $('#treePageA').tree("find", "node" + prgroup).target,
							data : [ {
								id : 'node' + res.data[icnt].formId,
								text : res.data[icnt].formName
							} ]
						});

						$('#treePageB').tree('append', {
							parent : null,
							data : [ {
								id : 'node' + prgroup,
								text : prgroup
							} ]
						});

						$('#treePageB').tree('append', {
							parent : $('#treePageB').tree("find", "node" + prgroup).target,
							data : [ {
								id : 'node' + res.data[icnt].formId,
								text : res.data[icnt].formName
							} ]
						});
					} else {
						$('#treePageA').tree('append', {
							parent : $('#treePageA').tree("find", "node" + prgroup).target,
							data : [ {
								id : 'node' + res.data[icnt].formId,
								text : res.data[icnt].formName
							} ]
						});

						$('#treePageB').tree('append', {
							parent : $('#treePageB').tree("find", "node" + prgroup).target,
							data : [ {
								id : 'node' + res.data[icnt].formId,
								text : res.data[icnt].formName
							} ]
						});
					}

				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}

	});
}
/**
 * 保存
 */
function toolSave() {

	var tab = $('#tabMain').tabs('getSelected');
	var tabindex = $('#tabMain').tabs('getTabIndex', tab);

	if (tabindex == 0) {

		var type = null;
		var panel = dgOrgPageA.Grid.datagrid("getPanel");
		var tr = panel.find("div.datagrid-cell-rownumber");
		var treeorgslt = $('#treeOrgA').tree('getChecked')[0].id;

		pageCtlAEERow(true);
		tr.each(function(icnt) {
			type = $(this).html();
			if (isNaN(type)) {
				if (type == "Insert") {
					$(this).html(icnt + 1);
				}
			}
		});

		if (dgOrgPageA.doModify(true)) {
			if (dgPageCtlA.doModify(true)) {
				toolRef();
				$('#treeOrgA').tree('check', $('#treeOrgA').tree('find', treeorgslt).target);
			}
		}
	} else if (tabindex == 1) {

		var type = null;
		var panel = dgOrgPageB.Grid.datagrid("getPanel");
		var tr = panel.find("div.datagrid-cell-rownumber");
		var treepageslt = $('#treePageB').tree('getChecked')[0].id;

		pageCtlBEERow(true);
		tr.each(function(icnt) {
			type = $(this).html();
			if (isNaN(type)) {
				if (type == "Insert") {
					$(this).html(icnt + 1);
				}
			}
		});

		if (dgOrgPageB.doModify(true)) {
			if (dgPageCtlB.doModify(true)) {
				toolRef();
				$('#treePageB').tree('check', $('#treePageB').tree('find', treepageslt).target);
			}
		}
	}
}

/**
 * 结束行编辑
 */
function pageCtlAEERow(showmsg) {
	if (dgPageCtlA.crteditrow != null) {
		if (dgPageCtlA.Grid.datagrid("validateRow", dgPageCtlA.crteditrow)) {
			if (instr(dgPageCtlA.ineditrows, fmtindex(dgPageCtlA.crteditrow)) >= 0) {
				dgPageCtlA.Grid.datagrid("endEdit", dgPageCtlA.crteditrow);
				// 判断数据是否更新
				var crtdata = dgPageCtlA.Grid.datagrid("getRows");

				if (instr(dgPageCtlA.insertrows, fmtindex(dgPageCtlA.crteditrow)) >= 0 || instr(dgPageCtlA.updaterows, fmtindex(dgPageCtlA.crteditrow)) >= 0
						|| JSON.stringify(crtdata[dgPageCtlA.crteditrow]) != dgPageCtlA.crteditrowdata) {
					dgPageCtlA.EEditRow();
				} else {
					dgPageCtlA.delModifyRow("E");
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
 * 结束行编辑
 */
function pageCtlBEERow(showmsg) {
	if (dgPageCtlB.crteditrow != null) {
		if (dgPageCtlB.Grid.datagrid("validateRow", dgPageCtlB.crteditrow)) {
			if (instr(dgPageCtlB.ineditrows, fmtindex(dgPageCtlB.crteditrow)) >= 0) {
				dgPageCtlB.Grid.datagrid("endEdit", dgPageCtlB.crteditrow);
				// 判断数据是否更新
				var crtdata = dgPageCtlB.Grid.datagrid("getRows");

				if (instr(dgPageCtlB.insertrows, fmtindex(dgPageCtlB.crteditrow)) >= 0 || instr(dgPageCtlB.updaterows, fmtindex(dgPageCtlB.crteditrow)) >= 0
						|| JSON.stringify(crtdata[dgPageCtlB.crteditrow]) != dgPageCtlB.crteditrowdata) {
					dgPageCtlB.EEditRow();
				} else {
					dgPageCtlB.delModifyRow("E");
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