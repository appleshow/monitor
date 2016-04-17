/**
 * @param pName
 *            Grid名
 * @param pHeadRows
 *            表列头的行数
 * @param bChk
 *            是否在第一列显示ChkBox
 * @param bCandEdit
 *            是否可编辑
 * @returns {comDataGrid}
 */
function comDataGrid(pName, pHeadRows, bChk, bCandEdit) {

	this.Grid = $("#" + pName);
	this.iheadrows = pHeadRows;
	this.bfrozdn = true;

	this.imodify = 0;

	this.dbdata = null;
	this.canedit = null;
	this.crtrow = null;
	this.crteditrow = null;
	this.crteditrowdata = null;

	this.ineditrows = null;
	this.updaterows = null;
	this.insertrows = null;
	this.deleterows = null;
	this.ROWINDEX = 1000000;

	if (bCandEdit == null) {
		this.canedit = false;
	} else {
		this.canedit = bCandEdit;
	}

	// 参数 执行数据查询
	this.dbinf = {
		query : [],
		modify : []
	};
	if (bChk) {
		this.aryheadcols = {
			bfrozen : [ [ {
				field : 'fcolchk',
				checkbox : true
			} ] ],
			ufrozen : [ [], [], [], [], [] ],
			all : [],
			show : []
		};
	} else {
		this.aryheadcols = {
			bfrozen : [ [] ],
			ufrozen : [ [], [], [], [], [] ],
			all : [],
			show : []
		};
	}

	this.aryheadcols.ufrozen.length = this.iheadrows;
}

/**
 * 设置Grid列属性
 * 
 * @param index
 *            行号
 * @param colid
 *            列标题
 * @param colname
 *            列字段
 * @param colwidth
 *            列宽
 * @param valign
 *            表明如何对其列数据，可选值：'left'，'right'，'center'
 * @param irowspan
 *            表明一个单元格跨几行
 * @param icolspan
 *            表明一个单元格跨几列
 * @param bsort
 *            设置为true允许对该列排序
 * @param bfrozen
 *            是否冻结列
 * @param bresizable
 *            设置为true允许该列被缩放
 * @param osorter
 *            T自定义字段排序函数，有2个参数：该列的第一个值,该列的第二个值
 * @param ostyler
 *            单元格样式函数，返回样式字符串装饰表格如'background:red'，function有3个参数： value：字段值, rowData：行数据, rowIndex：行索引
 * @param oedit
 *            表明编辑类型。如果属性是字符串类型表示编辑类型，如果是对象则包含2个参数： type：字符串，编辑类型，可选值：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree options：对象，对象于编辑类型的编辑器属性
 * @param oformat
 *            格式化单元格函数，有3个参数： value：字段的值 rowData：行数据 rowIndex：行索引
 * @param bprimery
 *            框架定义 关键列
 * @param bnecessary
 *            框架定义 必输列
 * @param binput
 *            框架定义 传出列
 * @param bhidden
 *            设置为true将隐藏列
 */
comDataGrid.prototype.setHeadCol = function(index, colid, colname, colwidth, valign, irowspan, icolspan, bsort, bfrozen, bresizable, osorter, ostyler, oedit, oformat, bprimary, bnecessary, binput,
		bhidden) {

	if (bfrozen) {// 添加Frozen列
		if (this.bfrozdn) {
			this.aryheadcols.bfrozen[index - 1].push({
				title : "<Strong\>" + colname,
				field : colid,
				width : colwidth,
				rowspan : irowspan,
				colspan : icolspan,
				align : valign,
				sortable : bsort,
				resizable : bresizable,
				hidden : bhidden,
				formatter : oformat,
				styler : ostyler,
				sorter : osorter,
				editor : bprimary ? null : oedit
			});
		}
	} else {// 添加非Frozen列
		this.bfrozdn = false;

		this.aryheadcols.ufrozen[index - 1].push({
			title : "<Strong\>" + colname,
			field : colid,
			width : colwidth,
			rowspan : irowspan,
			colspan : icolspan,
			align : valign,
			sortable : bsort,
			resizable : bresizable,
			hidden : bhidden,
			formatter : oformat,
			styler : ostyler,
			sorter : osorter,
			editor : bprimary ? null : oedit
		});
	}

	// 添加非 all 列
	if (colid) {
		this.aryheadcols.all.push({
			title : colname,
			field : colid,
			editor : oedit,
			primary : bprimary,
			necessary : bnecessary,
			input : binput
		});

		if (!bhidden) {
			this.aryheadcols.show.push({
				title : colname,
				field : colid,
				editor : oedit,
				primary : bprimary,
				necessary : bnecessary,
				input : binput,
				align : valign
			});
		}
	}
};

