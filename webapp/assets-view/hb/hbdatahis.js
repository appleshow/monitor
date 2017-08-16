var colors = [ "#2b908f", "#90ee7e", "#f45b5b", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", , "#514F78", "#42A07B", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F", "#42A07B", "#DDDF0D", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee" ];
var nodeLines = {}, nodeInfo = [], nodeType = [], nodeTypeItem = [];
var tabledataCurR, tabledataCurM, tabledataCurH, combNode, selectNode = "", selectTab = "", gridChangedR = true, gridChangedM = true, gridChangedH = true;

jQuery( document ).ready( function() {
	$( '#dateStr' ).val( ( new Date() ).format( "yyyy-MM-dd" ) + " 00:00:00" );
	$( '#dateEnd' ).val( ( new Date() ).format( "yyyy-MM-dd" ) + " 23:59:59" );

	refHbNode();

	combNode = $( "#nodeMN" ).select2( {
		placeholder : "选择一个类型",
		allowClear : true,
		language : "zh-CN"
	} );
	getCombNodeData();
	$( '#nodeMN' ).on( 'select2:select', function(evt) {
		selectNode = evt.params.data.id;
		gridChangedR = true;
		gridChangedH = true;
	} );

	$( 'a[data-toggle="tab"]' ).on( 'shown.bs.tab', function(e) {
		selectTab = e.target.hash;
	} );
	dateTimeDefualt();

	$( '#dateTimeStr' ).datetimepicker( {
		format : 'YYYY-MM-DD HH:mm:ss',
	} );
	$( '#dateTimeEnd' ).datetimepicker( {
		format : 'YYYY-MM-DD HH:mm:ss',
	} );
	$( "#dateTimeStr" ).on( "dp.change", function(e) {
		$( '#dateTimeEnd' ).data( "DateTimePicker" ).minDate( e.date );
	} );
	$( "#dateTimeEnd" ).on( "dp.change", function(e) {
		$( '#dateTimeStr' ).data( "DateTimePicker" ).maxDate( e.date );
	} );

} )

/**
 * 
 * @returns
 */
function refHbNode() {
	$.ajax( {
		async : false,
		type : "POST",
		url : "HbDataHisController.refHbNode",
		cache : false,
		data : ServerRequestPar( 1, {
			nodeMn : selectNode
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				nodeInfo = res.data;
				nodeType = res.subJoinResponseData.data;
				nodeTypeItem = res.subJoinResponseData.subJoinResponseData.data;

				$.each( res.data, function(index, value) {
					if ( value.hasOwnProperty( "nodeMn" ) && value.nodeMn != null ) {
						var nodeItems = value.nodeItem;
						var yAxis = [], series = [], pars = [], parNames = [], parUnits = [], parCount = 0;

						nodeLines[value.nodeMn] = {
							typeId : value.typeId,
							typeName : findTypeName( value.typeId ),
							nodeId : value.nodeId,
							nodeName : value.nodeName,
							label : []
						};
						if ( !( typeof ( nodeItems ) === "undefined" || nodeItems === null ) ) {
							nodeItems = $.parseJSON( nodeItems );
							parCount = -1;
							res.data[index].nodeItem = nodeItems;
							for ( var item in nodeItems ) {
								if ( nodeItems[item].select === 1 ) {
									var parName = findTypeItemName( value.typeId, item );
									var parUnit = nodeItems[item].hasOwnProperty( "itemUnit" ) ? nodeItems[item].itemUnit : '';

									parCount++;
									nodeLines[value.nodeMn][item] = [];
									pars.push( item );
									parNames.push( parName );
									parUnits.push( parUnit );
									yAxis.push( {
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
									} );
									series.push( {
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
									} );
								}
							}
						}

						nodeLines[value.nodeMn].chart = null;
						nodeLines[value.nodeMn].par = pars;
						nodeLines[value.nodeMn].parName = parNames;
						nodeLines[value.nodeMn].parUnit = parUnits;
						nodeLines[value.nodeMn].parCount = parCount + 1;
						nodeLines[value.nodeMn].yAxis = yAxis;
						nodeLines[value.nodeMn].series = series;

					}
				} );

			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
		}
	} );

}

function refData() {
	if ( selectNode === "" ) {
		callError( 100, "请先选择一个【站点名称】...!!" );
		return;
	}
	if ( selectTab === "#dataCurR" ) {
		if ( gridChangedR ) {
			gridChangedR = false;
			createTableRHis();
		} else {
			tabledataCurR.table.ajax.reload( null, false );
		}
	} else if ( selectTab === "#dataCurM" ) {
		if ( gridChangedM ) {
			gridChangedM = false;
			createTableMHis();
		} else {
			tabledataCurM.table.ajax.reload( null, false );
		}
	} else if ( selectTab === "#dataCurH" ) {
		var momentStr = moment( $( '#dateStr' ).val() );
		var momentEnd = moment( $( '#dateEnd' ).val() );
		var timeLength = momentEnd.diff( momentStr, 'days' ) + 1;

		if ( timeLength > 31 ) {
			callError( 100, "时间区间最大为【31天】，当前查询区间为：" + timeLength + "天...!!" );
			return;
		}
		if ( gridChangedH ) {
			gridChangedH = false;
			createTableHHis();
		} else {
			tabledataCurH.table.ajax.reload( null, false );
		}
	} else {
		refDataHis();
	}
}
/**
 * 
 * @returns
 */
function refDataHis() {
	var momentStr = moment( $( '#dateStr' ).val() );
	var momentEnd = moment( $( '#dateEnd' ).val() );
	var timeLength = momentEnd.diff( momentStr, 'days' );
	var dataType = "";

	if ( timeLength < 0 ) {
		callError( 100, "录入参数【起止时间】有误...!!" );
		return;
	} else if ( timeLength == 0 ) {
		if ( momentEnd.diff( momentStr, 'hours' ) == 0 ) {
			dataType = "2011";
		} else {
			dataType = "2051";
		}
	} else if ( timeLength <= 2 ) {
		dataType = "2051";
	} else {
		dataType = "2061";
	}

	$( "#lineCurBox" ).empty();
	$.ajax( {
		async : false,
		type : "POST",
		url : "HbDataHisController.refHbDataHis",
		cache : false,
		data : ServerRequestPar( 1, {
			nodeId : nodeLines[selectNode].nodeId,
			nodeMn : selectNode,
			dataType : dataType,
			dateStr : $( '#dateStr' ).val(),
			dateEnd : $( '#dateEnd' ).val()
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				for ( var node in nodeLines ) {
					if ( node === selectNode ) {
						var html = "";

						html += '<div class="panel panel-default subbox">';
						html += '<div class="panel-heading"><a href=# title="点击查看详细数据" onclick=showNodeDetail("' + node + '")>' + nodeLines[node].nodeName + '</a></div>';
						html += '<div class="panel-body line" id = "line' + nodeLines[node].nodeId + '"></div>';
						html += '</div>';

						$( "#lineCurBox" ).html( html );

						nodeLines[node].chart = new Highcharts.Chart( {
							credits : {
								// text : '嘉臣光电科技有限公司',
								// href : 'http://www.grasun-opt.com/'
								text : '',
								href : '#'
							},
							chart : {
								renderTo : "line" + nodeLines[node].nodeId,
								zoomType : 'xy'
							},
							title : {
								text : nodeLines[node].nodeName + ' - 历史曲线'
							},
							subtitle : {
								text : "【" + $( '#dateStr' ).val() + " ~ " + $( '#dateEnd' ).val() + "】"
							},
							xAxis : [ {
								title : {
									text : '',
									style : {
										color : colors[10]
									}
								},
								type : 'datetime',
								tickInterval : res.rowCount / 6,
								categories : []
							} ],
							yAxis : nodeLines[node].yAxis,
							tooltip : {
								crosshairs : true,
								shared : true
							},
							series : nodeLines[node].series,
						} );

						break;
					}
				}

				for ( var node in nodeLines ) {
					nodeLines[node].label = [];
					$.each( nodeLines[node].par, function(index, value) {
						nodeLines[node][value] = [];
					} );
				}

				$.each( res.data, function(index, value) {
					nodeLines[value.nodeMn].label.push( value.dataTime );
					if ( value.hasOwnProperty( "nodeData" ) ) {
						var nodeData = $.parseJSON( value.nodeData );

						$.each( nodeLines[value.nodeMn].par, function(index, par) {
							// nodeLines[value.nodeMn][par].push(Math.random()*10);
							if ( nodeData.hasOwnProperty( par ) ) {
								nodeLines[value.nodeMn][par].push( parseFloat( nodeData[par] ) );
							} else {
								nodeLines[value.nodeMn][par].push( 0 );
							}
						} );
					}
				} );

				nodeLines[selectNode].chart.xAxis[0].setCategories( nodeLines[selectNode].label );
				$.each( nodeLines[selectNode].par, function(index, par) {
					nodeLines[selectNode].chart.series[index].setData( nodeLines[selectNode][par] );
				} );

			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
		}
	} );

}

/**
 * 
 * @returns
 */
function createTableRHis() {
	$( "#tableR" ).empty();
	$( "#tableR" ).html( ' <table id="tbdataCurR" class="table table-striped table-bordered display responsive nowrap" cellspacing="0" width="100%"> <thead id="tbdataCurRHC"></thead></table>' );

	tabledataCurR = new CommDataTables( "#tbdataCurR", "#tbdataCurRHC", createColumnInfoR( selectNode ), callError );
	tabledataCurR.scrollY = 72;
	tabledataCurR.buttons = "P";
	tabledataCurR.lengthInfo = {
		lengthMenu : [ [ 50, 100, 300 ], [ "50条", "100条", "300条" ] ],
		pageLength : 50
	};
	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************

	tabledataCurR.create( null, datatableRAjax );
}
/**
 * 
 * @returns
 */
function createTableMHis() {
	$( "#tableM" ).empty();
	$( "#tableM" ).html( ' <table id="tbdataCurM" class="table table-striped table-bordered display responsive nowrap" cellspacing="0" width="100%"> <thead id="tbdataCurMHC"></thead></table>' );

	tabledataCurM = new CommDataTables( "#tbdataCurM", "#tbdataCurMHC", createColumnInfoM( selectNode ), callError );
	tabledataCurM.scrollY = 72;
	tabledataCurM.buttons = "P";
	tabledataCurM.lengthInfo = {
		lengthMenu : [ [ 50, 100, 300 ], [ "50条", "100条", "300条" ] ],
		pageLength : 50
	};
	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************

	tabledataCurM.create( null, datatableMAjax );
}
/**
 * 
 * @returns
 */
function createTableHHis() {
	$( "#tableH" ).empty();
	$( "#tableH" ).html( ' <table id="tbdataCurH" class="table table-striped table-bordered display responsive nowrap" cellspacing="0" width="100%"> <thead id="tbdataCurHHC"></thead></table>' );

	tabledataCurH = new CommDataTables( "#tbdataCurH", "#tbdataCurHHC", createColumnInfoH( selectNode ), callError );
	tabledataCurH.scrollY = 72;
	tabledataCurH.buttons = "P";

	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************

	tabledataCurH.create( null, dataTableHAjax );
}

/**
 * 
 * @param nodeMN
 * @returns
 */
function createColumnInfoR(nodeMN) {
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

	if ( nodeMN != null && nodeLines.hasOwnProperty( nodeMN ) && nodeLines[nodeMN].par.length > 0 ) {
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
		// innerHtmlUnit += "<th>" + nodeLines[nodeMN]["parUnit"][index] + "</th>";
		// }
		// innerHtml += "</tr>";
		// innerHtmlUnit += "</tr>";
		// innerHtml += innerHtmlUnit;

		innerHtml = "<tr>";
		innerHtml += "<th>站点名称</th>";
		innerHtml += "<th>监测时间</th>";

		for ( var index = 0; index < nodeLines[nodeMN].parCount; index++ ) {
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

	$( "#tbdataCurRHC" ).html( innerHtml );

	return tableColumnInfo;
}
/**
 * 
 * @param nodeMN
 * @returns
 */
function createColumnInfoM(nodeMN) {
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

	if ( nodeMN != null && nodeLines.hasOwnProperty( nodeMN ) && nodeLines[nodeMN].par.length > 0 ) {
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
		// innerHtmlUnit += "<th>" + nodeLines[nodeMN]["parUnit"][index] + "</th>";
		// }
		// innerHtml += "</tr>";
		// innerHtmlUnit += "</tr>";
		// innerHtml += innerHtmlUnit;

		innerHtml = "<tr>";
		innerHtml += "<th>站点名称</th>";
		innerHtml += "<th>监测时间</th>";

		for ( var index = 0; index < nodeLines[nodeMN].parCount; index++ ) {
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

	$( "#tbdataCurMHC" ).html( innerHtml );

	return tableColumnInfo;
}
/**
 * 
 * @param nodeMN
 * @returns
 */
function createColumnInfoH(nodeMN) {
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

	if ( nodeMN != null && nodeLines.hasOwnProperty( nodeMN ) && nodeLines[nodeMN].par.length > 0 ) {
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
		// innerHtmlUnit += "<th>" + nodeLines[nodeMN]["parUnit"][index] + "</th>";
		// }
		// innerHtml += "</tr>";
		// innerHtmlUnit += "</tr>";
		// innerHtml += innerHtmlUnit;

		innerHtml = "<tr>";
		innerHtml += "<th>站点名称</th>";
		innerHtml += "<th>监测时间</th>";

		for ( var index = 0; index < nodeLines[nodeMN].parCount; index++ ) {
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

	$( "#tbdataCurHHC" ).html( innerHtml );

	return tableColumnInfo;
}
/**
 * 
 * @param data
 * @param callback
 * @param settings
 * @returns
 */
function datatableRAjax(data, callback, settings) {
	var tableData = {
		draw : settings.iDraw,
		recordsTotal : 0,
		recordsFiltered : 0,
		data : []
	};
	if ( selectNode === "" ) {
		callback( tableData );
	}
	$.ajax( {
		async : false,
		type : "POST",
		url : "HbDataHisController.refHbDataHisGrid",
		cache : false,
		data : ServerRequestPar( 1, {
			nodeId : nodeLines[selectNode].nodeId,
			nodeMn : selectNode,
			dataType : '2011',
			dateStr : $( '#dateStr' ).val(),
			dateEnd : $( '#dateEnd' ).val(),
			pageNumber : data.length == -1 ? 0 : data.start / data.length + 1,
			pageSize : data.length == -1 ? 0 : data.length
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				tableData.recordsTotal = res.totalCount;
				tableData.recordsFiltered = res.totalCount;

				$.each( res.data, function(index, dataHis) {
					if ( dataHis.hasOwnProperty( "nodeData" ) ) {
						var lineData = $.parseJSON( dataHis.nodeData );

						$.each( nodeInfo, function(index, node) {
							if ( node.nodeMn === selectNode && node.hasOwnProperty( "nodeItem" ) ) {
								for ( var item in node.nodeItem ) {
									if ( node.nodeItem[item].select == 1 ) {
										var item = item;
										if ( lineData.hasOwnProperty( item ) && lineData[item] != "" ) {
											if ( node.nodeItem[item].itemVmin != "" && parseFloat( node.nodeItem[item].itemVmin ) > parseFloat( lineData[item] ) ) {
												lineData["_" + item] = '<kbd style="background:green" title="参数下限: ' + node.nodeItem[item].itemVmin + '">' + lineData[item] + '</kbd>';
											} else if ( node.nodeItem[item].itemVmax != "" && parseFloat( node.nodeItem[item].itemVmax ) < parseFloat( lineData[item] ) ) {
												lineData["_" + item] = '<kbd style="background:red" title="参数上限: ' + node.nodeItem[item].itemVmax + '">' + lineData[item] + '</kbd>';
											} else {
												lineData["_" + item] = lineData[item];
											}
										}
									}
								}
							}
						} );

						lineData.DT_RowId = "_" + index;
						lineData.nodeName = nodeLines[dataHis.nodeMn].nodeName;
						lineData.dataTime = dataHis.dataTime;

						tableData.data.push( lineData );
					}
				} );
			}
			callback( tableData );
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
			callback( tableData );
		}

	} );

}
/**
 * 
 * @param data
 * @param callback
 * @param settings
 * @returns
 */
function datatableMAjax(data, callback, settings) {
	var tableData = {
		draw : settings.iDraw,
		recordsTotal : 0,
		recordsFiltered : 0,
		data : []
	};
	if ( selectNode === "" ) {
		callback( tableData );
	}
	$.ajax( {
		async : false,
		type : "POST",
		url : "HbDataHisController.refHbDataHisGrid",
		cache : false,
		data : ServerRequestPar( 1, {
			nodeId : nodeLines[selectNode].nodeId,
			nodeMn : selectNode,
			dataType : '2051',
			dateStr : $( '#dateStr' ).val(),
			dateEnd : $( '#dateEnd' ).val(),
			pageNumber : data.length == -1 ? 0 : data.start / data.length + 1,
			pageSize : data.length == -1 ? 0 : data.length
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				tableData.recordsTotal = res.totalCount;
				tableData.recordsFiltered = res.totalCount;

				$.each( res.data, function(index, dataHis) {
					if ( dataHis.hasOwnProperty( "nodeData" ) ) {
						var lineData = $.parseJSON( dataHis.nodeData );

						$.each( nodeInfo, function(index, node) {
							if ( node.nodeMn === selectNode && node.hasOwnProperty( "nodeItem" ) ) {
								for ( var item in node.nodeItem ) {
									if ( node.nodeItem[item].select == 1 ) {
										var item = item;
										if ( lineData.hasOwnProperty( item ) && lineData[item] != "" ) {
											if ( node.nodeItem[item].itemVmin != "" && parseFloat( node.nodeItem[item].itemVmin ) > parseFloat( lineData[item] ) ) {
												lineData["_" + item] = '<kbd style="background:green" title="参数下限: ' + node.nodeItem[item].itemVmin + '">' + lineData[item] + '</kbd>';
											} else if ( node.nodeItem[item].itemVmax != "" && parseFloat( node.nodeItem[item].itemVmax ) < parseFloat( lineData[item] ) ) {
												lineData["_" + item] = '<kbd style="background:red" title="参数上限: ' + node.nodeItem[item].itemVmax + '">' + lineData[item] + '</kbd>';
											} else {
												lineData["_" + item] = lineData[item];
											}
										}
									}
								}
							}
						} );

						lineData.DT_RowId = "_" + index;
						lineData.nodeName = nodeLines[dataHis.nodeMn].nodeName;
						lineData.dataTime = dataHis.dataTime;

						tableData.data.push( lineData );
					}
				} );
			}
			callback( tableData );
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
			callback( tableData );
		}

	} );

}
/**
 * 
 * @param data
 * @param callback
 * @param settings
 * @returns
 */
function dataTableHAjax(data, callback, settings) {
	var tableData = {
		draw : settings.iDraw,
		recordsTotal : 0,
		recordsFiltered : 0,
		data : []
	};
	if ( selectNode === "" ) {
		callback( tableData );
	}
	$.ajax( {
		async : false,
		type : "POST",
		url : "HbDataHisController.refHbDataHisGrid",
		cache : false,
		data : ServerRequestPar( 1, {
			nodeId : nodeLines[selectNode].nodeId,
			nodeMn : selectNode,
			dataType : '2061',
			dateStr : $( '#dateStr' ).val(),
			dateEnd : $( '#dateEnd' ).val(),
			pageNumber : data.length == -1 ? 0 : data.start / data.length + 1,
			pageSize : data.length == -1 ? 0 : data.length
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				tableData.recordsTotal = res.totalCount;
				tableData.recordsFiltered = res.totalCount;

				$.each( res.data, function(index, dataHis) {
					if ( dataHis.hasOwnProperty( "nodeData" ) ) {
						var lineData = $.parseJSON( dataHis.nodeData );

						$.each( nodeInfo, function(index, node) {
							if ( node.nodeMn === selectNode && node.hasOwnProperty( "nodeItem" ) ) {
								for ( var item in node.nodeItem ) {
									if ( node.nodeItem[item].select == 1 ) {
										if ( lineData.hasOwnProperty( item ) && lineData[item] != "" ) {
											if ( node.nodeItem[item].itemVmin != "" && parseFloat( node.nodeItem[item].itemVmin ) > parseFloat( lineData[item] ) ) {
												lineData["_" + item] = '<kbd style="background:green" title="参数下限: ' + node.nodeItem[item].itemVmin + '">' + lineData[item] + '</kbd>';
											} else if ( node.nodeItem[item].itemVmax != "" && parseFloat( node.nodeItem[item].itemVmax ) < parseFloat( lineData[item] ) ) {
												lineData["_" + item] = '<kbd style="background:red" title="参数上限: ' + node.nodeItem[item].itemVmax + '">' + lineData[item] + '</kbd>';
											} else {
												lineData["_" + item] = lineData[item];
											}
										}
									}
								}
							}
						} );

						lineData.DT_RowId = "_" + index;
						lineData.nodeName = nodeLines[dataHis.nodeMn].nodeName;
						lineData.dataTime = dataHis.dataTime;

						tableData.data.push( lineData );
					}
				} );
			}
			callback( tableData );
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
			callback( tableData );
		}

	} );

}
/**
 * 
 * @param typeId
 * @returns
 */
function findTypeName(typeId) {
	var typeName = "";

	$.each( nodeType, function(index, type) {
		if ( type.typeId === typeId ) {
			typeName = type.typeName;
		}
	} );

	return typeName;
}

/**
 * 
 * @param typeId
 * @param typeItemId
 * @returns
 */
function findTypeItemName(typeId, typeItemId) {
	var typeItemName = "";

	$.each( nodeTypeItem, function(index, value) {
		if ( value.typeId === typeId && value.itemId === typeItemId ) {
			typeItemName = value.itemName;
		}
	} );

	return typeItemName;
}

/**
 * 
 * @returns
 */
function getCombNodeData() {
	var html = "", type = "-";

	for ( var node in nodeLines ) {
		if ( !( type === nodeLines[node].typeName ) ) {
			if ( !( type === "-" ) ) {
				html += "</optgroup>";
			}
			type = nodeLines[node].typeName;
			html += "<optgroup label='站点类型 - " + type + "'>";
		}
		html += "<option value='" + node + "'>" + nodeLines[node].nodeName + "</option>";
	}
	html += "</optgroup>";
	$( "#nodeMN" ).append( html );
}

/**
 * 
 * @returns
 */
function showNodeDetail(nodeMN) {
	$( '#mainTabs a[href="#dataCurR"]' ).tab( 'show' );
}

/**
 * 
 * @param code
 * @param message
 * @returns
 */
function callError(code, message) {
	$( "#mwTitle" ).html( '<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>' );
	$( "#mwMessage" ).html( message );
	$( "#modal-warning" ).modal( "show" );
}

function dateTimeDefualt() {
	moment.locale( 'en', {
		months : '一月_二月_三月_四月_五月_六月_七月_八月_九月_十月_十一月_十二月'.split( '_' ),
		monthsShort : '1月_2月_3月_4月_5月_6月_7月_8月_9月_10月_11月_12月'.split( '_' ),
		weekdays : '星期日_星期一_星期二_星期三_星期四_星期五_星期六'.split( '_' ),
		weekdaysShort : '周日_周一_周二_周三_周四_周五_周六'.split( '_' ),
		weekdaysMin : '日_一_二_三_四_五_六'.split( '_' ),
		longDateFormat : {
			LT : 'Ah点mm分',
			LTS : 'Ah点m分s秒',
			L : 'YYYY-MM-DD',
			LL : 'YYYY年MMMD日',
			LLL : 'YYYY年MMMD日Ah点mm分',
			LLLL : 'YYYY年MMMD日ddddAh点mm分',
			l : 'YYYY-MM-DD',
			ll : 'YYYY年MMMD日',
			lll : 'YYYY年MMMD日Ah点mm分',
			llll : 'YYYY年MMMD日ddddAh点mm分'
		},
		meridiemParse : /凌晨|早上|上午|中午|下午|晚上/,
		meridiemHour : function(hour, meridiem) {
			if ( hour === 12 ) {
				hour = 0;
			}
			if ( meridiem === '凌晨' || meridiem === '早上' || meridiem === '上午' ) {
				return hour;
			} else if ( meridiem === '下午' || meridiem === '晚上' ) {
				return hour + 12;
			} else {
				// '中午'
				return hour >= 11 ? hour : hour + 12;
			}
		},
		meridiem : function(hour, minute, isLower) {
			var hm = hour * 100 + minute;
			if ( hm < 600 ) {
				return '凌晨';
			} else if ( hm < 900 ) {
				return '早上';
			} else if ( hm < 1130 ) {
				return '上午';
			} else if ( hm < 1230 ) {
				return '中午';
			} else if ( hm < 1800 ) {
				return '下午';
			} else {
				return '晚上';
			}
		},
		calendar : {
			sameDay : function() {
				return this.minutes() === 0 ? '[今天]Ah[点整]' : '[今天]LT';
			},
			nextDay : function() {
				return this.minutes() === 0 ? '[明天]Ah[点整]' : '[明天]LT';
			},
			lastDay : function() {
				return this.minutes() === 0 ? '[昨天]Ah[点整]' : '[昨天]LT';
			},
			nextWeek : function() {
				var startOfWeek, prefix;
				startOfWeek = moment__default().startOf( 'week' );
				prefix = this.diff( startOfWeek, 'days' ) >= 7 ? '[下]' : '[本]';
				return this.minutes() === 0 ? prefix + 'dddAh点整' : prefix + 'dddAh点mm';
			},
			lastWeek : function() {
				var startOfWeek, prefix;
				startOfWeek = moment__default().startOf( 'week' );
				prefix = this.unix() < startOfWeek.unix() ? '[上]' : '[本]';
				return this.minutes() === 0 ? prefix + 'dddAh点整' : prefix + 'dddAh点mm';
			},
			sameElse : 'LL'
		},
		ordinalParse : /\d{1,2}(日|月|周)/,
		ordinal : function(number, period) {
			switch ( period ) {
				case 'd' :
				case 'D' :
				case 'DDD' :
					return number + '日';
				case 'M' :
					return number + '月';
				case 'w' :
				case 'W' :
					return number + '周';
				default :
					return number;
			}
		},
		relativeTime : {
			future : '%s内',
			past : '%s前',
			s : '几秒',
			m : '1 分钟',
			mm : '%d 分钟',
			h : '1 小时',
			hh : '%d 小时',
			d : '1 天',
			dd : '%d 天',
			M : '1 个月',
			MM : '%d 个月',
			y : '1 年',
			yy : '%d 年'
		},
		week : {
			// GB/T 7408-1994《数据元和交换格式·信息交换·日期和时间表示法》与ISO 8601:1988等效
			dow : 1, // Monday is the first day of the week.
			doy : 4
		// The week that contains Jan 4th is the first week of the year.
		}
	} );
}
