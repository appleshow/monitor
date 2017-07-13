/**
 * 当前页面全局变量
 */
var HBDataStatistics = {
	// 当前Tab页
	selectTab : "#dataCurR",
	tableR : undefined,
	tableM : undefined,
	tableH : undefined,
}
var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay
};
DataSourceTree.prototype = {
	data : function(options, callback) {
		setTimeout( function() {
			var url = "hbDataStatisticsController.referHbType", treeId = "";

			if ( options.id != null ) {
				if ( options.type === "folder" ) {
					url = "hbDataStatisticsController.referHbNode";
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
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8'
                        },
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
				url = "hbDataStatisticsController.referHbType";

				$.ajax( {
					async : false,
					type : "POST",
					url : url,
					cache : false,
					data : ServerRequestPar( 0, {} ),
					dataType : "json",
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
					success : function(res) {
						if ( res.code != 0 ) {
							callback( {
								data : []
							} );
						} else {
							var treeData = [];

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
		HBDataStatistics.selectTab = e.target.hash;
	} );
	// ******************** ==== ********************

	// ******** 开始初始化站点列表 ********
	$( '#tree-node' ).tree( {
		cacheItems : true,
		selectable : true,
		multiSelect : false,
		dataSource : treeDataNode,
		loadingHTML : '<div class="tree-loading"><i class="fa fa-rotate-right fa-spin"></i></div>',
	} );
	// ******************** ==== ********************
} )
/*******************************************************************************************************************************************************************************************************
 * 查询数据
 ******************************************************************************************************************************************************************************************************/
function refData(click) {
	var selectNodes = $( '#tree-node' ).tree( 'selectedItems' );
	if ( selectNodes.length == 0 ) {
		callError( 100, "请先选择一个站点...!!" );
		return;
	} else {
		var momentStr = moment( $( '#dateStr' ).val() );
		var momentEnd = moment( $( '#dateEnd' ).val() );
		var timeLength = momentEnd.diff( momentStr, 'days' ) + 1;

		if ( timeLength > 31 ) {
			callError( 100, "时间区间最大为【31天】，当前查询区间为：" + timeLength + "天...!!" );
			return;
		}
		$( "#nodeId" ).val( selectNodes[0].nodeId );
		if ( HBDataStatistics.selectTab === "#dataCurR" ) {
			if ( HBDataStatistics.tableR == undefined ) {
				HBDataStatistics.tableR = new CommDataTables( "#tbdataCurR", "#tbdataCurRHC", 17, callError );
				HBDataStatistics.tableR.serverInfo.referUrl = "hbDataStatisticsController.refHbData";
				HBDataStatistics.tableR.serverInfo.referControls.push( ControlPar( "text", "nodeId", "", $( "#nodeId" ) ) );
				HBDataStatistics.tableR.serverInfo.referControls.push( ControlPar( "real", "dataType", "2011", "" ) );
				HBDataStatistics.tableR.serverInfo.referControls.push( ControlPar( "text", "dateStr", "", $( "#dateStr" ) ) );
				HBDataStatistics.tableR.serverInfo.referControls.push( ControlPar( "text", "dateEnd", "", $( "#dateEnd" ) ) );
				HBDataStatistics.tableR.buttons = "P";
				// *********************************
				HBDataStatistics.tableR.create();
			} else {
				HBDataStatistics.tableR.table.ajax.reload( null, false );
			}
		} else if ( HBDataStatistics.selectTab === "#dataCurM" ) {
			if ( HBDataStatistics.tableM == undefined ) {
				HBDataStatistics.tableM = new CommDataTables( "#tbdataCurM", "#tbdataCurMHC", 17, callError );
				HBDataStatistics.tableM.serverInfo.referUrl = "hbDataStatisticsController.refHbData";
				HBDataStatistics.tableM.serverInfo.referControls.push( ControlPar( "text", "nodeId", "", $( "#nodeId" ) ) );
				HBDataStatistics.tableM.serverInfo.referControls.push( ControlPar( "real", "dataType", "2051", "" ) );
				HBDataStatistics.tableM.serverInfo.referControls.push( ControlPar( "text", "dateStr", "", $( "#dateStr" ) ) );
				HBDataStatistics.tableM.serverInfo.referControls.push( ControlPar( "text", "dateEnd", "", $( "#dateEnd" ) ) );
				HBDataStatistics.tableM.buttons = "P";
				// *********************************
				HBDataStatistics.tableM.create();
			} else {
				HBDataStatistics.tableM.table.ajax.reload( null, false );
			}
		} else if ( HBDataStatistics.selectTab === "#dataCurH" ) {
			if ( HBDataStatistics.tableH == undefined ) {
				HBDataStatistics.tableH = new CommDataTables( "#tbdataCurH", "#tbdataCurHHC", 17, callError );
				HBDataStatistics.tableH.serverInfo.referUrl = "hbDataStatisticsController.refHbData";
				HBDataStatistics.tableH.serverInfo.referControls.push( ControlPar( "text", "nodeId", "", $( "#nodeId" ) ) );
				HBDataStatistics.tableH.serverInfo.referControls.push( ControlPar( "real", "dataType", "2061", "" ) );
				HBDataStatistics.tableH.serverInfo.referControls.push( ControlPar( "text", "dateStr", "", $( "#dateStr" ) ) );
				HBDataStatistics.tableH.serverInfo.referControls.push( ControlPar( "text", "dateEnd", "", $( "#dateEnd" ) ) );
				HBDataStatistics.tableH.buttons = "P";
				// *********************************
				HBDataStatistics.tableH.create();
			} else {
				HBDataStatistics.tableH.table.ajax.reload( null, false );
			}
		}
	}
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