/**
 * 
 */
comDataGrid.prototype.Init = function() {

	this.dbdata = null;
	this.crtrow = null;
	this.crteditrow = null;
	this.crteditrowdata = null;

	this.ineditrows = null;
	this.updaterows = null;
	this.insertrows = null;
	this.deleterows = null;
	this.ROWINDEX = 100000;

};

/**
 * @param fCallBack
 *            请求回调函数
 */
comDataGrid.prototype.doRefer = function() {

	var url = null;
	var inf = null;
	var canref = true;
	var grid = this.Grid;

	if (this.canedit) {
		if (this.ineditrows != null || this.updaterows != null || this.insertrows != null || this.deleterows != null) {
			if ($cwmsg("数据未提交，忽略继续操作...??")) {
				this.ineditrows = null;
				this.insertrows = null;
				this.updaterows = null;
				this.deleterows = null;

				canref = true;
			} else {
				canref = false;
			}
		} else {
			canref = true;
		}
	} else {
		canref = true;
	}

	if (!canref) {
		return;
	}
	this.Grid.datagrid('loading');
	this.Init();
	if (this.dbinf.query.url == null) {
		$smsg("未定义 dbinf.query.url...!!", "E", -777);
	} else {
		url = this.dbinf.query.url;

		if (this.dbinf.query.inpar != null) {
			var icnt, parcnt;

			inf = {
				parCount : 0,
				inPar : {}
			};
			parcnt = 0;
			for (icnt = 0; icnt < this.dbinf.query.inpar.length; icnt++) {
				if (this.dbinf.query.inpar[icnt].type == "text") {
					if (this.dbinf.query.inpar[icnt].crtl.value != "") {
						parcnt++;
						inf.inPar[this.dbinf.query.inpar[icnt].name] = this.dbinf.query.inpar[icnt].crtl.value;
					}
				} else if (this.dbinf.query.inpar[icnt].type == "jtext") {
					if (this.dbinf.query.inpar[icnt].crtl.textbox('getValue') != "") {
						parcnt++;
						inf.inPar[this.dbinf.query.inpar[icnt].name] = this.dbinf.query.inpar[icnt].crtl.textbox('getValue');
					}
				} else if (this.dbinf.query.inpar[icnt].type == "jcomb") {
					if (this.dbinf.query.inpar[icnt].crtl.combobox('getValue') != "") {
						parcnt++;
						inf.inPar[this.dbinf.query.inpar[icnt].name] = this.dbinf.query.inpar[icnt].crtl.combobox('getValue');
					}
				} else if (this.dbinf.query.inpar[icnt].type == "jdate") {
					if (this.dbinf.query.inpar[icnt].crtl.datetimebox('getValue') != "") {
						parcnt++;
						inf.inPar[this.dbinf.query.inpar[icnt].name] = this.dbinf.query.inpar[icnt].crtl.datetimebox('getValue');
					}
				} else if (this.dbinf.query.inpar[icnt].type == "jnum") {
					if (this.dbinf.query.inpar[icnt].crtl.numberbox('getValue') != "") {
						parcnt++;
						inf.inPar[this.dbinf.query.inpar[icnt].name] = this.dbinf.query.inpar[icnt].crtl.numberbox('getValue');
					}
				} else if (this.dbinf.query.inpar[icnt].type == "real") {
					if (this.dbinf.query.inpar[icnt].value != "") {
						parcnt++;
						inf.inPar[this.dbinf.query.inpar[icnt].name] = this.dbinf.query.inpar[icnt].value;
					}
				} else {

				}
			}
			inf.parCount = parcnt;
		} else {
			inf = {
				parCount : 0
			};
		}

		grid.datagrid({
			url : url,
			queryParams : {
				inf : JSON.stringify(inf)
			}
		});
	}
};

