var colors = [ "#2b908f", "#90ee7e", "#f45b5b", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", , "#514F78", "#42A07B", "#9B5E4A", "#72727F", "#1F949A", "#82914E",
		"#86777F", "#42A07B", "#DDDF0D", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee" ];
var nodeLines = {}, nodeType = [], nodeTypeItem = [];

jQuery(document).ready(function() {
	refHbNode();
	refDataCur();

	setInterval("refDataCur()", 80000);
})

/**
 * 
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
		success : function(res) {
			if (res.code != 0) {
				callError(res.code, res.message);
			} else {
				var html = "";

				nodeType = res.subJoinResponseData.data;
				nodeTypeItem = res.subJoinResponseData.subJoinResponseData.data;
				$.each(res.data, function(index, value) {
					html += '<div class="panel panel-default subbox">';
					html += '<div class="panel-heading"><a href=# onclick=showNodeDetail()>' + value.nodeName + '</a></div>';
					html += '<div class="panel-body line" id = "line' + value.nodeId + '"></div>';
					html += '</div>';
				});

				$("#lineCurBox").html(html);

				$.each(res.data, function(index, value) {
					if (value.hasOwnProperty("nodeMn")) {
						var nodeItems = value.nodeItem;
						var yAxis = [], series = [], pars = [];

						nodeLines[value.nodeMn] = {
							id : value.typeId,
							label : []
						};
						if (!(typeof (nodeItems) === "undefined" || nodeItems === null)) {
							nodeItems = $.parseJSON(nodeItems);
							var index = -1;
							for ( var item in nodeItems) {
								if (nodeItems[item].select === 1) {
									index++;
									nodeLines[value.nodeMn][item] = [];
									pars.push(item);
									yAxis.push({
										gridLineWidth : 1,
										labels : {
											enabled : false,
											format : '{value}',
											style : {
												color : colors[index]
											}
										},
										title : {
											enabled : false,
											text : '',
											style : {
												color : colors[index]
											}
										},
										opposite : false,
									});
									series.push({
										name : findTypeItemName(value.typeId, item),
										type : 'spline',
										yAxis : index,
										data : [],
										tooltip : {
											valueSuffix : nodeItems[item].hasOwnProperty("itemUnit") ? nodeItems[item].itemUnit : ''
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
								text : '嘉臣光电科技有限公司',
								href : 'http://www.grasun-opt.com/'
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
							xAxis : [ {
								title : {
									text : '',
									style : {
										color : colors[10]
									}
								},
								type : 'datetime',
								tickInterval : 200,
								categories : []
							} ],
							yAxis : yAxis,
							tooltip : {
								crosshairs : true,
								shared : true
							},
							series : series,
						});

						nodeLines[value.nodeMn].chart = chart;
						nodeLines[value.nodeMn].par = pars;
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
 * 
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
		success : function(res) {
			if (res.code != 0) {
				callError(res.code, res.message);
			} else {
				$.each(res.data, function(index, value) {
					nodeLines[value.nodeMn].label.push(value.dataTime);
					if (value.hasOwnProperty("nodeData")) {
						var nodeData = $.parseJSON(value.nodeData);
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
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError(-900, "操作未完成，向服务器请求失败...");
		}
	});

}

/**
 * 
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
 * 
 * @returns
 */
function showNodeDetail() {
	alert(111);
}

/**
 * 
 * @param code
 * @param message
 * @returns
 */
function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}