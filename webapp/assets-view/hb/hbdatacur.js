var colors = [
		"#2b908f", "#90ee7e", "#f45b5b", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", , "#514F78", "#42A07B", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F",
		"#42A07B", "#DDDF0D", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"
];
var nodeLines = {}, nodeInfo = [], nodeType = [], nodeTypeItem = [], nodeDataCur = [];
var tableDataCur, combNode, selectNode = "", selectTab = "";

jQuery(document).ready(function() {
	refHbNode();
	refDataCur();

	combNode = $("#nodeMN").select2({
		placeholder : "选择一个类型",
		allowClear : true,
		language : "zh-CN"
	});
	getCombNodeData();
	$('#nodeMN').on('select2:select', function(evt) {
		selectNode = evt.params.data.id;
		createTableCur();
	});

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		selectTab = e.target.hash;
	});
	setInterval("refDataCur()", 80000);
})

/**
 * @returns
 */
function refHbNode() {
	$.ajax({
		async : false,
		type : "POST",
		url : "hbDataCurController.refHbNode",
		cache : false,
		data : ServerRequestPar(0, {}),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if (res.code != 0) {
				callError(res.code, res.message);
			} else {
				var html = "";

				nodeInfo = res.data;
				nodeType = res.subJoinResponseData.data;
				nodeTypeItem = res.subJoinResponseData.subJoinResponseData.data;
				$.each(res.data, function(index, value) {
					html += '<div class="panel panel-default subbox">';
					html += '<div class="panel-heading"><a href=# title="点击查看详细数据" onclick=showNodeDetail("' + value.nodeMn + '")>' + value.nodeName + '</a></div>';
					html += '<div class="panel-body line" id = "line' + value.nodeId + '"></div>';
					html += '</div>';
				});

				$("#lineCurBox").html(html);

				$.each(res.data, function(index, value) {
					if (value.hasOwnProperty("nodeMn")) {
						var nodeItems = value.nodeItem;
						var yAxis = [], series = [], pars = [], parNames = [], parUnits = [], parCount = 0;

						nodeLines[value.nodeMn] = {
							typeId : value.typeId,
							typeName : findTypeName(value.typeId),
							nodeId : value.nodeId,
							nodeName : value.nodeName,
							label : []
						};
						if (!(typeof (nodeItems) === "undefined" || nodeItems === null)) {
							nodeItems = $.parseJSON(nodeItems);
							parCount = -1;
							res.data[index].nodeItem = nodeItems;
							for ( var item in nodeItems) {
								if (nodeItems[item].select === 1) {
									var parName = findTypeItemName(value.typeId, item);
									var parUnit = nodeItems[item].hasOwnProperty("itemUnit") ? nodeItems[item].itemUnit : '';

									parCount++;
									nodeLines[value.nodeMn][item] = [];
									pars.push(item);
									parNames.push(parName);
									parUnits.push(parUnit);
									yAxis.push({
										gridLineWidth : 1,
										labels : {
											enabled : false,
											format : '{value}',
											style : {
												color : colors[parCount]
											}
										},
										title : {
											enabled : false,
											text : '',
											style : {
												color : colors[parCount]
											}
										},
										opposite : false,
									});
									series.push({
										name : parName,
										type : 'spline',
										yAxis : parCount,
										data : [],
										tooltip : {
											valueSuffix : parUnit,
										},
										marker : {
											enabled : false
										},
									});
								}
							}
						}

						var chart = new Highcharts.Chart({
							credits : {
								// text : '嘉臣光电科技有限公司',
								// href : 'http://www.grasun-opt.com/'
								text : '',
								href : '#'
							},
							chart : {
								renderTo : "line" + value.nodeId,
								zoomType : 'xy'
							},
							title : {
								text : '-实时曲线-'
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'middle',
								borderWidth : 0
							},
							xAxis : [
								{
									title : {
										text : '',
										style : {
											color : colors[10]
										}
									},
									type : 'datetime',
									tickInterval : 200,
									categories : []
								}
							],
							yAxis : yAxis,
							tooltip : {
								crosshairs : true,
								shared : true
							},
							series : series,
						});

						nodeLines[value.nodeMn].chart = chart;
						nodeLines[value.nodeMn].par = pars;
						nodeLines[value.nodeMn].parName = parNames;
						nodeLines[value.nodeMn].parUnit = parUnits;
						nodeLines[value.nodeMn].parCount = parCount + 1;
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError(-900, "操作未完成，向服务器请求失败...");
		}
	});

}

/**
 * @returns
 */
function refDataCur() {
	for ( var node in nodeLines) {
		nodeLines[node].label = [];
		$.each(nodeLines[node].par, function(index, value) {
			nodeLines[node][value] = [];
		});
	}

	$.ajax({
		async : false,
		type : "POST",
		url : "hbDataCurController.refHbDataLatest",
		cache : false,
		data : ServerRequestPar(0, {}),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if (res.code != 0) {
				callError(res.code, res.message);
			} else {
				nodeDataCur = [];
				$.each(res.data, function(index, value) {
					nodeLines[value.nodeMn].label.push(value.dataTime);
					if (value.hasOwnProperty("nodeData")) {
						var nodeData = $.parseJSON(value.nodeData);

						value.nodeData = nodeData;
						nodeDataCur.push(value);
						$.each(nodeLines[value.nodeMn].par, function(index, par) {
							// nodeLines[value.nodeMn][par].push(Math.random()*10);
							if (nodeData.hasOwnProperty(par)) {
								nodeLines[value.nodeMn][par].push(parseFloat(nodeData[par]));
							} else {
								nodeLines[value.nodeMn][par].push(0);
							}
						});
					}
				});

				for ( var node in nodeLines) {
					nodeLines[node].chart.xAxis[0].setCategories(nodeLines[node].label);
					$.each(nodeLines[node].par, function(index, par) {
						nodeLines[node].chart.series[index].setData(nodeLines[node][par]);
					});
				}
			}
			if (selectTab == '#dataCur') {
				createTableCur();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError(-900, "操作未完成，向服务器请求失败...");
		}
	});

}

/**
 * @returns
 */
function createTableCur() {
	$("#table").empty();
	$("#table").html(' <table id="tbDataCur" class="table table-striped table-bordered display responsive nowrap" cellspacing="0" width="100%"> <thead id="tbDataCurHC"></thead></table>');

	tableDataCur = new CommDataTables("#tbDataCur", "#tbDataCurHC", createColumnInfo(selectNode), callError);
	tableDataCur.scrollY = 72;
	tableDataCur.buttons = "RP";
	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************

	tableDataCur.create(null, dataTableAjax);
}
/**
 * @param nodeMN
 * @returns
 */
function createColumnInfo(nodeMN) {
	var tableColumnInfo = {};
	var innerHtml = "";

	innerHtml = "<tr>";
	tableColumnInfo.nodeName = {
		name : "站点名称",
		primary : 0,
		update : 0,
		edit : 0,
		type : "text",
		lock : 0,
		sort : 0,
		hide : 0,
		align : 0,
		prtype : "T"
	};
	innerHtml += "<th>站点名称</th>";
	tableColumnInfo.dataTime = {
		name : "监测时间",
		primary : 0,
		update : 0,
		edit : 0,
		type : "text",
		lock : 0,
		sort : 1,
		hide : 0,
		align : 0,
		prtype : "T"
	};
	innerHtml += "<th>监测时间</th>";
	innerHtml += "</tr>";

	if (nodeMN != null && nodeLines.hasOwnProperty(nodeMN) && nodeLines[nodeMN].par.length > 0) {
		// innerHtml = "<tr>";
		// innerHtml += "<th rowspan='2'>站点名称</th>";
		// innerHtml += "<th rowspan='2'>监测时间</th>";
		//
		// var innerHtmlUnit = "<tr>";
		// for (var index = 0; index < nodeLines[nodeMN].parCount; index++) {
		// var columnInfo = {};
		//
		// columnInfo.name = nodeLines[nodeMN]["parName"][index];
		// columnInfo.primary = 0;
		// columnInfo.update = 0;
		// columnInfo.edit = 0;
		// columnInfo.type = "text";
		// columnInfo.lock = 0;
		// columnInfo.sort = 0;
		// columnInfo.hide = 0;
		// columnInfo.align = 2;
		// columnInfo.prtype = "T";
		//
		// tableColumnInfo[nodeLines[nodeMN]["par"][index]] = columnInfo;
		//
		// innerHtml += "<th>" + nodeLines[nodeMN]["parName"][index] + "</th>";
		// innerHtmlUnit += "<th>" + nodeLines[nodeMN]["parUnit"][index] +
		// "</th>";
		// }
		// innerHtml += "</tr>";
		// innerHtmlUnit += "</tr>";
		// innerHtml += innerHtmlUnit;

		innerHtml = "<tr>";
		innerHtml += "<th>站点名称</th>";
		innerHtml += "<th>监测时间</th>";

		for (var index = 0; index < nodeLines[nodeMN].parCount; index++) {
			var columnInfo = {};

			columnInfo.name = nodeLines[nodeMN]["parName"][index];
			columnInfo.primary = 0;
			columnInfo.update = 0;
			columnInfo.edit = 0;
			columnInfo.type = "text";
			columnInfo.lock = 0;
			columnInfo.sort = 0;
			columnInfo.hide = 0;
			columnInfo.align = 2;
			columnInfo.prtype = "T";

			tableColumnInfo["_" + nodeLines[nodeMN]["par"][index]] = columnInfo;

			innerHtml += "<th>" + nodeLines[nodeMN]["parName"][index] + " (<small>" + nodeLines[nodeMN]["parUnit"][index] + "</small>)</th>";
		}
		innerHtml += "</tr>";
	}

	$("#tbDataCurHC").html(innerHtml);
	return tableColumnInfo;
}

/**
 * @param data
 * @param callback
 * @param settings
 * @returns
 */
function dataTableAjax(data, callback, settings) {
	var tableData = {
		draw : settings.iDraw,
		recordsTotal : 0,
		recordsFiltered : 0,
		data : []
	};

	$.each(nodeDataCur, function(index, dataCur) {
		if (dataCur.nodeMn == selectNode) {
			var lineData = dataCur.nodeData;

			$.each(nodeInfo, function(index, node) {
				if (node.nodeMn === selectNode && node.hasOwnProperty("nodeItem")) {
					for ( var item in node.nodeItem) {
						if (node.nodeItem[item].select == 1) {
							if (lineData.hasOwnProperty(item) && lineData[item] != "") {
								if (node.nodeItem[item].itemVmin != "" && parseFloat(node.nodeItem[item].itemVmin) > parseFloat(lineData[item])) {
									lineData["_" + item] = '<kbd style="background:green" title="参数下限: ' + node.nodeItem[item].itemVmin + '">' + lineData[item] + '</kbd>';
								} else if (node.nodeItem[item].itemVmax != "" && parseFloat(node.nodeItem[item].itemVmax) < parseFloat(lineData[item])) {
									lineData["_" + item] = '<kbd style="background:red" title="参数上限: ' + node.nodeItem[item].itemVmax + '">' + lineData[item] + '</kbd>';
								} else {
									lineData["_" + item] = lineData[item];
								}
							}
						}
					}
				}
			});

			lineData.DT_RowId = "_" + index;
			lineData.nodeName = nodeLines[dataCur.nodeMn].nodeName;
			lineData.dataTime = dataCur.dataTime;

			tableData.data.push(lineData);
			tableData.recordsTotal++;
			tableData.recordsFiltered++;
		}
	});

	callback(tableData);
}

/**
 * @param typeId
 * @returns
 */
function findTypeName(typeId) {
	var typeName = "";

	$.each(nodeType, function(index, type) {
		if (type.typeId === typeId) {
			typeName = type.typeName;
		}
	});

	return typeName;
}

/**
 * @param typeId
 * @param typeItemId
 * @returns
 */
function findTypeItemName(typeId, typeItemId) {
	var typeItemName = "";

	$.each(nodeTypeItem, function(index, value) {
		if (value.typeId === typeId && value.itemId === typeItemId) {
			typeItemName = value.itemName;
		}
	});

	return typeItemName;
}

/**
 * @returns
 */
function getCombNodeData() {
	var html = "", type = "-";

	for ( var node in nodeLines) {
		if (!(type === nodeLines[node].typeName)) {
			if (!(type === "-")) {
				html += "</optgroup>";
			}
			type = nodeLines[node].typeName;
			html += "<optgroup label='站点类型 - " + type + "'>";
		}
		html += "<option value='" + node + "'>" + nodeLines[node].nodeName + "</option>";
	}
	html += "</optgroup>";
	$("#nodeMN").append(html);
}

/**
 * @returns
 */
function showNodeDetail(nodeMN) {
	selectNode = nodeMN;
	$('#mainTabs a[href="#dataCur"]').tab('show');
	combNode.val(nodeMN).trigger("change");
	createTableCur();
}

/**
 * @param code
 * @param message
 * @returns
 */
function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}