/**
 * 
 */
comDataGrid.prototype.doModify = function(unref) {

	var error = false;
	var url = null;
	var inf = null;
	var contrl = this;
	var grid = this.Grid;

	if (this.dbinf.modify.url == null) {
		$smsg("未定义 dbinf.modify.url...!!", "E", -777);
		return false;
	} else {
		this.Grid.datagrid('loading');

		url = this.dbinf.modify.url;
		inf = {
			parCount : 0,
			inPar : []
		};

		// 生成参数
		var panel = this.Grid.datagrid("getPanel");
		var tr = panel.find("div.datagrid-cell-rownumber");
		var colinf = this.aryheadcols.all;
		var dgrid = this.Grid;
		var ipar = 0, type = null, parrow = null;
		var crtdata = null, rowdata = null, cellval = null;

		crtdata = this.Grid.datagrid("getRows");
		tr.each(function(icnt) {
			type = $(this).html();
			parrow = {};
			if (isNaN(type)) {
				switch (type) {
				case "Update":
					rowdata = crtdata[icnt];
					parrow["_type"] = "U";
					break;
				case "Insert":
					rowdata = crtdata[icnt];
					parrow["_type"] = "I";
					break;
				case "Delete":
					rowdata = crtdata[icnt];
					parrow["_type"] = "D";
					break;
				default:
					parrow = null;
				}
				if (parrow != null) {
					ipar++;
					for (var icol = 0; icol < colinf.length; icol++) {
						if (colinf[icol].input) {
							if (colinf[icol].editor == null) {
								cellval = rowdata[colinf[icol].field];
							} else {
								switch (colinf[icol].editor.type) {
								case "text":
									cellval = rowdata[colinf[icol].field];
									break;
								case "numberbox":
									cellval = rowdata[colinf[icol].field];
									break;
								default:
									cellval = rowdata[colinf[icol].field];
									break;
								}
							}
							if (cellval == null || cellval == "") {
								if (colinf[icol].necessary) {
									dgrid.crtrow = icnt;
									dgrid.datagrid("selectRow", icnt);
									$smsg("必输录入项: " + colinf[icol].title + " 不能为空...!!", "E");

									tr = null;
									panel = null;
									colinf = null;
									dgrid = null;
									type = null;
									parrow = null;
									crtdata = null;
									rowdata = null;

									// 终止向服务器请求
									ipar = 0;

									error = true;
									return;
								}
								cellval = "";
							}

							parrow[colinf[icol].field] = cellval;
						}
					}
					inf.inPar.push(parrow);
				}
			}
		});

		inf.parCount = ipar;
		this.imodify = ipar;

		tr = null;
		panel = null;
		colinf = null;
		dgrid = null;
		type = null;
		parrow = null;
		crtdata = null;
		rowdata = null;

		if (ipar > 0) {
			$.ajax({
				async : false,
				type : "POST",
				url : url,
				cache : false,
				data : "inf=" + JSON.stringify(inf),
				dataType : "json",
				success : function(res) {
					grid.datagrid('loaded');

					if (res.code != 0) {
						error = true;
						$smsg(res.message, "E", res.code);
					} else {
						contrl.ineditrows = null;
						contrl.updaterows = null;
						contrl.insertrows = null;
						contrl.deleterows = null;

						if (!unref) {
							contrl.doRefer();
						}
						error = false;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					grid.datagrid('loaded');
					error = true;
					$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
				}
			});

			return !error;
		} else {
			this.Grid.datagrid('loaded');

			return true;
		}
	}
};

/**
 * @param afrow
 * @param rowdata
 */
comDataGrid.prototype.doInsert = function(afrow, rowdata) {

	if (afrow != null) {
		this.crtrow = afrow + 1;
	} else {
		if (this.crtrow == null) {
			this.crtrow = this.Grid.datagrid('getRows').length;
		} else {
			this.crtrow = this.crtrow + 1;
		}
	}
	this.crteditrow = this.crtrow;

	this.Grid.datagrid('insertRow', {
		index : this.crtrow,
		row : rowdata
	});

	this.refModifyRow(this.crtrow, 1);
	this.addModifyRow("I", this.crtrow);
	this.BEditRow();
	this.setRowHeader("Insert", false);
	this.Grid.datagrid('selectRow', this.crtrow);
};

/**
 * @param index
 */
comDataGrid.prototype.doDelete = function(index) {

	var dorow = null;

	if (index == null) {
		dorow = this.crtrow;
	} else {
		dorow = index;
	}

	if (dorow == null) {
		$smsg("请选择要删除的行...!!", "I");
	} else {
		if (dorow != this.crtrow) {
			this.Grid.datagrid('selectRow', dorow);
		}
		// 检查要删除行的状态
		if (instr(this.deleterows, fmtindex(dorow)) >= 0) {
			// 已标记Delete的行不做处理
		} else {
			if ((instr(this.ineditrows, fmtindex(dorow)) >= 0) || (instr(this.updaterows, fmtindex(dorow)) >= 0) || (instr(this.insertrows, fmtindex(dorow)) >= 0)) {
				// 标记Update、Insert的行不可进行删除操作
				$smsg("编辑状态下不可进行删除操作...!!", "W");
			} else {
				// 初始状态的行
				this.addModifyRow("D", this.crtrow);
				this.setRowHeader("Delete", false, this.crtrow);
			}
		}

	}
};

comDataGrid.prototype.doCancel = function(index) {

	var dorow = null;

	if (index == null) {
		dorow = this.crtrow;
	} else {
		dorow = index;
	}

	if (dorow != null) {
		// 检查澈消行的状态
		if (instr(this.updaterows, fmtindex(dorow)) >= 0) {
			// 标记为Update的行
			if (dorow != this.crtrow) {
				this.Grid.datagrid('selectRow', dorow);
			}

			var originalRows = this.Grid.data('datagrid').originalRows;

			this.Grid.datagrid('updateRow', {
				index : dorow,
				row : originalRows[dorow]
			});
			if (this.crteditrow == dorow) {
				this.Grid.datagrid("endEdit", dorow);
				this.delModifyRow("E", dorow);
				this.crteditrow = null;
			}
			originalRows = null;
			this.delModifyRow("U", dorow);
		} else if (instr(this.insertrows, fmtindex(dorow)) >= 0) {
			// 标记为Insert的行
			var trows = this.Grid.datagrid('getRows').length - 1;

			this.delModifyRow("I", dorow);
			this.delModifyRow("E", dorow);
			this.Grid.datagrid('deleteRow', dorow);
			if (dorow == trows) {
				dorow = dorow - 1;
			} else {
				this.refModifyRow(dorow + 1, -1);
			}
			this.crtrow = dorow;
			this.Grid.datagrid('selectRow', dorow);
		} else if (instr(this.deleterows, fmtindex(dorow)) >= 0) {
			// 标记为Delete的行
			if (dorow != this.crtrow) {
				this.Grid.datagrid('selectRow', dorow);
				this.crtrow = dorow;
			}
			this.Grid.datagrid("refreshRow", dorow);
			this.delModifyRow("D", dorow);
		} else if (this.crteditrow == dorow) {
			if (dorow != this.crtrow) {
				this.Grid.datagrid('selectRow', dorow);
			}

			var originalRows = this.Grid.data('datagrid').originalRows;

			this.Grid.datagrid('updateRow', {
				index : dorow,
				row : originalRows[dorow]
			});

			this.delModifyRow("E", dorow);
			this.delModifyRow("U", dorow);
			this.Grid.datagrid("endEdit", dorow);
			originalRows = null;
			this.crteditrow = null;
		}
	}
};

/**
 * 取得ROWINDEX
 */
comDataGrid.prototype.getRowIndex = function() {

	this.ROWINDEX = this.ROWINDEX + 1;

	return this.ROWINDEX;

};

/**
 * 显Grid数据
 */
comDataGrid.prototype.showAll = function(data) {

	var showdata = null;

	// 保存服务器返回所有数据
	this.dbdata = data;
	if (data != null) {
		var pageinf = this.Grid.datagrid('getPager');
		if (pageinf != null) {
			pageinf = pageinf.pagination("options");
			showdata = GetDataGridData(data, pageinf.pageSize, pageinf.pageNumber);
		}
	}

	this.Grid.datagrid("loadData", showdata);
	this.Grid.datagrid('loaded');

};

/**
 * 显Grid行头数据
 */
comDataGrid.prototype.setRowHeader = function(type, ball, index) {

	var modifyrows = this.ineditrows + this.updaterows + this.insertrows + this.deleterows;

	if (modifyrows != null) {
		if (ball) {
			var panel = this.Grid.datagrid("getPanel");
			var tr = panel.find("div.datagrid-cell-rownumber");
			var urows = this.ineditrows;
			var irows = this.insertrows;

			tr.each(function(icnt) {
				if (instr(urows, fmtindex(icnt)) >= 0) {
					if (instr(irows, fmtindex(icnt)) >= 0) {
						$(this).html("Insert");
					} else {
						$(this).html("Update");
					}
				}
			});
		} else {
			var panel = this.Grid.datagrid("getPanel");
			var tr = panel.find("div.datagrid-cell-rownumber");
			var crtrow = null;

			if (index == null) {
				crtrow = this.crteditrow;
			} else {
				crtrow = index;
			}

			tr.each(function(icnt) {
				if (icnt == crtrow) {
					$(this).html(type);
					return;
				}
			});

		}
	}
};

/**
 * 重新取得行信息
 */
comDataGrid.prototype.refModifyRow = function(after, plus) {

	var mrows = null;

	if (this.ineditrows != null) {
		mrows = replace(this.ineditrows, /</g, "").split(">");
		this.ineditrows = null;
		for (var icnt = 0; icnt < mrows.length - 1; icnt++) {
			if (mrows[icnt] >= after) {
				mrows[icnt] = parseInt(mrows[icnt]) + plus;
			}
			this.addModifyRow("E", mrows[icnt]);
		}
	}
	if (this.updaterows != null) {
		mrows = replace(this.updaterows, /</g, "").split(">");
		this.updaterows = null;
		for (var icnt = 0; icnt < mrows.length - 1; icnt++) {
			if (mrows[icnt] >= after) {
				mrows[icnt] = parseInt(mrows[icnt]) + plus;
			}
			this.addModifyRow("U", mrows[icnt]);
		}
	}
	;
	if (this.insertrows != null) {
		mrows = replace(this.insertrows, /</g, "").split(">");
		this.insertrows = null;
		for (var icnt = 0; icnt < mrows.length - 1; icnt++) {
			if (mrows[icnt] >= after) {
				mrows[icnt] = parseInt(mrows[icnt]) + plus;
			}
			this.addModifyRow("I", mrows[icnt]);
		}
	}
	;
	if (this.deleterows != null) {
		mrows = replace(this.deleterows, /</g, "").split(">");
		this.deleterows = null;
		for (var icnt = 0; icnt < mrows.length - 1; icnt++) {
			if (mrows[icnt] >= after) {
				mrows[icnt] = parseInt(mrows[icnt]) + plus;
			}
			this.addModifyRow("D", mrows[icnt]);
		}
	}

};

/**
 * 添加行信息
 */
comDataGrid.prototype.addModifyRow = function(type, index) {

	if (type == "E") {
		if (index == null) {
			if (this.ineditrows == null) {
				this.ineditrows = fmtindex(this.crteditrow);
			} else {
				this.ineditrows = this.ineditrows + fmtindex(this.crteditrow);
			}
		} else {
			if (this.ineditrows == null) {
				this.ineditrows = fmtindex(index);
			} else {
				this.ineditrows = this.ineditrows + fmtindex(index);
			}
		}
	} else if (type == "U") {
		if (index == null) {
			if (this.updaterows == null) {
				this.updaterows = fmtindex(this.crteditrow);
			} else {
				this.updaterows = this.updaterows + fmtindex(this.crteditrow);
			}
		} else {
			if (this.updaterows == null) {
				this.updaterows = fmtindex(index);
			} else {
				this.updaterows = this.updaterows + fmtindex(index);
			}
		}
	} else if (type == "I") {
		if (index == null) {
			if (this.insertrows == null) {
				this.insertrows = fmtindex(this.crteditrow);
			} else {
				this.insertrows = this.insertrows + fmtindex(this.crteditrow);
			}
		} else {
			if (this.insertrows == null) {
				this.insertrows = fmtindex(index);
			} else {
				this.insertrows = this.insertrows + fmtindex(index);
			}
		}
	} else if (type == "D") {
		if (index == null) {
			if (this.deleterows == null) {
				this.deleterows = fmtindex(this.crteditrow);
			} else {
				this.deleterows = this.deleterows + fmtindex(this.crteditrow);
			}
		} else {
			if (this.deleterows == null) {
				this.deleterows = fmtindex(index);
			} else {
				this.deleterows = this.deleterows + fmtindex(index);
			}
		}
	}

};

/**
 * 移除行信息
 */
comDataGrid.prototype.delModifyRow = function(type, index) {

	if (type == "E") {
		if (this.ineditrows != null) {
			if (index == null) {
				this.ineditrows = this.ineditrows.replace(fmtindex(this.crteditrow), "");
			} else {
				this.ineditrows = this.ineditrows.replace(fmtindex(index), "");
			}
			if (this.ineditrows == "") {
				this.ineditrows = null;
			}
		}
	} else if (type == "U") {
		if (this.updaterows != null) {
			if (index == null) {
				this.updaterows = this.updaterows.replace(fmtindex(this.crteditrow), "");
			} else {
				this.updaterows = this.updaterows.replace(fmtindex(index), "");
			}
			if (this.updaterows == "") {
				this.updaterows = null;
			}
		}
	} else if (type == "I") {
		if (this.insertrows != null) {
			if (index == null) {
				this.insertrows = this.insertrows.replace(fmtindex(this.crteditrow), "");
			} else {
				this.insertrows = this.insertrows.replace(fmtindex(index), "");
			}
			if (this.insertrows == "") {
				this.insertrows = null;
			}
		}
	} else if (type == "D") {
		if (this.deleterows != null) {
			if (index == null) {
				this.deleterows = this.deleterows.replace(fmtindex(this.crteditrow), "");
			} else {
				this.deleterows = this.deleterows.replace(fmtindex(index), "");
			}
			if (this.deleterows == "") {
				this.deleterows = null;
			}
		}
	}
	;
};

/**
 * 处理Primary列的编辑状态
 */
comDataGrid.prototype.primaryCol = function(benable) {

	if (benable) {
		for (var icnt = 0; icnt < this.aryheadcols.all.length; icnt++) {
			if (this.aryheadcols.all[icnt].primary) {
				var coloption = this.Grid.datagrid("getColumnOption", this.aryheadcols.all[icnt].field);
				coloption.editor = this.aryheadcols.all[icnt].editor;
			}
		}
	} else {
		for (var icnt = 0; icnt < this.aryheadcols.all.length; icnt++) {
			if (this.aryheadcols.all[icnt].primary) {
				var coloption = this.Grid.datagrid("getColumnOption", this.aryheadcols.all[icnt].field);
				coloption.editor = null;
			}
		}

	}
};

/**
 * 开始行编辑
 */
comDataGrid.prototype.BEditRow = function() {

	if (this.canedit) {
		if (instr(this.deleterows, fmtindex(this.crtrow)) >= 0) {
			// 标记删除的行不可进入编辑状态
			this.crteditrow = null;
		} else {
			this.crteditrow = this.crtrow;

			if (instr(this.insertrows, fmtindex(this.crteditrow)) >= 0) {
				this.primaryCol(true);
			}
			this.Grid.datagrid("beginEdit", this.crteditrow);
			this.primaryCol(false);
			this.addModifyRow("E");
		}
	}
};

/**
 * 结束行编辑
 */
comDataGrid.prototype.EEditRow = function() {

	var otype = null;

	if (instr(this.ineditrows, fmtindex(this.crteditrow)) >= 0) {

		// 结束前取得原行状态信息
		if (instr(this.insertrows, fmtindex(this.crteditrow)) >= 0) {
			otype = "Insert";
		} else {
			otype = "Update";
			this.addModifyRow("U");
		}
		// this.Grid.datagrid("endEdit", this.crtrow);
		this.setRowHeader(otype, false);
		this.delModifyRow("E");
	}

};

/**
 * 全部结束行编辑
 */
comDataGrid.prototype.EEditAll = function() {

	if (this.ineditrows != null) {
		// var erows = replace(this.ineditrows, /</g, "").split(">");
		//
		// for ( var icnt = 0; icnt < erows.length - 1; icnt++) {
		// //this.Grid.datagrid("endEdit", parseInt(erows[icnt]));
		// if (instr(this.insertrows, fmtindex(parseInt(erows[icnt]))) < 0) {
		// // 不在Insert中，则标记不Update
		// this.addModifyRow("U", parseInt(erows[icnt]));
		// }
		// }

		this.setRowHeader(null, true);
	}
	this.ineditrows = null;

};

/**
 * 取消行编辑
 */
comDataGrid.prototype.CEditRow = function() {

	if (instr(this.ineditrows, fmtindex(this.crteditrow)) >= 0) {
		this.Grid.datagrid("cancelEdit", this.crtrow);
		this.delModifyRow("E");
		// 重新生成行标记
		if (instr(this.updaterows, fmtindex(this.crteditrow)) >= 0) {
			this.setRowHeader("Update", false, this.crteditrow);
		} else if (instr(this.insertrows, fmtindex(this.crteditrow)) >= 0) {
			this.setRowHeader("Insert", false, this.crteditrow);
		}
	}

};

/**
 * 全部取消行编辑
 */
comDataGrid.prototype.CEditAll = function() {

	if (this.ineditrows != null) {
		var erows = replace(this.ineditrows, /</g, "").split(">");

		for (var icnt = 0; icnt < erows.length - 1; icnt++) {
			this.Grid.datagrid("cancelEdit", parseInt(erows[icnt]));
			// 重新生成行标记
			if (instr(this.updaterows, fmtindex(erows[icnt])) >= 0) {
				this.setRowHeader("Update", false, erows[icnt]);
			} else if (instr(this.insertrows, fmtindex(erows[icnt])) >= 0) {
				this.setRowHeader("Insert", false, erows[icnt]);
			}
		}
		;
	}
	this.ineditrows = null;

};

/**
 * @param data
 * @param pagesize
 * @param pagenumber
 * @returns {}
 */
function GetDataGridData(gdata, pagesize, pagenumber) {

	var rowf = null;
	var rowt = null;

	if (gdata != null) {
		rowf = pagesize * (pagenumber - 1);
		rowt = pagesize * pagenumber - 1;

		rsdata = {
			total : gdata.total,
			rows : []
		};

		for (var icnt = 0; icnt < gdata.rows.length; icnt++) {
			if (icnt >= rowf && icnt <= rowt) {
				rsdata.rows.push(gdata.rows[icnt]);
			}
		}
	}
	return rsdata;

}

function ChangeToTable(printDatagrid, colfiled, rows) {
	var tableString = '<table cellspacing="0" class="pb">';
	var frozenColumns = printDatagrid.datagrid("options").frozenColumns; // 得到frozenColumns对象
	var columns = printDatagrid.datagrid("options").columns; // 得到columns对象
	var nameList = new Array();

	// 载入title
	if (typeof columns != 'undefined' && columns != '') {
		$(columns).each(function(index) {
			tableString += '\n<tr>';
			if (typeof frozenColumns != 'undefined' && typeof frozenColumns[index] != 'undefined') {
				for (var i = 0; i < frozenColumns[index].length; ++i) {
					if (!frozenColumns[index][i].hidden) {
						tableString += '\n<th width="' + frozenColumns[index][i].width + '"';
						if (typeof frozenColumns[index][i].rowspan != 'undefined' && frozenColumns[index][i].rowspan > 1) {
							tableString += ' rowspan="' + frozenColumns[index][i].rowspan + '"';
						}
						if (typeof frozenColumns[index][i].colspan != 'undefined' && frozenColumns[index][i].colspan > 1) {
							tableString += ' colspan="' + frozenColumns[index][i].colspan + '"';
						}
						if (typeof frozenColumns[index][i].field != 'undefined' && frozenColumns[index][i].field != '') {
							nameList.push(frozenColumns[index][i]);
						}
						tableString += '>' + frozenColumns[0][i].title + '</th>';
					}
				}
			}
			for (var i = 0; i < columns[index].length; ++i) {
				if (!columns[index][i].hidden) {
					tableString += '\n<th width="' + columns[index][i].width + '"';
					if (typeof columns[index][i].rowspan != 'undefined' && columns[index][i].rowspan > 1) {
						tableString += ' rowspan="' + columns[index][i].rowspan + '"';
					}
					if (typeof columns[index][i].colspan != 'undefined' && columns[index][i].colspan > 1) {
						tableString += ' colspan="' + columns[index][i].colspan + '"';
					}
					if (typeof columns[index][i].field != 'undefined' && columns[index][i].field != '') {
						nameList.push(columns[index][i]);
					}
					tableString += '>' + columns[index][i].title + '</th>';
				}
			}
			tableString += '\n</tr>';
		});
	}
	// 载入内容
	// var rows = printDatagrid.datagrid("getRows"); // 这段代码是获取当前页的所有行
	nameList = colfiled;
	for (var i = 0; i < rows.length; ++i) {
		tableString += '\n<tr>';
		for (var j = 0; j < nameList.length; ++j) {
			if (nameList[j].field != "-") {
				tableString += '<td';
				if (nameList[j].align != 'undefined' && nameList[j].align != '') {
					tableString += ' style="text-align:' + nameList[j].align + ';"';
				}
				tableString += '>';

				tableString += rows[i][nameList[j].field];

				tableString += '</td>';
			}
		}
		tableString += '\n</tr>';
	}
	tableString += '\n</table>';
	return tableString;
}

function Export(strXlsName, exportGrid, colfiled, rows) {

	var f = $('<form action="../datagridexp.jsp" method="post" id="fm1"></form>');
	var i = $('<input type="hidden" id="txtContent" name="txtContent" />');
	var l = $('<input type="hidden" id="txtName" name="txtName" />');
	i.val(ChangeToTable(exportGrid, colfiled, rows));
	i.appendTo(f);
	l.val(strXlsName);
	l.appendTo(f);
	f.appendTo(document.body).submit();
	// document.body.removeChild(f);
}