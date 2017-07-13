var MAP;
var allAddress = {}, selectNode = "-", selectAddress = "-";
var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay
};
DataSourceTree.prototype = {
	data : function(options, callback) {
		callback( {
			data : []
		} );
	}
};

var treeDataNode = new DataSourceTree( {
	data : [],
	delay : 400
} );

// 百度地图API功能
function loadJScript() {
	var script = document.createElement( "script" );
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=4bHlkDU3BDrBGjnLIDEzuLCjDdYhaun4&callback=init";
	document.body.appendChild( script );
}
function init() {
	// 百度地图API功能
	var map = new BMap.Map( "allmap" );

	MAP = map;
	$.ajax( {
		async : false,
		type : "POST",
		url : "hbNodeMapController.refHbNode",
		cache : false,
		data : ServerRequestPar(0, {}),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				var address = [];
				var breakRun = false, totalRun = res.data.length, finishRun = 0;
				$.each( res.data, function(index, node) {
					var nodeAttribute = $.parseJSON( node.nodeAtr );
					var _longitude = parseFloat( nodeAttribute.lx );
					var _latitude = parseFloat( nodeAttribute.ly );
					var point = new BMap.Point( _longitude, _latitude );
					var gc = new BMap.Geocoder();
					gc.getLocation( point, function(rs) {
						var addComp = rs.addressComponents;
						var tmp = {};
						tmp.province = addComp.province;
						tmp.city = addComp.city;
						tmp.nodeId = node.nodeId;
						address.push( tmp );

						finishRun++;
						var mapAddress = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
						console.log( "_____________________________________________" );
						console.log( mapAddress );
					} );
				} );
				setTimeout( function() {
					if ( finishRun >= totalRun ) {
						breakRun = true;
						drawMap( address, res, map )
					}
				}, 2000 );
				setTimeout( function() {
					if ( !breakRun && finishRun >= totalRun ) {
						breakRun = true;
						drawMap( address, res, map )
					}
				}, 2000 );
				setTimeout( function() {
					if ( !breakRun && finishRun >= totalRun ) {
						breakRun = true;
						drawMap( address, res, map )
					}
				}, 2000 );
				setTimeout( function() {
					if ( !breakRun && finishRun >= totalRun ) {
						drawMap( address, res, map );
					}
				}, 2000 );
				setTimeout( function() {
					if ( !breakRun && finishRun >= totalRun ) {
						drawMap( address, res, map );
					}
				}, 2000 );
				setTimeout( function() {
					if ( !breakRun && finishRun >= totalRun ) {
						drawMap( address, res, map );
					}
				}, 2000 );
				setTimeout( function() {
					if ( !breakRun && finishRun >= totalRun ) {
						drawMap( address, res, map );
					}
				}, 2000 );
				// *********************************************************
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
		}
	} );

	/*
	 * // ////////////////////////////////////// map.centerAndZoom( new BMap.Point( 116.417854, 39.921988 ), 15 ); var showText = "<button class='btn btn-default' type='button'
	 * style='width:180px'>Messages <span class='badge'>4444444</span></button>" + "<button class='btn btn-default' type='button' style='width:180px'>Messages <span class='badge'>4</span></button>" + "<button
	 * class='btn btn-default' type='button' style='width:180px'>Messages <span class='badge'>4</span></button>" + "<button class='btn btn-default' type='button' style='width:180px'>Messages <span
	 * class='badge'>4</span></button>" + "<button class='btn btn-default' type='button' style='width:180px'>Messages <span class='badge'>4</span></button>" + "<button class='btn btn-default'
	 * type='button' style='width:180px'>Messages <span class='badge'>4</span></button>" + "<button class='btn btn-default' type='button' style='width:180px'>Messages <span class='badge'>4</span></button>" + "<button
	 * class='btn btn-default' type='button' style='width:180px'>Messages <span class='badge'>4</span></button>" + "<button class='btn btn-default' type='button' style='width:180px'>Messages <span
	 * class='badge'>4</span></button>" + "<button class='btn btn-default' type='button' style='width:180px'>Messages <span class='badge'>4</span></button>"; var data_info = [ [ 116.417854,
	 * 39.921988, showText ], [ 116.406605, 39.921585, "<h6>地址：北京市东城区东华门大街--<h6>" ], [ 116.412222, 39.912345, "地址：北京市东城区正义路甲5号" ] ]; var opts = { width : 360, // 信息窗口宽度 height : 250, // 信息窗口高度
	 * title : "<div class='panel panel-default' style='margin-bottom:5px'><div class='panel-heading'>Panel heading without title</div></div>", // 信息窗口标题 enableMessage : true,// 设置允许信息窗发送短息 }; for (
	 * var i = 0; i < data_info.length; i++ ) { var marker = new BMap.Marker( new BMap.Point( data_info[i][0], data_info[i][1] ) ); // 创建标注 var content = data_info[i][2]; map.addOverlay( marker ); //
	 * 将标注添加到地图中 addClickHandler( content, marker ); } function addClickHandler(content, marker) { marker.addEventListener( "click", function(e) { openInfo( content, e ) } ); } function
	 * openInfo(content, e) { var p = e.target; var point = new BMap.Point( p.getPosition().lng, p.getPosition().lat ); var infoWindow = new BMap.InfoWindow( content, opts ); // 创建信息窗口对象
	 * map.openInfoWindow( infoWindow, point ); // 开启信息窗口 }
	 * 
	 */
}
/**
 * 
 * @param address
 * @param res
 * @returns
 */
