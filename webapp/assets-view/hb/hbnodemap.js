var nodeInfo = {}, mp;
// 百度地图API功能
function loadJScript() {
	var script = document.createElement( "script" );
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=4bHlkDU3BDrBGjnLIDEzuLCjDdYhaun4&callback=init";
	document.body.appendChild( script );
}
function init() {
	// 百度地图API功能
	mp = new BMap.Map( "allmap" );
	mp.setMapStyle( {
		style : "googlelite"
	} );
	mp.enableScrollWheelZoom();
	// 复杂的自定义覆盖物
	function ComplexCustomOverlay(point, text, mouseoverText) {
		this._point = point;
		this._text = text;
		this._overText = mouseoverText;
	}
	ComplexCustomOverlay.prototype = new BMap.Overlay();
	ComplexCustomOverlay.prototype.initialize = function(map) {
		this._map = map;
		var div = this._div = document.createElement( "div" );
		div.style.position = "absolute";
		div.style.zIndex = BMap.Overlay.getZIndex( this._point.lat );
		div.style.backgroundColor = "#EE5D5B";
		div.style.border = "1px solid #BC3B3A";
		div.style.color = "white";
		div.style.height = "24px";
		div.style.padding = "2px";
		div.style.lineHeight = "18px";
		div.style.whiteSpace = "nowrap";
		div.style.MozUserSelect = "none";
		div.style.fontSize = "12px"
		var span = this._span = document.createElement( "span" );
		div.appendChild( span );
		span.appendChild( document.createTextNode( this._text ) );
		var that = this;

		var arrow = this._arrow = document.createElement( "div" );
		arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
		arrow.style.position = "absolute";
		arrow.style.width = "11px";
		arrow.style.height = "10px";
		arrow.style.top = "22px";
		arrow.style.left = "10px";
		arrow.style.overflow = "hidden";
		div.appendChild( arrow );

		div.onmouseover = function() {
			this.style.backgroundColor = "#6BADCA";
			this.style.borderColor = "#0000ff";
			this.getElementsByTagName( "span" )[0].innerHTML = that._overText;
			arrow.style.backgroundPosition = "0px -20px";
		}

		div.onmouseout = function() {
			this.style.backgroundColor = "#EE5D5B";
			this.style.borderColor = "#BC3B3A";
			this.getElementsByTagName( "span" )[0].innerHTML = that._text;
			arrow.style.backgroundPosition = "0px 0px";
		}

		mp.getPanes().labelPane.appendChild( div );

		return div;
	}
	ComplexCustomOverlay.prototype.draw = function() {
		var map = this._map;
		var pixel = map.pointToOverlayPixel( this._point );
		this._div.style.left = pixel.x - parseInt( this._arrow.style.left ) + "px";
		this._div.style.top = pixel.y - 30 + "px";
	}

	$.ajax( {
		async : false,
		type : "POST",
		url : "hbNodeMapController.refHbNode",
		cache : false,
		data : "inf={}",
		dataType : "json",
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {
				var text = "", mouseoverText = "";
				var longitude = [], latitude = [], count = 0;
				$.each( res.data, function(index, node) {
					count++;
					text = node.nodeName;
					mouseoverText = node.nodeName + " 最新记录：" + node.utime;
					var attribute = $.parseJSON( node.nodeAtr );
					var _longitude = parseFloat( attribute.lx );
					var _latitude = parseFloat( attribute.ly );
					var myCompOverlay = new ComplexCustomOverlay( new BMap.Point( _longitude, _latitude ), text, mouseoverText );

					longitude.push( _longitude );
					latitude.push( _latitude );
					mp.addOverlay( myCompOverlay );
				} );

				longitude.sort();
				latitude.sort();
				count = parseInt( count / 2 );

				mp.centerAndZoom( new BMap.Point( longitude[count], latitude[count] ), 15 );
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
function refHbNode() {
	$.ajax( {
		async : false,
		type : "POST",
		url : "hbNodeMapController.refHbNode",
		cache : false,
		data : "inf={}",
		dataType : "json",
		success : function(res) {
			if ( res.code != 0 ) {
				callError( res.code, res.message );
			} else {

			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
		}
	} );
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
