/**
 * 当前页面全局变量
 */
var HBDataContrast = {
	// 当前Tab页
	selectTab : undefined,
	// 是否需要重新生成分钟数据表格
	gridChangedM : true,
	// 是否需要重新生成小时数据表格
	gridChangedH : true,
	combTypeData : [],
	combTypeItemData : [],
	combNodeData : [],
	// 重置表格状态
	resetGridStatus : function() {
		this.gridChangedM = true;
		this.gridChangedH = true;
	},
}
var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay
};
DataSourceTree.prototype = {
	data : function(options, callback) {
		setTimeout( function() {
			var url = "hbNodeConfig.referHbType", treeId = "";

			if ( options.id != null ) {
				if ( options.type === "folder" ) {
					url = "hbNodeConfig.referHbNode";
					treeId = options.id.replace( "type", "" );

					$.ajax( {
						async : false,
						type : "POST",
						url : url,
						cache : false,
						data : ServerRequestPar( 1, {
							typeId : treeId
						} ),
						dataType : "json",
						success : function(res) {
							if ( res.code != 0 ) {
								callback( {
									data : []
								} );
							} else {
								var treeData = [];

								$.each( res.data, function(index, value) {
									var nodeItem = value["nodeItem"];

									if ( !( typeof ( nodeItem ) === "undefined" || nodeItem === null ) ) {
										res.data[index]["nodeItem"] = $.parseJSON( nodeItem );
									}
								} );

								$.merge( HBDataContrast.combNodeData, res.data );
								$.each( res.data, function(index, value) {
									var item = {};

									item.id = "node" + value.nodeId;
									item.name = value.nodeName;
									item.type = 'item';
									item.typeId = value.typeId;
									item.nodeId = value.nodeId;

									treeData.push( item );
								} );

								callback( {
									data : treeData
								} );

							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							callback( {
								data : []
							} );
						}
					} );

				} else {

				}
			} else {
				url = "hbNodeConfig.referHbType";

				$.ajax( {
					async : false,
					type : "POST",
					url : url,
					cache : false,
					data : ServerRequestPar( 0, {} ),
					dataType : "json",
					success : function(res) {
						if ( res.code != 0 ) {
							callback( {
								data : []
							} );
						} else {
							var treeData = [];

							HBDataContrast.combTypeData = res.data;
							if ( !( typeof ( res.subJoinJson ) === "undefined" || res.subJoinJson === null ) ) {
								if ( !( typeof ( res.subJoinJson["typeItems"] ) === "undefined" || res.subJoinJson["typeItems"] === null ) ) {
									HBDataContrast.combTypeItemData = res.subJoinJson["typeItems"];
								}
							}
							$.each( res.data, function(index, value) {
								var item = {};

								item.id = "type" + value.typeId;
								item.name = value.typeName;
								item.type = 'folder';

								treeData.push( item );
							} );

							callback( {
								data : treeData
							} );

						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						callback( {
							data : []
						} );
					}
				} );
			}

		}, this._delay )
	}
};

var treeDataNode = new DataSourceTree( {
	data : [],
	delay : 400
} );

jQuery( document ).ready( function() {
	// ******** 开始初始时间区间 ********
	$( '#dateStr' ).val( ( new Date() ).format( "yyyy-MM-dd" ) + " 00:00:00" );
	$( '#dateEnd' ).val( ( new Date() ).format( "yyyy-MM-dd" ) + " 23:59:59" );
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
	// ******************** ==== ********************

	// ******** Tab选择事件 ********
	$( 'a[data-toggle="tab"]' ).on( 'shown.bs.tab', function(e) {
		HBDataContrast.selectTab = e.target.hash;
	} );
	// ******************** ==== ********************

	// ******** 开始初始化站点列表 ********
	$( '#tree-node' ).tree( {
		cacheItems : true,
		selectable : true,
		multiSelect : true,
		dataSource : treeDataNode,
		loadingHTML : '<div class="tree-loading"><i class="fa fa-rotate-right fa-spin"></i></div>',
	} );
	// ******************** ==== ********************
} )
/*******************************************************************************************************************************************************************************************************
 * 
 ******************************************************************************************************************************************************************************************************/
function refData() {
	var selectNodes = $( '#tree-node' ).tree( 'selectedItems' );
	console.log( JSON.stringify( HBDataContrast.combNodeData ) )
	console.log( JSON.stringify( selectNodes ) )
	if ( selectNodes.length == 0 ) {
		callError( 100, "请先选择一个站点...!!" );
		return;
	}
	if ( HBDataContrast.selectTab === "#dataCurM" ) {
		if ( HBDataContrast.gridChangedM ) {
			HBDataContrast.gridChangedM = false;
			createTableMHis( selectNodes );
		} else {
			tabledataCurM.table.ajax.reload( null, false );
		}
	} else if ( HBDataContrast.selectTab === "#dataCurH" ) {
		var momentStr = moment( $( '#dateStr' ).val() );
		var momentEnd = moment( $( '#dateEnd' ).val() );
		var timeLength = momentEnd.diff( momentStr, 'days' ) + 1;

		if ( timeLength > 31 ) {
			callError( 100, "时间区间最大为【31天】，当前查询区间为：" + timeLength + "天...!!" );
			return;
		}
		if ( HBDataContrast.gridChangedH ) {
			HBDataContrast.gridChangedH = false;
			createTableHHis( selectNodes );
		} else {
			tabledataCurH.table.ajax.reload( null, false );
		}
	}
}
/*******************************************************************************************************************************************************************************************************
 * 生成表格 - 分钟数据
 ******************************************************************************************************************************************************************************************************/
function createTableMHis(selectNodes) {
	$( "#tableM" ).empty();
	$( "#tableM" ).html( ' <table id="tbdataCurM" class="table table-striped table-bordered display responsive nowrap" cellspacing="0" width="100%"> <thead id="tbdataCurMHC"></thead></table>' );

	tabledataCurM = new CommDataTables( "#tbdataCurM", "#tbdataCurMHC", createColumnInfo( selectNodes, "#tbdataCurMHC" ), callError );
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

	tabledataCurM.create( null, dataTableMAjax );
}
/*******************************************************************************************************************************************************************************************************
 * 生成表格 - 小时数据
 ******************************************************************************************************************************************************************************************************/
function createTableHHis(selectNodes) {
	$( "#tableH" ).empty();
	$( "#tableH" ).html( ' <table id="tbdataCurH" class="table table-striped table-bordered display responsive nowrap" cellspacing="0" width="100%"> <thead id="tbdataCurHHC"></thead></table>' );

	tabledataCurH = new CommDataTables( "#tbdataCurH", "#tbdataCurHHC", createColumnInfo( selectNodes, "#tbdataCurHHC" ), callError );
	tabledataCurH.scrollY = 72;
	tabledataCurH.buttons = "P";

	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************

	tabledataCurH.create( null, dataTableHAjax );
}
/*******************************************************************************************************************************************************************************************************
 * 动态生成表格列信息
 ******************************************************************************************************************************************************************************************************/
function createColumnInfo(selectNodes, headColumn) {
	var tableColumnInfo = {};
	var innerHtml = "", parLine = "  ";

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
		sort : 0,
		hide : 0,
		align : 0,
		prtype : "T"
	};
	innerHtml += "<th>监测时间</th>";

	$.each( HBDataContrast.combNodeData, function(indexNode, nodeData) {
		var select = false;
		$.each( selectNodes, function(indexSelect, selectNode) {
			if ( nodeData.nodeId == selectNode.nodeId ) {
				select = true;
			}
		} );
		if ( select && nodeData.hasOwnProperty( "nodeItem" ) ) {
			for ( var par in nodeData.nodeItem ) {
				if ( nodeData.nodeItem[par].select == 1 && parLine.indexOf( "-" + par + "-" ) <= 0 ) {
					var parName = "";
					var columnInfo = {};
					$.each( HBDataContrast.combTypeItemData, function(indexParName, parData) {
						if ( parData.typeId == nodeData.typeId && parData.itemId == par ) {
							parName = parData.itemName;
						}
					} );

					columnInfo.name = par;
					columnInfo.primary = 0;
					columnInfo.update = 0;
					columnInfo.edit = 0;
					columnInfo.type = "text";
					columnInfo.lock = 0;
					columnInfo.sort = 0;
					columnInfo.hide = 0;
					columnInfo.align = 2;
					columnInfo.prtype = "T";

					tableColumnInfo["_" + par] = columnInfo;
					innerHtml += "<th>" + parName + " (<small>" + nodeData.nodeItem[par].itemUnit + "</small>)</th>";
					parLine += "-" + par + "-";
				}
			}
		}
	} );
	innerHtml += "</tr>";
	$( headColumn ).html( innerHtml );

	return tableColumnInfo;
}
/*******************************************************************************************************************************************************************************************************
 * 错误提示框
 ******************************************************************************************************************************************************************************************************/
function callError(code, message) {
	$( "#mwTitle" ).html( '<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>' );
	$( "#mwMessage" ).html( message );
	$( "#modal-warning" ).modal( "show" );
}
/*******************************************************************************************************************************************************************************************************
 * 定义日期时间控件格式
 ******************************************************************************************************************************************************************************************************/
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