function drawMap(address, res, map) {
	console.log( "****************************" );
	console.log( JSON.stringify( address ) );

	var temp;
	temp = res.subJoinResponseData;
	var nodeItemsInfo = temp.data;
	temp = temp.subJoinResponseData;
	var nodeDataLatstOne = temp.data;
	var longitude = [], latitude = [], count = 0;
	var nodeAddress = {};

	$.each( address, function(index, adl) {
		if ( nodeAddress.hasOwnProperty( adl.province ) ) {
			if ( !nodeAddress[adl.province].hasOwnProperty( adl.city ) ) {
				nodeAddress[adl.province][adl.city] = "," + adl.nodeId + ",";
			} else {
				nodeAddress[adl.province][adl.city] = nodeAddress[adl.province][adl.city] + "," + adl.nodeId + ",";
			}
		} else {
			nodeAddress[adl.province] = {};
			nodeAddress[adl.province][adl.city] = "," + adl.nodeId + ",";
		}
	} );

	allAddress = nodeAddress;
	console.log( "++++++++++++++++++++++++++++++++++++++++++++" );
	console.log( JSON.stringify( nodeAddress ) );

	$.each( res.data, function(index, node) {
		if ( selectNode == "-" || selectNode.indexOf( "," + node.nodeId + "," ) > 0 ) {

			count++;
			var nodeTime = "";
			var nodeAttribute = $.parseJSON( node.nodeAtr );
			var nodeType = node.typeId;
			var nodeMn = node.nodeMn;
			var nodeItems;
			if ( typeof ( node.nodeItem ) === "undefined" || node.nodeItem === null ) {
				nodeItems = {};
			} else {
				nodeItems = $.parseJSON( node.nodeItem );
			}
			var _longitude = parseFloat( nodeAttribute.lx );
			var _latitude = parseFloat( nodeAttribute.ly );

			var content = "";
			var itemCount = 0;
			for ( var itemId in nodeItems ) {
				if ( nodeItems[itemId].select == "1" ) {
					itemCount++;
					var itemInfo = nodeItems[itemId];
					var itemName = "-", itemUnit = "", itemValue = 0, itemValueMin = 0, itemValueMax = 0;
					$.each( nodeItemsInfo, function(index, item) {
						if ( item.typeId == nodeType && item.itemId == itemId ) {
							itemName = item.itemName;
						}
					} );
					itemUnit = itemInfo.itemUnit;
					itemValueMin = parseFloat( itemInfo.itemVmin );
					itemValueMax = parseFloat( itemInfo.itemVmax );
					$.each( nodeDataLatstOne, function(index, data) {
						if ( data.nodeMn == nodeMn ) {
							nodeTime = data.dataTime;
							var nodeData = $.parseJSON( data.nodeData );
							for ( var _itemId in nodeData ) {
								if ( _itemId == itemId ) {
									itemValue = nodeData[_itemId];
									break;
								}
							}
						}
					} );
					if ( itemValue < itemValueMin ) {
						content += "<button class='btn btn-success' type='button' style='width:200px' title='参数下限:" + itemValueMin + "'>" + itemName + " <span class='badge'>" + itemValue + itemUnit + "</span></button>";
					} else if ( itemValue > itemValueMax ) {
						content += "<button class='btn btn-danger' type='button' style='width:200px' title='参数上限:" + itemValueMax + "'>" + itemName + " <span class='badge'>" + itemValue + itemUnit + "</span></button>";
					} else {
						content += "<button class='btn btn-default' type='button' style='width:200px'>" + itemName + " <span class='badge'>" + itemValue + itemUnit + "</span></button>";
					}
				}
			}
			if ( itemCount < 3 ) {
				itemCount = 90;
			} else if ( itemCount < 5 ) {
				itemCount = 140;
			} else if ( itemCount < 7 ) {
				itemCount = 160;
			} else if ( itemCount < 9 ) {
				itemCount = 200;
			} else if ( itemCount < 11 ) {
				itemCount = 240;
			} else if ( itemCount < 13 ) {
				itemCount = 280;
			} else if ( itemCount < 15 ) {
				itemCount = 320;
			} else if ( itemCount < 17 ) {
				itemCount = 360;
			} else if ( itemCount < 19 ) {
				itemCount = 400;
			} else {
				itemCount = 500;
			}
			// 创建标注
			var marker = new BMap.Marker( new BMap.Point( _longitude, _latitude ) );
			// 将标注添加到地图中
			map.addOverlay( marker );
			marker.addEventListener( "click", function(e) {
				var opts = {
					width : 400, // 信息窗口宽度
					height : itemCount, // 信息窗口高度 <
					title : "<div class='panel panel-default' style='margin-bottom:5px'><div class='panel-heading'><strong>" + node.nodeName + "</strong><small>&nbsp&nbsp&nbsp&nbsp更新时间:" + nodeTime + "</small></div></div>", // 信息窗口标题
					enableMessage : true,// 设置允许信息窗发送短息
				};
				var p = e.target;
				var point = new BMap.Point( p.getPosition().lng, p.getPosition().lat );
				var infoWindow = new BMap.InfoWindow( content, opts ); // 创建信息窗口对象
				map.openInfoWindow( infoWindow, point ); // 开启信息窗口
			} );

			longitude.push( _longitude );
			latitude.push( _latitude );

		}
	} );

	longitude.sort();
	latitude.sort();
	count = parseInt( count / 2 );

	if ( count > 0 ) {
		map.centerAndZoom( new BMap.Point( longitude[count], latitude[count] ), 15 );
	}

	// ************************************************
	var html = '<div class="row" style="width:250px;">';
	html += '<div class="col-lg-12 col-sm-12 col-xs-12">';
	html += '<div class="widget flat radius-bordered">';
	html += '<div class="widget-header bg-palegreen">';
	html += '<span class="widget-caption">站点区域选择</span>';
	html += '<div class="widget-buttons">';
	html += '<a href="#" data-toggle="collapse" onclick="sizeMap(-1)">';
	html += '<i class="fa fa-search-minus"></i>';
	html += '</a>';
	html += '<a href="#" data-toggle="dispose" onclick="sizeMap(1)">';
	html += '<i class="fa fa-search-plus"></i>';
	html += '</a>';
	html += '</div>';
	html += '</div>';
	html += '<div class="widget-body">';
	html += '<div id="treeAddress" class="tree tree-solid-line">';
	html += '<div class="tree-folder" style="display: none;">';
	html += '<div class="tree-folder-header">';
	html += '<i class="fa fa-folder"></i>';
	html += '<div class="tree-folder-name"></div>';
	html += '</div>';
	html += '<div class="tree-folder-content">';
	html += '</div>';
	html += '<div class="tree-loader" style="display: none;"></div>';
	html += '</div>';
	html += '<div class="tree-item" style="display: none;">';
	html += '<i class="tree-dot"></i>';
	html += '<div class="tree-item-name"></div>';
	html += '</div>';
	for ( var province in nodeAddress ) {
		html += '<div class="tree-folder" style="display: block;">';
		html += '<div class="tree-folder-header">';
		html += '<i class="blueberry fa fa-folder-open"></i>';
		html += '<div class="tree-folder-name">';
		html += ' ' + province + ' ';
		html += '<div class="tree-actions"></div>';
		html += '</div>';
		html += '</div>';
		html += '<div class="tree-folder-content">';
		html += '<div class="tree-folder" style="display: block;">';
		for ( var city in nodeAddress[province] ) {
			if ( selectAddress == "-" || selectAddress.indexOf( "," + city + "," ) > 0 ) {
				html += '<div class="tree-item tree-selected" style="display: block;">';
				html += '<i class="fa fa-check-square-o"></i>';
				html += '<div class="tree-item-name">';
				html += ' ' + city + ' ';
				html += '</div>';
				html += '</div>';
			} else {
				html += '<div class="tree-item" style="display: block;">';
				html += '<i class="tree-dot"></i>';
				html += '<div class="tree-item-name">';
				html += ' ' + city + ' ';
				html += '</div>';
				html += '</div>';
			}
		}
		html += '</div>';
		html += '</div>';
		html += '</div>';
	}
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';

	var cr = new BMap.CopyrightControl( {
		anchor : BMAP_ANCHOR_TOP_RIGHT
	} ); // 设置版权控件位置
	map.addControl( cr ); // 添加版权控件

	var bs = map.getBounds(); // 返回地图可视区域
	cr.addCopyright( {
		id : 1,
		content : html,
		bounds : bs
	} );

	$( '#treeAddress' ).tree( {
		cacheItems : true,
		selectable : true,
		multiSelect : true,
		dataSource : treeDataNode,
	} );
	// Copyright(id,content,bounds)类作为CopyrightControl.addCopyright()方法的参数
	// ************************************************

}
/**
 * 
 * @returns
 */
function refData() {
	var u = $( '#treeAddress' ).find( ".tree-selected" );

	selectAddress = "  ";
	selectNode = "   ";
	for ( var index = 0; index < u.length; index++ ) {
		var addr = u["" + index].innerText.replace( /(^\s*)|(\s*$)/g, "" );
		selectAddress += "," + addr + ",";
		for ( var province in allAddress ) {
			for ( var city in allAddress[province] ) {
				if ( city == addr ) {
					selectNode += allAddress[province][city];
				}
			}
		}
	}
	console.log( selectAddress );
	console.log( selectNode );
	loadJScript();
}
/**
 * 
 * @param map
 * @param value
 * @returns
 */
function sizeMap(value) {
	MAP.setZoom( MAP.getZoom() + value );
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
window.onload = loadJScript; // 异步加载地图
